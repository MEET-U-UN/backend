package co.edu.unal.software_engineering.meetu.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.*;
import co.edu.unal.software_engineering.meetu.log.*;

/**
 * The persistent class for the user database table.
 */
@Entity
@Table(name = "budget", schema = "public")
public class Budget extends LogModel {

    private static final long serialVersionUID = 1L;

    /**
     * Attributes
     */
    @Id
    @SequenceGenerator(name = "BUDGET_BUDGETID_GENERATOR", sequenceName = "public.budget_budget_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "BUDGET_BUDGETID_GENERATOR", strategy = GenerationType.SEQUENCE)

    @Column(name = "budget_id")
    private Integer budget_id;

    @Column(name = "money")
    private Integer money;

    // bi-directional many-to-one association to Plan
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id")
    private Plan plan;


    /**
     * Constructors
     */
    public Budget() {
    }


    /**
     * Getters and Setters
     */
    public Integer getBudget_id() {
        return budget_id;
    }

    public void setBudget_id(Integer budget_id) {
        this.budget_id = budget_id;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }


    /**
     * Methods
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Budget))
            return false;
        return budget_id.equals(((Budget) object).getBudget_id());
    }

    @Override
    public int hashCode() {
        return budget_id;
    }
}