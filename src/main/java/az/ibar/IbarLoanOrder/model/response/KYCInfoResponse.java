package az.ibar.IbarLoanOrder.model.response;

import javax.persistence.Column;

public class KYCInfoResponse {
    private String address;
    private String lastEmployerName;
    private String lastEmpStartDate;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLastEmployerName() {
        return lastEmployerName;
    }

    public void setLastEmployerName(String lastEmployerName) {
        this.lastEmployerName = lastEmployerName;
    }

    public String getLastEmpStartDate() {
        return lastEmpStartDate;
    }

    public void setLastEmpStartDate(String lastEmpStartDate) {
        this.lastEmpStartDate = lastEmpStartDate;
    }

    @Override
    public String toString() {
        return "KYCInfoResponse{" +
                "address='" + address + '\'' +
                ", lastEmployerName='" + lastEmployerName + '\'' +
                ", lastEmpStartDate='" + lastEmpStartDate + '\'' +
                '}';
    }
}
