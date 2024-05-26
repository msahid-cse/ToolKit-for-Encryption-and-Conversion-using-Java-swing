package data_communiation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BitStuffingDeStuffing extends JFrame implements ActionListener {

    private JTextField inputField;
    private JLabel outputLabel;
    private JButton processButton;
    private JComboBox<String> processOptions;

    public BitStuffingDeStuffing() {

        // Frame setup
        setTitle("Bit Stuffing and De-Stuffing");
        getContentPane().setBackground(new Color(136, 196, 245));
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setLocation(200, 60);
        setLayout(null); // Using absolute layout
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Header
        JLabel headerLabel = new JLabel("Bit Stuffing and De-Stuffing");
        headerLabel.setFont(new Font("Serif", Font.BOLD, 40));
        headerLabel.setForeground(new Color(0, 0, 0));
        headerLabel.setBounds(250, 30, 700, 100);
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
        processOptions = new JComboBox<>(new String[]{"Bit Stuffing", "Bit De-Stuffing"});
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
                JOptionPane.showMessageDialog(this, "Please enter some Bit to process.", "Empty Input", JOptionPane.WARNING_MESSAGE);
            } else {
                if ("Bit Stuffing".equals(selectedOption)) {
                    bitStuffing(input);
                } else if ("Bit De-Stuffing".equals(selectedOption)) {
                    bitDeStuffing(input);
                }
            }
        }
    }

    private void bitStuffing(String input) {
        StringBuilder stuffed = new StringBuilder();
        int count = 0;

        for (char c : input.toCharArray()) {
            if (c == '1') {
                count++;
                stuffed.append(c);
                if (count == 5) {
                    stuffed.append('0');
                    count = 0;
                }
            } else {
                stuffed.append(c);
                count = 0;
            }
        }

        outputLabel.setText("Stuffed: " + stuffed.toString());
    }

    private void bitDeStuffing(String input) {
        StringBuilder deStuffed = new StringBuilder();
        int count = 0;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '1') {
                count++;
                deStuffed.append(c);
                if (count == 5 && i + 1 < input.length() && input.charAt(i + 1) == '0') {
                    i++; // Skip the '0'
                    count = 0;
                }
            } else {
                deStuffed.append(c);
                count = 0;
            }
        }

        outputLabel.setText("De-Stuffed: " + deStuffed.toString());
    }

    public static void main(String[] args) {
        new BitStuffingDeStuffing();
    }
}
