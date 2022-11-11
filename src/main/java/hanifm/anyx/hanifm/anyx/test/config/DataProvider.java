package hanifm.anyx.hanifm.anyx.test.config;

import hanifm.anyx.hanifm.anyx.test.model.database.Adjustment;
import hanifm.anyx.hanifm.anyx.test.repository.AdjustmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@AllArgsConstructor
public class DataProvider implements CommandLineRunner {

    private AdjustmentRepository adjustmentRepository;


    @Override
    @Transactional
    public void run(String... strings) {
        //insert payment adjustment configuration
        adjustmentRepository.save(new Adjustment(null, "CASH", 0.9, 1.0, 0.05));
        adjustmentRepository.save(new Adjustment(null, "CASH_ON_DELIVERY", 1.0, 1.02, 0.05));
        adjustmentRepository.save(new Adjustment(null, "VISA", 0.95, 1.0, 0.03));
        adjustmentRepository.save(new Adjustment(null, "MASTERCARD", 0.95, 1, 0.03));
        adjustmentRepository.save(new Adjustment(null, "AMEX", 0.98, 1.01, 0.02));
        adjustmentRepository.save(new Adjustment(null, "JCB", 0.95, 1.0, 0.05));

    }
}
