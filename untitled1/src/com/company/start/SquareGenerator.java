package com.company.start;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SquareGenerator extends JFrame {
    private JTextField sizeTextField;
    private JButton generateButton;
    private JButton colorButton;
    private JPanel drawingPanel;
    private Color shapeColor;

    public SquareGenerator() {
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
        backButton.addActionListener(new SquareGenerator.BackButtonActionListener());
        topPanel.add(backButton, BorderLayout.WEST);

        // Add the shape categories panel on top
        JPanel topicPanel = new JPanel();
        topicPanel.setBackground(Color.WHITE);
        topicPanel.setPreferredSize(new Dimension(getWidth(), 150));

        JLabel topicLabel = new JLabel("Let's Create a Square");
        topicLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 80));
        topicLabel.setOpaque(true);
        topicLabel.setBackground(Color.YELLOW); // Background color for the topic label
        topicLabel.setHorizontalAlignment(SwingConstants.CENTER);
        topicPanel.add(topicLabel);
        topPanel.add(topicPanel, BorderLayout.CENTER);

        containerPanel.add(topPanel);

        // Create input panel
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel sizeLabel = new JLabel("Length(mm):");
        sizeLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 50));
        sizeTextField = new JTextField(10);
        sizeTextField.setPreferredSize(new Dimension(100,50));
        sizeTextField.setFont(sizeTextField.getFont().deriveFont(32f).deriveFont(Font.BOLD)); // Increase font size
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
        inputPanel.add(sizeLabel);
        inputPanel.add(sizeTextField);
        inputPanel.add(colorButton);
        inputPanel.add(generateButton);
        containerPanel.add(inputPanel);

        add(containerPanel, BorderLayout.NORTH);

        // Create drawing panel
        drawingPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Get the size value from the text field
                String sizeText = sizeTextField.getText();

                if (!sizeText.isEmpty()) {
                    try {
                        int size = Integer.parseInt(sizeText);

                        // Calculate the top-left corner coordinates of the square
                        int x = (getWidth() - size) / 2;
                        int y = (getHeight() - size) / 2;

                        // Set the fill color
                        g.setColor(shapeColor);
                        g.fillRect(x, y, size, size);
                    } catch (NumberFormatException ex) {
                        // Handle the case where the size value is not a valid number
                        System.out.println("Invalid size value: " + sizeText);
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
                Color selectedColor = JColorChooser.showDialog(SquareGenerator.this, "Choose Color", shapeColor);
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
            Square square = new Square();
            square.setVisible(true);
            dispose();
        }
    }

}
