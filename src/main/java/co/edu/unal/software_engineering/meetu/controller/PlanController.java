package co.edu.unal.software_engineering.meetu.controller;

import co.edu.unal.software_engineering.meetu.model.Budget;
import co.edu.unal.software_engineering.meetu.model.Plan;
import co.edu.unal.software_engineering.meetu.pojo.CreatePlanPOJO;
import co.edu.unal.software_engineering.meetu.service.BudgetService;
import co.edu.unal.software_engineering.meetu.service.PlanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@CrossOrigin
@RestController
public class PlanController {

    private final PlanService planService;
    // private final BudgetService budgetService;

    public PlanController(PlanService planService, BudgetService budgetService){
        this.planService = planService;
       // this.budgetService = budgetService;
    }


    @PostMapping( value = { "/plan" } )

    public ResponseEntity register(@RequestBody CreatePlanPOJO planPOJO ){

        Plan newPlan = new Plan();

        newPlan.setDescription(planPOJO.getDescription());
        newPlan.setTitle(planPOJO.getTitle());
        // newPlan.setBudgets(planPOJO.getBudgets());
        planService.save(newPlan);

        List<Budget> listBudgets = planPOJO.getBudgets();
        List<Budget> ltbg = new ArrayList<Budget>();
        for (Budget budget: listBudgets) {
            Budget newBudget = new Budget();
            newBudget.setMoney(budget.getMoney());
            newBudget.setPlan(newPlan);
            ltbg.add(newBudget);
            // newPlan.addBudget(newBudget);
        }
        newPlan.setBudgets(ltbg);
        planService.save(newPlan);

        return new ResponseEntity( HttpStatus.CREATED );
    }


    @GetMapping( value = { "/consultaplan/{planId}" } )
    public Plan register( @PathVariable Integer planId){
        Plan temp = planService.findById(planId);
        return temp;
    }
}