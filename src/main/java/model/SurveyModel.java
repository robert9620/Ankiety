package model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class SurveyModel {
    private int id;
    private String name;
    private boolean completed = false;
    List<UserModel> completedBy;
    private Date completedDate;

    public SurveyModel(){
        this.completedBy = new LinkedList<UserModel>();
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

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void setCompletedDate(Date completedDate) {
        this.completedDate = completedDate;
    }

    public void addCompletedBy(UserModel user){
        this.completedBy.add(user);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String[] toTable(){
        String[] surveyInfo = new String[3];
        surveyInfo[0] = "1";
        surveyInfo[1] = String.valueOf(this.name);
        surveyInfo[2] = String.valueOf(this.completedDate);
        return surveyInfo;
    }
}
