package hanifm.anyx.hanifm.anyx.test.resolver;

import graphql.kickstart.tools.GraphQLQueryResolver;
import hanifm.anyx.hanifm.anyx.test.model.SalesLogRq;
import hanifm.anyx.hanifm.anyx.test.model.SalesLogRs;
import hanifm.anyx.hanifm.anyx.test.usecase.MainUsecase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class QueryResolver implements GraphQLQueryResolver {

    @Autowired
    MainUsecase mainUsecase;

    public SalesLogRs GetSalesLog(SalesLogRq request) {
        log.info("[SalesLog][START][{}]", request);
        SalesLogRs response = mainUsecase.getSalesLog(request);
        log.info("[SalesLog][END][{}]", response);
        return response;
    }
}
