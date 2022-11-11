package hanifm.anyx.hanifm.anyx.test.model;

import hanifm.anyx.hanifm.anyx.test.config.PaymentMethodEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRq {

    private String price;
    private Float price_modifier;
    private String payment_method;
    private Date datetime;
}
