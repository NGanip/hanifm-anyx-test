package hanifm.anyx.hanifm.anyx.test.resolver;

import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Hanif Muhammad [xsis.hanifm@xl.co.id]
 * created at 11/11/2022 19:01
 */

@Component
@AllArgsConstructor
public class QueryResolver implements GraphQLQueryResolver {

    public String doNothing() {
        return "nothing indeed";
    }
}
