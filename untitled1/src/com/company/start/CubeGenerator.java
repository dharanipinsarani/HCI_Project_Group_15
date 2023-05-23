package com.company.start;

import com.company.start.Cube;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CubeGenerator extends JFrame {
    private JTextField sideLengthTextField;
    private JButton generateButton;
    private JButton colorButton;
    private JPanel drawingPanel;
    private Color shapeColor;

    public CubeGenerator() {
        setTitle("Cube 3D Shape Generator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create a Container panel for input and top panels
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

        /// Add the shape categories panel on top
        JPanel topicPanel = new JPanel();
        topicPanel.setBackground(Color.WHITE);
        topicPanel.setPreferredSize(new Dimension(getWidth(), 150));

        JLabel topicLabel = new JLabel("Let's Create a Cube");
        topicLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 80));
        topicLabel.setOpaque(true);
        topicLabel.setBackground(Color.YELLOW); // Background color for the topic label
        topicLabel.setHorizontalAlignment(SwingConstants.CENTER);
        topicPanel.add(topicLabel);
        topPanel.add(topicPanel, BorderLayout.CENTER);

        containerPanel.add(topPanel);

        // Create input panels
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel sideLengthLabel = new JLabel("Side Length (mm):");
        sideLengthLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 50));
        sideLengthTextField = new JTextField(10);
        sideLengthTextField.setPreferredSize(new Dimension(100, 50));
        sideLengthTextField.setFont(sideLengthTextField.getFont().deriveFont(32f).deriveFont(Font.BOLD)); // Increase font size
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
        inputPanel.add(sideLengthLabel);
        inputPanel.add(sideLengthTextField);
        inputPanel.add(colorButton);
        inputPanel.add(generateButton);
        containerPanel.add(inputPanel);

        add(containerPanel, BorderLayout.NORTH);

        // Create drawing panels
        drawingPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Get the side length value from the text field
                String sideLengthText = sideLengthTextField.getText();

                if (!sideLengthText.isEmpty()) {
                    try {
                        double sideLength = Double.parseDouble(sideLengthText);

                        // Convert the side length from millimeters to pixels based on the screen resolution
                        double pixelsPerMillimeter = Toolkit.getDefaultToolkit().getScreenResolution() / 25.4;
                        int cubeSideLength = (int) (sideLength * pixelsPerMillimeter);

                        // Calculate the top-left corner coordinates of the cube
                        int x = (getWidth() - cubeSideLength) / 2;
                        int y = (getHeight() - cubeSideLength) / 2;

                        // Set the fill color
                        g.setColor(shapeColor);

                        // Draw the cube
                        g.fillRect(x, y, cubeSideLength, cubeSideLength);
                        g.drawRect(x, y, cubeSideLength, cubeSideLength);
                    } catch (NumberFormatException ex) {
                        // Handle the case where the side length value is not a valid number
                        System.out.println("Invalid side length value");
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
                Color selectedColor = JColorChooser.showDialog(CubeGenerator.this, "Choose Color", shapeColor);
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
            Cube cube = new Cube();
            cube.setVisible(true);
        }
    }
}
