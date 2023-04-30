package matrix;

import java.util.List;

/**
* Matrix interface
* */
public interface Matrix {
    /**
     * multiplies the matrix by the given
     * @param matrix matrix by which the initial matrix will be multiplied from the right
     * @return result of multiplication
     */
    Matrix multiply(Matrix matrix);

    /**
     * @return transposed matrix
     */
    Matrix Transposition();

    /**
     * @return height of the matrix
     */
    int getHeight();

    /**
     * @return width of the matrix
     */
    int getWidth();

    /**
     * @return two-dimensional list of matrix elements
     */
    List<List<Double>> getList();

    /**
     * @param i row index
     * @param j column index
     * @return element at these coordinates
     */
    Double getElement(int i, int j) throws IndexOutOfBoundsException;
}
