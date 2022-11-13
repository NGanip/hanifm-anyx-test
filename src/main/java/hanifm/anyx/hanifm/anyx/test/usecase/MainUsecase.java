package hanifm.anyx.hanifm.anyx.test.usecase;

import hanifm.anyx.hanifm.anyx.test.model.*;
import hanifm.anyx.hanifm.anyx.test.model.database.Adjustment;
import hanifm.anyx.hanifm.anyx.test.model.database.SalesLog;
import hanifm.anyx.hanifm.anyx.test.repository.AdjustmentRepository;
import hanifm.anyx.hanifm.anyx.test.repository.SalesLogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Component
@Slf4j
public class MainUsecase {

    @Autowired
    AdjustmentRepository adjustmentRepository;

    @Autowired
    SalesLogRepository salesLogRepository;

    public CalculationRs calculate(CalculationRq request) {

        CalculationRs calculationRs = new CalculationRs();
        CalculationInfo calculationInfo = new CalculationInfo();
        double price;
        // convert path: float -> String -> double
        // somehow, if float directly converted into double, it's not really equal
        double priceModifier = Double.parseDouble(String.valueOf(request.getPrice_modifier()));
        double finalPrice;
        String finalPriceString;
        double points;
        int finalPoints;

        try {

            /*validate input START*/
            //validate price
            try {
                price = Double.parseDouble(request.getPrice());
            } catch (NumberFormatException e) {
                throw new Exception("invalid price format");
            }
            log.info("price: {}", price);

            //validate payment_method
            Adjustment currentMethod = adjustmentRepository.findByPaymentMethod(request.getPayment_method()).orElse(null);
            if (currentMethod == null)
                throw new Exception("invalid payment method");
            log.info("currentMethod: {}", currentMethod);

            //validate price_modifier based on payment method
            log.info("priceModifier: {}", priceModifier);
            if (currentMethod.getAdjustmentDown() > priceModifier || currentMethod.getAdjustmentUp() < priceModifier)
                throw new Exception("price modifier out of range");
            /*validate input END*/

            /*core logic START*/
            //calculate
            finalPrice = price * priceModifier;
            finalPriceString = String.format("%.2f", finalPrice);
            calculationInfo.setFinal_price(finalPriceString);
            points = price * currentMethod.getAdjustmentPoints();
            finalPoints = (int) Math.round(points);
            calculationInfo.setPoints(finalPoints);
            /*core logic END*/

            //save payment
            SalesLog salesLog = new SalesLog(
                    null,
                    Timestamp.from(request.getDatetime()),
                    finalPriceString,
                    finalPoints,
                    price,
                    priceModifier,
                    request.getPayment_method()
            );
            log.info("[SAVE LOG][START]:[{}}", salesLog);
            SalesLog savedSaleLog = salesLogRepository.save(salesLog);
            log.info("[SAVE LOG][SUCCESS]:[{}}", savedSaleLog);

            //set success
            calculationRs.setData(calculationInfo);
            calculationRs.setError(null);
        } catch (Exception e) {
            //handle error
            calculationRs.setData(null);
            calculationRs.setError(e.getMessage());
        }

        return calculationRs;
    }

    public SalesLogRs getSalesLog(SalesLogRq request) {
        SalesLogRs salesLogRs = new SalesLogRs();
        List<SalesLogData> dataList = new ArrayList<>();
        List<SalesLog> dbSalesLogs;


        try {
            //validate if START datetime occurs before END datetime
            if (!request.getStartDateTime().isBefore(request.getEndDateTime()))
                throw new Exception("invalid time range");

//            List<SalesLog> dbSalesLogs = new ArrayList<>(salesLogRepository.findAll());

            //get all sales logs between START and +1 hour, then repeat
            //prepare time variable
            Instant currentTime = request.getStartDateTime();
            Instant nextTime = request.getStartDateTime().plus(Duration.ofHours(1));

            //main logic: split sales log by hour
            while (!currentTime.isAfter(request.getEndDateTime())) {
                dbSalesLogs = salesLogRepository.findByDatetimeGreaterThanEqualAndDatetimeLessThanOrderByDatetimeAsc(
                        Timestamp.from(currentTime),
                        Timestamp.from(nextTime)
                ).orElse(null);

                if (dbSalesLogs != null) {
                    SalesLogData currentData = new SalesLogData(
                            String.valueOf(currentTime),
                            "0.0",
                            0
                    );
                    //sum sales and points for current hour
                    for (SalesLog dbLog : dbSalesLogs) {
                        currentData.setSales(String.format("%.2f", Double.parseDouble(currentData.getSales()) + Double.parseDouble(dbLog.getSales())));
                        currentData.setPoints(currentData.getPoints() + dbLog.getPoints());
                    }
                    //add new sum of log for current hour
                    if (Double.parseDouble(currentData.getSales()) > 0)
                        dataList.add(currentData);
                }

                currentTime = nextTime;
                nextTime = nextTime.plus(Duration.ofHours(1));
            }

            //throw error if not logs found
            if (dataList.size() == 0)
                throw new Exception("data not found");

            //set success
            salesLogRs.setData(dataList);
            salesLogRs.setError(null);
        } catch (Exception e) {
            //handle error
            salesLogRs.setData(null);
            salesLogRs.setError(e.getMessage());
        }

        return salesLogRs;
    }
}
