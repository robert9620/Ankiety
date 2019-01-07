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
//                CompleteSurveyView test = new CompleteSurveyView("Wypełnij ankietę");
//
//                test.addSurvey("testowa", new ActionListener() {
//                    public void actionPerformed(ActionEvent e) {
//                        System.out.println("work");
//                    }
//                });
//                test.addSurvey("testowa2", new ActionListener() {
//                    public void actionPerformed(ActionEvent e) {
//                        System.out.println("work2");
//                    }
//                });
//            }
//        });
    }
}