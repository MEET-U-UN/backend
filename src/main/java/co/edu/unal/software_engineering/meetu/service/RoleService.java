package co.edu.unal.software_engineering.meetu.service;

import co.edu.unal.software_engineering.meetu.model.Role;
import co.edu.unal.software_engineering.meetu.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RoleService{

    private final RoleRepository roleRepository;

    public RoleService( RoleRepository roleRepository ){
        this.roleRepository = roleRepository;
    }

    public Role findById( Integer id ){
        return roleRepository.findById( id ).orElse( null );
    }

    public List<Role> getAll( ){
        return roleRepository.findAll( );
    }

}
