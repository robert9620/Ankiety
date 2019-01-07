package controller.loginpanel;

import view.FrameView;
import view.loginpanel.RegisterView;

import model.Server.ConnectivityModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterController extends controller.Controller{
    private RegisterView view;
    private ConnectivityModel con;
    private FrameView previouesView;

    public RegisterController(FrameView previouesView) {
        this.view = new RegisterView("Rejestracja");
        this.previouesView = previouesView;
        setViewButtonSignUp();
        setViewButtonGoToLogin();
    }

    public RegisterController(FrameView previouesView, ConnectivityModel con) {
        this(previouesView);
        this.con = con;
    }

    private void setViewButtonSignUp() {
        view.setButtonSignUp(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("click register button");
                register();
            }
        });
    }

    private void setViewButtonGoToLogin() {
        view.setButtonGoToLogIn(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                view.dispose();
                previouesView.setVisible(true);
            }
        });
    }

    private void register() {
        String err = "";

        //login
        String login = view.getInputLogin();
        Pattern pattern = Pattern.compile("\\w+");
        Matcher matcher = pattern.matcher(login);
        if (!matcher.matches()) {
            err += "Wpisano niepoprawny login\n";
        }
        else{
            if (isLoginExists(login, new ConnectivityModel())) {
                err += "Wpisany login jest zajęty";
            }
        }

        //password
        String password = String.valueOf(view.getInputPassword());
        String repassword = String.valueOf(view.getInputRepeatPassword());
        pattern = Pattern.compile("[^ ]+");
        matcher = pattern.matcher(password);
        if (!matcher.matches()) {
            err += "Wpisano niepoprawne hasło\n";
        } else if (!password.equals(repassword)) {
            err += "Wpisane hasła nie są takie same\n";
        }

        if (err.equals("")) {
            this.con = new ConnectivityModel();

            try {
                PreparedStatement preparedStatement = null;

                String query="INSERT INTO `user` (`login`, `password`) VALUES (?, ?)";

                preparedStatement = con.getConn().prepareStatement(query);

                preparedStatement.setString(1, view.getInputLogin());
                preparedStatement.setString(2, String.valueOf(view.getInputPassword()));

                preparedStatement.executeUpdate();

                System.out.println("Zarejestrowano");

                if(view.showSuccessMessage() == 0){
                    view.dispose();
                    previouesView.setVisible(true);
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
        else {
            view.setErrorMessage(err);
        }
    }

    private boolean isLoginExists(String login, ConnectivityModel con) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql="select * from user where login=?";

        try {
            preparedStatement = con.getConn().prepareStatement(sql);
            preparedStatement.setString(1, login);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                return true;
            }
            else {
                return false;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            con.close();
        }

        return false;
    }
}