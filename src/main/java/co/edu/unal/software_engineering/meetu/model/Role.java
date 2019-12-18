package co.edu.unal.software_engineering.meetu.model;

import co.edu.unal.software_engineering.meetu.log.LogModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the role database table.
 */
@Entity
@Table( name = "role", schema = "public" )
public class Role extends LogModel {

    private static final long serialVersionUID = 1L;

    private final static int ROLE_USER_ID = 1;
    private final static String ROLE_USER_NAME = "User";
    private final static int ROLE_ADMIN_ID = 2;
    private final static String ROLE_ADMIN_NAME = "Administrator";

    private final static Role USER = new Role( ROLE_USER_ID, ROLE_USER_NAME );
    private final static Role ADMIN = new Role( ROLE_ADMIN_ID, ROLE_ADMIN_NAME );


    /**
     * Attributes
     */

    @Id
    @Column( name = "role_id" )
    private Integer id;

    @Column( name = "role_name" )
    private String roleName;

    //bi-directional many-to-many association to User
    @JsonIgnore
    @ManyToMany( mappedBy = "roles" )
    private List<User> users;

    /**
     * Constructors
     */

    public Role( ){ }

    private Role (Integer id, String roleName){
        this.id = id;
        this.roleName = roleName;
        this.users = new ArrayList<>();
    }

    /**
     * Getters and Setters
     */

    public Integer getId( ){
        return this.id;
    }

    public void setId( Integer id ){
        this.id = id;
    }

    public String getRoleName( ){
        return this.roleName;
    }

    public void setRoleName( String roleName ){
        this.roleName = roleName;
    }

    public List<User> getUsers( ){
        return this.users;
    }

    public void setUsers( List<User> users ){
        this.users = users;
    }

    /**
     * Methods
     */

    public static Role getUser( ){
        return USER;
    }

    public static Role getAdmin( ){
        return ADMIN;
    }


    @Override
    public boolean equals( Object object ){
        if( !(object instanceof Role) ) return false;
        return id.equals( ((Role) object).getId( ) );
    }

    @Override
    public int hashCode( ){
        return id;
    }

}