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
import java.util.ArrayList;
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
        double priceModifier = Double.valueOf(request.getPrice_modifier());
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
            SalesLog salesLog = new SalesLog(null,
                    new Timestamp(request.getDatetime().getTime()),
                    finalPriceString,
                    finalPoints);
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


        try {
            List<SalesLog> dbSalesLogs = new ArrayList<>(salesLogRepository.findAll());

            for (SalesLog dbLog : dbSalesLogs) {
                dataList.add(new SalesLogData(dbLog.getDatetime(), dbLog.getSales(), dbLog.getPoints()));
            }

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
