package co.edu.unal.software_engineering.meetu.controller;

import co.edu.unal.software_engineering.meetu.model.Role;
import co.edu.unal.software_engineering.meetu.service.RoleService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;

@CrossOrigin
@RestController
public class RoleController{

    private RoleService roleService;

    public RoleController( RoleService roleService ){
        this.roleService = roleService;
    }

    private final Logger logger = LoggerFactory.getLogger(RoleController.class);

    @GetMapping( value = { "/roles" } )
    public List<Role> getAllRoles( ){
        logger.info("Roles returned successfully ");
        return roleService.getAll( );
    }
}
