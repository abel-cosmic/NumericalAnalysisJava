import java.util.ArrayList;

public class MatrixUtils {

    // Calculate determinant of a square matrix
    public static int calculateDeterminant(ArrayList<ArrayList<Integer>> matrix) {
        int size = matrix.size();
        if (size == 1) {
            return matrix.get(0).get(0);
        } else if (size == 2) {
            // For a 2x2 matrix
            return matrix.get(0).get(0) * matrix.get(1).get(1) - matrix.get(0).get(1) * matrix.get(1).get(0);
        } else {
            int determinant = 0;
            for (int i = 0; i < size; i++) {
                determinant += matrix.get(0).get(i) * getCofactor(matrix, 0, i);
            }
            return determinant;
        }
    }

    // Get the cofactor of a matrix element
    private static int getCofactor(ArrayList<ArrayList<Integer>> matrix, int row, int col) {
        int sign = (int) Math.pow(-1, row + col);
        return sign * calculateDeterminant(getMinor(matrix, row, col));
    }

    // Get the minor of a matrix element
    private static ArrayList<ArrayList<Integer>> getMinor(ArrayList<ArrayList<Integer>> matrix, int row, int col) {
        int size = matrix.size();
        ArrayList<ArrayList<Integer>> minor = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            if (i != row) {
                ArrayList<Integer> rowList = new ArrayList<>();
                for (int j = 0; j < size; j++) {
                    if (j != col) {
                        rowList.add(matrix.get(i).get(j));
                    }
                }
                minor.add(rowList);
            }
        }

        return minor;
    }
}
