package view;

import javax.swing.*;
import java.awt.*;

public abstract class FrameView extends JFrame{
    protected int frameWidth = 1000;
    protected int frameHeight = 600;
    private String name;
    private JMenuBar menuBar;
    private JMenu completedSurveys;
    private JMenu completeSurvey;

    public FrameView(String name) throws HeadlessException {
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

        completedSurveys = new JMenu("Wypełnone ankiety");
        completedSurveys.getAccessibleContext().setAccessibleDescription("Przejdź do widoku wypełnionych ankiet");
        menuBar.add(completedSurveys);

        completeSurvey = new JMenu("Wypełnij ankietę");
        completeSurvey.getAccessibleContext().setAccessibleDescription("Przejdź do widoku wypełniania ankiet");
        menuBar.add(completeSurvey);
    }

}