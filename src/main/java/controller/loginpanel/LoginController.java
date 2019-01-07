package controller.loginpanel;

import controller.userpanel.SurveysController;
import model.UserModel;
import view.loginpanel.LoginView;

import model.Server.ConnectivityModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController extends controller.Controller{
    private LoginView view;
    private ConnectivityModel con;

    public LoginController() {
        this.view = new LoginView("Logowanie");
        setButtonLogInEvent();
        setButtonGoToSignUpEvent();
    }

    private void setButtonLogInEvent() {
        view.setButtonLogIn(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("click login button");
                login();
            }
        });
    }

    private void setButtonGoToSignUpEvent() {
        view.setButtonGoToSignUp(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("click go sign up button");
                view.setVisible(false);
                new RegisterController(view);
            }
        });
    }

    private void login() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String sql="select * from user where login=? and password=?";

        boolean status = false;
        try{
            con =  new ConnectivityModel();

            preparedStatement = con.getConn().prepareStatement(sql);
            preparedStatement.setString(1, view.getLogin());
            preparedStatement.setString(2, String.valueOf(view.getPassword()));
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                status = true;
            }
            else {
                status = false;
            }
        }
        catch(SQLException e) {
            System.out.println(e);
        }
        catch (Exception e){
            System.out.println(e);
        }
        finally {
            if (status) {
                System.out.println("Zalogowano");

                preparedStatement = null;
                resultSet = null;

                view.setVisible(false);
                UserModel user = new UserModel(view.getLogin());
                new SurveysController(user);
            }
            else {
                view.setErrorLabel("Błędny login lub hasło");
                con.close();
            }
        }
    }
}
