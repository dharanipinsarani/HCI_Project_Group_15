package com.company.start;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Circle extends JFrame {

    private JPanel shapePanel;
    private CardLayout cardLayout;
    private int cardIndex = 0;
    private JButton previousButton;
    private JButton nextButton;

    public Circle() {
        super("Shapes Learning App - Circle Descriptions");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create and set the layout for the shape description view
        JPanel panel = new JPanel(new BorderLayout());
        getContentPane().add(panel);

        // Create a top panel for the title
        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.WHITE);
        panel.add(topPanel, BorderLayout.NORTH);

        // Create the back button
        ImageIcon backIcon = new ImageIcon("E:\\untitled1\\media\\back.png");
        JButton backButton = new JButton(backIcon);
        backButton.setOpaque(true);
        backButton.setBorderPainted(false);
        backButton.setBackground(Color.WHITE);
        backButton.addActionListener(new Circle.BackButtonActionListener());
        topPanel.add(backButton);

        // Add the title label to the top panel
        JLabel titleLabel = new JLabel("Let's Learn About Circle");
        titleLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 40));
        titleLabel.setOpaque(true);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBackground(Color.WHITE);
        topPanel.add(titleLabel);
        topPanel.add(titleLabel, BorderLayout.CENTER);;

        // Create the shape cards panel with CardLayout
        shapePanel = new JPanel();
        cardLayout = new CardLayout();
        shapePanel.setLayout(cardLayout);
        panel.add(shapePanel, BorderLayout.CENTER);

        // Add the description cards for the circle
        JPanel circleCard1 = createDescriptionCard("A circle is a closed 2D shape formed by tracing a point that moves in a plane such that its distance from a given point is constant.", "E:\\untitled1\\media\\learnCircle.png");
        shapePanel.add(circleCard1);

        JPanel circleCard2 = createDescriptionCard("There are many objects we have seen in the real world that are circular in shape. Some of the examples are: Ring, CD/Disc, Bangles,Coins,Wheels,Button,Dartboard,Hula hoop, etc. We can observe many such examples in our day to day life.", "E:\\untitled1\\media\\circle examples.png");
        shapePanel.add(circleCard2);

        // Create the navigation buttons panel
        JPanel navigationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(navigationPanel, BorderLayout.SOUTH);

        // Create the previous button
        ImageIcon prevIcon = new ImageIcon("E:\\untitled1\\media\\prev.png");
        previousButton = new JButton(prevIcon);
        previousButton.setEnabled(false);
        previousButton.setFont(new Font("Arial", Font.BOLD, 16));
        previousButton.setForeground(Color.WHITE);
        previousButton.setBackground(new Color(51, 122, 183));
        previousButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        previousButton.addActionListener(new PreviousButtonActionListener());
        previousButton.addActionListener(new PreviousButtonActionListener());
        navigationPanel.add(previousButton);

        // Create the next button
        ImageIcon nextIcon = new ImageIcon("E:\\untitled1\\media\\next.png");
        nextButton = new JButton(nextIcon);
        nextButton.addActionListener(new NextButtonActionListener());
        nextButton.setFont(new Font("Segoe UI Black", Font.BOLD, 25));
        nextButton.setForeground(Color.WHITE);
        nextButton.setBackground(new Color(51, 122, 183));
        nextButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        nextButton.addActionListener(new NextButtonActionListener());
        navigationPanel.add(nextButton);

        // Set the size and visibility of the window
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Set to fullscreen
        setVisible(true);
    }
    private class BackButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            MainMenu2D mainMenu2D = new MainMenu2D();
            mainMenu2D.setVisible(true);
            dispose();
        }
    }
    private JPanel createDescriptionCard(String description, String imageFileName) {
        JPanel cardPanel = new JPanel(new BorderLayout());
        cardPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));

        JPanel contentPanel = new JPanel(new BorderLayout());

        JLabel imageLabel = new JLabel(new ImageIcon(imageFileName));
        imageLabel.setBorder(BorderFactory.createEmptyBorder(30, 5, 10, 0));
        imageLabel.setHorizontalAlignment(SwingConstants.LEFT);
        imageLabel.setVerticalAlignment(SwingConstants.TOP);

        JTextPane descriptionPane = new JTextPane();
        descriptionPane.setContentType("text/html");
        descriptionPane.setText("<html><body style='font-family: Arial; font-size: 30px;'>" +
                "<div style='text-align: justify; text-indent: 30px;d isplay: block;margin-top: 200;margin-left: 50;margin-right: 70;'>" + description + "</div></body></html>");
        descriptionPane.setEditable(false);
        descriptionPane.setBackground(cardPanel.getBackground());

        contentPanel.add(imageLabel, BorderLayout.WEST);
        contentPanel.add(descriptionPane, BorderLayout.CENTER);

        cardPanel.add(contentPanel, BorderLayout.CENTER);

        return cardPanel;
    }

    private class PreviousButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (cardIndex > 0) {
                cardIndex--;
                cardLayout.previous(shapePanel);
                updateButtonStates();
            }
        }
    }


    private class NextButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (cardIndex < shapePanel.getComponentCount() - 1) {
                cardIndex++;
                cardLayout.next(shapePanel);
                updateButtonStates();

                if (cardIndex == shapePanel.getComponentCount() - 1) {
                    nextButton.setText("Let's Create a Circle");
                    nextButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String buttonText = nextButton.getText();
                            if (buttonText.equals("Let's Create a Circle")) {
                                CircleGenerator circleGenerator = new CircleGenerator();
                                circleGenerator.setVisible(true);
                                dispose();
                            }
                        }
                    });
                }
            }

        }
    }

    private void updateButtonStates() {
        previousButton.setEnabled(cardIndex > 0);
        nextButton.setEnabled(true);
        nextButton.setText("");
    }

}
