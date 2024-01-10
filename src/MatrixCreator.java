import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MatrixCreator extends JFrame {
    private JTextField rowsField, colsField;
    private final JPanel matrixPanel;
    private JButton generateButton, clearButton;

    public MatrixCreator() {
        super("Matrix Generator");
        JLabel rowsLabel = new JLabel("Rows:");
        JLabel colsLabel = new JLabel("Columns:");
        rowsField = new JTextField(5);
        colsField = new JTextField(5);
        generateButton = new JButton("Generate Matrix");
        clearButton = new JButton("Clear");
        matrixPanel = new JPanel();
        setLayout(new BorderLayout());
        JPanel inputPanel = new JPanel(new GridLayout(1, 8));
        inputPanel.add(rowsLabel);
        inputPanel.add(rowsField);
        inputPanel.add(colsLabel);
        inputPanel.add(colsField);
        inputPanel.add(generateButton);
        inputPanel.add(clearButton);

        // Add the input panel to the north of the main frame
        add(inputPanel, BorderLayout.NORTH);

        // Register event handlers for the buttons
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateMatrix();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearMatrix();
            }
        });

        // Add the matrix panel to the center of the main frame
        add(new JScrollPane(matrixPanel), BorderLayout.CENTER);

        // Set frame properties
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame
        setVisible(true);
    }

    private void generateMatrix() {
        try {
            int rows = Integer.parseInt(rowsField.getText());
            int cols = Integer.parseInt(colsField.getText());

            if (rows <= 0 || cols <= 0) {
                JOptionPane.showMessageDialog(this, "Rows and columns must be positive integers.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Clear existing components in the matrix panel
            matrixPanel.removeAll();
            matrixPanel.setLayout(new GridLayout(rows, cols));

            // Create JTextFields dynamically based on user input
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    JTextField textField = new JTextField(5);
                    matrixPanel.add(textField);
                }
            }

            // Update the layout
            matrixPanel.revalidate();
            matrixPanel.repaint();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid integers for rows and columns.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearMatrix() {
        rowsField.setText("");
        colsField.setText("");
        matrixPanel.removeAll();
        matrixPanel.revalidate();
        matrixPanel.repaint();
    }
}
