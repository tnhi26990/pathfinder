package pathfinder.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.*;

public class WinningMessage extends JFrame
{
    JLabel message = new JLabel("You win! Yayy!");

    public WinningMessage()
    {
        this.setPreferredSize(new Dimension(350, 80));
        this.getContentPane().setBackground(new Color(255, 204, 255));
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.message.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.add(this.message);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }
}

