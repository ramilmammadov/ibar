package az.ibar.IbarLoanOrder.model.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "loan_request")
public class LoanRequestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "national_id", nullable = false)
    @Length(min = 8, max = 15)
    private String nationalId;

    @Column(name = "loan_amount", nullable = false)
    @Range(min = 1000, max = 100000)
    private double loanAmount;

    @Column(name = "period", nullable = false)
    @Range(min = 6, max = 36)
    private int period;

    @Column(name = "user_id", nullable = false)
    private int userId;

    @Column(name = "date_created", nullable = false)
    private Date dateCreated;

    @Column(name = "status", nullable = false)
    private int status;

    public LoanRequestEntity() {
    }

    public LoanRequestEntity(String nationalId, double loanAmount, int period, int userId, Date dateCreated, int status) {
        this.nationalId = nationalId;
        this.loanAmount = loanAmount;
        this.period = period;
        this.userId = userId;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    @Override
    public String toString() {
        return "LoanRequestEntity{" +
                "id=" + id +
                ", nationalId='" + nationalId + '\'' +
                ", loanAmount='" + loanAmount + '\'' +
                ", period=" + period +
                ", userId=" + userId +
                ", dateCreated=" + dateCreated +
                ", status=" + status +
                '}';
    }
}