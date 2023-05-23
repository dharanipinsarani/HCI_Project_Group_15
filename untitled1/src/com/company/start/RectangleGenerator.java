package com.company.start;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RectangleGenerator extends JFrame {
    private JTextField widthTextField;
    private JTextField heightTextField;
    private JButton generateButton;
    private JButton colorButton;
    private JPanel drawingPanel;
    private Color shapeColor;

    public RectangleGenerator() {
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
        backButton.addActionListener(new RectangleGenerator.BackButtonActionListener());
        topPanel.add(backButton, BorderLayout.WEST);

        // Add the shape categories panel on top
        JPanel topicPanel = new JPanel();
        topicPanel.setBackground(Color.WHITE);
        topicPanel.setPreferredSize(new Dimension(getWidth(), 150));

        JLabel topicLabel = new JLabel("Let's Create a Rectangle");
        topicLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 80));
        topicLabel.setOpaque(true);
        topicLabel.setBackground(Color.YELLOW); // Background color for the topic label
        topicLabel.setHorizontalAlignment(SwingConstants.CENTER);
        topicPanel.add(topicLabel);
        topPanel.add(topicPanel, BorderLayout.CENTER);

        containerPanel.add(topPanel);

        // Create input panel
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel widthLabel = new JLabel("Width(mm):");
        widthLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 50));
        JLabel heightLabel = new JLabel("Height(mm):");
        heightLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 50));
        widthTextField = new JTextField(10);
        widthTextField.setPreferredSize(new Dimension(100,50));
        widthTextField.setFont(widthTextField.getFont().deriveFont(32f).deriveFont(Font.BOLD)); // Increase font size
        heightTextField = new JTextField(10);
        widthTextField.setPreferredSize(new Dimension(100,50));
        widthTextField.setFont(widthTextField.getFont().deriveFont(32f).deriveFont(Font.BOLD)); // Increase font size
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

                // Get the width and height values from the text fields
                String widthText = widthTextField.getText();
                String heightText = heightTextField.getText();

                if (!widthText.isEmpty() && !heightText.isEmpty()) {
                    try {

                        double widthMillimeters = Double.parseDouble(widthText);
                        double heightMillimeters = Double.parseDouble(heightText);

                        // Convert millimeters to pixels
                        double pixelsPerMillimeter = Toolkit.getDefaultToolkit().getScreenResolution() / 25.4;
                        double widthPixels = widthMillimeters * pixelsPerMillimeter;
                        double heightPixels = heightMillimeters * pixelsPerMillimeter;


                        // Calculate the top-left corner coordinates of the rectangle
                        int x = (int) ((getWidth() - widthPixels) / 2);
                        int y = (int) ((getHeight() - heightPixels) / 2);

                        // Set the fill color
                        g.setColor(shapeColor);
                        g.fillRect(x, y, (int) widthPixels, (int) heightPixels);
                    } catch (NumberFormatException ex) {
                        // Handle the case where the width or height values are not valid numbers
                        System.out.println("Invalid width or height value");
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
                Color selectedColor = JColorChooser.showDialog(RectangleGenerator.this, "Choose Color", shapeColor);
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
            Rectangle rectangle = new Rectangle();
            rectangle.setVisible(true);
            dispose();
        }
    }
}
