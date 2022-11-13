package hanifm.anyx.hanifm.anyx.test.repository;

import hanifm.anyx.hanifm.anyx.test.model.database.SalesLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface SalesLogRepository extends JpaRepository<SalesLog, Long> {

    Optional<List<SalesLog>> findByDatetimeGreaterThanEqualAndDatetimeLessThanOrderByDatetimeAsc(
            Timestamp startDateTime, Timestamp endDateTime
    );
}
