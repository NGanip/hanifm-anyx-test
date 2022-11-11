package hanifm.anyx.hanifm.anyx.test.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRs {

    private PaymentInfo data;
    private String error;
}
