package hanifm.anyx.hanifm.anyx.test.resolver;

import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class QueryResolver implements GraphQLQueryResolver {

    public String doNothing() {
        return "nothing indeed";
    }
}
