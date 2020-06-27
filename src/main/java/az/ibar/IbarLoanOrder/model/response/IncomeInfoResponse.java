package az.ibar.IbarLoanOrder.model.response;

public class IncomeInfoResponse {
    private double income;
    private double deductions;



    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public double getDeductions() {
        return deductions;
    }

    public void setDeductions(double deductions) {
        this.deductions = deductions;
    }

    @Override
    public String toString() {
        return "IncomeInfoResponse{" +
                "income=" + income +
                ", deductions=" + deductions +
                '}';
    }
}
