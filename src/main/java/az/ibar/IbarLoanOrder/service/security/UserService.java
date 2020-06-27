package az.ibar.IbarLoanOrder.service.security;

import az.ibar.IbarLoanOrder.model.entity.IncomeInfoEntity;
import az.ibar.IbarLoanOrder.model.entity.security.Role;
import az.ibar.IbarLoanOrder.model.entity.security.User;
import az.ibar.IbarLoanOrder.model.request.SignUpRequest;
import az.ibar.IbarLoanOrder.repository.RoleRepository;
import az.ibar.IbarLoanOrder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;

    public User getCurrentUser() {
        User user = null;
        try {
            SecurityContext securityContext = SecurityContextHolder.getContext();
            String username;
            if (null != securityContext.getAuthentication()) {
                username = securityContext.getAuthentication().getName();
                Optional<User> usero = userRepository.findByUsername(username);
                user = usero.get();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return user;
    }

    public boolean saveUser(SignUpRequest signUpRequest, String roleName) {
        try {
            User user = new User();
            user.setName(signUpRequest.getName());
            user.setSurname(signUpRequest.getSurname());
            user.setEmail(signUpRequest.getEmail());
            user.setUsername(signUpRequest.getUsername());
            user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
            Role role = roleRepository.findByName(roleName).get();
            List<Role> userRoles = new ArrayList<>();
            userRoles.add(role);
            user.setRoles(userRoles);
            userRepository.save(user);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
