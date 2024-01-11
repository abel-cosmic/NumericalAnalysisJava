import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DeterminantSolver extends JFrame {
    private final JTextField rowsField;
    public final JPanel matrixPanel;
    static ArrayList<ArrayList<Integer>> matrix;
    public DeterminantSolver() {
        super("Square Matrix Calculator");
        JLabel rowsLabel = new JLabel("Size");
        rowsField = new JTextField(5);
        JButton generateButton = new JButton("Generate Square Matrix");
        JButton clearButton = new JButton("Clear");
        JButton calcButton = new JButton("Calculate Determinant");
        matrixPanel = new JPanel();
        setLayout(new BorderLayout());
        JPanel inputPanel = new JPanel(new GridLayout(1, 8));
        inputPanel.add(rowsLabel);
        inputPanel.add(rowsField);
        inputPanel.add(generateButton);
        inputPanel.add(clearButton);
        inputPanel.add(calcButton);
        // Add the input panel to the north of the main frame
        add(inputPanel, BorderLayout.NORTH);
        // Register event handlers for the buttons
        generateButton.addActionListener(e -> generateMatrix());
        clearButton.addActionListener(e -> clearMatrix());
        calcButton.addActionListener(e -> calcDeterminant());
        // Add the matrix panel to the center of the main frame
        add(new JScrollPane(matrixPanel), BorderLayout.CENTER);
        matrix = new ArrayList<>();
        // Set frame properties
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame
        setVisible(true);
    }
    private ArrayList<ArrayList<Integer>> getMatrixData(DeterminantSolver matrixPanel, int rows) {
        Component[] components = matrixPanel.matrixPanel.getComponents(); // Use matrixPanel.matrixPanel to get components
        ArrayList<ArrayList<Integer>> matrixData = new ArrayList<>();
        int index = 0;
        for (int i = 0; i < rows; i++) {
            ArrayList<Integer> rowList = new ArrayList<>();
            for (int j = 0; j < rows; j++) {
                JTextField textField = (JTextField) components[index++];
                String text = textField.getText().trim();
                if (!text.isEmpty()) {
                    rowList.add(Integer.parseInt(text));
                } else {
                    // Handle the case when the text field is empty (you can set a default value or show an error)
                    rowList.add(0); // Set a default value to 0
                }
            }
            matrixData.add(rowList);
        }
        return matrixData;
    }
    private void generateMatrix() {
        try {
            int rows = Integer.parseInt(rowsField.getText());
            // Clear existing components in the matrix panel
            matrixPanel.removeAll();
            matrixPanel.setLayout(new GridLayout(rows, rows));
            matrix = new ArrayList<>();
            // Create JTextFields dynamically based on user input
            for (int i = 0; i < rows; i++) {
                ArrayList<Integer> rowList = new ArrayList<>();
                for (int j = 0; j < rows; j++) {
                    JTextField textField = new JTextField(5);
                    textField.setHorizontalAlignment(SwingConstants.CENTER);
                    Font boldFont = new Font(textField.getFont().getName(), Font.BOLD, 24);
                    textField.setFont(boldFont);
                    matrixPanel.add(textField);
                    rowList.add(0);
                }
                matrix.add(rowList);
            }
            // Update the layout
            matrixPanel.revalidate();
            matrixPanel.repaint();
        } catch (NumberFormatException ex) {
//            System.out.println(ex);
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid integers for rows and columns.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void calcDeterminant() {
        matrix = getMatrixData(this, matrix.size());
        int determinant = MatrixUtils.calculateDeterminant(matrix);
        JOptionPane.showMessageDialog(this, "Determinant: " + determinant, "Determinant Result", JOptionPane.INFORMATION_MESSAGE);
    }
    private void clearMatrix() {
        rowsField.setText("");
        matrixPanel.removeAll();
        matrix.clear();
        matrixPanel.revalidate();
        matrixPanel.repaint();
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(DeterminantSolver::new);
    }
}
