package matrix;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public Matrix multiply(Matrix matrix) {
        return null;
    }

    @Override
    public Matrix Transposition() {
        return null;
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
        if (obj instanceof DenseMatrix)
        {
            if (this == obj)
                return true;
            if (this.hashCode != ((DenseMatrix) obj).hashCode)
                return false;
            return this.matrixList.equals(((DenseMatrix) obj).getList());
        }
        else return false;
    }
}
