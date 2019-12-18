package co.edu.unal.software_engineering.meetu.pojo;

import co.edu.unal.software_engineering.meetu.model.*;

import java.util.List;

public class CreatePlanPOJO {

    private String title;
    private String description;
    private List<Budget> budgets;
    private List<Comment> comments;
    private List<Location> locations;
    private List<Option> options;
    private List<PossibleDate> dates;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Budget> getBudgets() {
        return budgets;
    }

    public void setBudgets(List<Budget> budgets) {
        this.budgets = budgets;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public List<PossibleDate> getDates() {
        return dates;
    }

    public void setDates(List<PossibleDate> dates) {
        this.dates = dates;
    }
}
