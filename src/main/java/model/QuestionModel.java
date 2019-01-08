package model;

import java.util.LinkedList;
import java.util.List;

public class QuestionModel {
    int id;
    SurveyModel survey;
    String content;

    private List<AnswerModel> answers;

    public QuestionModel(){
        this.answers = new LinkedList<AnswerModel>();
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

    public List<AnswerModel> getAnswers() {
        return answers;
    }

    public void addAnswer(AnswerModel answer) {
        answers.add(answer);
    }
}
