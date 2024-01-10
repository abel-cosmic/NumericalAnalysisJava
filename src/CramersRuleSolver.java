import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class CramersRuleSolver {
    public CramersRuleSolver(){
        String equationCountString = JOptionPane.showInputDialog("Enter the number of linear equations:");
        int equationCount = Integer.parseInt(equationCountString);

        if (equationCount <= 0) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a positive number of equations.");
            System.exit(0);
        }

        // Create a JFrame
        JFrame frame = new JFrame("Linear Equation Solver");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a JPanel to hold the input fields
        JPanel panel = new JPanel(new GridLayout(equationCount, equationCount + 1));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Add padding
        // Set preferred size for the panel window
        Dimension panelSize = new Dimension(600, 350);
        panel.setPreferredSize(panelSize);

        // Create and add input fields to the panel with plus signs and equal signs
        Set<Character> variables = new HashSet<>();
        for (int i = 0; i < equationCount; i++) {
            for (int j = 0; j < equationCount + 1; j++) {
                JTextField textField = new JTextField(5);
                textField.setHorizontalAlignment(SwingConstants.CENTER);
                Font boldFont = new Font(textField.getFont().getName(), Font.BOLD, 14);
                textField.setFont(boldFont);

                if (j < equationCount - 1) {
                    panel.add(textField);
                    JLabel plusLabel = new JLabel(" + ");
                    panel.add(plusLabel);
                } else if (j == equationCount - 1) {
                    panel.add(textField);
                    JLabel equalLabel = new JLabel(" = ");
                    panel.add(equalLabel);
                } else {
                    panel.add(textField);
                }

                // Validate input for numbers and variables
                String input = textField.getText().trim();
                if (!isValidInput(input)) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid numeric and variable combination.");
                    System.exit(0);
                }

                // Extract variables for checking
                if (j < equationCount - 1) {
                    for (char c : input.toCharArray()) {
                        if (Character.isAlphabetic(c)) {
                            variables.add(c);
                        }
                    }
                }
            }
        }

        // Check if all equations have the same variable definitions
        if (variables.size() > 2) {
            JOptionPane.showMessageDialog(null, "Invalid input. Equations should have two or fewer variables.");
            System.exit(0);
        }

        // Create a button for solving the equations (optional)
        JButton solveButton = new JButton("Solve Equations");
        solveButton.addActionListener(e -> solveEquations(equationCount, panel));

        // Add the panel and button to the frame
        frame.add(panel, BorderLayout.CENTER);
        frame.add(solveButton, BorderLayout.SOUTH);

        // Set frame properties
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    public static void main(String[] args) {

    }

    private  boolean isValidInput(String input) {
        // Add your validation logic here (e.g., using regular expressions)
        // The input should be a combination of numbers and variables (e.g., "2x", "3y", etc.)
        // Modify the logic based on your requirements
        return input.matches("^\\d*[a-zA-Z]?$");
    }

    private  void solveEquations(int equationCount, JPanel panel) {
        // Add your code to extract values from input fields and solve the equations
        // You can use panel.getComponent(index) to access individual input fields
        // Perform the necessary computations based on the user's input
    }
}
