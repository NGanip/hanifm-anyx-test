package hanifm.anyx.hanifm.anyx.test.controller;

import hanifm.anyx.hanifm.anyx.test.model.PaymentRq;
import hanifm.anyx.hanifm.anyx.test.model.PaymentInfo;
import hanifm.anyx.hanifm.anyx.test.model.PaymentRs;
import hanifm.anyx.hanifm.anyx.test.usecase.PaymentUsecase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class MainController {

    @Autowired
    PaymentUsecase paymentUsecase;

    @QueryMapping
    public PaymentRs calculatePayment(PaymentRq request) {
        log.info("[calculatePayment][START][{}]", request);
        PaymentRs response = paymentUsecase.calculate(request);
        log.info("[calculatePayment][END][{}]", request);
        return response;
    }
}
