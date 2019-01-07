package controller.userpanel.completeSurvey;

import model.Server.ConnectivityModel;
import model.SurveyModel;
import model.UserModel;
import view.userpanel.completeSurvey.CompleteSurveyView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    private List<SurveyModel> surveys;

    public CompleteSurveyController(UserModel user) {
        this.view = new CompleteSurveyView("Wypełnij ankietę");
        this.user = user;
        super.addMenuActions(view, user);

        this.surveys = new LinkedList<SurveyModel>();

        this.getSurveys();
    }

    public CompleteSurveyController(UserModel user, ConnectivityModel con) {
        this(user);
        this.con = con;
    }

    private void getSurveys() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String sql="select * from survey";
        try{
            con =  new ConnectivityModel();
            preparedStatement = con.getConn().prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                SurveyModel survey = new SurveyModel();
                survey.setName(resultSet.getString("name"));
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
            this.addSurveysToView();
        }
    }

    private void addSurveysToView(){
        Iterator it = surveys.iterator();
        while(it.hasNext()){
            final SurveyModel survey = (SurveyModel) it.next();
            view.addSurvey(survey.getName(), new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.out.println(survey.getName() + " clicked");
                }
            });
        }
    }
}
