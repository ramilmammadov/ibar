package az.ibar.IbarLoanOrder.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "income_info")
public class IncomeInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "income", nullable = false)
    private Double income;

    @Column(name = "deductions", nullable = false)
    private Double deductions;

    @Column(name = "national_id", nullable = false)
    private String nationalId;

    public IncomeInfoEntity() {
    }

    public Integer getId() {
        return id;
    }

    public IncomeInfoEntity(Double income, Double deductions, String nationalId) {
        this.income = income;
        this.deductions = deductions;
        this.nationalId = nationalId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public Double getDeductions() {
        return deductions;
    }

    public void setDeductions(Double deductions) {
        this.deductions = deductions;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }
}