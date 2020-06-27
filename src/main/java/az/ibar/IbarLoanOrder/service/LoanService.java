package az.ibar.IbarLoanOrder.service;

import az.ibar.IbarLoanOrder.Enums.ResponseCode;
import az.ibar.IbarLoanOrder.Enums.status.LoanStatus;
import az.ibar.IbarLoanOrder.model.entity.LoanRequestEntity;
import az.ibar.IbarLoanOrder.model.request.LoanRequest;
import az.ibar.IbarLoanOrder.model.response.GeneralResponse;
import az.ibar.IbarLoanOrder.model.response.LoanResponse;
import az.ibar.IbarLoanOrder.repository.LoanRequestRepository;
import az.ibar.IbarLoanOrder.repository.UserRepository;
import az.ibar.IbarLoanOrder.service.security.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanService {
    @Autowired
    LoanRequestRepository loanRequestRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    MailSenderService mailSenderService;

    public boolean saveLoan(LoanRequest loanRequest) {
        try {
            LoanRequestEntity request = new LoanRequestEntity();
            ModelMapper modelMapper = new ModelMapper();
            request = modelMapper.map(loanRequest, LoanRequestEntity.class);
            request.setUserId(userService.getCurrentUser().getId());
            request.setStatus(LoanStatus.ADDED.getStatus());
            request.setDateCreated(new Date());
            loanRequestRepository.save(request);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public List<LoanResponse> loanList() {
        try {
            List<LoanRequestEntity> requests = loanRequestRepository.findAllByUserId(userService.getCurrentUser().getId());
            ModelMapper modelMapper = new ModelMapper();
            List<LoanResponse> loanResponses = requests
                    .stream()
                    .map(user -> modelMapper.map(user, LoanResponse.class))
                    .collect(Collectors.toList());
            return loanResponses;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public boolean processLoan(Integer id, LoanStatus status) {
        try {
            LoanRequestEntity loanRequestEntity = loanRequestRepository.findByIdAndStatus(id, LoanStatus.ADDED.getStatus()).get();
            loanRequestEntity.setStatus(status.getStatus());
            loanRequestRepository.save(loanRequestEntity);
            if (userService.getCurrentUser().getEmail() != null && userService.getCurrentUser().getEmail().length() > 0) {
                if (status.getStatus() == LoanStatus.ACCEPED.getStatus()) {
                    mailSenderService.sendEmail("Your loan order has been accepted!", "IBAR - Loan info", userService.getCurrentUser().getEmail());
                } else if (status.getStatus() == LoanStatus.REJECTED.getStatus()) {
                    mailSenderService.sendEmail("Your loan order has been rejected!", "IBAR - Loan info", userService.getCurrentUser().getEmail());
                }
            }
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
