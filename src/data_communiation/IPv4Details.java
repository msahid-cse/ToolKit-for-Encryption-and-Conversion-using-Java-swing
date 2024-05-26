package data_communiation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class IPv4Details extends JFrame implements ActionListener {

    private JTextField ipInputField;
    private JLabel ipOutputLabel;
    private JButton calculateButton;
    private JComboBox<String> conversionTypeComboBox;

    public IPv4Details() {

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
        JLabel headerLabel = new JLabel("IPv4 Details");
        headerLabel.setFont(new Font("Serif", Font.BOLD, 40));
        headerLabel.setForeground(new Color(0, 0, 0));
        headerLabel.setBounds(400, 30, 500, 100);
        add(headerLabel);

        // Sub Header
        JLabel header2Label = new JLabel("Enter IP address and Subnet Mask Prefix Length (e.g., 255.255.255.255/24)");
        header2Label.setFont(new Font("Serif", Font.BOLD, 20));
        header2Label.setForeground(Color.white);
        header2Label.setBounds(200, 110, 700, 100);
        add(header2Label);

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

        // Conversion Type Dropdown
        String[] conversionTypes = {"Decimal to Binary Details", "Decimal to Decimal Details"};
        conversionTypeComboBox = new JComboBox<>(conversionTypes);
        conversionTypeComboBox.setFont(new Font("Serif", Font.BOLD, 18));
        conversionTypeComboBox.setBounds(300, 250, 250, 50);
        add(conversionTypeComboBox);

        // Calculate Button
        calculateButton = new JButton("Calculate");
        calculateButton.setForeground(new Color(255, 255, 255));
        calculateButton.setBackground(new Color(1, 55, 125));
        calculateButton.addActionListener(this);
        calculateButton.setFont(new Font("Serif", Font.BOLD, 20));
        calculateButton.setBounds(600, 250, 150, 50);
        add(calculateButton);

        // Back Button
        ImageIcon b_backgroundImage = new ImageIcon("IMAGES/Back.png");
        JButton Back = new JButton("< Back", b_backgroundImage);
        Back.setFont(new Font("Arial", Font.BOLD, 20));
        Back.setBounds(0, 0, 85, 25);
        Back.setBackground(new Color(159, 126, 219));
        Back.setForeground(Color.white);
        Back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
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

        // Output Label
        ipOutputLabel = new JLabel();
        ipOutputLabel.setBounds(50, 350, 900, 200);
        ipOutputLabel.setBackground(new Color(255, 255, 255));
        ipOutputLabel.setForeground(Color.yellow);
        ipOutputLabel.setFont(new Font("Serif", Font.BOLD, 20));
        ipOutputLabel.setVerticalAlignment(SwingConstants.TOP);
        add(ipOutputLabel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String ipInput = ipInputField.getText();
        String selectedConversion = (String) conversionTypeComboBox.getSelectedItem();
        if (e.getSource() == calculateButton) {
            if ("Decimal to Binary Details".equals(selectedConversion)) {
                calculateBinaryDetails(ipInput);
            } else if ("Decimal to Decimal Details".equals(selectedConversion)) {
                calculateDecimalDetails(ipInput);
            }
        }
    }

    private void calculateBinaryDetails(String ipInput) {
        try {
            String[] parts = ipInput.split("/");
            if (parts.length != 2) {
                ipOutputLabel.setText("Invalid input format. Expected format: x.x.x.x/x");
                return;
            }

            String ipPart = parts[0];
            int prefixLength = Integer.parseInt(parts[1]);

            if (!ipPart.matches("^\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}$") || prefixLength < 0 || prefixLength > 32) {
                ipOutputLabel.setText("Invalid IPv4 Address or prefix length");
                return;
            }

            String[] octets = ipPart.split("\\.");
            int[] ipOctets = Arrays.stream(octets).mapToInt(Integer::parseInt).toArray();

            if (Arrays.stream(ipOctets).anyMatch(o -> o < 0 || o > 255)) {
                ipOutputLabel.setText("Invalid IPv4 Address");
                return;
            }

            StringBuilder binaryIP = new StringBuilder();
            for (int octet : ipOctets) {
                String binary = String.format("%8s", Integer.toBinaryString(octet)).replace(' ', '0');
                binaryIP.append(binary).append('.');
            }

            binaryIP.setLength(binaryIP.length() - 1);

            String binaryIPStr = binaryIP.toString();
            String subnetMask = generateSubnetMask(prefixLength);
            String networkAddress = calculateNetworkAddress(binaryIPStr, subnetMask);
            String broadcastAddress = calculateBroadcastAddress(networkAddress, subnetMask);
            char ipClass = determineClass(ipOctets[0]);
            int numberOfHosts = (int) Math.pow(2, 32 - prefixLength) - 2;

            String output = String.format(
                    "<html>IPv4: %s<br>Subnet Mask: %s<br>Network Address: %s<br> Broadcast Address: %s<br>Class: %s<br>Number of hosts: %d</html>",
                    binaryIPStr, subnetMask, networkAddress, broadcastAddress, ipClass, numberOfHosts
            );
            ipOutputLabel.setText(output);
        } catch (Exception ex) {
            ipOutputLabel.setText("An error occurred: " + ex.getMessage());
        }
    }

    private void calculateDecimalDetails(String ipInput) {
        try {
            String[] parts = ipInput.split("/");
            if (parts.length != 2) {
                ipOutputLabel.setText("Invalid input format. Expected format: x.x.x.x/x");
                return;
            }

            String ipPart = parts[0];
            int prefixLength = Integer.parseInt(parts[1]);

            if (!ipPart.matches("^\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}$") || prefixLength < 0 || prefixLength > 32) {
                ipOutputLabel.setText("Invalid IPv4 Address or prefix length");
                return;
            }

            String[] octets = ipPart.split("\\.");
            int[] ipOctets = Arrays.stream(octets).mapToInt(Integer::parseInt).toArray();

            if (Arrays.stream(ipOctets).anyMatch(o -> o < 0 || o > 255)) {
                ipOutputLabel.setText("Invalid IPv4 Address");
                return;
            }

            String subnetMask = generateSubnetMaskDecimal(prefixLength);
            String networkAddress = calculateNetworkAddressDecimal(ipPart, subnetMask);
            String broadcastAddress = calculateBroadcastAddressDecimal(networkAddress, subnetMask);
            char ipClass = determineClass(ipOctets[0]);
            int numberOfHosts = (int) Math.pow(2, 32 - prefixLength) - 2;

            String output = String.format(
                    "<html>IPv4: %s<br>Subnet Mask: %s<br>Network Address: %s<br> Broadcast Address: %s<br>Class: %s<br>Number of hosts: %d</html>",
                    ipPart, subnetMask, networkAddress, broadcastAddress, ipClass, numberOfHosts
            );
            ipOutputLabel.setText(output);
        } catch (Exception ex) {
            ipOutputLabel.setText("An error occurred: " + ex.getMessage());
        }
    }

    private String generateSubnetMask(int prefixLength) {
        StringBuilder subnetMask = new StringBuilder();
        for (int i = 0; i < 32; i++) {
            subnetMask.append(i < prefixLength ? '1' : '0');
            if ((i + 1) % 8 == 0 && i != 31) {
                subnetMask.append('.');
            }
        }
        return subnetMask.toString();
    }

    private String generateSubnetMaskDecimal(int prefixLength) {
        int mask = 0xffffffff << (32 - prefixLength);
        return String.format("%d.%d.%d.%d",
                (mask >> 24) & 0xff,
                (mask >> 16) & 0xff,
                (mask >> 8) & 0xff,
                mask & 0xff);
    }

    private String calculateNetworkAddress(String binaryIP, String subnetMask) {
        StringBuilder networkAddress = new StringBuilder();
        for (int i = 0; i < binaryIP.length(); i++) {
            if (binaryIP.charAt(i) == '.' || subnetMask.charAt(i) == '.') {
                networkAddress.append('.');
            } else {
                networkAddress.append(binaryIP.charAt(i) == '1' && subnetMask.charAt(i) == '1' ? '1' : '0');
            }
        }
        return networkAddress.toString();
    }

    private String calculateNetworkAddressDecimal(String ipPart, String subnetMask) {
        String[] ipOctets = ipPart.split("\\.");
        String[] maskOctets = subnetMask.split("\\.");
        int[] networkAddress = new int[4];
        for (int i = 0; i < 4; i++) {
            networkAddress[i] = Integer.parseInt(ipOctets[i]) & Integer.parseInt(maskOctets[i]);
        }
        return String.format("%d.%d.%d.%d", networkAddress[0], networkAddress[1], networkAddress[2], networkAddress[3]);
    }

    private String calculateBroadcastAddress(String networkAddress, String subnetMask) {
        StringBuilder broadcastAddress = new StringBuilder();
        for (int i = 0; i < networkAddress.length(); i++) {
            if (networkAddress.charAt(i) == '.' || subnetMask.charAt(i) == '.') {
                broadcastAddress.append('.');
            } else {
                broadcastAddress.append(networkAddress.charAt(i) == '1' || subnetMask.charAt(i) == '0' ? '1' : '0');
            }
        }
        return broadcastAddress.toString();
    }

    private String calculateBroadcastAddressDecimal(String networkAddress, String subnetMask) {
        String[] networkOctets = networkAddress.split("\\.");
        String[] maskOctets = subnetMask.split("\\.");
        int[] broadcastAddress = new int[4];
        for (int i = 0; i < 4; i++) {
            broadcastAddress[i] = Integer.parseInt(networkOctets[i]) | ~Integer.parseInt(maskOctets[i]) & 0xff;
        }
        return String.format("%d.%d.%d.%d", broadcastAddress[0], broadcastAddress[1], broadcastAddress[2], broadcastAddress[3]);
    }

    private char determineClass(int firstOctet) {
        if (firstOctet >= 0 && firstOctet <= 127) {
            return 'A';
        } else if (firstOctet >= 128 && firstOctet <= 191) {
            return 'B';
        } else if (firstOctet >= 192 && firstOctet <= 223) {
            return 'C';
        } else if (firstOctet >= 224 && firstOctet <= 239) {
            return 'D';
        } else {
            return 'E';
        }
    }

    public static void main(String[] args) {
        new IPv4Details();
    }
}
