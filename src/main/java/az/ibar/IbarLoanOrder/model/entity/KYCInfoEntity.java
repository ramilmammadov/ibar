package az.ibar.IbarLoanOrder.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "kyc_info")
public class KYCInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "last_employer_name", nullable = false)
    private String lastEmployerName;

    @Column(name = "last_emp_start_date", nullable = false)
    private String lastEmpStartDate;

    @Column(name = "national_id", nullable = false)
    private String nationalId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }
}