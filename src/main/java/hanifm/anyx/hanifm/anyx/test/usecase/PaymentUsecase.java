package hanifm.anyx.hanifm.anyx.test.usecase;

import hanifm.anyx.hanifm.anyx.test.model.PaymentRq;
import hanifm.anyx.hanifm.anyx.test.model.PaymentInfo;
import hanifm.anyx.hanifm.anyx.test.model.PaymentRs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PaymentUsecase {

    public PaymentRs calculate(PaymentRq request) {
        PaymentRs paymentRs = new PaymentRs();
        PaymentInfo paymentInfo = new PaymentInfo();

        try {

            //validate input



            //calculate


        } catch (Exception e) {
            //handle error


        }

        return paymentRs;
    }
}
