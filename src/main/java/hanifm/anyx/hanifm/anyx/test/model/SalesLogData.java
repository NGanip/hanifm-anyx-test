package hanifm.anyx.hanifm.anyx.test.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesLogData {

    private Date datetime;
    private String sales;
    private int points;
}
