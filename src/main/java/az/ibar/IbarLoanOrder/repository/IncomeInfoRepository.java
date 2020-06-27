package az.ibar.IbarLoanOrder.repository;

import az.ibar.IbarLoanOrder.model.entity.IncomeInfoEntity;
import az.ibar.IbarLoanOrder.model.entity.KYCInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Ramil Mammadov
 */
public interface IncomeInfoRepository extends JpaRepository<IncomeInfoEntity, Long> {
    Optional<IncomeInfoEntity> findByNationalId(String id);
}