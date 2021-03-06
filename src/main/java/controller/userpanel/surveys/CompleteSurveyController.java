package controller.userpanel.surveys;

import model.QuestionModel;
import model.connectivity.JDBCConnectivityModel;
import model.SurveyModel;
import model.UserModel;
import view.userpanel.surveys.CompleteSurveyView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class CompleteSurveyController extends controller.Controller{
    private CompleteSurveyView view;
    private JDBCConnectivityModel con;
    private UserModel user;
    private SurveyModel survey;

    private String activeViewName = "Wypełnij ankietę";
    private List<QuestionModel> questions;

    public CompleteSurveyController(UserModel user, SurveyModel survey) {
        this.view = new CompleteSurveyView(activeViewName);
        this.user = user;
        this.survey = survey;

        this.questions = new LinkedList<QuestionModel>();

        view.setSurveyName(survey.getName());
        this.getQuestions();
        view.setButtonAcceptListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CompleteSurveyController.this.setButtonAccept();
            }
        });
    }

    public CompleteSurveyController(UserModel user, JDBCConnectivityModel con, SurveyModel survey) {
        this(user, survey);
        this.con = con;
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

    private void setButtonAccept(){
        if(this.checkAnswers()){
            if(view.showSuccessMessage() == 0){
                this.saveAnswersToDataBase();
                this.setSurveyCompletedByUser();
                view.dispose();
            }
        }
        else{
            view.showErrorMessage("Musisz udzielić odpowiedzi na wszystkie pytania");
        }
    }

    private boolean checkAnswers(){
        String[] answers = view.getAnswers();
        for(int i=0; i<answers.length; i++){
            if(answers[i].equals("")){
                return false;
            }
        }
        return true;
    }

    private void saveAnswersToDataBase(){
        String[] answers = view.getAnswers();
        this.con = new JDBCConnectivityModel();
        try {
            for(int i=0; i<answers.length; i++) {
                PreparedStatement preparedStatement = null;

                String query = "INSERT INTO `answer` (`id`, `questionId`, `content`) VALUES (NULL, ?, ?);";

                preparedStatement = con.getConn().prepareStatement(query);

                preparedStatement.setInt(1, questions.get(i).getId());
                preparedStatement.setString(2, answers[i]);

                preparedStatement.executeUpdate();
            }
        }
        catch (SQLException e) {
            System.out.println(e);
        }
        catch (Exception e) {
            System.out.println(e);
        }
        finally {
            con.close();
        }
    }

    private void setSurveyCompletedByUser(){
        this.con = new JDBCConnectivityModel();
        try {
            PreparedStatement preparedStatement = null;

            String query = "INSERT INTO `completedSurvey` (`id`, `surveyId`, `userLogin`, `date`) VALUES (NULL, ?, ?, NOW())";

            preparedStatement = con.getConn().prepareStatement(query);

            preparedStatement.setInt(1, survey.getId());
            preparedStatement.setString(2, user.getLogin());

            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println(e);
        }
        catch (Exception e) {
            System.out.println(e);
        }
        finally {
            survey.setCompleted(true);
            survey.addCompletedBy(user);
            Date todaysDate = new Date();
            survey.setCompletedDate(new SimpleDateFormat("yyyy-mm-dd").format(todaysDate));
            con.close();
        }
    }
}
