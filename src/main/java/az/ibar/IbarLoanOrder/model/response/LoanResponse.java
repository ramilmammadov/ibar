package az.ibar.IbarLoanOrder.model.response;

import java.util.Date;

public class LoanResponse {
    private Integer id;
    private String nationalId;
    private double loanAmount;
    private int period;
    private Date dateCreated;
    private int status;

    public LoanResponse() {
    }

    public LoanResponse(Integer id, String nationalId, double loanAmount, int period, Date dateCreated, int status) {
        this.id = id;
        this.nationalId = nationalId;
        this.loanAmount = loanAmount;
        this.period = period;
        this.dateCreated = dateCreated;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
