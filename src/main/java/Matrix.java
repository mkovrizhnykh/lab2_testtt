public class Matrix
{
    private static void Print_Result_Matrix(double[][] matrix)
    {
        System.out.println("Result matrix:");
        for(int i=0;i<matrix.length;i++)
        {
            for(int j=0;j<matrix[0].length;j++)
                System.out.print(matrix[i][j]+" ");
            System.out.println();
        }
    }




    public static double[][] Multiplication(double[][] firstMatrix, double[][] secondMatrix)
    {
        /*System.out.println("A:");
        Print_Result_Matrix(firstMatrix);
        System.out.println("B:");
        Print_Result_Matrix(secondMatrix);*/
        double[][] result = new double[firstMatrix.length][secondMatrix[0].length];

        for (int row = 0; row < result.length; row++)
        {
            for (int col = 0; col < result[row].length; col++)
            {
                result[row][col] = Multiply_Matrices_Cell(firstMatrix, secondMatrix, row, col);
            }
        }
        Print_Result_Matrix(result);
        return result;
    }
    private static double Multiply_Matrices_Cell(double[][] firstMatrix, double[][] secondMatrix, int row, int col)
    {
        double cell = 0;
        for (int i = 0; i < secondMatrix.length; i++)
        {
            cell += firstMatrix[row][i] * secondMatrix[i][col];
        }
        return cell;
    }





    public static double[][] Addition(double[][] firstMatrix, double[][] secondMatrix)
    {
        double[][] result = new double[firstMatrix.length][secondMatrix[0].length];
        for (int row = 0; row < result.length; row++)
        {
            for (int col = 0; col < result[row].length; col++)
            {
                result[row][col] = firstMatrix[row][col] + secondMatrix[row][col];
            }
        }
        Print_Result_Matrix(result);
        return result;
    }





    public static double[][] Subtraction(double[][] firstMatrix, double[][] secondMatrix)
    {
        double[][] result = new double[firstMatrix.length][secondMatrix[0].length];
        for (int row = 0; row < result.length; row++)
        {
            for (int col = 0; col < result[row].length; col++)
            {
                result[row][col] = firstMatrix[row][col] - secondMatrix[row][col];
            }
        }
        Print_Result_Matrix(result);
        return result;
    }





    public static double[][] Division(double[][] firstMatrix, double[][] secondMatrix)
    {
        if (secondMatrix.length != secondMatrix[0].length)
        {
            System.out.println("Matrix B must be quadratic!");
            return secondMatrix;
        }
        int N = secondMatrix.length;
        double[][] result = new double[firstMatrix.length][secondMatrix[0].length];
        double[][] reverse_secondMatrix = new double[secondMatrix.length][secondMatrix.length];

        double temp;
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
            {
                reverse_secondMatrix[i][j] = 0f;
                if (i == j)
                    reverse_secondMatrix[i][j] = 1f;
            }

        for (int k = 0; k < N; k++)
        {
            temp = secondMatrix[k][k];
            for (int j = 0; j < N; j++)
            {
                secondMatrix[k][j] /= temp;
                reverse_secondMatrix[k][j] /= temp;
            }
            for (int i = k + 1; i < N; i++)
            {
                temp = secondMatrix[i][k];
                for (int j = 0; j < N; j++)
                {
                    secondMatrix[i][j] -= secondMatrix[k][j] * temp;
                    reverse_secondMatrix[i][j] -= reverse_secondMatrix[k][j] * temp;
                }
            }
        }
        for (int k = N - 1; k > 0; k--)
        {
            for (int i = k - 1; i >= 0; i--)
            {
                temp = secondMatrix[i][k];
                for (int j = 0; j < N; j++)
                {
                    secondMatrix[i][j] -= secondMatrix[k][j] * temp;
                    reverse_secondMatrix[i][j] -= reverse_secondMatrix[k][j] * temp;
                }
            }
        }
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                secondMatrix[i][j] = reverse_secondMatrix[i][j];

        result = Multiplication(reverse_secondMatrix,firstMatrix);
        return result;
    }
}

