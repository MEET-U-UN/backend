package co.edu.unal.software_engineering.meetu.service;


import co.edu.unal.software_engineering.meetu.model.Role;
import co.edu.unal.software_engineering.meetu.model.User;
import co.edu.unal.software_engineering.meetu.pojo.RegisterUserPOJO;
import co.edu.unal.software_engineering.meetu.repository.UserRepository;
import co.edu.unal.software_engineering.meetu.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith( SpringRunner.class )
@DataJpaTest
@AutoConfigureTestDatabase( replace = NONE )
public class UserServiceTest{

    @TestConfiguration
    static class UserServiceImpTestConfiguration{
        @Autowired
        private UserRepository userRepository;

        @Bean
        public UserService userService( ){
            return new UserService( userRepository );
        }
    }

    @Autowired
    private UserService userService;


    @Test
    public void crudTest( ){

        String username = "Aag23";
        String last_name = "Avila";
        String phone_number = "6872088";
        String email = "luaavilagu@unal.edu.co";
        String city = "Bogota";
        String password = "password";


        User createUser = new User( );
        createUser.setUsername( username );
        createUser.setLast_name( last_name );
        createUser.setPhone_number(phone_number);
        createUser.setEmail( email );
        createUser.setCity(city);
        createUser.setPassword(password);

        userService.save( createUser );

        User readUser = userService.findByEmail(email);
        assertEquals( createUser, readUser );

        createUser.addRole( Role.getUser( ) );
        userService.save( createUser );

        User updatedUser = userService.findByEmail( email );
        assertEquals( createUser.getRoles( ), updatedUser.getRoles( ) );
    }

    @Test
    public void isRightUserTest( ){
        RegisterUserPOJO user = new RegisterUserPOJO( );
        assertFalse( userService.isRightUser( user ) );

        user.setUsername( null );
        user.setLast_name("");
        user.setPhone_number("");
        user.setEmail("");
        user.setCity("");
        user.setPassword("");
        assertFalse( userService.isRightUser( user ) );

        user.setUsername( "" );
        user.setLast_name(null);
        user.setPhone_number("");
        user.setEmail("");
        user.setCity("");
        user.setPassword("");
        assertFalse( userService.isRightUser( user ) );

        user.setUsername( "" );
        user.setLast_name("");
        user.setPhone_number(null);
        user.setEmail("");
        user.setCity("");
        user.setPassword("");
        assertFalse( userService.isRightUser( user ) );

        user.setUsername( "" );
        user.setLast_name("");
        user.setPhone_number("");
        user.setEmail(null);
        user.setCity("");
        user.setPassword("");
        assertFalse( userService.isRightUser( user ) );

        user.setUsername( "" );
        user.setLast_name("");
        user.setPhone_number("");
        user.setEmail("");
        user.setCity(null);
        user.setPassword("");
        assertFalse( userService.isRightUser( user ) );

        user.setUsername( "" );
        user.setLast_name("");
        user.setPhone_number("");
        user.setEmail("");
        user.setCity("");
        user.setPassword(null);
        assertFalse( userService.isRightUser( user ) );

        user.setUsername( "laag23$" );
        user.setLast_name("Avila");
        user.setPhone_number("3142069606");
        user.setEmail("luaavilagu@unal.edu.co");
        user.setCity("Bogota");
        user.setPassword("Alejandro12345$");
        assertFalse( userService.isRightUser( user ) );

        user.setUsername( "laag23" );
        user.setLast_name("Avila21");
        user.setPhone_number("3142069606");
        user.setEmail("luaavilagu@unal.edu.co");
        user.setCity("Bogota");
        user.setPassword("Alejandro12345$");
        assertFalse( userService.isRightUser( user ) );

        user.setUsername( "laag23" );
        user.setLast_name("Avila");
        user.setPhone_number("31420a69606");
        user.setEmail("luaavilagu@unal.edu.co");
        user.setCity("Bogota");
        user.setPassword("Alejandro12345$");
        assertFalse( userService.isRightUser( user ) );

        user.setUsername( "laag23" );
        user.setLast_name("Avila");
        user.setPhone_number("3142069606");
        user.setEmail("luaavilaguunal.edu.co");
        user.setCity("Bogota");
        user.setPassword("Alejandro12345$");
        assertFalse( userService.isRightUser( user ) );

        user.setUsername( "laag23" );
        user.setLast_name("Avila");
        user.setPhone_number("3142069606");
        user.setEmail("luaavilagu@unal.edu.co");
        user.setCity("Bogotá");
        user.setPassword("Alejandro12345$");
        assertFalse( userService.isRightUser( user ) );

        user.setUsername( "laag23" );
        user.setLast_name("Avila");
        user.setPhone_number("3142069606");
        user.setEmail("luaavilagu@unal.edu.co");
        user.setCity("Bogotá");
        user.setPassword("Alejandro12345.");
        assertFalse( userService.isRightUser( user ) );

        user.setUsername( "laag23" );
        user.setLast_name("Avila");
        user.setPhone_number("3142069606");
        user.setEmail("luaavilagu@unal.edu.co");
        user.setCity("Bogota");
        user.setPassword("Alejandro12345$");
        assertTrue( userService.isRightUser( user ) );

    }

}
