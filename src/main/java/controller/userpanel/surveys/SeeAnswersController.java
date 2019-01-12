package controller.userpanel.surveys;

import controller.Controller;
import model.AnswerModel;
import model.QuestionModel;
import model.connectivity.JDBCConnectivityModel;
import model.SurveyModel;
import model.UserModel;
import view.userpanel.surveys.SeeAnswersView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class SeeAnswersController extends Controller{
    private SeeAnswersView view;
    private JDBCConnectivityModel con;
    private UserModel user;
    private SurveyModel survey;

    private String activeViewName = "Zobacz odpowiedzi";
    private List<QuestionModel> questions;

    public SeeAnswersController(UserModel user, SurveyModel survey) {
        this.view = new SeeAnswersView(activeViewName);
        this.user = user;
        this.survey = survey;
        super.addMenuActions(view, user, activeViewName);

        this.questions = new LinkedList<QuestionModel>();

        view.setSurveyName(survey.getName());

        this.getQuestions();
        Iterator it = questions.iterator();
        while(it.hasNext()){
            final QuestionModel question = (QuestionModel) it.next();
            this.getAnswers(question);
        }

        this.addQuestionsAndAnswersToView();
        view.setButtonBackListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                view.dispose();
                new SelectSurveyController(SeeAnswersController.this.user);
            }
        });
    }

    private void getQuestions() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String sql="SELECT * FROM `question` WHERE question.surveyId = ?";
        try{
            con =  new JDBCConnectivityModel();
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
    }

    private void getAnswers(QuestionModel question){
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String sql="SELECT * FROM `answer` WHERE answer.questionId = ?";
        try{
            con =  new JDBCConnectivityModel();
            preparedStatement = con.getConn().prepareStatement(sql);
            preparedStatement.setInt(1, question.getId());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                AnswerModel answer = new AnswerModel();
                answer.setId(resultSet.getInt("id"));
                answer.setQuestion(question);
                answer.setContent(resultSet.getString("content"));
                question.addAnswer(answer);
            }
        }
        catch(SQLException e) {
            System.out.println(e);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    private void addQuestionsAndAnswersToView(){
        Iterator questionsIt = questions.iterator();
        while(questionsIt.hasNext()){
            final QuestionModel question = (QuestionModel) questionsIt.next();

            List<AnswerModel> answers = question.getAnswers();
            String[] stringAnswers = new String[answers.size()];
            Iterator answersIt = answers.iterator();
            int i = 0;
            while(answersIt.hasNext()){
                stringAnswers[i] = ((AnswerModel) answersIt.next()).getContent();
                i++;
            }
            view.addQuestionAndAnswers(question.getContent(), stringAnswers);
        }
    }
}
