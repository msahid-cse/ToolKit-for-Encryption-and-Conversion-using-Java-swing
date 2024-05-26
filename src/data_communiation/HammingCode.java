package data_communiation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HammingCode extends JFrame implements ActionListener {

    private JTextField inputField;
    private JLabel outputLabel;
    private JButton evenParityButton;
    private JButton oddParityButton;

    public HammingCode() {
        // Frame setup
        setTitle("Hamming Code");
        getContentPane().setBackground(new Color(136, 196, 245));
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setLocation(200, 60);
        setLayout(null); // Using absolute layout
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Header
        JLabel headerLabel = new JLabel("Hamming Code");
        headerLabel.setFont(new Font("Serif", Font.BOLD, 40));
        headerLabel.setForeground(new Color(0, 0, 0));
        headerLabel.setBounds(350, 30, 700, 100);
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

        // Buttons
        evenParityButton = new JButton("Even Parity");
        evenParityButton.setForeground(new Color(255, 255, 255));
        evenParityButton.setBackground(new Color(1, 55, 125));
        evenParityButton.addActionListener(this);
        evenParityButton.setBounds(300, 250, 150, 50);
        add(evenParityButton);

        oddParityButton = new JButton("Odd Parity");
        oddParityButton.addActionListener(this);
        oddParityButton.setBackground(new Color(1, 55, 125));
        oddParityButton.setForeground(new Color(255, 255, 255));
        oddParityButton.setBounds(600, 250, 150, 50);
        add(oddParityButton);

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
                new welcomePage();
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

        // Background image
        JLabel backgroundImageLabel = new JLabel(new ImageIcon("IMAGES/image.jpg"));
        backgroundImageLabel.setBounds(0, 0, 1000, 600);
        add(backgroundImageLabel);
        // Set the background image as the last component so it does not cover other components
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String input = inputField.getText();

        if (input.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter some bit to process.", "Empty Input", JOptionPane.WARNING_MESSAGE);
        } else {
            int initial = e.getSource() == evenParityButton ? 0 : 1;
            solve(input, initial);
        }
    }

    private void solve(String input, int initial) {
        int p_n = 0;
        int c_l;
        int j = 0;
        int k = 0;
        int i = 0;

        while (input.length() > Math.pow(2, i) - (i + 1)) {
            p_n++;
            i++;
        }

        c_l = p_n + input.length();

        int[] code = new int[c_l];

        for (i = 0; i < c_l; i++) {
            if (i == Math.pow(2, k) - 1) {
                code[i] = 0;
                k++;
            } else {
                code[i] = Character.getNumericValue(input.charAt(j));
                j++;
            }
        }

        for (i = 0; i < p_n; i++) {
            int position = (int) Math.pow(2, i);
            int value = hamCalc(position, c_l, code, initial);
            code[position - 1] = value;
        }

        StringBuilder result = new StringBuilder("The Output Code is: ");
        for (int value : code) {
            result.append(value).append(" ");
        }

        outputLabel.setText(result.toString().trim());
    }

    private int hamCalc(int position, int c_l, int[] code, int initial) {
        int count = 0;
        int i = position - 1;

        while (i < c_l) {
            for (int j = i; j < i + position; j++) {
                if (j < c_l && code[j] == 1) {
                    count++;
                }
            }
            i += 2 * position;
        }

        if (initial == 0) {
            return count % 2 == 0 ? 0 : 1;
        } else {
            return count % 2 == 0 ? 1 : 0;
        }
    }

    public static void main(String[] args) {
        new HammingCode();
    }
}
