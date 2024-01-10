import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class NewtonInterpolation extends JFrame {

    private JTextField numberOfDS;
    JPanel dataPanel;
    public NewtonInterpolation() {

        // Create components
        JLabel labelDs = new JLabel("Number of Data sets:");
        numberOfDS = new JTextField(5);
        JButton enterDataButton = new JButton("Enter Data");
        JButton calculateButton = new JButton("Calculate");
        JButton clearButton = new JButton("Clear");
        dataPanel = new JPanel();
        JPanel resultPanel = new JPanel();

        // Set layout for the main frame
        setLayout(new BorderLayout());

        // Create a panel for input fields and buttons
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Add padding
        inputPanel.add(labelDs);
        inputPanel.add(numberOfDS);
        inputPanel.add(enterDataButton);
        inputPanel.add(clearButton);
        inputPanel.add(calculateButton);

        // Add the input panel to the north of the main frame
        add(inputPanel, BorderLayout.NORTH);

        // Register event handlers for the buttons
        enterDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateLayout();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearLayout();
            }
        });

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculate();
            }
        });

        // Add the matrix panels to the center of the main frame
        JPanel matricesPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        matricesPanel.add(new JScrollPane(dataPanel));
        matricesPanel.add(new JScrollPane(resultPanel));
        add(matricesPanel, BorderLayout.CENTER);

        // Set frame properties
        setSize(1200, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame
        setVisible(true);
    }

    public void clearLayout() {
        numberOfDS.setText("");
        dataPanel.removeAll();
        dataPanel.revalidate();
        dataPanel.repaint();
    }
    public void generateLayout() {
        int nDS = Integer.parseInt(numberOfDS.getText());
        dataPanel.setLayout(new GridLayout(nDS, 2, 5, 5));
        for (int i = 0; i < nDS; i++) {
            JTextField textField = new JTextField(5);
            dataPanel.add(textField);
            JTextField textField2= new JTextField(5);
            dataPanel.add(textField2);
        }
        dataPanel.revalidate();
        dataPanel.repaint();

//        Scanner scanner = new Scanner(System.in);
//
//        // Input the number of data points
//        System.out.print("Enter the number of data points: ");
//        int n = scanner.nextInt();
//
//        // Input the x and y values
//        double[] x = new double[n];
//        double[] y = new double[n];
//
//        System.out.println("Enter the x and y values:");
//
//        for (int i = 0; i < n; i++) {
//            System.out.print("x[" + i + "]: ");
//            x[i] = scanner.nextDouble();
//            System.out.print("y[" + i + "]: ");
//            y[i] = scanner.nextDouble();
//        }
//
//        // Calculate divided difference table
//        double[][] dividedDifferenceTable = calculateDividedDifferenceTable(x, y);
//
//        // Display the divided difference table
//        System.out.println("\nDivided Difference Table:");
//        displayTable(x, dividedDifferenceTable);
//
//        // Display the interpolating polynomial
//        System.out.println("\nInterpolating Polynomial:");
//        displayPolynomial(dividedDifferenceTable, x);
    }


    public void calculate() {
        numberOfDS.setText("");
        dataPanel.removeAll();
        dataPanel.revalidate();
        dataPanel.repaint();
    }

    private double[][] getMatrixData(int rows) {
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
                System.out.println("Table["+i+","+j+"] = "  + table[i][j]);
            }
        }

        return table;
    }

    // Method to display the divided difference table
    private static void displayTable(double[] x, double[][] table) {
        int n = table.length;
        System.out.printf("%-10s\t", "X");
        System.out.printf("%-10s\t", "F(x)");
        for (int i = 1; i < n; i++) {
            System.out.printf("%-10s\t", (i + "th Order"));
        }
        System.out.println();
        for (int i = 0; i < n; i++) {
            System.out.printf("%.6f\t", x[i]);
            for (int j = 0; j <= i; j++) {
                System.out.printf("%.6f\t", table[i][j]);
            }
            System.out.println();
        }
    }

    // Method to display the interpolating polynomial
    private static void displayPolynomial(double[][] table, double[] x) {
        int n = table.length;

        System.out.print("P(x) = " + table[0][0]);

        for (int i = 1; i < n; i++) {
            System.out.print(" + " + table[i][i] + " * ");

            for (int j = 0; j < i; j++) {double xValue = x[j];
                if (xValue < 0) {
                    System.out.print("(x - " + Math.abs(xValue) + ")");
                } else {
                    System.out.print("(x + " + xValue + ")");
                }
            }
        }
        System.out.println();
    }
}
