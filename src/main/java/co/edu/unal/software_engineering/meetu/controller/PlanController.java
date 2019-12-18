package co.edu.unal.software_engineering.meetu.controller;

import co.edu.unal.software_engineering.meetu.model.*;
import co.edu.unal.software_engineering.meetu.pojo.CreatePlanPOJO;
import co.edu.unal.software_engineering.meetu.repository.PlanRepository;
//import co.edu.unal.software_engineering.meetu.service.BudgetService;
import co.edu.unal.software_engineering.meetu.service.PlanService;
import co.edu.unal.software_engineering.meetu.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import co.edu.unal.software_engineering.meetu.exception.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;


@CrossOrigin
@RestController
public class PlanController {

    private final Logger logger = LoggerFactory.getLogger(PlanController.class);

    private final PlanService planService;
    private final UserService userService;

    public PlanController(PlanService planService, UserService userService) {
        this.planService = planService;
        this.userService = userService;
    }

    @Autowired
    private PlanRepository planRepository;

    @PostMapping( value = { "/plan/" } )
    public ResponseEntity register(@RequestBody CreatePlanPOJO planPOJO ){
        String email = SecurityContextHolder.getContext( ).getAuthentication( ).getName();
        User existingUser = userService.findByEmail( email );

        Plan newPlan = new Plan();
        newPlan.setDescription(planPOJO.getDescription());
        newPlan.setTitle(planPOJO.getTitle());
        planService.save(newPlan);

        List<Budget> listBudgets = planPOJO.getBudgets();
        List<Budget> ltbg = new ArrayList<Budget>();
        for (Budget budget : listBudgets) {
            Budget newBudget = new Budget();
            newBudget.setMoney(budget.getMoney());
            newBudget.setPlan(newPlan);
            ltbg.add(newBudget);
        }
        newPlan.setBudgets(ltbg);


        List<Comment> listComments = planPOJO.getComments();
        List<Comment> ltcmm = new ArrayList<Comment>();
        for (Comment comment : listComments) {
            Comment newComment = new Comment();
            newComment.setCommentary(comment.getCommentary());
            newComment.setPlan(newPlan);
            ltcmm.add(newComment);
        }
        newPlan.setComments(ltcmm);


        List<Location> listLocations = planPOJO.getLocations();
        List<Location> ltlc = new ArrayList<Location>();
        for (Location location : listLocations) {
            Location newLocation = new Location();
            newLocation.setName(location.getName());
            newLocation.setPlan(newPlan);
            ltlc.add(newLocation);
        }
        newPlan.setLocations(ltlc);


        List<Option> listOptions = planPOJO.getOptions();
        List<Option> lto = new ArrayList<Option>();
        for (Option option : listOptions) {
            Option newOption = new Option();
            newOption.setName(option.getName());
            newOption.setPlan(newPlan);
            lto.add(newOption);
        }
        newPlan.setOptions(lto);


        List<PossibleDate> listDates = planPOJO.getDates();
        List<PossibleDate> ltpd = new ArrayList<PossibleDate>();
        for (PossibleDate date : listDates) {
            PossibleDate newDate = new PossibleDate();
            newDate.setStart_date(date.getStart_date());
            newDate.setEnd_date(date.getEnd_date());
            newDate.setPlan(newPlan);
            ltpd.add(newDate);
        }
        newPlan.setDates(ltpd);

        List<User> users = new ArrayList<User>();
        users.add(existingUser);
        newPlan.setUsers(users);

        planService.save(newPlan);

        logger.info("user " + email + " successfully created a plan");
        return new ResponseEntity( HttpStatus.CREATED );
    }


    @GetMapping(value = {"/plan/"}) //Get plan
    public List<Plan> getPlan() {
        String email = SecurityContextHolder.getContext( ).getAuthentication( ).getName();
        User userTemp = userService.findByEmail( email );
        List<Plan> temp = userTemp.getPlans();

        logger.info("user " + email + " successfully got its plans");
        return temp;
    }


    @PutMapping( value = {"plan/{planId}"} ) //Update plan
    public ResponseEntity updatePlan( @PathVariable Integer planId, @RequestBody CreatePlanPOJO planPOJO) {
        String email = SecurityContextHolder.getContext( ).getAuthentication( ).getName();
        User existingUser = userService.findByEmail( email );

        if (!planService.existsById(planId)){
            logger.info("user " + email + " could not update plan " + planId + "successfully ");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }else{
            Plan erase = planService.findById(planId);
            planService.delete(erase);

            Plan temp = new Plan();
            temp.setTitle(planPOJO.getTitle());
            temp.setDescription(planPOJO.getDescription());

            List<Budget> listBudgets = planPOJO.getBudgets();
            List<Budget> ltbg = new ArrayList<Budget>();
            for (Budget budget : listBudgets) {
                Budget newBudget = new Budget();
                newBudget.setMoney(budget.getMoney());
                newBudget.setPlan(temp);
                ltbg.add(newBudget);
            }
            temp.setBudgets(ltbg);

            List<Comment> listComments = planPOJO.getComments();
            List<Comment> ltcmm = new ArrayList<Comment>();
            for (Comment comment : listComments) {
                Comment newComment = new Comment();
                newComment.setCommentary(comment.getCommentary());
                newComment.setPlan(temp);
                ltcmm.add(newComment);
            }
            temp.setComments(ltcmm);

            List<Location> listLocations = planPOJO.getLocations();
            List<Location> ltlc = new ArrayList<Location>();
            for (Location location : listLocations) {
                Location newLocation = new Location();
                newLocation.setName(location.getName());
                newLocation.setPlan(temp);
                ltlc.add(newLocation);
            }
            temp.setLocations(ltlc);

            List<Option> listOptions = planPOJO.getOptions();
            List<Option> lto = new ArrayList<Option>();
            for (Option option : listOptions) {
                Option newOption = new Option();
                newOption.setName(option.getName());
                newOption.setPlan(temp);
                lto.add(newOption);
            }
            temp.setOptions(lto);

            List<PossibleDate> listDates = planPOJO.getDates();
            List<PossibleDate> ltpd = new ArrayList<PossibleDate>();
            for (PossibleDate date : listDates) {
                PossibleDate newDate = new PossibleDate();
                newDate.setStart_date(date.getStart_date());
                newDate.setEnd_date(date.getEnd_date());
                newDate.setPlan(temp);
                ltpd.add(newDate);
            }
            temp.setDates(ltpd);

            List<User> users = new ArrayList<User>();
            users.add(existingUser);
            temp.setUsers(users);

            planService.save(temp);

            logger.info("user " + email + " updated plan " + planId + "successfully ");
            return new ResponseEntity(HttpStatus.OK);
        }
    }


    @DeleteMapping(value = {"/plan/{planId}"})   //delete plan
    public ResponseEntity<?> deletePlan(@PathVariable Integer planId) {
        String email = SecurityContextHolder.getContext( ).getAuthentication( ).getName();
        return planRepository.findById(planId)
                .map(plan -> {
                    planRepository.delete(plan);
                    logger.info("user " + email + " deleted plan " + planId + "successfully ");
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Plan not found with id " + planId));
    }

}