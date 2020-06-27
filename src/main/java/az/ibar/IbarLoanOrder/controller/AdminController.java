package az.ibar.IbarLoanOrder.controller;

import az.ibar.IbarLoanOrder.Enums.ResponseCode;
import az.ibar.IbarLoanOrder.Enums.status.LoanStatus;
import az.ibar.IbarLoanOrder.model.entity.IncomeInfoEntity;
import az.ibar.IbarLoanOrder.model.entity.KYCInfoEntity;
import az.ibar.IbarLoanOrder.model.entity.LoanRequestEntity;
import az.ibar.IbarLoanOrder.model.response.IncomeInfoResponse;
import az.ibar.IbarLoanOrder.model.response.KYCInfoResponse;
import az.ibar.IbarLoanOrder.model.response.GeneralResponse;
import az.ibar.IbarLoanOrder.model.response.LoanResponse;
import az.ibar.IbarLoanOrder.repository.IncomeInfoRepository;
import az.ibar.IbarLoanOrder.repository.KYCInfoRepository;
import az.ibar.IbarLoanOrder.repository.LoanRequestRepository;
import az.ibar.IbarLoanOrder.service.LoanService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Ramil Mammadov
 */
@RestController
@RequestMapping("private/admin")
public class AdminController {

    @Autowired
    KYCInfoRepository kycInfoRepository;

    @Autowired
    IncomeInfoRepository incomeInfoRepository;

    @Autowired
    LoanService loanService;

    @Autowired
    LoanRequestRepository loanRequestRepository;

    @GetMapping("/kyc-info")
    public GeneralResponse kycInfo(@RequestParam String nationalId) {
        Optional<KYCInfoEntity> kycInfoEntity = kycInfoRepository.findByNationalId(nationalId);
        KYCInfoResponse kycInfo = new KYCInfoResponse();
        if (kycInfoEntity.isPresent()) {
            ModelMapper modelMapper = new ModelMapper();
            kycInfo = modelMapper.map(kycInfoEntity.get(), KYCInfoResponse.class);
            return new GeneralResponse(ResponseCode.SUCCESS.getDescription(), ResponseCode.SUCCESS.getCode(), kycInfo);
        } else {
            return new GeneralResponse(ResponseCode.DATA_NOT_FOUND.getDescription(), ResponseCode.DATA_NOT_FOUND.getCode(), null);
        }
    }

    @GetMapping("/income-info")
    public GeneralResponse incomeInfo(@RequestParam String nationalId) {
        Optional<IncomeInfoEntity> incomeInfoEntity = incomeInfoRepository.findByNationalId(nationalId);
        IncomeInfoResponse incomeInfo = new IncomeInfoResponse();
        if (incomeInfoEntity.isPresent()) {
            ModelMapper modelMapper = new ModelMapper();
            incomeInfo = modelMapper.map(incomeInfoEntity.get(), IncomeInfoResponse.class);
            return new GeneralResponse(ResponseCode.SUCCESS.getDescription(), ResponseCode.SUCCESS.getCode(), incomeInfo);
        } else {
            return new GeneralResponse(ResponseCode.DATA_NOT_FOUND.getDescription(), ResponseCode.DATA_NOT_FOUND.getCode(), null);
        }
    }


    @PostMapping("/accept-loan")
    public GeneralResponse acceptLoan(@RequestParam Integer loanId) {
        if (!loanRequestRepository.findByIdAndStatus(loanId, LoanStatus.ADDED.getStatus()).isPresent()) {
            return new GeneralResponse(ResponseCode.DATA_NOT_FOUND.getDescription(), ResponseCode.DATA_NOT_FOUND.getCode(), null);
        } else if (loanService.processLoan(loanId, LoanStatus.ACCEPED)) {
            return new GeneralResponse(ResponseCode.SUCCESS.getDescription(), ResponseCode.SUCCESS.getCode(), null);
        } else {
            return new GeneralResponse(ResponseCode.INTERNAL_ERROR.getDescription(), ResponseCode.INTERNAL_ERROR.getCode(), null);
        }
    }

    @PostMapping("/reject-loan")
    public GeneralResponse rejectLoan(@RequestParam Integer loanId) {
        if (!loanRequestRepository.findByIdAndStatus(loanId, LoanStatus.ADDED.getStatus()).isPresent()) {
            return new GeneralResponse(ResponseCode.DATA_NOT_FOUND.getDescription(), ResponseCode.DATA_NOT_FOUND.getCode(), null);
        } else if (loanService.processLoan(loanId, LoanStatus.REJECTED)) {
            return new GeneralResponse(ResponseCode.SUCCESS.getDescription(), ResponseCode.SUCCESS.getCode(), null);
        } else {
            return new GeneralResponse(ResponseCode.INTERNAL_ERROR.getDescription(), ResponseCode.INTERNAL_ERROR.getCode(), null);
        }
    }

    @GetMapping("/order-history")
    public GeneralResponse orderHistory() {
        List<LoanRequestEntity> loanList = loanRequestRepository.findAll();
        ModelMapper modelMapper = new ModelMapper();
        List<LoanResponse> loanResponses = loanList
                .stream()
                .map(user -> modelMapper.map(user, LoanResponse.class))
                .collect(Collectors.toList());
        return new GeneralResponse(ResponseCode.SUCCESS.getDescription(), ResponseCode.SUCCESS.getCode(), loanResponses);
    }

}
