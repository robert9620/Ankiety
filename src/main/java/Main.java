import controller.loginpanel.LoginController;
import model.connectivity.ClientTCPModel;

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
//                CompleteSurveyView test = new CompleteSurveyView("Wypełnij ankietę");
//                test.setSurveyName("Testowa ankieta");
//                test.addQuestion("Czy lubisz lody?");
//                test.addQuestion("Czy lubisz napoje gazowane?");
//                test.addQuestion("Czy lubisz pizze?");
//            }
//        });
    }
}