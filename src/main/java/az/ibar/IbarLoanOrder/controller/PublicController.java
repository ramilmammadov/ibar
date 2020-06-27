package az.ibar.IbarLoanOrder.controller;

import az.ibar.IbarLoanOrder.Enums.ResponseCode;
import az.ibar.IbarLoanOrder.model.request.LoginRequest;
import az.ibar.IbarLoanOrder.model.request.SignUpRequest;
import az.ibar.IbarLoanOrder.model.response.GeneralResponse;
import az.ibar.IbarLoanOrder.model.response.JwtResponse;
import az.ibar.IbarLoanOrder.repository.UserRepository;
import az.ibar.IbarLoanOrder.service.ValidationService;
import az.ibar.IbarLoanOrder.service.security.JwtUtils;
import az.ibar.IbarLoanOrder.service.security.ToolsService;
import az.ibar.IbarLoanOrder.service.security.UserDetailsImpl;
import az.ibar.IbarLoanOrder.service.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("public")
public class PublicController {


    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserService userService;


    @Autowired
    ValidationService validationService;

    @PostMapping("/sign-up")
    public GeneralResponse signUp(@RequestBody SignUpRequest signUpRequest) {
        GeneralResponse generalResponse = validationService.validateSignUp(signUpRequest);
        if (generalResponse.getCode() != 1) {
            return generalResponse;
        } else {
            if (userService.saveUser(signUpRequest, "ROLE_USER")) {
                return new GeneralResponse(ResponseCode.SUCCESS.getDescription(), ResponseCode.SUCCESS.getCode(), null);
            } else {
                return new GeneralResponse(ResponseCode.INTERNAL_ERROR.getDescription(), ResponseCode.INTERNAL_ERROR.getCode(), null);

            }
        }
    }


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                        loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                roles));
    }


}
