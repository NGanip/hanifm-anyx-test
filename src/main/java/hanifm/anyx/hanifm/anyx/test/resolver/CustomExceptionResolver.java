package hanifm.anyx.hanifm.anyx.test.resolver;

import graphql.schema.CoercingSerializeException;



public class CustomExceptionResolver extends CoercingSerializeException {

    public CustomExceptionResolver(String message) {
        super("invalid input");
    }
}
