import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CreamersRuleSolver extends JFrame{
    public CreamersRuleSolver(){
        String equationCountString = JOptionPane.showInputDialog("Enter the number of linear equations:");
        int equationCount = Integer.parseInt(equationCountString);
        if (equationCount <= 0) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a positive number of equations.");
            System.exit(0);
        }
        JFrame frame = new JFrame("Linear Equation Solver");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel(new GridLayout(equationCount, equationCount + 1));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Add padding
        Dimension panelSize = new Dimension(600, 350);
        panel.setPreferredSize(panelSize);
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
                String input = textField.getText().trim();
                if (j < equationCount - 1) {
                    for (char c : input.toCharArray()) {
                        if (Character.isAlphabetic(c)) {
                            variables.add(c);
                        }
                    }
                }
            }
        }
        if (variables.size() > 2) {
            JOptionPane.showMessageDialog(null, "Invalid input. Equations should have two or fewer variables.");
            System.exit(0);
        }
        JButton solveButton = new JButton("Solve Equations");
        solveButton.addActionListener(e -> solveEquations(equationCount, panel));
        frame.add(panel, BorderLayout.CENTER);
        frame.add(solveButton, BorderLayout.SOUTH);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    private double determinant(double[][] matrix) {
        // Implementation of determinant calculation goes here
        // This function should handle square matrices
        // (You need to implement this part based on your matrix size)
        return 0.0; // Placeholder, replace with actual determinant calculation
    }

    private double[] solveLinearSystem(double[][] coefficientsMatrix, double[] constantsVector) {
        // Get the number of equations
        return constantsVector;
    }

    private void solveEquations(int equationCount, JPanel panel) {
        // this is the pace which handles the solving of the linear equation
    }
}
