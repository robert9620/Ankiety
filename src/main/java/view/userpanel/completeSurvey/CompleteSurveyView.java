package view.userpanel.completeSurvey;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.*;

public class CompleteSurveyView extends view.FrameView{

    private int padding = 50;
    private int firstLabelHeight = 20;
    private int spaceBetween = 36;
    private int howManySurveysExists = 0;

    public CompleteSurveyView(String name) throws HeadlessException {
        //super(name);

        this.createMenu();
    }

    public void addSurvey(String labelText, ActionListener buttonListener){
        JLabel label = new JLabel(labelText);
        label.setSize(label.getPreferredSize());
        label.setLocation(padding, firstLabelHeight + (spaceBetween*howManySurveysExists));
        this.add(label);

        JButton button = new JButton("wypełnij");
        button.setSize(button.getPreferredSize());
        button.setLocation(padding+label.getPreferredSize().width+20,firstLabelHeight + (spaceBetween*howManySurveysExists)-6);
        button.addActionListener(buttonListener);
        this.add(button);

        howManySurveysExists++;
    }
}