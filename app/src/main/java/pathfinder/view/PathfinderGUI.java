package pathfinder.view;

import pathfinder.Observer;
import pathfinder.controller.GameController;
import pathfinder.model.Grid;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class PathfinderGUI implements Observer, ActionListener {
    private JPanel panel;
    private JLabel attemptsCounter;
    private JFrame frame;
    public GameController controller;
    public int gridSize;
    public Buttons buttons;
    public Grid grid;
    public int prevClickedRow = -1;
    public int prevClickedColumn = -1;

    public PathfinderGUI(Grid grid, GameController controller)
    {
        this.controller = controller;
        
        this.grid = grid;
        this.grid.register(this); 
        this.gridSize = grid.getSize();

        this.buttons = new Buttons(this, this.gridSize);

        this.frame = new JFrame("Pathfinder");

        this.panel = new JPanel();
        this.panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        this.panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        this.attemptsCounter = new JLabel("Attempts: " + String.valueOf(grid.getAttempts()));
        this.attemptsCounter.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.buttons.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        this.buttons.setOpaque(false);

        this.panel.add(buttons);
        this.panel.add(attemptsCounter);

        this.frame.add(panel);
        this.frame.pack();
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {
        JButton button = (JButton) e.getSource();
        int col = (int) button.getClientProperty("col");
        int row = (int) button.getClientProperty("row");

        prevClickedColumn = col;
        prevClickedRow = row;

        this.buttons.setRow(row);
        this.buttons.setColumn(col);
        this.controller.userPressed(row, col);
        
        
    }

    public void update()
    {
        if (this.grid.getSquare() == true)
        {
            this.buttons.turnGreen();
        }
        else
        {
            this.buttons.turnRed();
            
            int delay = 500;
            Timer delayTimer = new Timer(delay, new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    grid.reset();
                    panel.remove(buttons);
                    panel.remove(attemptsCounter);
                    buttons = new Buttons(PathfinderGUI.this, gridSize);
                    attemptsCounter.setText("Attempts: " + String.valueOf(grid.getAttempts()));
                    panel.add(buttons);
                    panel.add(attemptsCounter);
                    panel.revalidate();
                    panel.repaint();
                }
            });

            delayTimer.setRepeats(false); 
            delayTimer.start(); 
        }
        if (this.grid.isFound())
        {
            WinningMessage win = new WinningMessage();
            frame.setVisible(false);
            frame.dispose();
        }

    }

    public void showError()
    {
        ErrorMessage error = new ErrorMessage();
    }
    
}

