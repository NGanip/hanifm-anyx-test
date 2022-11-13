package hanifm.anyx.hanifm.anyx.test.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalculationRq {

    private String price;
    private Float price_modifier;
    private String payment_method;
    private Instant datetime;
}
