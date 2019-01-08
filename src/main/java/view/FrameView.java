package view;

import javax.swing.*;
import javax.swing.event.MenuListener;
import java.awt.*;

public abstract class FrameView extends JFrame{
    protected int frameWidth = 1000;
    protected int frameHeight = 600;
    private JMenuBar menuBar;
    private JMenu surveysView;
    private JMenu selectSurveyView;

    public FrameView(String name) throws HeadlessException {
        super(name);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        this.setLayout(null);
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setLocation((screenWidth-frameWidth)/2, (screenHeight-frameHeight)/2);
        this.setSize(frameWidth, frameHeight);
    }

    protected void createMenu(){
        menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);

        surveysView = new JMenu("Wypełnone ankiety");
        surveysView.getAccessibleContext().setAccessibleDescription("Przejdź do widoku wypełnionych ankiet");
        menuBar.add(surveysView);

        selectSurveyView = new JMenu("Ankiety");
        selectSurveyView.getAccessibleContext().setAccessibleDescription("Przejdź do widoku ankiet");
        menuBar.add(selectSurveyView);
    }

    public void setSurveysViewMenuListener(MenuListener menuListener){
        surveysView.addMenuListener(menuListener);
    }

    public void setSelectSurveyViewMenuListener(MenuListener menuListener){
        selectSurveyView.addMenuListener(menuListener);
    }
}