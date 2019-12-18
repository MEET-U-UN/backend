package co.edu.unal.software_engineering.meetu.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Private package class for the relation with M...M table
 */

@Entity
@Table( name = "user_plan", schema = "public" )
class UserPlan implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Attributes
     */

    @EmbeddedId
    private UserPlanPK userPlanPK;

    /**
     * Constructors
     */

    public UserPlan( ){ }

    /**
     * Getters and Setters
     */

    User getUser( ){
        return userPlanPK.getUser( );
    }

    void setUser( User user ){
        userPlanPK.setUser( user );
    }

    Plan getPlan( ){
        return userPlanPK.getPlan( );
    }

    void setPlan( Plan plan ){
        userPlanPK.setPlan( plan );
    }

    /**
     * Methods
     */

    @Override
    public boolean equals( Object object ){
        if( !(object instanceof UserPlan) ) return false;
        UserPlan userPlan = (UserPlan) object;
        return getUser( ).getId( ).equals( userPlan.getUser( ).getId( ) ) &&
                getPlan( ).getPlan_id( ).equals( userPlan.getPlan( ).getPlan_id( ) );
    }

    @Override
    public int hashCode( ){
        final int prime = 31;
        int hash = 17;
        hash = hash * prime + getUser( ).hashCode( );
        hash = hash * prime + getPlan( ).hashCode( );
        return hash;
    }

    /**
     * Private class for primary key
     */

    @Embeddable
    private static class UserPlanPK implements Serializable{

        private static final long serialVersionUID = 1L;

        /**
         * Attributes
         */

        @ManyToOne
        @JoinColumn( name = "user_id", insertable = false, updatable = false )
        private User user;

        @ManyToOne
        @JoinColumn( name = "plan_id", insertable = false, updatable = false )
        private Plan plan;

        /**
         * Constructor
         */

        public UserPlanPK( ){ }

        public UserPlanPK( User user, Plan plan ){
            this.user = user;
            this.plan = plan;
        }

        /**
         * Getters and Setters
         */

        public User getUser( ){
            return user;
        }

        public void setUser( User user ){
            this.user = user;
        }

        public Plan getPlan( ){
            return plan;
        }

        public void setPlan( Plan plan ){
            this.plan = plan;
        }
    }

}