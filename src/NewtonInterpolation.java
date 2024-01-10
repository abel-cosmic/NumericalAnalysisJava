import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewtonInterpolation extends JFrame {

    private final JTextField numberOfDS;
    private final JPanel dataPanel, resultPanel;

    public NewtonInterpolation() {

        // Create components
        JLabel labelDs = new JLabel("Number of Data sets:");
        numberOfDS = new JTextField(5);
        JButton enterDataButton = new JButton("Enter Data");
        JButton calculateButton = new JButton("Calculate");
        JButton clearButton = new JButton("Clear");
        dataPanel = new JPanel();
        resultPanel = new JPanel();

        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Add padding
        inputPanel.add(labelDs);
        inputPanel.add(numberOfDS);
        inputPanel.add(enterDataButton);
        inputPanel.add(clearButton);
        inputPanel.add(calculateButton);
        add(inputPanel, BorderLayout.NORTH);

        enterDataButton.addActionListener(e -> generateLayout());

        clearButton.addActionListener(e -> clearLayout());

        calculateButton.addActionListener(e -> calculate());

        JPanel matricesPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        matricesPanel.add(new JScrollPane(dataPanel));
        matricesPanel.add(new JScrollPane(resultPanel));
        add(matricesPanel, BorderLayout.CENTER);

        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame
        setVisible(true);
    }

    public void clearLayout() {
        numberOfDS.setText("");
        dataPanel.removeAll();
        dataPanel.revalidate();
        dataPanel.repaint();
        resultPanel.removeAll();
        resultPanel.revalidate();
        resultPanel.repaint();
    }

    public void generateLayout() {
        int nDS = Integer.parseInt(numberOfDS.getText());
        dataPanel.setLayout(new GridLayout(nDS, 2, 5, 5));
        for (int i = 0; i < nDS; i++) {
            JTextField textField = new JTextField(5);
            dataPanel.add(textField);
            JTextField textField2 = new JTextField(5);
            dataPanel.add(textField2);
        }
        dataPanel.revalidate();
        dataPanel.repaint();
    }

    public void calculate() {
        int nDS = Integer.parseInt(numberOfDS.getText());
        double[][] data = getDataSet(nDS);
        double[] xValues = new double[data.length];
        double[] yValues = new double[data.length];
        for (int i = 0; i < data.length; i++) {
            xValues[i] = data[i][0];
            yValues[i] = data[i][1];
        }
        double[][] dividedDifferenceTable = calculateDividedDifferenceTable(xValues, yValues);
        JTextPane result = new JTextPane();
        result.setText(displayTableToString(xValues,  dividedDifferenceTable));
        JTextPane equation = new JTextPane();
        equation.setText(displayPolynomialToString(dividedDifferenceTable, xValues));
        resultPanel.removeAll();
        resultPanel.revalidate();
        resultPanel.repaint();
        resultPanel.setLayout(new GridLayout(2, 1));
        resultPanel.add(result);
        resultPanel.add(equation);
        resultPanel.revalidate();
        resultPanel.repaint();
    }

    private double[][] getDataSet(int rows) {
        Component[] components = dataPanel.getComponents();
        double[][] matrixData = new double[rows][2];

        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < 2; j++) {
                JTextField textField = (JTextField) components[index++];
                matrixData[i][j] = Double.parseDouble(textField.getText());
            }
        }
        return matrixData;
    }

    // Method to calculate divided difference table
    private static double[][] calculateDividedDifferenceTable(double[] x, double[] y) {
        int n = x.length;
        double[][] table = new double[n][n];

        // Fill in the first column with y values
        for (int i = 0; i < n; i++) {
            table[i][0] = y[i];
        }

        // Calculate divided differences
        for (int j = 1; j < n; j++) {
            for (int i = j; i < n; i++) {
                table[i][j] = (table[i][j - 1] - table[i - 1][j - 1]) / (x[i] - x[i - j]);
                System.out.println("Table[" + i + "," + j + "] = " + table[i][j]);
            }
        }

        return table;
    }

    private static String displayTableToString(double[] x, double[][] table) {
        int n = table.length;
        StringBuilder result = new StringBuilder();
        result.append(String.format("%-10s\t", "X"))
                .append(String.format("%-10s\t", "F(x)"));

        for (int i = 1; i < n; i++) {
            result.append(String.format("%-10s\t", (i + "th Order")));
        }
        result.append("\n");

        for (int i = 0; i < n; i++) {
            result.append(String.format("%.2f\t", x[i]));
            for (int j = 0; j <= i; j++) {
                result.append(String.format("%.2f\t", table[i][j]));
            }
            result.append("\n");
        }

        return result.toString();
    }

    private static String displayPolynomialToString(double[][] table, double[] x) {
        int n = table.length;
        StringBuilder result = new StringBuilder();
        result.append("F(x) = ").append(table[0][0]);

        for (int i = 1; i < n; i++) {
            result.append(" + ").append(table[i][i]).append(" * ");

            for (int j = 0; j < i; j++) {
                double xValue = x[j];
                if (xValue < 0) {
                    result.append("(x - ").append(Math.abs(xValue)).append(")");
                } else {
                    result.append("(x + ").append(xValue).append(")");
                }
            }
        }
        result.append("\n");

        return result.toString();
    }
}