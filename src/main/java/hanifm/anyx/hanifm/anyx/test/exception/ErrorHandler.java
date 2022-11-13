package hanifm.anyx.hanifm.anyx.test.exception;

import graphql.ErrorType;
import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.kickstart.execution.error.GraphQLErrorHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class ErrorHandler implements GraphQLErrorHandler {


    @Override
    public List<GraphQLError> processErrors(List<GraphQLError> errors) {
        return errors.stream().map(this::getNested).collect(Collectors.toList());
    }

    private GraphQLError getNested(GraphQLError error) {
        if (error instanceof ExceptionWhileDataFetching) {
            ExceptionWhileDataFetching exceptionWhileDataFetching = (ExceptionWhileDataFetching) error;
            if (exceptionWhileDataFetching.getException() instanceof GraphQLError) {
                GraphQLError currentError = (GraphQLError) exceptionWhileDataFetching.getException();

                log.info("get message: {}", currentError.getMessage());
                //TODO handling DateTime parsing error
                if (currentError.getMessage().contains("Failed to parse Date")) {
                    return GraphqlErrorBuilder.newError()
                            .errorType(ErrorType.ValidationError)
                            .message("invalid datetime format")
                            .build();
                } else {
                    return currentError;
                }
            }
        }
        return error;
    }
}
