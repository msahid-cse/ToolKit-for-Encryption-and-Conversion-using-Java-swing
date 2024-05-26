package data_communiation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CharStuffingDeStuffing extends JFrame implements ActionListener {

    private JTextField inputField;
    private JLabel outputLabel;
    private JButton processButton;
    private JComboBox<String> processOptions;

    private static final char ESC = 'E'; // Escape character
    private static final char FLAG = 'F'; // Flag character

    public CharStuffingDeStuffing() {

        // Frame setup
        setTitle("Character Stuffing and De-Stuffing");
        getContentPane().setBackground(new Color(136, 196, 245));
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setLocation(200, 60);
        setLayout(null); // Using absolute layout
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Header
        JLabel headerLabel = new JLabel("Character Stuffing and De-Stuffing");
        headerLabel.setFont(new Font("Serif", Font.BOLD, 40));
        headerLabel.setForeground(new Color(0, 0, 0));
        headerLabel.setBounds(200, 30, 700, 100);
        add(headerLabel);

        // Input section
        JLabel inputLabel = new JLabel("Input:");
        inputLabel.setFont(new Font("Serif", Font.BOLD, 24));
        inputLabel.setForeground(new Color(0, 0, 0));
        inputLabel.setBounds(200, 180, 150, 50);
        add(inputLabel);

        inputField = new JTextField();
        inputField.setForeground(Color.DARK_GRAY);
        inputField.setBounds(300, 180, 450, 50);
        inputField.setFont(new Font("Serif", Font.BOLD, 20));
        add(inputField);

        // Dropdown menu
        processOptions = new JComboBox<>(new String[]{"Character Stuffing", "Character De-Stuffing"});
        processOptions.setBounds(300, 250, 200, 50);
        processOptions.setFont(new Font("Serif", Font.BOLD, 20));
        add(processOptions);

        // Process button
        processButton = new JButton("Process");
        processButton.setForeground(new Color(255, 255, 255));
        processButton.setBackground(new Color(1, 55, 125));
        processButton.addActionListener(this);
        processButton.setBounds(550, 250, 150, 50);
        processButton.setFont(new Font("Serif", Font.BOLD, 20));
        add(processButton);

        // Load the background image
        ImageIcon b_backgroundImage = new ImageIcon("IMAGES/Back.png");
        // Create the button and set the background image as its icon
        JButton Back = new JButton("< Back", b_backgroundImage);
        Back.setFont(new Font("Arial", Font.BOLD, 20));
        Back.setBounds(0, 0, 85, 25);
        Back.setBackground(new Color(159, 126, 219));
        Back.setForeground(Color.white);
        Back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Perform action on button click
                new StuffingDeStuffing();
                setVisible(false);
            }
        });
        add(Back);
                //color button background
       ImageIcon color_background = new ImageIcon("IMAGES/color.png");
        //color button
        JButton c = new JButton("<BG color>",color_background);
        c.setBounds(900, 0, 100, 25);
        c.setBackground(new Color(159,126	,219));
        c.setForeground(Color.white);
        c.setFont(new Font("Arial", Font.BOLD, 12));

        c.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Color cc = JColorChooser.showDialog(null, "choose a color", Color.green);
                getContentPane().setBackground(cc);
            }
        });
       add(c);

        // Output
        outputLabel = new JLabel();
        outputLabel.setBounds(225, 350, 780, 50);
        outputLabel.setBackground(new Color(255, 255, 255));
        outputLabel.setForeground(Color.yellow);
        outputLabel.setFont(new Font("Serif", Font.BOLD, 25));
        add(outputLabel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String input = inputField.getText();
        String selectedOption = (String) processOptions.getSelectedItem();

        if (e.getSource() == processButton) {
            if (input.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter some Character to process.", "Empty Input", JOptionPane.WARNING_MESSAGE);
            } else {
                if ("Character Stuffing".equals(selectedOption)) {
                    charStuffed(input);
                } else if ("Character De-Stuffing".equals(selectedOption)) {
                    charDeStuffed(input);
                }
            }
        }
    }

    private void charStuffed(String input) {
        StringBuilder stuffed = new StringBuilder();
        stuffed.append(FLAG); // Start of frame

        // Stuff data
        for (char c : input.toCharArray()) {
            if (c == FLAG || c == ESC) {
                stuffed.append(ESC); // Escape
            }
            stuffed.append(c);
        }

        stuffed.append(FLAG); // End of frame
        outputLabel.setText("Stuffed: " + stuffed.toString());
    }

    private void charDeStuffed(String input) {
        StringBuilder deStuffed = new StringBuilder();

        // Skip start and end flags
        for (int i = 1; i < input.length() - 1; i++) {
            char c = input.charAt(i);
            if (c == ESC) {
                // If ESC character, check next character
                char nextChar = input.charAt(++i);
                if (nextChar == ESC || nextChar == FLAG) {
                    // Escaped character, include it
                    deStuffed.append(nextChar);
                } else {
                    // Not escaped, include both ESC and nextChar
                    deStuffed.append(ESC).append(nextChar);
                }
            } else {
                // Regular character, include it
                deStuffed.append(c);
            }
        }

        outputLabel.setText("De-Stuffed: " + deStuffed.toString());
    }

    public static void main(String[] args) {
        new CharStuffingDeStuffing();
    }
}
