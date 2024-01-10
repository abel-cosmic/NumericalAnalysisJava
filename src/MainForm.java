import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainForm extends JFrame {
    public MainForm() {
        setTitle("Numerical Analysis Assignment");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));
        JButton btnMatrixMultiplication = createProblemButton("Matrix Multiplication", "A Java program that accepts two matrices and displays their product.");
        JButton btnInterpolation = createProblemButton("Interpolation", "A Java program that implements interpolation using divided difference formula. Your program should display the divided difference table and the corresponding equation.");
        JButton btnCramersRule = createProblemButton("Cramer's Rule", "A Java program that solves system of linear equations using Cramer’s rule. In your program, try to apply concepts of object-oriented programming and GUI.");
        JButton btnDeterminant = createProblemButton("Determinant", "A Java program that finds the determinant of a square matrix of any row and column size.");
        JButton btnGroupMembers = new JButton("Group Members");
        btnGroupMembers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GroupMembersList.displayGroupMembers();
            }
        });
        panel.add(btnMatrixMultiplication);
        panel.add(btnInterpolation);
        panel.add(btnCramersRule);
        panel.add(btnDeterminant);
        panel.add(btnGroupMembers);
        add(panel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JButton createProblemButton(String problemTitle, String problemDescription) {
        JButton button = new JButton(problemTitle);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displaySolution(problemTitle, problemDescription);
            }
        });
        return button;
    }

    private void displaySolution(String problemTitle, String problemDescription) {
        int option = JOptionPane.showOptionDialog(
                this,
                problemDescription,
                "Solution for " + problemTitle,
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                new Object[]{"OK"},
                null);
        if (option == 0) {
            openSolverClass(problemTitle);
        }
    }

    private void openSolverClass(String problemTitle) {
        switch (problemTitle) {
            case "Matrix Multiplication":
                MatrixMultiplier matrixSolver = new MatrixMultiplier();
                matrixSolver.setVisible(true);
                break;
            case "Interpolation":
                NewtonInterpolation interpolationSolver = new NewtonInterpolation();
                interpolationSolver.setVisible(true);
                break;
            case "Cramer's Rule":
                CreamersRuleSolver cramerSolver = new CreamersRuleSolver();
                cramerSolver.setVisible(true);
                break;
            case "Determinant":
//                DeterminantSolver determinantSolver = new DeterminantSolver();
//                determinantSolver.setVisible(true);
                break;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainForm();
            }
        });
    }
}

class GroupMembersList {
    public static void displayGroupMembers() {
        String[] members = {"Abel Shibabaw RCS/291/21","Dagim Tesfaye RCS/300/21" ,"Mohammed Ibrahim RCS/2013/22"};
        String membersList = String.join("\n", members);
        JOptionPane.showMessageDialog(null, "Group Members:\n" + membersList, "Group Members List", JOptionPane.INFORMATION_MESSAGE);
    }
}
