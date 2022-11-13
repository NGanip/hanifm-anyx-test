package hanifm.anyx.hanifm.anyx.test.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesLogRq {

    private Instant startDateTime;
    private Instant endDateTime;
}
