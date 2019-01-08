package view.loginpanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class RegisterView extends view.FrameView {
    private int frameWidth = 500;
    private int frameHeight = 500;

    private int firstLabelHeight = 50;
    private int firstFieldHeight = 70;
    private int spaceBetween = 70;

    private JButton buttonSignUp;
    private JButton buttonGoToLogin;
    private JTextField inputLogin;
    private JPasswordField inputPassword;
    private JPasswordField inputRepeatPassword;

    public RegisterView(String name) throws HeadlessException {
        super(name);

        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setLocation((screenWidth- frameWidth)/2, (screenHeight- frameHeight)/2);

        this.setSize(frameWidth, frameHeight);

        this.setLayout(null);

        JLabel labelLogin = new JLabel("Login:");
        labelLogin.setSize(labelLogin.getPreferredSize());
        labelLogin.setLocation(50, firstLabelHeight);
        this.add(labelLogin);

        inputLogin = new JTextField();
        inputLogin.setSize(frameWidth -100, 40);
        inputLogin.setLocation(50, firstFieldHeight);
        this.add(inputLogin);

        JLabel labelPassword = new JLabel("Hasło:");
        labelPassword.setSize(labelPassword.getPreferredSize());
        labelPassword.setLocation(50, firstLabelHeight+spaceBetween);
        this.add(labelPassword);

        inputPassword = new JPasswordField();
        inputPassword.setSize(frameWidth -100, 40);
        inputPassword.setLocation(50, firstFieldHeight+spaceBetween);
        this.add(inputPassword);

        JLabel labelRepeatPassword = new JLabel("Powtórz hasło:");
        labelRepeatPassword.setSize(labelRepeatPassword.getPreferredSize());
        labelRepeatPassword.setLocation(50, firstLabelHeight+(2*spaceBetween));
        this.add(labelRepeatPassword);

        inputRepeatPassword = new JPasswordField();
        inputRepeatPassword.setSize(frameWidth -100, 40);
        inputRepeatPassword.setLocation(50, firstFieldHeight+(2*spaceBetween));
        this.add(inputRepeatPassword);

        buttonGoToLogin = new JButton("Logowanie");
        buttonGoToLogin.setBorder(null);
        buttonGoToLogin.setSize(buttonGoToLogin.getPreferredSize());
        buttonGoToLogin.setLocation(50, 400);
        buttonGoToLogin.setForeground(Color.blue);
        this.add(buttonGoToLogin);

        buttonSignUp = new JButton("Zarejestruj");
        buttonSignUp.setSize(100,50);
        buttonSignUp.setLocation(350,380);
        this.getRootPane().setDefaultButton(buttonSignUp);
        this.add(buttonSignUp);
    }

    public int showSuccessMessage(){
        return JOptionPane.showOptionDialog(this,
                "Stworzono nowe konto użytkownika",
                "Zarejestrowano",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                null);
    }

    public void showErrorMessage(String errorMessage){
        JOptionPane.showMessageDialog(this,
                errorMessage,
                "Błędne dane",
                JOptionPane.ERROR_MESSAGE);
    }

    public void setButtonSignUp(ActionListener actionListener){
        buttonSignUp.addActionListener(actionListener);
    }

    public void setButtonGoToLogIn(ActionListener actionListener){
        buttonGoToLogin.addActionListener(actionListener);
    }

    public String getInputLogin() {
        return inputLogin.getText();
    }

    public char[] getInputPassword() {
        return inputPassword.getPassword();
    }

    public char[] getInputRepeatPassword() {
        return inputRepeatPassword.getPassword();
    }
}