package controller.loginpanel;

import controller.userpanel.SurveysController;
import model.UserModel;
import view.loginpanel.LoginView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class LoginController extends controller.Controller{
    private LoginView view;

    public LoginController() {
        this.view = new LoginView("Logowanie");
        setButtonLogInEvent();
        setButtonGoToSignUpEvent();
    }

    private void setButtonLogInEvent() {
        view.setButtonLogIn(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("click login button");
                String address = "192.168.1.10";
                int port = 12367;

                try {
                    Socket socket = new Socket(InetAddress.getByName(address), port);
                    socket.setTcpNoDelay(true);

                    OutputStream os = socket.getOutputStream();
                    ObjectOutputStream oos = new ObjectOutputStream(os);
                    UserModel user = new UserModel(view.getLogin(), view.getPassword());
                    oos.writeObject(user);

                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String response = in.readLine();
                    System.out.println(response);
                    login(response);

                    PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
                    out.println("exit");
                    out.flush();

                    socket.close();
                } catch (Exception er) {
                    System.err.println(er);
                }
            }
        });
    }

    private void login(String response) {
        if (response.equals("")) {
            System.out.println("Zalogowano");

            view.dispose();
            UserModel user = new UserModel(view.getLogin());
            new SurveysController(user);
        }
        else {
            view.setErrorLabel(response);
        }
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
}
