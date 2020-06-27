package az.ibar.IbarLoanOrder.model.request;

public class LoanRequest {
    private String nationalId;
    private double loanAmount;
    private int period;

    public LoanRequest(String nationalId, double loanAmount, int perion) {
        this.nationalId = nationalId;
        this.loanAmount = loanAmount;
        this.period = perion;
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

    public void setPeriod(int perion) {
        this.period = perion;
    }


}
