package az.ibar.IbarLoanOrder.repository;

import az.ibar.IbarLoanOrder.model.entity.KYCInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Ramil Mammadov
 */
public interface KYCInfoRepository extends JpaRepository<KYCInfoEntity, Long> {
    Optional<KYCInfoEntity> findByNationalId(String id);
}