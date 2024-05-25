package pathfinder.view;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import pathfinder.ControllerInterface;
import pathfinder.model.ScoresLoader;


public class LevelSelectionGUI implements ActionListener {
    private JFrame mainFrame;
    private JPanel mainPanel;
    private JLabel welcomeMessage;
    private JLabel wordLabel;
    private JButton []buttons = new JButton[3];
    private String []levels = {"Easy", "Medium", "Hard"};
    private JLabel[] scoreList = new JLabel[3];
    private ControllerInterface controller;

    public LevelSelectionGUI(ControllerInterface controller) {
        this.controller = controller;

        mainFrame = new JFrame("PathFinder");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        welcomeMessage = new JLabel("Welcome to PathFinder!");
        welcomeMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcomeMessage.setFont(new Font("Verdana", Font.PLAIN, 25));
        welcomeMessage.setForeground(Color.BLUE);

        wordLabel = new JLabel("Choose a level of difficulty:");
        wordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        wordLabel.setFont(new Font("Verdana", Font.BOLD, 16));

        mainPanel = new JPanel();
        mainPanel.setBackground(new Color(153, 255, 153));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setPreferredSize(new Dimension(450, 325));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(50, 20, 20, 20));
        mainPanel.add(welcomeMessage);
        wordLabel.setBorder(BorderFactory.createEmptyBorder(30, 10, 20, 10));
        mainPanel.add(wordLabel);

        for (int i = 0; i < levels.length; i ++)
        {
            JLabel score = new JLabel();
            score.setText(levels[i] + " Best Score: " + ScoresLoader.getAttempts(levels[i]));
            score.setAlignmentX(Component.CENTER_ALIGNMENT);
            scoreList[i] = score;
            mainPanel.add(score);

            this.buttons[i] = new JButton(levels[i]);
            this.buttons[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            this.buttons[i].addActionListener(this);
            mainPanel.add(this.buttons[i]);
            mainPanel.add(Box.createVerticalStrut(20));            
        }

           
        
        mainFrame.add(mainPanel);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    public void redoScores()
    {
        for (int i = 0; i < scoreList.length; i ++)
        {
            scoreList[i].setText(levels[i] + " Best Score: " + ScoresLoader.getAttempts(levels[i]));
        }
    }

    @Override
    public void actionPerformed(ActionEvent event)
    {
        JButton button = (JButton) event.getSource();
        String text = button.getText();
        this.controller.userPressed(text);
    }
}

