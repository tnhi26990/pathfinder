package pathfinder.view;

import java.awt.GridLayout;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.*;

public class Buttons extends JPanel {
    private JButton[][] buttonsArray;
    private int col;
    private int row;

    public Buttons(ActionListener listener, int size)
    {
        this.buttonsArray = new JButton[size][size];
        this.setLayout(new GridLayout(size, size, 0, 0));
        this.setPreferredSize(new Dimension(size*75, size*75));

        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                buttonsArray[i][j] = new JButton();
                buttonsArray[i][j].setPreferredSize(new Dimension(75,75));
                buttonsArray[i][j].putClientProperty("col", j);
                buttonsArray[i][j].putClientProperty("row", i);
                buttonsArray[i][j].addActionListener(listener);
                this.add(buttonsArray[i][j]);
            }
        }

        buttonsArray[0][size - 1].setText("Finish");
        buttonsArray[size - 1][0].setText("Start");
                
    }

    public void setRow(int row)
    {
        this.row = row;
    }

    public void setColumn(int col)
    {
        this.col = col;
    }

    public void turnGreen()
    {
        buttonsArray[this.row][this.col].setOpaque(true);
        buttonsArray[this.row][this.col].setContentAreaFilled(true);
        buttonsArray[this.row][this.col].setBorderPainted(false);
        buttonsArray[this.row][this.col].setFocusPainted(false);
        buttonsArray[this.row][this.col].setText("");
        buttonsArray[this.row][this.col].setBackground(Color.GREEN);
    }

    public void turnRed()
    {
        buttonsArray[this.row][this.col].setOpaque(true);
        buttonsArray[this.row][this.col].setContentAreaFilled(true);
        buttonsArray[this.row][this.col].setBorderPainted(false);
        buttonsArray[this.row][this.col].setFocusPainted(false);
        buttonsArray[this.row][this.col].setText("");
        buttonsArray[this.row][this.col].setBackground(Color.RED);
    }
}

