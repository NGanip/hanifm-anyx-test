package hanifm.anyx.hanifm.anyx.test.resolver;

import graphql.kickstart.tools.GraphQLMutationResolver;
import hanifm.anyx.hanifm.anyx.test.model.CalculationRq;
import hanifm.anyx.hanifm.anyx.test.model.CalculationRs;
import hanifm.anyx.hanifm.anyx.test.usecase.MainUsecase;
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
    MainUsecase mainUsecase;

    @Transactional
    public CalculationRs calculatePayment(CalculationRq request) {
        log.info("[calculatePayment][START][{}]", request);
        CalculationRs response = mainUsecase.calculate(request);
        log.info("[calculatePayment][END][{}]", response);
        return response;
    }
}
