package model.Server;

import model.SurveyModel;

import java.util.LinkedList;
import java.util.List;

public class SurveysModel {
    List<SurveyModel> surveys;

    public SurveysModel(){
        this.surveys = new LinkedList<SurveyModel>();
    }

    public void addSurvey(SurveyModel survey) {
        this.surveys.add(survey);
    }

    public List<SurveyModel> getSurveys() {
        return surveys;
    }

    public void setSurveys(List<SurveyModel> surveys) {
        this.surveys = surveys;
    }
}