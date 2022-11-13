package hanifm.anyx.hanifm.anyx.test.config;

import hanifm.anyx.hanifm.anyx.test.model.database.Adjustment;
import hanifm.anyx.hanifm.anyx.test.model.database.SalesLog;
import hanifm.anyx.hanifm.anyx.test.repository.AdjustmentRepository;
import hanifm.anyx.hanifm.anyx.test.repository.SalesLogRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;

@Component
@AllArgsConstructor
@Slf4j
public class DataProvider implements CommandLineRunner {

    private AdjustmentRepository adjustmentRepository;
    private SalesLogRepository salesLogRepository;

    @Override
    @Transactional
    public void run(String... strings) {
        //insert payment adjustment configuration
        log.info("[CONFIGURE PAYMENT ADJUSTMENT][START]");
        adjustmentRepository.save(new Adjustment(null, "CASH", 0.9, 1.0, 0.05));
        adjustmentRepository.save(new Adjustment(null, "CASH_ON_DELIVERY", 1.0, 1.02, 0.05));
        adjustmentRepository.save(new Adjustment(null, "VISA", 0.95, 1.0, 0.03));
        adjustmentRepository.save(new Adjustment(null, "MASTERCARD", 0.95, 1, 0.03));
        adjustmentRepository.save(new Adjustment(null, "AMEX", 0.98, 1.01, 0.02));
        adjustmentRepository.save(new Adjustment(null, "JCB", 0.95, 1.0, 0.05));
        log.info("[CONFIGURE PAYMENT ADJUSTMENT][END]");

        //insert to log table
        log.info("[POPULATE PREDEFINED LOGS][START]");
        salesLogRepository.save(new SalesLog(null, Timestamp.from(Instant.parse("2022-09-01T00:00:01Z")),"95", 3, 100, 0.95, "MASTERCARD"));
        salesLogRepository.save(new SalesLog(null, Timestamp.from(Instant.parse("2022-09-01T00:00:01Z")),"95", 3, 100, 0.95, "MASTERCARD"));
        salesLogRepository.save(new SalesLog(null, Timestamp.from(Instant.parse("2022-09-01T01:00:01Z")),"95", 3, 100, 0.95, "MASTERCARD"));
        salesLogRepository.save(new SalesLog(null, Timestamp.from(Instant.parse("2022-09-01T01:00:01Z")),"95", 3, 100, 0.95, "MASTERCARD"));
        salesLogRepository.save(new SalesLog(null, Timestamp.from(Instant.parse("2022-09-01T01:00:01Z")),"95", 3, 100, 0.95, "MASTERCARD"));
        salesLogRepository.save(new SalesLog(null, Timestamp.from(Instant.parse("2022-09-01T02:00:01Z")),"95", 3, 100, 0.95, "MASTERCARD"));
        salesLogRepository.save(new SalesLog(null, Timestamp.from(Instant.parse("2022-09-01T02:00:01Z")),"95", 3, 100, 0.95, "MASTERCARD"));
        salesLogRepository.save(new SalesLog(null, Timestamp.from(Instant.parse("2022-09-01T02:00:01Z")),"95", 3, 100, 0.95, "MASTERCARD"));
        salesLogRepository.save(new SalesLog(null, Timestamp.from(Instant.parse("2022-09-01T04:00:01Z")),"95", 3, 100, 0.95, "MASTERCARD"));
        salesLogRepository.save(new SalesLog(null, Timestamp.from(Instant.parse("2022-09-01T04:00:01Z")),"95", 3, 100, 0.95, "MASTERCARD"));
        salesLogRepository.save(new SalesLog(null, Timestamp.from(Instant.parse("2022-09-01T04:00:01Z")),"95", 3, 100, 0.95, "MASTERCARD"));
        salesLogRepository.save(new SalesLog(null, Timestamp.from(Instant.parse("2022-09-02T06:00:01Z")),"95", 3, 100, 0.95, "MASTERCARD"));
        salesLogRepository.save(new SalesLog(null, Timestamp.from(Instant.parse("2022-09-02T06:00:01Z")),"95", 3, 100, 0.95, "MASTERCARD"));
        salesLogRepository.save(new SalesLog(null, Timestamp.from(Instant.parse("2022-09-02T01:00:01Z")),"95", 3, 100, 0.95, "MASTERCARD"));
        log.info("[POPULATE PREDEFINED LOGS][END]");
    }
}
