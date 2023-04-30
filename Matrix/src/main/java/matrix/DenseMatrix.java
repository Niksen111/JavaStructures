package matrix;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
* Dense matrix
* */
public class DenseMatrix implements Matrix {
    private List<List<Double>> matrixList = new ArrayList<>();
    private int hashCode = 0;

    /**
     * Creates a matrix for the given list.
     * @param list given list.
     * @throws IllegalArgumentException this list cannot be converted.
     */
    public DenseMatrix(List<List<Double>> list) throws IllegalArgumentException{
        if (list.isEmpty() || list.get(0).isEmpty())
            throw new IllegalArgumentException("Matrix width 0.");
        var firstSize = list.get(0).size();
        for (var row : list) {
            if (row.size() != firstSize)
                throw new IllegalArgumentException("Matrix has non-rectangular dimensions");
        }

        this.matrixList = list;
        hashCode = this.matrixList.hashCode();
    }

    /**
     * Creates a null matrix with specified dimensions.
     */
    public DenseMatrix(int height, int width)
    {
        List<Double> temp = new ArrayList<>();
        for (int i = 0; i < width; i++)
        {
            temp.add(0.d);
        }
        for (int i = 0; i < height; i++)
        {
            matrixList.add(new ArrayList<>(temp));
        }

        hashCode = this.matrixList.hashCode();
    }

    /**
     * Creates a matrix from a given file.
     * @param fileName path to file.
     */
    public DenseMatrix(String fileName) throws IOException {
        var reader = new BufferedReader(new FileReader(fileName));
        String line = reader.readLine();
        int width = 0;
        if (line != null) {
            width = line.split(" ").length;
        }
        while (line != null) {
            List<Double> buffer =
                    Arrays.stream(line.split(" "))
                            .mapToDouble(Double::parseDouble)
                            .boxed()
                            .collect(Collectors.toList());

            if (width != buffer.size()) {
                this.matrixList = null;
                throw new IOException("Incorrect format of the matrix in the file");
            }

            this.matrixList.add(buffer);
            line = reader.readLine();
        }

        hashCode = this.matrixList.hashCode();
    }

    @Override
    public Matrix multiply(Matrix matrix) throws NonMultiplicativeMatricesException {
        if (getWidth() != matrix.getHeight())
            throw new NonMultiplicativeMatricesException();
        var result = new DenseMatrix(getHeight(), matrix.getWidth());
        for (int i = 0; i < getHeight(); ++i) {
            for (int j = 0; j < matrix.getWidth(); ++j)
            {
                for (int k = 0; k < getWidth(); ++k) {
                    result.setElement(i, j, result.getElement(i, j) + getElement(i, k) * matrix.getElement(k, j));
                }
            }
        }

        return result;
    }

    @Override
    public Matrix Transposition() {
        var newMatrix = new DenseMatrix(getWidth(), getHeight());
        for (int i = 0; i < getWidth(); ++i) {
            for (int j = 0; j < getHeight(); ++j) {
                newMatrix.setElement(i, j, getElement(j, i));
            }
        }

        return newMatrix;
    }

    @Override
    public int getHeight() {
        return matrixList.size();
    }

    @Override
    public int getWidth() {
        return matrixList.get(0).size();
    }

    @Override
    public List<List<Double>> getList() {
        return this.matrixList;
    }

    @Override
    public Double getElement(int i, int j) {
        return matrixList.get(i).get(j);
    }

    @Override
    public void setElement(int i, int j, Double value) {
        matrixList.get(i).set(j, value);
    }

    @Override
    public String toString() {
        if (this.matrixList == null)
            return "";
        StringBuilder result = new StringBuilder();
        for (List<Double> row: this.matrixList)
        {
            for (double number: row)
            {
                result.append(number).append(" ");
            }
            result.append("\n");
        }
        return result.toString();
    }

    @Override
    public int hashCode() {
        return hashCode;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DenseMatrix) {
            if (this == obj)
                return true;
            if (this.hashCode != ((DenseMatrix) obj).hashCode)
                return false;
            return this.matrixList.equals(((DenseMatrix) obj).getList());
        }
        else if (obj instanceof Matrix matrix) {
            if (matrix.getHeight() != getHeight() || matrix.getWidth() != getWidth())
                return false;
            for (int i = 0; i < getHeight(); ++i) {
                for (int j = 0; j < getWidth(); ++j) {
                    if (!matrix.getElement(i,j).equals(getElement(i, j)))
                    {
                        return false;
                    }
                }
            }
            
            return true;
        }
        else return false;
    }
}
