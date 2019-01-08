package view.userpanel.completeSurvey;

import model.QuestionModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class CompleteSurveyView extends view.FrameView{
    private int padding = 50;
    private int firstLabelHeight = 100;
    private int spaceBetween = 36;
    private int howManyQuestionsExists = 0;

    private List<JTextField> textFields;
    private JLabel labelTitle;
    private JButton buttonAccept;

    public CompleteSurveyView(String name) throws HeadlessException {
        super(name);

        textFields = new LinkedList<JTextField> ();

        labelTitle = new JLabel();
        labelTitle.setLocation(padding, 20);
        labelTitle.setForeground(Color.BLUE);
        labelTitle.setFont(labelTitle.getFont().deriveFont(32f));
        this.add(labelTitle);

        buttonAccept = new JButton("Gotowe");
        buttonAccept.setSize(buttonAccept.getPreferredSize());
        buttonAccept.setLocation(padding,firstLabelHeight);
        this.getRootPane().setDefaultButton(buttonAccept);
        this.add(buttonAccept);

        this.createMenu();
    }

    public void setSurveyName(String surveyName){
        labelTitle.setText(surveyName);
        labelTitle.setSize(labelTitle.getPreferredSize());
    }

    public void addQuestion(String question){
        JLabel label = new JLabel(question);
        label.setSize(label.getPreferredSize());
        label.setLocation(padding, firstLabelHeight + (spaceBetween*howManyQuestionsExists));
        this.add(label);

        JTextField textField = new JTextField();
        textField.setSize(200, 40);
        textField.setLocation(padding+label.getPreferredSize().width+20, firstLabelHeight + (spaceBetween*howManyQuestionsExists)-12);
        this.add(textField);
        textFields.add(textField);

        buttonAccept.setLocation(padding, firstLabelHeight + spaceBetween*(howManyQuestionsExists+1));

        howManyQuestionsExists++;
    }

    public void setButtonAccept(ActionListener actionListener){
        buttonAccept.addActionListener(actionListener);
    }

    public List<JTextField> getTextFields() {
        return textFields;
    }

    public int showSuccessMessage(){
        return JOptionPane.showOptionDialog(this,
                "Twoje odpowiedzi na pytania w ankiecie zostały zapisane.",
                "Ankieta wypełniona",
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

    public String[] getAnswers(){
        String[] answers = new String[textFields.size()];
        Iterator it = textFields.iterator();
        int intIt = 0;
        while(it.hasNext()){
            JTextField answer = (JTextField) it.next();
            answers[intIt] = answer.getText();
            intIt++;
        }
        return answers;
    }
}
