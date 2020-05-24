import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
class MatrixTest {
    @DisplayName("Multiplication test")
    @Test
    void multiplication() {
        double[][] A = {{1,2,3},{4,5,6}};
        double[][] B = {{7,8},{9,1},{2,3}};
        double[][] Expected = {{31,19},{85,55}};
        assertArrayEquals( Expected, Matrix.Multiplication(A,B));
    }
    @DisplayName("Addition test")
    @Test
    void addition() {
        double[][] A = {{2,-3,1},{5,4,-2}};
        double[][] B = {{4,2,-5},{-4,1,3}};
        double[][] Expected = {{6,-1,-4},{1,5,1}};
        assertArrayEquals( Expected, Matrix.Addition(A,B));
    }
    @DisplayName("Subtraction test")
    @Test
    void subtraction() {
        double[][] A = {{5,1,6},{-1,7,9},{-8,2,3}};
        double[][] B = {{8,1,4},{5,-5,7},{-3,-2,7}};
        double[][] Expected = {{-3,0,2},{-6,12,2},{-5,4,-4}};
        assertArrayEquals( Expected, Matrix.Subtraction(A,B));
    }
    @DisplayName("Division test")
    @Test
    void division() {
        double[][] A = {{1,3},{5,6}};
        double[][] B = {{6,2},{4,3}};
        double[][] Expected = {{-0.7,-0.30000000000000016},{2.6,2.3999999999999995}};
        assertArrayEquals( Expected, Matrix.Division(A,B));
    }
    @DisplayName("Multiplication test with input files (A.txt, B.txt, C.txt")
    @Test
    void multiplication_file() {
        int[]Sizes = new int [2];
        ArrayList<String> Massive = new ArrayList<String>();
        Get_matrix("Input_files/resources/A.txt", Massive, Sizes);
        double[][] A = new double[Sizes[0]][Sizes[1]];
        Fill_Matrix(A,Massive,Sizes);
        Get_matrix("Input_files/resources/B.txt", Massive, Sizes);
        double[][] B = new double[Sizes[0]][Sizes[1]];
        Fill_Matrix(B,Massive,Sizes);
        Get_matrix("Input_files/resources/C.txt", Massive, Sizes);
        double[][] C = new double[Sizes[0]][Sizes[1]];
        Fill_Matrix(C,Massive,Sizes);
        assertArrayEquals(C, Matrix.Multiplication(A,B));
    }
    void Get_matrix(String File, ArrayList<String> Massive, int[]Sizes) {
        try (BufferedReader Reader = new BufferedReader(new FileReader(File))){
            String Line;
            while ((Line = Reader.readLine()) != null) {
                Massive.add(Line);
            }
        }
        catch (Exception s) {
            System.out.println("Error message:\n" + s + "\nAbort test");
            return;
        }
        Sizes[0] = Massive.size();
        Sizes[1] = 0;
        for (char Letter:Massive.get(0).toCharArray()) {
            if (Letter == ' ')Sizes[1]++;
        }
        Sizes[1]++;
    }
    void Fill_Matrix(double[][]Matrix, ArrayList<String> Massive, int[]Sizes){
        for (int Row = 0; Row < Sizes[0]; Row++){
            String Line[]=Massive.get(Row).split(" ");
            for (int Column = 0; Column < Sizes[1]; Column++){
                Matrix[Row][Column] = Integer.parseInt(Line[Column]);
            }
        }
        Massive.clear();
        Sizes[0]=0;
        Sizes[1]=0;
    }
    @DisplayName("Division test with timeout")
    @Test
    @Timeout(value = 10000000, unit = TimeUnit.NANOSECONDS)
    void division_timeout() {
        double[][] A = {{1,3},{5,6}};
        double[][] B = {{6,2},{4,3}};
        double[][] Expected = {{-0.7,-0.30000000000000016},{2.6,2.3999999999999995}};
        assertArrayEquals( Expected, Matrix.Division(A,B));
    }
    @DisplayName("Multiplication test with timeout")
    @Test
    @Timeout(value = 10000000, unit = TimeUnit.NANOSECONDS)
    void multiplication_timeout() {
        double[][] A = {{1,2,3},{4,5,6}};
        double[][] B = {{7,8},{9,1},{2,3}};
        double[][] Expected = {{31,19},{85,55}};
        assertArrayEquals( Expected, Matrix.Multiplication(A,B));
    }
    @DisplayName("Subtraction test with timeout")
    @Test
    @Timeout(value = 10000000, unit = TimeUnit.NANOSECONDS)
    void subtraction_timeout() {
        double[][] A = {{5,1,6},{-1,7,9},{-8,2,3}};
        double[][] B = {{8,1,4},{5,-5,7},{-3,-2,7}};
        double[][] Expected = {{-3,0,2},{-6,12,2},{-5,4,-4}};
        assertArrayEquals( Expected, Matrix.Subtraction(A,B));
    }
    @DisplayName("Addition test with timeout")
    @Test
    @Timeout(value = 10000000, unit = TimeUnit.NANOSECONDS)
    void addition_time() {
        double[][] A = {{2,-3,1},{5,4,-2}};
        double[][] B = {{4,2,-5},{-4,1,3}};
        double[][] Expected = {{6,-1,-4},{1,5,1}};
        assertArrayEquals( Expected, Matrix.Addition(A,B));
    }
    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    public class Parameters_Test
    {
        private Iterable<double[][][]> dataForTest() {
            return Arrays.asList(new double[][][][]{
                    {{{2,-3,1},{5,4,-2}},{{4,2,-5},{-4,1,3}},{{6,-1,-4},{1,5,1}}},
                    {{{1,2},{3,4}},{{5,6},{7,8}},{{6,8},{10,12}}},
                    {{{6,8},{10,12}},{{-1,3},{-5,-10}},{{5,11},{5,2}}}
            });
        }
        @DisplayName("Matrix addition test nested parametrized ")
        @ParameterizedTest
        @MethodSource("dataForTest")
        public void paramTest(double[][][] Input) {
            assertArrayEquals(Input[2], Matrix.Addition(Input[0],Input[1]));
        }
    }

}