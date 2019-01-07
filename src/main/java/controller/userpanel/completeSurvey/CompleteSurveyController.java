package controller.userpanel.completeSurvey;

import model.QuestionModel;
import model.Server.ConnectivityModel;
import model.SurveyModel;
import model.UserModel;
import view.userpanel.completeSurvey.CompleteSurveyView;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class CompleteSurveyController extends controller.Controller{
    private CompleteSurveyView view;
    private ConnectivityModel con;
    private UserModel user;
    private SurveyModel survey;

    private String activeViewName = "Wypełnij ankietę";
    private List<QuestionModel> questions;

    public CompleteSurveyController(UserModel user, SurveyModel survey) {
        this.view = new CompleteSurveyView(activeViewName);
        this.user = user;
        this.survey = survey;
        super.addMenuActions(view, user, activeViewName);

        this.questions = new LinkedList<QuestionModel>();

        view.setSurveyName(survey.getName());
        this.getQuestion();

        //TODO
        //zrobic dzialanie buttona gotowe - ma wyswietlic okno dialogowe, pobrac odpowiedzi z view, zapisac je w bazie, dodac do bazy informacje kto wypelnil ankiete, przeniesc do kolejnego widoku
    }

    public CompleteSurveyController(UserModel user, ConnectivityModel con, SurveyModel survey) {
        this(user, survey);
        this.con = con;
    }

    private void getQuestion() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String sql="SELECT * FROM `question` WHERE question.surveyId = ?";
        try{
            con =  new ConnectivityModel();
            preparedStatement = con.getConn().prepareStatement(sql);
            preparedStatement.setInt(1, survey.getId());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                QuestionModel question = new QuestionModel();
                question.setId(resultSet.getInt("id"));
                question.setSurvey(survey);
                question.setContent(resultSet.getString("content"));
                questions.add(question);
            }
        }
        catch(SQLException e) {
            System.out.println(e);
        }
        catch (Exception e){
            System.out.println(e);
        }
        finally {
            this.addQuestionsToView();
        }
    }

    private void addQuestionsToView(){
        Iterator it = questions.iterator();
        while(it.hasNext()){
            final QuestionModel question = (QuestionModel) it.next();
            view.addQuestion(question.getContent());
        }
    }
}
