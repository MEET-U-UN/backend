package co.edu.unal.software_engineering.meetu.service;


import co.edu.unal.software_engineering.meetu.model.Role;
import co.edu.unal.software_engineering.meetu.repository.RoleRepository;
import co.edu.unal.software_engineering.meetu.service.RoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith( SpringRunner.class )
@DataJpaTest
@AutoConfigureTestDatabase( replace = NONE )
public class RoleServiceTest{

    @TestConfiguration
    static class RoleServiceTestImplTestContextConfiguration{
        @Autowired
        private RoleRepository roleRepository;

        @Bean
        public RoleService roleService( ) {
            return new RoleService( roleRepository );
        }
    }

    @Autowired
    private RoleService roleService;


    public RoleServiceTest( ){ }


    @Test
    public void findByIdTest( ){
        Role user = roleService.findById( Role.getUser( ).getId( ) );
        Role admin = roleService.findById( Role.getAdmin( ).getId( ) );
        assertEquals( user, Role.getUser( ) );
        assertEquals( admin, Role.getAdmin( ) );




        assertNotEquals( user, Role.getAdmin( ) );
        assertNotEquals( admin, Role.getUser( ) );
    }

}
