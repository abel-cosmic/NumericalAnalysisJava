import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class MatrixMultiplier extends JFrame {
    private JTextField rowsField1, colsField1, rowsField2, colsField2;
    private JPanel matrixPanel1, matrixPanel2, resultPanel;
    private JButton generateButton, clearButton, multiplyButton;

    public MatrixMultiplier() {
        super("Matrix Generator");
        // Create components
        JLabel matrix1Label = new JLabel(" Matrix 1:");
        JLabel matrix2Label = new JLabel(" Matrix 2:");
        JLabel rowsLabel1 = new JLabel(" Rows:");
        JLabel colsLabel1 = new JLabel(" Columns:");
        JLabel rowsLabel2 = new JLabel(" Rows:");
        JLabel colsLabel2 = new JLabel(" Columns:");
        rowsField1 = new JTextField(5);
        colsField1 = new JTextField(5);
        rowsField2 = new JTextField(5);
        colsField2 = new JTextField(5);
        generateButton = new JButton("Generate Matrices");
        generateButton.setBackground(Color.BLUE);
        generateButton.setForeground(Color.WHITE);
        clearButton = new JButton("Clear");
        clearButton.setBackground(Color.RED);
        clearButton.setForeground(Color.white);
        multiplyButton = new JButton("Multiply");
        multiplyButton.setBackground(Color.green);
        multiplyButton.setForeground(Color.white);
        matrixPanel1 = new JPanel();
        matrixPanel2 = new JPanel();
        resultPanel = new JPanel();

        // Set layout for the main frame
        setLayout(new BorderLayout());

        // Create a panel for input fields and buttons
        JPanel inputPanel = new JPanel(new GridLayout(3, 7, 5, 5));
        inputPanel.setBorder(new EmptyBorder(30, 5, 5, 5)); // Add padding
        inputPanel.add(matrix1Label);
        inputPanel.add(new JLabel());
        inputPanel.add(generateButton);
        inputPanel.add(new JLabel());
        inputPanel.add(matrix2Label);
        inputPanel.add(rowsLabel1);
        inputPanel.add(rowsField1);
        inputPanel.add(multiplyButton);
        inputPanel.add(rowsLabel2);
        inputPanel.add(rowsField2);
        inputPanel.add(colsLabel1);
        inputPanel.add(colsField1);
        inputPanel.add(clearButton);
        inputPanel.add(colsLabel2);
        inputPanel.add(colsField2);
        // Add the input panel to the north of the main frame
        add(inputPanel, BorderLayout.NORTH);

        // Register event handlers for the buttons
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateMatrices();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearMatrices();
            }
        });

        multiplyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                multiplyMatrices();
            }
        });

        // Add the matrix panels to the center of the main frame
        JPanel matricesPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        matricesPanel.add(new JScrollPane(matrixPanel1));
        matricesPanel.add(new JScrollPane(matrixPanel2));
        matricesPanel.add(new JScrollPane(resultPanel));
        add(matricesPanel, BorderLayout.CENTER);

        // Set frame properties
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame
        setVisible(true);
    }

    private void generateMatrices() {
        try {
            int rows1 = Integer.parseInt(rowsField1.getText());
            int cols1 = Integer.parseInt(colsField1.getText());
            int rows2 = Integer.parseInt(rowsField2.getText());
            int cols2 = Integer.parseInt(colsField2.getText());

            if (rows1 <= 0 || cols1 <= 0 || rows2 <= 0 || cols2 <= 0) {
                JOptionPane.showMessageDialog(this, "Rows and columns must be positive integers.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Clear existing components in the matrix panels
            matrixPanel1.removeAll();
            matrixPanel2.removeAll();
            resultPanel.removeAll();

            matrixPanel1.setLayout(new GridLayout(rows1, cols1, 5, 5)); // Add padding
            matrixPanel2.setLayout(new GridLayout(rows2, cols2, 5, 5)); // Add padding
            resultPanel.setLayout(new GridLayout(rows1, cols2, 5, 5)); // Add padding

            // Create JTextFields dynamically based on user input for Matrix 1
            for (int i = 0; i < rows1; i++) {
                for (int j = 0; j < cols1; j++) {
                    JTextField textField = new JTextField(5);
                    textField.setHorizontalAlignment(SwingConstants.CENTER);
                    Font boldFont = new Font(textField.getFont().getName(), Font.BOLD, 18);
                    textField.setFont(boldFont);
                    matrixPanel1.add(textField);
                }
            }

            // Create JTextFields dynamically based on user input for Matrix 2
            for (int i = 0; i < rows2; i++) {
                for (int j = 0; j < cols2; j++) {
                    JTextField textField = new JTextField(5);
                    textField.setHorizontalAlignment(SwingConstants.CENTER);
                    Font boldFont = new Font(textField.getFont().getName(), Font.BOLD, 18);
                    textField.setFont(boldFont);
                    matrixPanel2.add(textField);
                }
            }

            // Update the layout
            matrixPanel1.revalidate();
            matrixPanel1.repaint();
            matrixPanel2.revalidate();
            matrixPanel2.repaint();
            resultPanel.revalidate();
            resultPanel.repaint();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid integers for rows and columns.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void clearMatrices() {
        // Clear input fields and reset matrix panels
        rowsField1.setText("");
        colsField1.setText("");
        rowsField2.setText("");
        colsField2.setText("");
        matrixPanel1.removeAll();
        matrixPanel2.removeAll();
        resultPanel.removeAll();
        matrixPanel1.revalidate();
        matrixPanel1.repaint();
        matrixPanel2.revalidate();
        matrixPanel2.repaint();
        resultPanel.revalidate();
        resultPanel.repaint();
    }

    private void multiplyMatrices() {
        // Get the matrices data from the text fields
        int rows1 = Integer.parseInt(rowsField1.getText());
        int cols1 = Integer.parseInt(colsField1.getText());
        int rows2 = Integer.parseInt(rowsField2.getText());
        int cols2 = Integer.parseInt(colsField2.getText());

        double[][] matrix1 = getMatrixData(matrixPanel1, rows1, cols1);
        double[][] matrix2 = getMatrixData(matrixPanel2, rows2, cols2);

        // Perform matrix multiplication
        double[][] result = multiplyMatrices(matrix1, matrix2);

        // Display the result in the result panel
        if (result != null) {
            displayResult(resultPanel, result);
        }
    }


    private double[][] getMatrixData(JPanel matrixPanel, int rows, int cols) {
        Component[] components = matrixPanel.getComponents();
        double[][] matrixData = new double[rows][cols];

        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                JTextField textField = (JTextField) components[index++];
                matrixData[i][j] = Double.parseDouble(textField.getText());
            }
        }

        return matrixData;
    }


    private double[][] multiplyMatrices(double[][] matrix1, double[][] matrix2) {
        int rows1 = matrix1.length;
        int cols1 = matrix1[0].length;
        int rows2 = matrix2.length;
        int cols2 = matrix2[0].length;

        System.out.println(cols1);
        System.out.println(rows2);
        if (cols1 != rows2 && cols2 != rows1) {
            JOptionPane.showMessageDialog(this, "Matrix multiplication is not possible. The number of columns in Matrix 1 must be equal to the number of rows in Matrix 2.", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
            else{
            double[][] result = new double[rows1][cols2];

            for (int i = 0; i < rows1; i++) {
                for (int j = 0; j < cols2; j++) {
                    for (int k = 0; k < cols1; k++) {
                        result[i][j] += matrix1[i][k] * matrix2[k][j];
                    }
                }
            }
            return result;
        }

    }

    private void displayResult(JPanel resultPanel, double[][] result) {
        resultPanel.removeAll();
        resultPanel.setLayout(new GridLayout(result.length, result[0].length, 5, 5)); // Add padding

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                JTextField textField = new JTextField(5);
                textField.setText(String.valueOf(result[i][j]));
                textField.setHorizontalAlignment(SwingConstants.CENTER);
                Font boldFont = new Font(textField.getFont().getName(), Font.BOLD, 18);
                textField.setFont(boldFont);
                textField.setEditable(false);
                resultPanel.add(textField);
            }
        }

        resultPanel.revalidate();
        resultPanel.repaint();
    }


}