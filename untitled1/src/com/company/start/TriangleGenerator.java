package com.company.start;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TriangleGenerator extends JFrame {
    private JTextField baseTextField;
    private JTextField heightTextField;
    private JButton generateButton;
    private JButton colorButton;
    private JPanel drawingPanel;
    private Color shapeColor;

    public TriangleGenerator() {
        setTitle("Shape Generator");
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
        backButton.addActionListener(new TriangleGenerator.BackButtonActionListener());
        topPanel.add(backButton, BorderLayout.WEST);

        // Add the shape categories panel on top
        JPanel topicPanel = new JPanel();
        topicPanel.setBackground(Color.WHITE);
        topicPanel.setPreferredSize(new Dimension(getWidth(), 150));

        JLabel topicLabel = new JLabel("Let's Create a Triangle");
        topicLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 80));
        topicLabel.setOpaque(true);
        topicLabel.setBackground(Color.YELLOW); // Background color for the topic label
        topicLabel.setHorizontalAlignment(SwingConstants.CENTER);
        topicPanel.add(topicLabel);
        topPanel.add(topicPanel, BorderLayout.CENTER);

        containerPanel.add(topPanel);

        // Create input panel
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel baseLabel = new JLabel("Base(mm):");
        baseLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 50));
        JLabel heightLabel = new JLabel("Height(mm):");
        heightLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 50));
        baseTextField = new JTextField(10);
        baseTextField.setPreferredSize(new Dimension(100,50));
        heightTextField = new JTextField(10);
        heightTextField.setPreferredSize(new Dimension(100,50));
        baseTextField.setFont(baseTextField.getFont().deriveFont(32f).deriveFont(Font.BOLD)); // Increase font size
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
        inputPanel.add(baseLabel);
        inputPanel.add(baseTextField);
        inputPanel.add(heightLabel);
        inputPanel.add(heightTextField);
        inputPanel.add(colorButton);
        inputPanel.add(generateButton);
        inputPanel.setBackground(Color.WHITE);
        containerPanel.add(inputPanel);

        add(containerPanel, BorderLayout.NORTH);

        // Create drawing panel
        drawingPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Get the base and height values from the text fields
                String baseText = baseTextField.getText();
                String heightText = heightTextField.getText();

                if (!baseText.isEmpty() && !heightText.isEmpty()) {
                    try {
                        double baseMillimeters = Double.parseDouble(baseText);
                        double heightMillimeters = Double.parseDouble(heightText);

                        // Convert millimeters to pixels
                        double pixelsPerMillimeter = Toolkit.getDefaultToolkit().getScreenResolution() / 25.4;
                        double basePixels = baseMillimeters * pixelsPerMillimeter;
                        double heightPixels = heightMillimeters * pixelsPerMillimeter;

                        // Calculate the top-left corner coordinates of the triangle
                        int x = (int) ((getWidth() - basePixels) / 2);
                        int y = (int) ((getHeight() - heightPixels) / 2);


                        // Set the fill color
                        g.setColor(shapeColor);

                        int[] xPoints = {x, x + (int) basePixels, x + ((int) basePixels / 2)};
                        int[] yPoints = {y + (int) heightPixels, y + (int) heightPixels, y};


                        // Draw the triangle
                        g.fillPolygon(xPoints, yPoints, 3);
                    } catch (NumberFormatException ex) {
                        // Handle the case where the base or height values are not valid numbers
                        System.out.println("Invalid base or height value");
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
                Color selectedColor = JColorChooser.showDialog(TriangleGenerator.this, "Choose Color", shapeColor);
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
        setVisible(true);;
    }

    public class BackButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Triangle triangle = new Triangle();
            triangle.setVisible(true);
            dispose();
        }
    }

}
