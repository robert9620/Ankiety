package controller.userpanel;

import model.Server.ConnectivityModel;
import model.Server.SurveysModel;
import model.SurveyModel;
import model.UserModel;
import view.userpanel.SurveysView;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

public class SurveysController {
    private SurveysView view;
    private UserModel user;
    private SurveysModel model;
    private ConnectivityModel con;

    public SurveysController(UserModel user) {
        this.model = new SurveysModel();
        this.view = new SurveysView("Wypełnione ankiety");
        this.user = user;
        this.getSurveys();
    }

    public SurveysController(UserModel user, ConnectivityModel con) {
        this(user);
        this.con = con;
    }

    private void getSurveys() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String sql="select * from completedSurvey INNER JOIN survey on survey.id = completedSurvey.surveyId where completedSurvey.userLogin = ?";
        try{
            con =  new ConnectivityModel();
            preparedStatement = con.getConn().prepareStatement(sql);
            preparedStatement.setString(1,String.valueOf(user.getLogin()));
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                SurveyModel survey = new SurveyModel();
                survey.setCompleted(true);
                survey.addCompletedBy(user);
                survey.setName(resultSet.getString("name"));
                survey.setCompletedDate(resultSet.getDate("date"));
                model.addSurvey(survey);
            }
        }
        catch(SQLException e) {
            System.out.println(e);
        }
        catch (Exception e){
            System.out.println(e);
        }
        finally {
            this.addColumnsToSurveysTable();
        }
    }

    public void addColumnsToSurveysTable(){
        List<SurveyModel> surveyList = model.getSurveys();
        Iterator it = surveyList.iterator();
        while(it.hasNext()){
            SurveyModel survey = (SurveyModel) it.next();
            view.addColumnToTable(survey.toTable());
        }
    }
}
