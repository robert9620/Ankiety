package model;

public class QuestionModel {
    int id;
    SurveyModel survey;
    String content;

    public QuestionModel(){

    }

    public QuestionModel(int id, SurveyModel survey, String content) {
        this.id = id;
        this.survey = survey;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SurveyModel getSurvey() {
        return survey;
    }

    public void setSurvey(SurveyModel survey) {
        this.survey = survey;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
