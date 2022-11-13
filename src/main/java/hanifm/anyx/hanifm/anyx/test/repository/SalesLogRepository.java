package hanifm.anyx.hanifm.anyx.test.repository;

import hanifm.anyx.hanifm.anyx.test.model.database.SalesLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesLogRepository extends JpaRepository<SalesLog, Long> {
}
