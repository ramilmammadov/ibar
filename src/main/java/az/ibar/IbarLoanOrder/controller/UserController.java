package az.ibar.IbarLoanOrder.controller;

import az.ibar.IbarLoanOrder.Enums.ResponseCode;
import az.ibar.IbarLoanOrder.model.request.LoanRequest;
import az.ibar.IbarLoanOrder.model.response.GeneralResponse;
import az.ibar.IbarLoanOrder.model.response.LoanResponse;
import az.ibar.IbarLoanOrder.repository.LoanRequestRepository;
import az.ibar.IbarLoanOrder.service.LoanService;
import az.ibar.IbarLoanOrder.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("private/user")
public class UserController {


    @Autowired
    LoanRequestRepository loanRequestRepository;

    @Autowired
    LoanService loanService;

    @Autowired
    ValidationService validationService;

    @PostMapping("/order")
    public GeneralResponse order(@RequestBody LoanRequest loanRequest) {
        GeneralResponse response = validationService.validateLoanRequest(loanRequest);
        if (response.getCode() != 1)
            return response;
        else {
            if (loanService.saveLoan(loanRequest)) {
                return new GeneralResponse(ResponseCode.SUCCESS.getDescription(), ResponseCode.SUCCESS.getCode(), null);
            } else {
                return new GeneralResponse(ResponseCode.INTERNAL_ERROR.getDescription(), ResponseCode.INTERNAL_ERROR.getCode(), null);

            }
        }

    }

    @GetMapping("/order-history")
    public GeneralResponse orderHistory() {
        List<LoanResponse> loanList = loanService.loanList();
        return new GeneralResponse(ResponseCode.SUCCESS.getDescription(), ResponseCode.SUCCESS.getCode(), loanList);
    }
}
