package controller;

import controller.userpanel.completeSurvey.CompleteSurveyController;
import controller.userpanel.SurveysController;
import model.UserModel;
import view.FrameView;

import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class Controller {
    public Controller(){
    }

    protected void addMenuActions(final FrameView view, final UserModel user){
        view.setCompleteSurveyViewMenuListener(new MenuListener() {
            public void menuSelected(MenuEvent e) {
                view.dispose();
                new CompleteSurveyController(user);
            }

            public void menuDeselected(MenuEvent e) {

            }

            public void menuCanceled(MenuEvent e) {

            }
        });

        view.setSurveysViewMenuListener(new MenuListener() {
            public void menuSelected(MenuEvent e) {
                view.dispose();
                new SurveysController(user);
            }

            public void menuDeselected(MenuEvent e) {

            }

            public void menuCanceled(MenuEvent e) {

            }
        });
    }
}
