package view.userpanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class SurveysView extends view.FrameView{

    private JTable surveyTable;
    private JScrollPane scrollPane;
    private DefaultTableModel model;

    private int padding = 10;


    public SurveysView(String name) throws HeadlessException {
        super(name);

        this.createMenu();

        surveyTable = new JTable();
        model = new DefaultTableModel(){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        model.addColumn("LP");
        model.addColumn("Ankieta");
        model.addColumn("Data wypełnienia");
        surveyTable = new JTable(model);
        scrollPane = new JScrollPane(surveyTable);
        scrollPane.setLocation(padding, padding);
        scrollPane.setSize(frameWidth-(padding*2), frameHeight-70);
        this.add(scrollPane);
    }

    public void addColumnToTable(Object[] row) {
        //String[] columnRow = {"1", "Czy podoba Ci się aplikacja", "01-01-2019"};
        row[0] = String.valueOf(model.getRowCount()+1);
        model.addRow(row);
    }
}