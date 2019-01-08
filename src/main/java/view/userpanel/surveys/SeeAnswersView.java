package view.userpanel.surveys;

import view.FrameView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SeeAnswersView extends FrameView{
    private int padding = 50;
    private int firstLabelHeight = 100;
    private int spaceBetween = 36;
    private int howManyAnswersExists = 0;

    private JLabel labelTitle;
    private JButton buttonBack;

    public SeeAnswersView(String name){
        super(name);

        this.createMenu();

        labelTitle = new JLabel();
        labelTitle.setLocation(padding, 20);
        labelTitle.setForeground(Color.BLUE);
        labelTitle.setFont(labelTitle.getFont().deriveFont(32f));
        this.add(labelTitle);

        buttonBack = new JButton("Wróć");
        buttonBack.setSize(buttonBack.getPreferredSize());
        buttonBack.setLocation(padding,firstLabelHeight);
        this.getRootPane().setDefaultButton(buttonBack);
        this.add(buttonBack);
    }

    public void addQuestionAndAnswers(String questionText, String[] answerText){
        JLabel question = new JLabel(questionText+": ");
        question.setForeground(Color.BLUE);
        question.setSize(question.getPreferredSize());
        question.setLocation(padding, firstLabelHeight + (spaceBetween*howManyAnswersExists));
        this.add(question);

        int xPosition = padding + question.getPreferredSize().width + 10;
        for(int i=0; i<answerText.length; i++) {
            if(i>0){
                answerText[i] = "; " + answerText[i];
            }
            JLabel answer = new JLabel(answerText[i]);
            answer.setSize(answer.getPreferredSize());
            answer.setLocation( xPosition, firstLabelHeight + (spaceBetween * howManyAnswersExists));
            this.add(answer);

            xPosition += answer.getPreferredSize().getWidth();
        }

        buttonBack.setLocation(padding, firstLabelHeight + spaceBetween*(howManyAnswersExists+1));

        howManyAnswersExists++;
    }

    public void setSurveyName(String surveyName){
        labelTitle.setText(surveyName);
        labelTitle.setSize(labelTitle.getPreferredSize());
    }

    public void setButtonBackListener(ActionListener actionListener){
        buttonBack.addActionListener(actionListener);
    }
}
