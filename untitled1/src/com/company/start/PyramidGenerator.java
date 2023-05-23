package com.company.start;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PyramidGenerator extends JFrame {
    private JTextField baseLengthTextField;
    private JTextField heightTextField;
    private JButton generateButton;
    private JButton colorButton;
    private JPanel drawingPanel;
    private Color shapeColor;

    public PyramidGenerator() {
        setTitle("Pyramid 3D Shape Generator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create a container panel for input and top panels
        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));

        // Create a top panel for the back button and topic
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);

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

        JLabel topicLabel = new JLabel("Let's Create a Pyramid");
        topicLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 80));
        topicLabel.setOpaque(true);
        topicLabel.setBackground(Color.YELLOW); // Background color for the topic label
        topicLabel.setHorizontalAlignment(SwingConstants.CENTER);
        topicPanel.add(topicLabel);
        topPanel.add(topicPanel, BorderLayout.CENTER);

        containerPanel.add(topPanel);

        // Create input panel
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel baseLengthLabel = new JLabel("Base Length (mm):");
        baseLengthLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 50));
        baseLengthTextField = new JTextField(10);
        baseLengthTextField.setPreferredSize(new Dimension(100, 50));
        baseLengthTextField.setFont(baseLengthTextField.getFont().deriveFont(32f).deriveFont(Font.BOLD)); // Increase font size
        JLabel heightLabel = new JLabel("Height (mm):");
        heightLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 50));
        heightTextField = new JTextField(10);
        heightTextField.setPreferredSize(new Dimension(100, 50));
        heightTextField.setFont(heightTextField.getFont().deriveFont(32f).deriveFont(Font.BOLD)); // Increase font size
        ImageIcon generateIcon = new ImageIcon("E:\\untitled1\\media\\generate.png");
        generateButton = new JButton();
        generateButton.setIcon(generateIcon);
        generateButton.setText("Generate");
        generateButton.setBackground(Color.WHITE);
        ImageIcon changeCol = new ImageIcon("E:\\untitled1\\media\\changeCol.png");
        colorButton = new JButton();
        colorButton.setIcon(changeCol);
        colorButton.setText("Change Color");
        colorButton.setBackground(Color.WHITE);
        inputPanel.add(baseLengthLabel);
        inputPanel.add(baseLengthTextField);
        inputPanel.add(heightLabel);
        inputPanel.add(heightTextField);
        inputPanel.add(colorButton);
        inputPanel.add(generateButton);
        containerPanel.add(inputPanel);

        add(containerPanel, BorderLayout.NORTH);

        // Create drawing panel
        drawingPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Get the base length and height values from the text fields
                String baseLengthText = baseLengthTextField.getText();
                String heightText = heightTextField.getText();

                if (!baseLengthText.isEmpty() && !heightText.isEmpty()) {
                    try {
                        double baseLength = Double.parseDouble(baseLengthText);
                        double height = Double.parseDouble(heightText);

                        // Convert the base length and height from millimeters to pixels based on the screen resolution
                        double pixelsPerMillimeter = Toolkit.getDefaultToolkit().getScreenResolution() / 25.4;
                        int baseWidth = (int) (baseLength * pixelsPerMillimeter);
                        int pyramidHeight = (int) (height * pixelsPerMillimeter);

                        // Calculate the center coordinates of the base square
                        int centerX = getWidth() / 2;
                        int centerY = getHeight() / 2;

                        // Calculate the top-left corner coordinates of the base square
                        int x = centerX - (baseWidth / 2);
                        int y = centerY - (baseWidth / 2);

                        // Set the fill color
                        g.setColor(shapeColor);

                        // Draw the base square
                        g.fillRect(x, y, baseWidth, baseWidth);

                        // Calculate the top vertex coordinates of the pyramid
                        int topX = centerX;
                        int topY = y - pyramidHeight;

                        // Draw the triangular faces of the pyramid
                        int[] xPoints = {x, x + (baseWidth / 2), x + baseWidth};
                        int[] yPoints = {y, y - pyramidHeight, y};
                        g.fillPolygon(xPoints, yPoints, 3);

                        int[] xPoints1 = {x + baseWidth, x + (baseWidth / 2), topX};
                        int[] yPoints1 = {y, y - pyramidHeight, topY};
                        g.fillPolygon(xPoints1, yPoints1, 3);

                        int[] xPoints2 = {x, x + (baseWidth / 2), topX};
                        int[] yPoints2 = {y, y - pyramidHeight, topY};
                        g.fillPolygon(xPoints2, yPoints2, 3);

                        int[] xPoints3 = {x, x + baseWidth, topX};
                        int[] yPoints3 = {y, y, topY};
                        g.fillPolygon(xPoints3, yPoints3, 3);
                    } catch (NumberFormatException ex) {
                        // Handle the case where the base length or height value is not a valid number
                        System.out.println("Invalid base length or height value");
                    }
                }
            }
        };
        add(drawingPanel, BorderLayout.CENTER);

        // Attach action listener to the generate button
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawingPanel.repaint();
            }
        });

        // Attach action listener to the color button
        colorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color selectedColor = JColorChooser.showDialog(PyramidGenerator.this, "Choose Color", shapeColor);
                if (selectedColor != null) {
                    shapeColor = selectedColor;
                }
            }
        });

        shapeColor = Color.RED; // Initial color

        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Set to fullscreen
        setVisible(true);
    }

    public class BackButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Pyramid pyramid = new Pyramid();
            pyramid.setVisible(true);
            dispose();
        }
    }
}
