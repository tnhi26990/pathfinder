package pathfinder.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.*;

public class ErrorMessage extends JFrame
{
    JLabel message = new JLabel("Error: Square clicked is not adjacent to current position");

    public ErrorMessage()
    {
        this.setPreferredSize(new Dimension(350, 80));
        this.getContentPane().setBackground(new Color(255, 200, 200));
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.message.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.add(this.message);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}

