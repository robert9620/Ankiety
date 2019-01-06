package model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class SurveyModel {
    private String name;
    private boolean completed = false;
    List<UserModel> completedBy;
    private Date completedDate;

    private int ordinalNumber;

    public SurveyModel(){
        this.completedBy = new LinkedList<UserModel>();
        this.ordinalNumber = 1;
    }

    public SurveyModel(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Date getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(Date completedDate) {
        this.completedDate = completedDate;
    }

    public void addCompletedBy(UserModel user){
        this.completedBy.add(user);
    }

    public String[] toTable(){
        String[] surveyInfo = new String[3];
        surveyInfo[0] = String.valueOf(this.ordinalNumber++);
        surveyInfo[1] = String.valueOf(this.name);
        surveyInfo[2] = String.valueOf(this.completedDate);
        return surveyInfo;
    }
}
