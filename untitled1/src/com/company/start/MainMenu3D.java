package com.company.start;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenu3D extends JFrame {

    private JPanel shapePanel;

    public MainMenu3D() {
        super("Shapes Learning App - Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create and set the layout for the main menu page
        JPanel panel = new JPanel(new BorderLayout());
        getContentPane().add(panel);

        // Create a top panel for the back button and topic
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);
        panel.add(topPanel, BorderLayout.NORTH);

        // Create the back button
        ImageIcon backIcon = new ImageIcon("E:\\untitled1\\media\\back.png");
        JButton backButton = new JButton(backIcon);
        backButton.setOpaque(true);
        backButton.setBorderPainted(false);
        backButton.setBackground(Color.WHITE);
        backButton.addActionListener(new MainMenu3D.BackButtonActionListener());
        topPanel.add(backButton, BorderLayout.WEST);

        // Add the shape categories panel on top
        JPanel topicPanel = new JPanel();
        topicPanel.setBackground(Color.WHITE);
        topicPanel.setPreferredSize(new Dimension(getWidth(), 150));

        JLabel topicLabel = new JLabel("Choose a Shape Category");
        topicLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 80));
        topicLabel.setOpaque(true);
        topicLabel.setBackground(Color.YELLOW); // Background color for the topic label
        topicLabel.setHorizontalAlignment(SwingConstants.CENTER);
        topicPanel.add(topicLabel);
        topPanel.add(topicPanel, BorderLayout.CENTER);


        // Add the shape categories in a parallel card view
        shapePanel = new JPanel(new GridLayout(1, 4, 10, 30));
        shapePanel.setBackground(Color.WHITE);
        panel.add(shapePanel, BorderLayout.CENTER);

        // Add the "Circles" category card
        JPanel cylinderCard = createCategoryCard("CYLINDER", "E:\\untitled1\\media\\cylinder.png",500,500,new Color(245, 205, 95));
        cylinderCard.addMouseListener(new CategoryCardMouseListener(cylinderCard,new Color(245, 205, 95)));
        shapePanel.add(cylinderCard);

        // Add the "Squares" category card
        JPanel cubeCard = createCategoryCard("CUBE", "E:\\untitled1\\media\\cube.png",500,500,new Color(232, 146, 221));
        cubeCard.addMouseListener(new CategoryCardMouseListener(cubeCard,new Color(232, 146, 221)));
        shapePanel.add(cubeCard);

        // Add the "Triangles" category card
        JPanel pyramidCard = createCategoryCard("PYRAMID", "E:\\untitled1\\media\\pyramid.png",500,500,new Color(166, 150, 149));
        pyramidCard.addMouseListener(new CategoryCardMouseListener(pyramidCard,new Color(166, 150, 149)));
        shapePanel.add(pyramidCard);

        // Add the "Rectangles" category card
        JPanel cuboidCard = createCategoryCard("CUBOID", "E:\\untitled1\\media\\cuboid.png",500,500,new Color(12, 114, 8));
        cuboidCard.addMouseListener(new CategoryCardMouseListener(cuboidCard,new Color(12, 114, 8)));
        shapePanel.add(cuboidCard);

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
    private class BackButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            LandingPage landingPage = new LandingPage();
            landingPage.setVisible(true);
            dispose();
        }
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
                case "CYLINDER":
                    Cylinder cylinder = new Cylinder();
                    cylinder.setVisible(true);
                    break;
                case "CUBE":
                    Cube cube = new Cube();
                    cube.setVisible(true);
                    break;
                case "PYRAMID":
                    Pyramid pyramid = new Pyramid();
                    pyramid.setVisible(true);
                    break;
                case "CUBOID":
                    Cuboid cuboid = new Cuboid();
                    cuboid.setVisible(true);
                    break;
            }

            dispose();
        }

    }


}
