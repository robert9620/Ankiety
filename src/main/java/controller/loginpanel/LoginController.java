package controller.loginpanel;

import controller.userpanel.SurveysController;
import model.UserModel;
import view.loginpanel.LoginView;

import model.Server.ConnectivityModel;
import model.Server.LoginModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    private LoginModel model;
    private LoginView view;
    private ConnectivityModel con;

    public LoginController() {
        this.model = new LoginModel();
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
        try{
            con =  new ConnectivityModel();
            this.setLoginModelLogin(this.getLoginViewLogin());
            this.setLoginModelPassword(this.getLoginViewPassword());

            preparedStatement = con.getConn().prepareStatement(sql);
            preparedStatement.setString(1, this.getLoginModelLogin());
            preparedStatement.setString(2, this.getLoginModelPassword());
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                this.setLoginModelStatus(true);
            }
            else {
                this.setLoginModelStatus(false);
            }
        }
        catch(SQLException e) {
            System.out.println(e);
        }
        catch (Exception e){
            System.out.println(e);
        }
        finally {
            if (model.getStatus()) {
                System.out.println("Zalogowano");

                preparedStatement = null;
                resultSet = null;

                view.setVisible(false);
                UserModel user = new UserModel(this.getLoginModelLogin());
                new SurveysController(user);
            }
            else {
                view.setErrorLabel("Błędny login lub hasło");
                con.close();
            }
        }
    }

    //Model getters and setters
    private void setLoginModelLogin(String login){
        model.setLogin(login);
    }

    private String getLoginModelLogin(){
        return model.getLogin();
    }

    private void setLoginModelPassword(String password){
        model.setPassword(password);
    }

    private String getLoginModelPassword(){
        return model.getPassword();
    }

    private void setLoginModelStatus(boolean status){
        model.setStatus(status);
    }

    //View getters and setters
    private String getLoginViewLogin(){
        return view.getLogin();
    }

    private String getLoginViewPassword(){
        return String.valueOf(view.getPassword());
    }
}
