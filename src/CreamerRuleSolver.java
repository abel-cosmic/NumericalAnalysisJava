import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class CreamerRuleSolver extends JFrame {
    private final JTextField[][] matrixFields;
    private final JTextField[] constantsFields;
    private int numberOfRows;
    private final JTextArea solutionArea;

    public CreamerRuleSolver() {
        String rowsInput = JOptionPane.showInputDialog("Enter the number of equations:");
        try {
            numberOfRows = Integer.parseInt(rowsInput);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.");
            System.exit(1);
        }

        matrixFields = new JTextField[numberOfRows][numberOfRows];
        constantsFields = new JTextField[numberOfRows];
        solutionArea = new JTextArea(10, 30);
        solutionArea.setEditable(false);

        initializeGUI();

        JButton solveButton = new JButton("Solve using Cramer's Rule");
        solveButton.addActionListener(e -> solveSystem());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(solveButton);

        JPanel resultPanel = new JPanel();
        resultPanel.add(new JScrollPane(solutionArea));

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        mainPanel.add(resultPanel, BorderLayout.CENTER);

        add(mainPanel);

        setTitle("Equation Solver");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initializeGUI() {
        JPanel matrixPanel = new JPanel(new GridLayout(numberOfRows, numberOfRows + 1, 10, 5));

        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfRows; j++) {
                matrixFields[i][j] = new JTextField(5);
                matrixFields[i][j].setHorizontalAlignment(JTextField.CENTER);
                matrixFields[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                matrixPanel.add(matrixFields[i][j]);
            }

            JLabel equalsLabel = new JLabel("=");
            equalsLabel.setFont(new Font("Arial", Font.BOLD, 14));
            matrixPanel.add(equalsLabel);

            constantsFields[i] = new JTextField(5);
            constantsFields[i].setHorizontalAlignment(JTextField.CENTER);
            constantsFields[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            matrixPanel.add(constantsFields[i]);
        }

        matrixPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(matrixPanel, BorderLayout.CENTER);

        add(inputPanel, BorderLayout.NORTH);
    }

    private void solveSystem() {
        ArrayList<ArrayList<Integer>> coefficients = new ArrayList<>();

        // Parse user input and populate coefficients matrix
        for (int i = 0; i < numberOfRows; i++) {
            ArrayList<Integer> rowList = new ArrayList<>();
            for (int j = 0; j < numberOfRows; j++) {
                try {
                    rowList.add(Integer.parseInt(matrixFields[i][j].getText()));
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid input in coefficient matrix. Please enter valid numbers.");
                    return;
                }
            }
            coefficients.add(rowList);
        }
        ArrayList<Integer> constants = new ArrayList<>();
        for (int i = 0; i < numberOfRows; i++) {
            try {
                constants.add(Integer.parseInt(constantsFields[i].getText()));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input in constants matrix. Please enter valid numbers.");
                return;
            }
        }
        solutionArea.setText("");
        solutionArea.append("Coefficient Matrix:\n");
        for (ArrayList<Integer> rowList : coefficients) {
            for (int value : rowList) {
                solutionArea.append(value + "\t");
            }
            solutionArea.append("\n");
        }
        solutionArea.append("\nConstants Matrix:\n");
        for (int value : constants) {
            solutionArea.append(value + "\n");
        }
        int determinant = MatrixUtils.calculateDeterminant(coefficients);
        if (determinant == 0) {
            solutionArea.append("\nThe system is inconsistent or has infinitely many solutions (Determinant is zero).\n");
            return;
        }
        int[] solutions = new int[numberOfRows];
        for (int i = 0; i < numberOfRows; i++) {
            ArrayList<ArrayList<Integer>> modifiedMatrix = getModifiedMatrix(coefficients, constants, i);
            solutions[i] = MatrixUtils.calculateDeterminant(modifiedMatrix) / determinant;
        }
        solutionArea.append("\nSolution:\n");
        for (int i = 0; i < numberOfRows; i++) {
            solutionArea.append("x" + (i + 1) + " = " + solutions[i] + "\n");
        }
        JOptionPane.showMessageDialog(null, "System solved using Cramer's Rule. Check the GUI for the solution.");
    }
    private ArrayList<ArrayList<Integer>> getModifiedMatrix(ArrayList<ArrayList<Integer>> coefficients, ArrayList<Integer> constants, int columnIndex) {
        ArrayList<ArrayList<Integer>> modifiedMatrix = new ArrayList<>();

        for (int i = 0; i < numberOfRows; i++) {
            ArrayList<Integer> rowList = new ArrayList<>();
            for (int j = 0; j < numberOfRows; j++) {
                rowList.add((j == columnIndex) ? constants.get(i) : coefficients.get(i).get(j));
            }
            modifiedMatrix.add(rowList);
        }

        return modifiedMatrix;
    }
}
