package hanifm.anyx.hanifm.anyx.test.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesLogData {

    private String datetime;
    private String sales;
    private int points;
}
