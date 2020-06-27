package az.ibar.IbarLoanOrder.repository;

import az.ibar.IbarLoanOrder.model.entity.LoanRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Ramil Mammadov
 */
public interface LoanRequestRepository extends JpaRepository<LoanRequestEntity, Integer> {
    Optional<LoanRequestEntity> findById(Integer id);

    List<LoanRequestEntity> findAllByUserId(Integer id);

    Optional<LoanRequestEntity> findByIdAndStatus(Integer id, Integer status);
}