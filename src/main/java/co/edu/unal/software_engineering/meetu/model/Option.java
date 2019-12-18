package co.edu.unal.software_engineering.meetu.model;

import co.edu.unal.software_engineering.meetu.log.LogModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the user database table.
 */
@Entity
@Table( name = "option", schema = "public" )
public class Option extends LogModel {

    private static final long serialVersionUID = 1L;

    /**
     * Attributes
     */

    @Id
    @SequenceGenerator( name = "OPTION_OPTIONID_GENERATOR", sequenceName = "public.option_option_id_seq", allocationSize = 1 )
    @GeneratedValue( generator = "OPTION_OPTIONID_GENERATOR", strategy = GenerationType.SEQUENCE )
    @Column( name = "option_id" )

    private Integer option_id;

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

    public Option( ){ }

    /**
     * Getters and Setters
     */

    public Integer getId() {
        return option_id;
    }

    public void setIdOption(Integer id) {
        this.option_id = id;
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
    public boolean equals( Object object ){
        if( !(object instanceof Option) ) return false;
        return option_id.equals( ((Option) object).getId( ) );
    }

    @Override
    public int hashCode( ){
        return option_id;
    }

}