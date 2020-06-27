package az.ibar.IbarLoanOrder.service;

import az.ibar.IbarLoanOrder.Enums.ResponseCode;
import az.ibar.IbarLoanOrder.model.request.LoanRequest;
import az.ibar.IbarLoanOrder.model.request.SignUpRequest;
import az.ibar.IbarLoanOrder.model.response.GeneralResponse;
import az.ibar.IbarLoanOrder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {

    @Autowired
    UserRepository userRepository;

    public GeneralResponse validateLoanRequest(LoanRequest loanRequest) {
        if (loanRequest.getLoanAmount() < 500 || loanRequest.getLoanAmount() > 100000) {
            return new GeneralResponse(ResponseCode.VALIDATION.getDescription(), ResponseCode.VALIDATION.getCode(), "Amount must be between 500 - 100 000.");
        } else if (loanRequest.getNationalId().length() < 8 || loanRequest.getNationalId().length() > 12) {
            return new GeneralResponse(ResponseCode.VALIDATION.getDescription(), ResponseCode.VALIDATION.getCode(), "National ID length must be between 8 - 12.");
        } else if (loanRequest.getPeriod() < 10 || loanRequest.getPeriod() > 100) {
            return new GeneralResponse(ResponseCode.VALIDATION.getDescription(), ResponseCode.VALIDATION.getCode(), "Period must be between 10 - 100.");
        } else {
            return new GeneralResponse(ResponseCode.SUCCESS.getDescription(), ResponseCode.SUCCESS.getCode(), null);

        }
    } public GeneralResponse validateSignUp(SignUpRequest signUpRequest) {
        if (signUpRequest.getUsername().length() > 21 || signUpRequest.getUsername().length() < 4) {
            return new GeneralResponse(ResponseCode.VALIDATION.getDescription(), ResponseCode.VALIDATION.getCode(), "Username length must be between 5-20.");
        } else if (signUpRequest.getPassword().length() > 21 || signUpRequest.getPassword().length() < 7) {
            return new GeneralResponse(ResponseCode.VALIDATION.getDescription(), ResponseCode.VALIDATION.getCode(), "Password length must be between 8-20.");
        } else if (userRepository.findByUsername(signUpRequest.getUsername()).isPresent()) {
            return new GeneralResponse(ResponseCode.ALREADY_EXIST.getDescription(), ResponseCode.ALREADY_EXIST.getCode(), "This username" + ResponseCode.ALREADY_EXIST.getDescription());
        } else {
            return new GeneralResponse(ResponseCode.SUCCESS.getDescription(), ResponseCode.SUCCESS.getCode(), null);

        }
    }
}
