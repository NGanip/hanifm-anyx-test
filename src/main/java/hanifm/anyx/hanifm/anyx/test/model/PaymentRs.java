package hanifm.anyx.hanifm.anyx.test.model;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRs {

    private PaymentInfo data;
    private String error;
}
