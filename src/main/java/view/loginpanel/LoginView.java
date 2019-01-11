package view.loginpanel;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;

public class LoginView extends view.FrameView{
    private int frameWidth = 500;
    private int frameHeight = 500;

    private int firstLabelHeight = 50;
    private int firstFieldHeight = 70;
    private int spaceBetween = 70;

    private JLabel labelLogin;
    private JLabel labelPassword;
    private JButton buttonGoToSignUp;
    private JButton buttonLogIn;
    private JTextField inputLogin;
    private JPasswordField inputPassword;

    public LoginView(String name){
        super(name);

        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setLocation((screenWidth-frameWidth)/2, (screenHeight-frameHeight)/2);

        this.setSize(frameWidth, frameHeight);

        this.setLayout(null);

        labelLogin = new JLabel("Login:");
        labelLogin.setSize(labelLogin.getPreferredSize());
        labelLogin.setLocation(50, firstLabelHeight);
        this.add(labelLogin);

        inputLogin = new JTextField();
        inputLogin.setSize(frameWidth-100, 40);
        inputLogin.setLocation(50, firstFieldHeight);
        this.add(inputLogin);

        labelPassword = new JLabel("Hasło:");
        labelPassword.setSize(labelPassword.getPreferredSize());
        labelPassword.setLocation(50, firstLabelHeight + spaceBetween);
        this.add(labelPassword);

        inputPassword = new JPasswordField();
        inputPassword.setSize(frameWidth-100, 40);
        inputPassword.setLocation(50, firstFieldHeight + spaceBetween);
        this.add(inputPassword);

        buttonLogIn = new JButton("Zaloguj");
        buttonLogIn.setSize(100,50);
        buttonLogIn.setLocation(350,380);
        this.getRootPane().setDefaultButton(buttonLogIn);
        this.add(buttonLogIn);

        buttonGoToSignUp = new JButton("Rejestracja");
        buttonGoToSignUp.setBorder(null);
        buttonGoToSignUp.setSize(buttonGoToSignUp.getPreferredSize());
        buttonGoToSignUp.setLocation(50, 400);
        buttonGoToSignUp.setForeground(Color.blue);
        this.add(buttonGoToSignUp);
    }

    public void setButtonLogIn(ActionListener actionListener){
        buttonLogIn.addActionListener(actionListener);
    }

    public void setButtonGoToSignUp(ActionListener actionListener){
        buttonGoToSignUp.addActionListener(actionListener);
    }

    public char[] getPassword() {
        return inputPassword.getPassword();
    }

    public String getLogin() {
        return inputLogin.getText();
    }

    public void setErrorLabel(String errorMessage){
        JOptionPane.showMessageDialog(this,
                errorMessage,
                "Błędne dane",
                JOptionPane.ERROR_MESSAGE);
    }
}