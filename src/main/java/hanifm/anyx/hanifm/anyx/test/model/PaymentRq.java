package hanifm.anyx.hanifm.anyx.test.model;

import hanifm.anyx.hanifm.anyx.test.config.properties.PaymentMethodEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class PaymentRq {

    private String price;
    private Float price_modifier;
    private PaymentMethodEnum payment_method;
    private Timestamp date_time;
}
