package controller.userpanel;

import model.connectivity.ConnectivityModel;
import model.SurveyModel;
import model.UserModel;
import view.userpanel.SurveysView;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class SurveysController extends controller.Controller{
    private SurveysView view;
    private UserModel user;
    private ConnectivityModel con;

    private String activeViewName = "Wypełnione ankiety";
    private List<SurveyModel> surveys;

    public SurveysController(UserModel user) {
        this.view = new SurveysView(activeViewName);
        this.user = user;
        super.addMenuActions(view, user, activeViewName);

        this.surveys = new LinkedList<SurveyModel>();

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
                surveys.add(survey);
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

    private void addColumnsToSurveysTable(){
        Iterator it = surveys.iterator();
        while(it.hasNext()){
            SurveyModel survey = (SurveyModel) it.next();
            view.addColumnToTable(survey.toTable());
        }
    }
}
