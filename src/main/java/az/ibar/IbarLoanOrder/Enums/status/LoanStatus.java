package az.ibar.IbarLoanOrder.Enums.status;

public enum LoanStatus {
    ADDED(0),
    ACCEPED(1),
    REJECTED(2),
    ;

    private final int status;

    private LoanStatus(int status) {
        this.status = status;
     }


    public int getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return String.valueOf(status);
    }
}
