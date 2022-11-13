package hanifm.anyx.hanifm.anyx.test.resolver;

import hanifm.anyx.hanifm.anyx.test.config.DataProvider;
import hanifm.anyx.hanifm.anyx.test.model.CalculationRq;
import hanifm.anyx.hanifm.anyx.test.model.CalculationRs;
import hanifm.anyx.hanifm.anyx.test.repository.AdjustmentRepository;
import hanifm.anyx.hanifm.anyx.test.usecase.MainUsecase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureHttpGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.graphql.test.tester.HttpGraphQlTester;

import java.time.Instant;

@SpringBootTest
@AutoConfigureHttpGraphQlTester
class MutationResolverTest {

    @Autowired
    HttpGraphQlTester graphQlTester;

//    @Test
    void calculatePaymentValidData() {
        //TODO create unittest

        // language=GraphQl
        String doc =
                "mutation CalculatePayment($calculationRq: CalculationRq!) {\n" +
                        "  calculatePayment(paymentRq: $calculationRq) {\n" +
                        "    data {\n" +
                        "      final_price\n" +
                        "      points\n" +
                        "    }\n" +
                        "    error\n" +
                        "  }\n" +
                        "}";

        CalculationRq req = new CalculationRq(
                "2000",
                0.97F,
                "MASTERCARD",
                Instant.parse("2022-09-01T03:00:02Z"));

        graphQlTester.document(doc)
                .variable("$calculationRq", req)
                .execute()
                .path("calculatePayment")
                .entity(CalculationRs.class)
                .satisfies(calculationRs -> {
                    assert calculationRs.getData() != null;
                });
    }
}