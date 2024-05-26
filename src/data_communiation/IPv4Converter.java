package data_communiation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IPv4Converter extends JFrame implements ActionListener {

    private JTextField ipInputField;
    private JLabel ipOutputLabel;
    private JButton convertButton;
    private JComboBox<String> conversionOptions;

    public IPv4Converter() {

        // Frame setup
        setTitle("IPv4 Implementation");
        getContentPane().setBackground(new Color(136, 196, 245));
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setLocation(200, 60);
        setLayout(null); // Using absolute layout
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Header
        JLabel headerLabel = new JLabel("IPv4 Implementation");
        headerLabel.setFont(new Font("Serif", Font.BOLD, 40));
        headerLabel.setForeground(new Color(0, 0, 0));
        headerLabel.setBounds(350, 30, 700, 100);
        add(headerLabel);

        // Input section
        JLabel inputLabel = new JLabel("IP Input:");
        inputLabel.setFont(new Font("Serif", Font.BOLD, 24));
        inputLabel.setForeground(new Color(0, 0, 0));
        inputLabel.setBounds(200, 180, 150, 50);
        add(inputLabel);

        ipInputField = new JTextField();
        ipInputField.setForeground(Color.DARK_GRAY);
        ipInputField.setBounds(300, 180, 450, 50);
        ipInputField.setFont(new Font("Serif", Font.BOLD, 20));
        add(ipInputField);

        // Dropdown menu
        conversionOptions = new JComboBox<>(new String[]{"Decimal to Binary", "Binary to Decimal"});
        conversionOptions.setBounds(300, 250, 200, 50);
        conversionOptions.setFont(new Font("Serif", Font.BOLD, 20));
        add(conversionOptions);

        // Convert button
        convertButton = new JButton("Convert");
        convertButton.setForeground(new Color(255, 255, 255));
        convertButton.setBackground(new Color(1, 55, 125));
        convertButton.addActionListener(this);
        convertButton.setBounds(550, 250, 150, 50);
        add(convertButton);

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
                new IPv4Implementation();
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
        ipOutputLabel = new JLabel();
        ipOutputLabel.setBounds(225, 350, 780, 50);
        ipOutputLabel.setBackground(new Color(255,255,255));
        ipOutputLabel.setForeground(Color.yellow);
        ipOutputLabel.setFont(new Font("Serif", Font.BOLD, 25));
        add(ipOutputLabel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String ipInput = ipInputField.getText();
        String selectedOption = (String) conversionOptions.getSelectedItem();

        if (e.getSource() == convertButton) {
            if ("Decimal to Binary".equals(selectedOption)) {
                convertDecimalToBinary(ipInput);
            } else if ("Binary to Decimal".equals(selectedOption)) {
                convertBinaryToDecimal(ipInput);
            }
        }
    }

    private void convertDecimalToBinary(String ipInput) {
        if (ipInput.matches("^\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}$")) {
            String[] octets = ipInput.split("\\.");
            StringBuilder binaryAddress = new StringBuilder();

            for (String octet : octets) {
                int decimal = Integer.parseInt(octet);
                String binary = Integer.toBinaryString(decimal);
                binary = String.format("%8s", binary).replace(' ', '0');
                binaryAddress.append(binary).append('.');
            }

            ipOutputLabel.setText("Binary: " + binaryAddress.substring(0, binaryAddress.length() - 1));
        } else {
            ipOutputLabel.setText("Invalid IPv4 Decimal Address");
        }
    }

    private void convertBinaryToDecimal(String ipInput) {
        if (ipInput.matches("^(\\d{8}\\.){3}\\d{8}$")) {
            String[] octets = ipInput.split("\\.");
            StringBuilder decimalAddress = new StringBuilder();

            for (String octet : octets) {
                int decimal = Integer.parseInt(octet, 2);
                decimalAddress.append(decimal).append('.');
            }

            ipOutputLabel.setText("Decimal: " + decimalAddress.substring(0, decimalAddress.length() - 1));
        } else {
            ipOutputLabel.setText("Invalid IPv4 Binary Address");
        }
    }

    public static void main(String[] args) {
        new IPv4Converter();
    }
}
