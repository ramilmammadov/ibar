package az.ibar.IbarLoanOrder.repository;

import java.util.Optional;

import az.ibar.IbarLoanOrder.model.entity.security.Role;
import az.ibar.IbarLoanOrder.model.entity.security.User;
        import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Ramil Mammadov
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}