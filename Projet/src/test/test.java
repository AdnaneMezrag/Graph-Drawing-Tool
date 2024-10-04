package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class test {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                showOptionsDialog();
            }
        });
    }

    private static void showOptionsDialog() {
        JFrame optionsFrame = new JFrame("Options");
        optionsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create radio buttons
        JRadioButton radioButton1 = new JRadioButton("Option 1");
        JRadioButton radioButton2 = new JRadioButton("Option 2");
        JRadioButton radioButton3 = new JRadioButton("Option 3");

        // Create button group
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(radioButton1);
        buttonGroup.add(radioButton2);
        buttonGroup.add(radioButton3);

        // Create OK button
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Retrieve selected option
                String selectedOption = "";
                if (radioButton1.isSelected()) {
                    selectedOption = "Option 1";
                } else if (radioButton2.isSelected()) {
                    selectedOption = "Option 2";
                } else if (radioButton3.isSelected()) {
                    selectedOption = "Option 3";
                }

                // Close dialog
                optionsFrame.dispose();

                // Proceed with creating the main JFrame
                createMainWindow(selectedOption);
            }
        });

        // Add components to the optionsFrame
        optionsFrame.setLayout(new GridLayout(4, 1));
        optionsFrame.add(new JLabel("Choose an option:"));
        optionsFrame.add(radioButton1);
        optionsFrame.add(radioButton2);
        optionsFrame.add(radioButton3);
        optionsFrame.add(okButton);
        optionsFrame.pack();
        optionsFrame.setLocationRelativeTo(null); // Center the dialog window
        optionsFrame.setVisible(true);
    }

    private static void createMainWindow(String selectedOption) {
        JFrame frame = new JFrame("Main Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("You selected: " + selectedOption);
        frame.getContentPane().add(label, BorderLayout.CENTER);

        frame.pack();
        frame.setLocationRelativeTo(null); // Center the main window
        frame.setVisible(true);
    }
}
