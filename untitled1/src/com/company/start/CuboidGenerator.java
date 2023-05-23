package com.company.start;

import com.company.start.Cuboid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CuboidGenerator extends JFrame {
    private JTextField lengthTextField;
    private JTextField widthTextField;
    private JTextField heightTextField;
    private JButton generateButton;
    private JButton colorButton;
    private JPanel drawingPanel;
    private Color shapeColor;

    public CuboidGenerator() {
        setTitle("Cuboid 3D Shape Generator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create a container panel for input and top panels
        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));

        // Create a top panel for the back button and topics
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

        JLabel topicLabel = new JLabel("Let's Create a Cuboid");
        topicLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 80));
        topicLabel.setOpaque(true);
        topicLabel.setBackground(Color.YELLOW); // Background color for the topic label
        topicLabel.setHorizontalAlignment(SwingConstants.CENTER);
        topicPanel.add(topicLabel);
        topPanel.add(topicPanel, BorderLayout.CENTER);

        containerPanel.add(topPanel);

        // Create input panel
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel lengthLabel = new JLabel("Length (mm):");
        lengthLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 30));
        lengthTextField = new JTextField(10);
        lengthTextField.setPreferredSize(new Dimension(100, 30));
        lengthTextField.setFont(lengthTextField.getFont().deriveFont(16f).deriveFont(Font.BOLD)); // Increase font size
        JLabel widthLabel = new JLabel("Width (mm):");
        widthLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 30));
        widthTextField = new JTextField(10);
        widthTextField.setPreferredSize(new Dimension(100, 50));
        widthTextField.setFont(widthTextField.getFont().deriveFont(16f).deriveFont(Font.BOLD)); // Increase font size
        JLabel heightLabel = new JLabel("Height (mm):");
        heightLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 30));
        heightTextField = new JTextField(10);
        heightTextField.setPreferredSize(new Dimension(100, 30));
        heightTextField.setFont(heightTextField.getFont().deriveFont(16f).deriveFont(Font.BOLD)); // Increase font size
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
        inputPanel.add(lengthLabel);
        inputPanel.add(lengthTextField);
        inputPanel.add(widthLabel);
        inputPanel.add(widthTextField);
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

                // Get the length, width, and height values from the text fields
                String lengthText = lengthTextField.getText();
                String widthText = widthTextField.getText();
                String heightText = heightTextField.getText();

                if (!lengthText.isEmpty() && !widthText.isEmpty() && !heightText.isEmpty()) {
                    try {
                        double length = Double.parseDouble(lengthText);
                        double width = Double.parseDouble(widthText);
                        double height = Double.parseDouble(heightText);

                        // Convert the length, width, and height from millimeters to pixels based on the screen resolution
                        double pixelsPerMillimeter = Toolkit.getDefaultToolkit().getScreenResolution() / 25.4;
                        int cuboidLength = (int) (length * pixelsPerMillimeter);
                        int cuboidWidth = (int) (width * pixelsPerMillimeter);
                        int cuboidHeight = (int) (height * pixelsPerMillimeter);

                        // Calculate the top-left corner coordinates of the cuboid
                        int x = (getWidth() - cuboidLength) / 2;
                        int y = (getHeight() - cuboidHeight) / 2;

                        // Set the fill color
                        g.setColor(shapeColor);

                        // Draw the cuboid
                        g.fillRect(x, y, cuboidLength, cuboidHeight);
                        g.drawRect(x, y, cuboidLength, cuboidHeight);
                    } catch (NumberFormatException ex) {
                        // Handle the case where the length, width, or height value is not a valid number
                        System.out.println("Invalid length, width, or height value");
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
                Color selectedColor = JColorChooser.showDialog(CuboidGenerator.this, "Choose Color", shapeColor);
                if (selectedColor != null) {
                    shapeColor = selectedColor;
                }
            }
        });

        shapeColor = Color.RED; // Initial colors

        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Set to fullscreen
        setVisible(true);
    }

    public class BackButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Cuboid cuboid = new Cuboid();
            cuboid.setVisible(true);
            dispose();
        }
    }
}
