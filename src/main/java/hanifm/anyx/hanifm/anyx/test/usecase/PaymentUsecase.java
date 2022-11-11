package hanifm.anyx.hanifm.anyx.test.usecase;

import hanifm.anyx.hanifm.anyx.test.model.PaymentInfo;
import hanifm.anyx.hanifm.anyx.test.model.PaymentRq;
import hanifm.anyx.hanifm.anyx.test.model.PaymentRs;
import hanifm.anyx.hanifm.anyx.test.model.database.Adjustment;
import hanifm.anyx.hanifm.anyx.test.repository.AdjustmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class PaymentUsecase {

    @Autowired
    AdjustmentRepository adjustmentRepository;

    public PaymentRs calculate(PaymentRq request) {
        log.info("do calculate");

        PaymentRs paymentRs = new PaymentRs();
        PaymentInfo paymentInfo = new PaymentInfo();
        double price = 0.0;
        double final_price = 0.0;
        double adjustmentUp = 0.0;
        double adjustmentDown = 0.0;

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



            /*validate input END*/


            //calculate
            paymentInfo.setFinal_price("nice");
            paymentInfo.setPoints(5);

            //set success
            paymentRs.setData(paymentInfo);
            paymentRs.setError(null);
        } catch (Exception e) {
            //handle error
            paymentRs.setData(null);
            paymentRs.setError(e.getMessage());
        }

        return paymentRs;
    }
}
