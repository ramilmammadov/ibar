package az.ibar.IbarLoanOrder.repository;

import java.util.Optional;

import az.ibar.IbarLoanOrder.model.entity.security.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Ramil Mammadov
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}