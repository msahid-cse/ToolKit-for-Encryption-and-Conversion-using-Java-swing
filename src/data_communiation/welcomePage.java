package data_communiation;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class welcomePage extends JFrame implements ActionListener {

    // Constructor
    welcomePage() {
        //creat frame
        JFrame frame=new JFrame("DATA COMMUNICATION LAB");
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(136, 196, 245));
       // f.getContentPane().setBackground(Color.white);
        frame.setSize(1000, 600);
        frame.setLocation(200, 60);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);


        
        
        // Header
        JLabel headerLabel = new JLabel("ToolKit For Encryption and Conversion");
        headerLabel.setFont(new Font("Serif", Font.BOLD, 40));
        headerLabel.setForeground(new Color(255, 255, 255));
        headerLabel.setBounds(200, 50, 700, 100);
        frame.add(headerLabel);
        
              ImageIcon backgroundImage = new ImageIcon("IMAGES/welcome.png"); // Replace this with your image path
        JLabel background = new JLabel(backgroundImage);
        //background.setLayout(new BorderLayout());
        frame.add(background);

        // Set the size of the JLabel to match the JFrame
        background.setBounds(0, 0, 1000, 600);

        // Topics buttons
        JButton btnClass1 = new JButton("Stuffing and De-stuffing");
        btnClass1.setFont(new Font("Arial", Font.BOLD, 18));
        btnClass1.setBounds(350, 200, 350, 40);
        btnClass1.setBackground(new Color(1, 55, 125));
        btnClass1.setForeground(Color.WHITE);
        btnClass1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
             //   JOptionPane.showMessageDialog(null, "Opening Stuffing and De-stuffing class...");
            new StuffingDeStuffing();
            frame.setVisible(false);
            }
        });
        frame.add(btnClass1);

        JButton btnClass2 = new JButton("IPv4 implementation");
        btnClass2.setFont(new Font("Arial", Font.BOLD, 18));
        btnClass2.setBounds(350, 250, 350, 40);
        btnClass2.setBackground(new Color(1, 55, 125));
        btnClass2.setForeground(Color.WHITE);
        btnClass2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               // JOptionPane.showMessageDialog(null, "Opening IPv4 implementation class...");
            new IPv4Implementation();
            frame.setVisible(false);
            }
        });
        frame.add(btnClass2);

        JButton btnClass3 = new JButton("Hamming Code");
        btnClass3.setFont(new Font("Arial", Font.BOLD, 18));
        btnClass3.setBounds(350, 300, 350, 40);
        btnClass3.setBackground(new Color(1, 55, 125));
        btnClass3.setForeground(Color.WHITE);
        btnClass3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showMessageDialog(null, "Opening Hamming Code class...");
                new HammingCode();
               frame.setVisible(false);
            }
        });
        frame.add(btnClass3);

        JButton btnClass4 = new JButton("Hamming Code Error Detection");
        btnClass4.setFont(new Font("Arial", Font.BOLD, 18));
        btnClass4.setBounds(350, 350, 350, 40);
        btnClass4.setBackground(new Color(1, 55, 125));
        btnClass4.setForeground(Color.WHITE);
        btnClass4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               // JOptionPane.showMessageDialog(null, "Opening BMI Calculator class...");
            new HammingCodeErrorCorrection();
           frame.setVisible(false);
            }
        });
        frame.add(btnClass4);




        // Developer info button
        JButton developerInfoButton = new JButton("Developer Info");
        developerInfoButton.setBounds(880, 0, 120, 25);
        developerInfoButton.setBackground(new Color(1, 55, 125));
        developerInfoButton.setForeground(Color.WHITE);
        developerInfoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Developer Information...");
                //new DeveloperInformation(); Uncomment this line if DeveloperInformation class exists
                frame.setVisible(false);
            }
        });
        frame.add(developerInfoButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle action events
    }

    public static void main(String[] args) {
        new welcomePage();
    }
}
