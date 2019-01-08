package view.userpanel.surveys;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.*;

public class SelectSurveyView extends view.FrameView{

    private int padding = 50;
    private int firstLabelHeight = 20;
    private int spaceBetween = 36;
    private int howManySurveysExists = 0;

    public SelectSurveyView(String name){
        super(name);

        this.createMenu();
    }

    public void addSurvey(String labelText, ActionListener buttonCompleteListener, ActionListener buttonSeeAnswersListener){
        JLabel label = new JLabel(labelText);
        label.setSize(label.getPreferredSize());
        label.setLocation(padding, firstLabelHeight + (spaceBetween*howManySurveysExists));
        this.add(label);

        JButton buttonComplete = new JButton("wypełnij");
        buttonComplete.setSize(buttonComplete.getPreferredSize());
        buttonComplete.setLocation(padding+label.getPreferredSize().width+20,firstLabelHeight + (spaceBetween*howManySurveysExists)-6);
        buttonComplete.addActionListener(buttonCompleteListener);
        this.add(buttonComplete);

        JButton buttonSeeAnswers = new JButton("zobacz odpowiedzi");
        buttonSeeAnswers.setSize(buttonSeeAnswers.getPreferredSize());
        buttonSeeAnswers.setLocation(padding+label.getPreferredSize().width+buttonComplete.getPreferredSize().width+40,firstLabelHeight + (spaceBetween*howManySurveysExists)-6);
        buttonSeeAnswers.addActionListener(buttonSeeAnswersListener);
        this.add(buttonSeeAnswers);

        howManySurveysExists++;
    }
}