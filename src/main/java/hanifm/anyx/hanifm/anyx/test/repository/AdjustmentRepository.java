package hanifm.anyx.hanifm.anyx.test.repository;

import hanifm.anyx.hanifm.anyx.test.model.database.Adjustment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdjustmentRepository extends JpaRepository<Adjustment, Long> {

    Optional<Adjustment> findByPaymentMethod(String paymentMethod);
}
