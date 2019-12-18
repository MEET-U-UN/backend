package co.edu.unal.software_engineering.meetu.service;

import co.edu.unal.software_engineering.meetu.model.Plan;
import co.edu.unal.software_engineering.meetu.model.User;
import co.edu.unal.software_engineering.meetu.pojo.CreatePlanPOJO;
import co.edu.unal.software_engineering.meetu.pojo.RegisterUserPOJO;
import co.edu.unal.software_engineering.meetu.repository.PlanRepository;
import co.edu.unal.software_engineering.meetu.repository.UserRepository;
import org.springframework.stereotype.Service;


@Service
public class PlanService {

    private final PlanRepository planRepository;

    public PlanService(PlanRepository planRepository ){
        this.planRepository = planRepository;
    }

    public Plan findById(Integer id) {
        return planRepository.findById(id).orElse(null);
    }

    public void save( Plan plan ){
        planRepository.save( plan );
    }

    public void delete (Plan plan) {
        planRepository.delete(plan);
    }

    public boolean existsById(Integer id){
        return planRepository.existsById(id);
    }

    public boolean isRightPlan(CreatePlanPOJO plan ){
        boolean correctness = plan.getDescription() != null && plan.getTitle() != null;
        if( correctness ){
            correctness = !plan.getDescription( ).trim( ).isEmpty( )
                    && plan.getTitle( ).trim( ).isEmpty( );
        }
        return correctness;
    }
}