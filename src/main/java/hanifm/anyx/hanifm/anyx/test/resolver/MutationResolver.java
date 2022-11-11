package hanifm.anyx.hanifm.anyx.test.resolver;

import graphql.kickstart.tools.GraphQLMutationResolver;
import hanifm.anyx.hanifm.anyx.test.model.PaymentRq;
import hanifm.anyx.hanifm.anyx.test.model.PaymentRs;
import hanifm.anyx.hanifm.anyx.test.usecase.PaymentUsecase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@AllArgsConstructor
public class MutationResolver implements GraphQLMutationResolver {

    @Autowired
    PaymentUsecase paymentUsecase;

    @Transactional
    public PaymentRs calculatePayment(PaymentRq request) {
        log.info("[calculatePayment][START][{}]", request);
        PaymentRs response = paymentUsecase.calculate(request);
        log.info("[calculatePayment][END][{}]", response);
        return response;
    }
}
