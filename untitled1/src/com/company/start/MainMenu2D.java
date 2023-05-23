package com.company.start;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenu2D extends JFrame {

    private JPanel shapePanel;

    public MainMenu2D() {
        super("Shapes Learning App - Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create and set the layout for the main menu pages
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
        backButton.addActionListener(new BackButtonActionListener());
        topPanel.add(backButton, BorderLayout.WEST);

        // Add the shape categories panel on top
        JPanel topicPanel = new JPanel();
        topicPanel.setBackground(Color.WHITE);
        topicPanel.setPreferredSize(new Dimension(getWidth(), 150));

       


        // Add the shape categories in a parallel card view
        shapePanel = new JPanel(new GridLayout(1, 4, 10, 30));
        shapePanel.setBackground(Color.WHITE);
        panel.add(shapePanel, BorderLayout.CENTER);

        // Add the "Circles" category card
        JPanel circleCard = createCategoryCard("CIRCLE", "E:\\untitled1\\media\\4.jpg",500,500,new Color(250, 188, 164));
        circleCard.addMouseListener(new CategoryCardMouseListener(circleCard,new Color(250, 188, 164)));
        shapePanel.add(circleCard);

        // Add the "Squares" category card
        JPanel squareCard = createCategoryCard("SQUARE", "E:\\untitled1\\media\\2.png",500,500,new Color(80, 155, 158));
        squareCard.addMouseListener(new CategoryCardMouseListener(squareCard,new Color(80, 155, 158)));
        shapePanel.add(squareCard);

        // Add the "Triangles" category card
        JPanel triangleCard = createCategoryCard("TRIANGLE", "E:\\untitled1\\media\\3.png",500,500,new Color(234, 113, 113));
        triangleCard.addMouseListener(new CategoryCardMouseListener(triangleCard,new Color(234, 113, 113)));
        shapePanel.add(triangleCard);

        // Add the "Rectangles" category card
        JPanel rectangleCard = createCategoryCard("RECTANGLE", "E:\\untitled1\\media\\1.png",500,500,new Color(253, 188, 103));
        rectangleCard.addMouseListener(new CategoryCardMouseListener(rectangleCard,new Color(253, 188, 103)));
        shapePanel.add(rectangleCard);

        // Set the size and visibility of the window
        pack();
        setResizable(false);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Set to fullscreen
        GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(this);
        setVisible(true);
    }

    private JPanel createCategoryCard(String categoryName, String iconFileName, int width, int height, Color color) {
        JPanel cardPanel = new JPanel(new BorderLayout());
        cardPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        cardPanel.setBackground(Color.WHITE);

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
                case "CIRCLE":
                    Circle circlesPage = new Circle();
                    circlesPage.setVisible(true);
                    break;
                case "SQUARE":
                    Square squaresPage = new Square();
                    squaresPage.setVisible(true);
                    break;
                case "TRIANGLE":
                    Triangle trianglesPage = new Triangle();
                    trianglesPage.setVisible(true);
                    break;
                case "RECTANGLE":
                    Rectangle rectanglesPage = new Rectangle();
                    rectanglesPage.setVisible(true);
                    break;
            }
            dispose();
        }

    }

}
