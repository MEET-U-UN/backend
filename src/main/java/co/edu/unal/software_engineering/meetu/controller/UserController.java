package co.edu.unal.software_engineering.meetu.controller;

import co.edu.unal.software_engineering.meetu.auth.configuration.WebSecurityConfiguration;
import co.edu.unal.software_engineering.meetu.exception.ResourceNotFoundException;
import co.edu.unal.software_engineering.meetu.model.Role;
import co.edu.unal.software_engineering.meetu.model.Roles;
import co.edu.unal.software_engineering.meetu.model.User;
import co.edu.unal.software_engineering.meetu.pojo.RegisterUserPOJO;
import co.edu.unal.software_engineering.meetu.repository.UserRepository;
import co.edu.unal.software_engineering.meetu.service.RoleService;
import co.edu.unal.software_engineering.meetu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;


@CrossOrigin
@RestController
public class UserController {

    private final UserService userService;
    private final RoleService roleService;
    private WebSecurityConfiguration webSecurityConfiguration;

    @Autowired
    private UserRepository userRepository;

    public UserController(UserService userService, RoleService roleService, WebSecurityConfiguration webSecurityConfiguration){
        this.userService = userService;
        this.roleService = roleService;
        this.webSecurityConfiguration = webSecurityConfiguration;
    }


    @PostMapping( value = { "/user/register/{roleId}" } )
    public ResponseEntity register(@PathVariable Integer roleId, @RequestBody RegisterUserPOJO userPOJO ){
        Roles roles = Roles.getInstance();
        if(roles.getRoleList() == null){
            roles.setRoleList(roleService.getAll());
        }

        Role role = roles.findRoleById(roleId);
        User existingUser = userService.findByEmail( userPOJO.getEmail( ) );
        boolean correcto = userService.isRightUser(userPOJO);
        if( role == null || existingUser != null || !correcto ){
            return new ResponseEntity( HttpStatus.BAD_REQUEST );
        }
        User newUser = new User( );
        PasswordEncoder passwordEncoder = webSecurityConfiguration.passwordEncoder();
        newUser.setPassword(passwordEncoder.encode(userPOJO.getPassword()));
        newUser.setUsername( userPOJO.getUsername( ).toLowerCase() );
        newUser.setLast_name(userPOJO.getLast_name().toLowerCase());
        newUser.setPhone_number(userPOJO.getPhone_number());
        newUser.setEmail(userPOJO.getEmail().toLowerCase());
        newUser.setCity(userPOJO.getCity().toLowerCase());
        newUser.setRoles( Collections.singletonList( role ) );

        userService.save( newUser );
        return new ResponseEntity( HttpStatus.CREATED );
    }


    @GetMapping( value =  {"user/"}) // Get user
    public User getUserByEmail() {
        String email = SecurityContextHolder.getContext( ).getAuthentication( ).getName();
        User existingUser = userService.findByEmail( email );
        return userService.findByEmail(email);
    }


    @PutMapping( value = {"user/"}) //Update user
    public ResponseEntity updateUser( @RequestBody RegisterUserPOJO userPOJO){

        PasswordEncoder passwordEncoder = webSecurityConfiguration.passwordEncoder();

        String email = SecurityContextHolder.getContext( ).getAuthentication( ).getName();
        User temp = userService.findByEmail( email );
        if(userPOJO.getEmail() != null){
            temp.setEmail(userPOJO.getEmail().toLowerCase());
        }
        if(userPOJO.getPhone_number() != null){
            temp.setPhone_number(userPOJO.getPhone_number());
        }
        if(userPOJO.getLast_name() != null){
            temp.setLast_name(userPOJO.getLast_name().toLowerCase());
        }
        if(userPOJO.getCity() != null){
            temp.setCity(userPOJO.getCity().toLowerCase());
        }
        if(userPOJO.getPassword() != null){
            temp.setPassword(passwordEncoder.encode(userPOJO.getPassword()));
        }
        if(userPOJO.getUsername() != null){
            temp.setUsername(userPOJO.getUsername().toLowerCase());
        }

        userService.save(temp);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping(value = {"user/"})   //delete plan
    public ResponseEntity<?> deletePlan() {
        String email = SecurityContextHolder.getContext( ).getAuthentication( ).getName();
        userService.delete(userService.findByEmail(email));
        return new ResponseEntity( HttpStatus.OK );
    }

}