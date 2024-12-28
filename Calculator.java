import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator {
    // Create frame
    private JFrame frame;
    // Create text field to display input
    private JTextField textField;
    // Store the operator and operands for calculations
    private String operator = "";
    private double firstOperand = 0;

    public Calculator() {
        // Create the frame
        frame = new JFrame("Simple Calculator");
        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Create the text field to display the input
        textField = new JTextField();
        textField.setFont(new Font("Arial", Font.BOLD, 24));
        textField.setHorizontalAlignment(JTextField.RIGHT);
        frame.add(textField, BorderLayout.NORTH);

        // Create panel for buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 10, 10));

        // Define buttons and their actions
        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "C", "0", "=", "+"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buttonClicked(e);
                }
            });
            panel.add(button);
        }

        // Add panel to frame
        frame.add(panel, BorderLayout.CENTER);

        // Set the frame visible
        frame.setVisible(true);
    }

    private void buttonClicked(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.charAt(0) == 'C') {
            // Clear the text field
            textField.setText("");
            operator = "";
            firstOperand = 0;
        } else if (command.charAt(0) == '=') {
            // Perform the calculation
            double result = 0;
            double secondOperand = Double.parseDouble(textField.getText());

            switch (operator) {
                case "+":
                    result = firstOperand + secondOperand;
                    break;
                case "-":
                    result = firstOperand - secondOperand;
                    break;
                case "*":
                    result = firstOperand * secondOperand;
                    break;
                case "/":
                    result = firstOperand / secondOperand;
                    break;
            }

            textField.setText(String.valueOf(result));
            operator = "";
            firstOperand = result;
        } else {
            // If an operator is clicked
            if ("+-*/".contains(command)) {
                operator = command;
                firstOperand = Double.parseDouble(textField.getText());
                textField.setText("");
            } else {
                // If a number is clicked
                textField.setText(textField.getText() + command);
            }
        }
    }

    public static void main(String[] args) {
        // Create and display the calculator
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Calculator();
            }
        });
    }
}