package com.company.start;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LandingPage extends JFrame {

    private JPanel shapePanel;
    public LandingPage() {

        super("Shapes Learning App ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create and set the layout for the main menu page
        JPanel panel = new JPanel(new BorderLayout());
        getContentPane().add(panel);

        // Add the shape categories panel on top
        JPanel topicPanel = new JPanel();
        topicPanel.setBackground(Color.WHITE);
        topicPanel.setPreferredSize(new Dimension(getWidth(), 150));

        JLabel topicLabel = new JLabel("Let's Create a");
        topicLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 80));
        topicLabel.setOpaque(true);
        topicLabel.setBackground(Color.YELLOW); // Background color for the topic label
        topicLabel.setHorizontalAlignment(SwingConstants.CENTER);
        topicPanel.add(topicLabel);
        panel.add(topicPanel, BorderLayout.NORTH);


        // Add the shape categories in a parallel card view
        shapePanel = new JPanel(new GridLayout(1, 2, 10, 30));
        shapePanel.setBackground(Color.WHITE);
        panel.add(shapePanel, BorderLayout.CENTER);

        // Add the "Circles" category card
        JPanel card2D = createCategoryCard("2D SHAPE", "E:\\untitled1\\media\\2D.png",500,500,new Color(239, 128, 87));
        card2D.addMouseListener(new CategoryCardMouseListener(card2D,new Color(239, 128, 87)));
        shapePanel.add(card2D);

        // Add the "Squares" category card
        JPanel card3D = createCategoryCard("3D OBJECT", "E:\\untitled1\\media\\3D.png",500,500,new Color(113, 93, 252));
        card3D.addMouseListener(new CategoryCardMouseListener(card3D,new Color(113, 93, 252)));
        shapePanel.add(card3D);

        // Set the size and visibility of the window
        pack();
        setResizable(false);
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Set to fullscreen
    }

    private JPanel createCategoryCard(String categoryName, String iconFileName,int width, int height,Color color) {
        JPanel cardPanel = new JPanel(new BorderLayout());
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));

        JLabel categoryLabel = new JLabel(categoryName);
        categoryLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 40));
        categoryLabel.setForeground(Color.WHITE);
        categoryLabel.setOpaque(true);
        categoryLabel.setBackground(color); // Background color for the category label
        categoryLabel.setHorizontalAlignment(SwingConstants.CENTER);
        cardPanel.add(categoryLabel, BorderLayout.NORTH);

        ImageIcon originalIcon = new ImageIcon(iconFileName);
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        JLabel iconLabel = new JLabel(resizedIcon);
        iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
        cardPanel.add(iconLabel, BorderLayout.CENTER);

        return cardPanel;
    }


    private class CategoryCardMouseListener extends MouseAdapter {

        private JPanel cardPanel;
        private Color originalColor;
        private Color hoveredColor;


        public CategoryCardMouseListener(JPanel cardPanel,Color hoveredColor) {
            this.cardPanel = cardPanel;
            this.originalColor = cardPanel.getBackground();
            this.hoveredColor = hoveredColor;
        }

        public void mouseEntered(MouseEvent e) {
            cardPanel.setBackground(hoveredColor);
        }

        public void mouseExited(MouseEvent e) {
            cardPanel.setBackground(originalColor);
        }


        public void mouseClicked(MouseEvent e) {
            JPanel cardPanel = (JPanel) e.getSource();
            String categoryName = ((JLabel) cardPanel.getComponent(0)).getText();

            // Open the corresponding category page
            switch (categoryName) {
                case "2D SHAPE":
                    MainMenu2D mainMenu2D = new MainMenu2D();
                    break;
                case "3D OBJECT":
                    MainMenu3D mainMenu3D = new MainMenu3D();
                    break;
            }
            dispose();
        }
    }

}
