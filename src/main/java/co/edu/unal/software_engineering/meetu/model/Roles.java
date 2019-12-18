package co.edu.unal.software_engineering.meetu.model;

import java.util.List;

public final class Roles {


    private static Roles INSTANCE;
    List<Role> roleList;

    private Roles() {
    }

    public synchronized static Roles getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new Roles();
        }

        return INSTANCE;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public Role findRoleById(int role_id){
        for (Role role: roleList){
            if(role.getId() == role_id){
                return role;
            }
        }
        return null;
    }
}

