import view.userpanel.SurveysView;
import controller.loginpanel.LoginController;

import java.awt.EventQueue;

public class Main {

    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginController();
            }
        });

//        EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new SurveysView("Wypełnione ankiety");
//            }
//        });
    }
}