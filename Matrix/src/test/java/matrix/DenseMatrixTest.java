package matrix;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DenseMatrixTest {

    @Test
    public void multiplyDD()
    {
        try {
            Matrix m1 = new DenseMatrix("m1.txt");
            Matrix m2 = new DenseMatrix("m2.txt");
            Matrix expected = new DenseMatrix("result.txt");
            assertEquals(expected, m1.multiply(m2));
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public void multiplyIncorrectDense()
    {
        try {
            Matrix m1 = new DenseMatrix("m.txt");
            Matrix m2 = new DenseMatrix("m2.txt");
            Matrix expected = new DenseMatrix("empty.txt");
            assertEquals(expected, m1.multiply(m2));
        } catch (IOException e) {
            fail();
        }
    }
    @Test
    public void multiplyDE() throws RuntimeException {
        try {
            Matrix m1 = new DenseMatrix("m1.txt");
            Matrix m2 = new DenseMatrix("empty.txt");
            Matrix expected = new DenseMatrix("m1.txt");
            assertEquals(expected, m1.multiply(m2));
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public void multiplyED()
    {
        try {
            Matrix m1 = new DenseMatrix("empty.txt");
            Matrix m2 = new DenseMatrix("m2.txt");
            Matrix expected = new DenseMatrix("m2.txt");
            assertEquals(expected, m1.multiply(m2));
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public  void multiplyEE()
    {
        try {
            Matrix m1 = new DenseMatrix("empty.txt");
            Matrix m2 = new DenseMatrix("empty.txt");
            Matrix expected = new DenseMatrix("empty.txt");
            assertEquals(expected, m1.multiply(m2));
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public void readDenseMatrix()
    {
        Matrix m = null;
        try {
            m = new DenseMatrix("m.txt");
        } catch (IOException e) {
            fail();
        }

        double[][] arr = {{13.7, 15.2, 17.1, 0, 0.2}, {14, -4.1, -16, 0, 3}, {2, 0, 2.3, 5, 9}, {0, 0, 0, 0, 0}};
        List<List<Double>> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++)
        {
            list.add(new ArrayList<>());
            for (int j = 0; j < arr[0].length; j++)
            {
                list.get(i).add(j, arr[i][j]);
            }
        }
        Matrix expected = new DenseMatrix(list);

        assertEquals(expected, m);
    }

    @Test
    public void readEmptyDenseMatrix()
    {
        Matrix m = null;
        try {
            m = new DenseMatrix("empty.txt");
        } catch (IOException e) {
            fail();
        }
        List<List<Double>> list = new ArrayList<>();
        Matrix expected = new DenseMatrix(list);
        assertEquals(expected, m);
    }
}