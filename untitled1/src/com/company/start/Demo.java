package com.company.start;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Demo extends JFrame {

    private JButton startButton;

    public Demo() {
        super("Shapes Learning App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create and set the layout for the landing page
        JPanel panel = new JPanel(new BorderLayout());
        getContentPane().add(panel);

        // Add a welcome message and a friendly character
        JLabel welcomeLabel = new JLabel("Welcome to Shapes Learning!");
        welcomeLabel.setFont(new Font("Times New Roman", Font.BOLD, 36));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(welcomeLabel,BorderLayout.NORTH);
        JLabel characterLabel = new JLabel(new ImageIcon("E:\\untitled1\\src\\com\\company\\start\\welcome.jpg"));
        panel.add(characterLabel, BorderLayout.CENTER);

        // Add the "Start" button in the center of the page
        startButton = new JButton("Start Learning");
        startButton.setFont(new Font("Times New Roman", Font.BOLD, 24));
        startButton.setPreferredSize(new Dimension(250, 80));
        startButton.setBackground(new Color(9, 121, 105));
        startButton.setForeground(Color.WHITE);
        panel.add(startButton, BorderLayout.SOUTH);

        // Add an action listener to the "Start" button
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open the landing page
                LandingPage landingPage = new LandingPage();
                dispose();
            }
        });

        // Set the size and visibility of the window
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        // Remove window decorations (title bar, close button, etc.)

    }

    public JButton getStartButton() {
        return startButton;
    }

}
