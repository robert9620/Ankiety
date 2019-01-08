package controller;

import controller.userpanel.surveys.SelectSurveyController;
import controller.userpanel.SurveysController;
import model.UserModel;
import view.FrameView;

import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class Controller {
    public Controller(){
    }

    protected void addMenuActions(final FrameView activeView, final UserModel user, String activeViewName){
        activeView.setSelectSurveyViewMenuListener(new MenuListener() {
            public void menuSelected(MenuEvent e) {
                activeView.dispose();
                new SelectSurveyController(user);
            }

            public void menuDeselected(MenuEvent e) {

            }

            public void menuCanceled(MenuEvent e) {

            }
        });

        activeView.setSurveysViewMenuListener(new MenuListener() {
            public void menuSelected(MenuEvent e) {
                activeView.dispose();
                new SurveysController(user);
            }

            public void menuDeselected(MenuEvent e) {

            }

            public void menuCanceled(MenuEvent e) {

            }
        });
    }
}
