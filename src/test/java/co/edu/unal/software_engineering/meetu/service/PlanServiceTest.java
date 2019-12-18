package co.edu.unal.software_engineering.meetu.service;


import co.edu.unal.software_engineering.meetu.model.Plan;
import co.edu.unal.software_engineering.meetu.model.Role;
import co.edu.unal.software_engineering.meetu.model.User;
import co.edu.unal.software_engineering.meetu.pojo.RegisterUserPOJO;
import co.edu.unal.software_engineering.meetu.repository.PlanRepository;
import co.edu.unal.software_engineering.meetu.repository.UserRepository;
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
public class PlanServiceTest {

    @TestConfiguration
    static class UserServiceImpTestConfiguration{
        @Autowired
        private PlanRepository planRepository;

        @Bean
        public PlanService planService ( ){
            return new PlanService( planRepository );
        }
    }

    @Autowired
    private PlanService planService;


    @Test
    public void crudTest( ){

        String title = "Salida";
        String description = "Amigos";

        Plan planTemp = new Plan();
        planTemp.setTitle(title);
        planTemp.setDescription(description);

        planService.save(planTemp);
        int id = planTemp.getPlan_id();

        Plan readPlan = planService.findById(id);
        assertEquals( planService, readPlan );

    }

}
