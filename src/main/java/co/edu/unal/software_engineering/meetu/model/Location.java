package co.edu.unal.software_engineering.meetu.model;

import co.edu.unal.software_engineering.meetu.log.LogModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the user database table.
 */
@Entity
@Table(name = "location", schema = "public")
public class Location extends LogModel {

    private static final long serialVersionUID = 1L;

    /**
     * Attributes
     */
    @Id
    @SequenceGenerator(name = "LOCATION_LOCATIONID_GENERATOR", sequenceName = "public.location_location_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "LOCATION_LOCATIONID_GENERATOR", strategy = GenerationType.SEQUENCE)

    @Column(name = "location_id")
    private Integer location_id;

    @Column(name = "name")
    private String name;

    // bi-directional many-to-one association to Plan
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id")
    private Plan plan;


    /**
     * Constructors
     */
    public Location() {
    }


    /**
     * Getters and Setters
     */

    public Integer getLocation_id() {
        return location_id;
    }

    public void setLocation_id(Integer location_id) {
        this.location_id = location_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        if (!(object instanceof Location))
            return false;
        return location_id.equals(((Location) object).getLocation_id());
    }

    @Override
    public int hashCode() {
        return location_id;
    }
}