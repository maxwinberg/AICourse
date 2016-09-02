/**
 * Created by MaxWinLaptop on 2016-09-02.
 */
public class MatrixHandler {

    public static double[][] multiplyMatrix(double[][] firstMatrix, double[][] secondMatrix){

        /* Create another 2d array to store the result using the original arrays' lengths on row and column respectively. */
        double [][] result = new double[firstMatrix.length][secondMatrix[0].length];

        /* Loop through each and get product, then sum up and store the value */
        for (int i = 0; i < firstMatrix.length; i++) {
            for (int j = 0; j < secondMatrix[0].length; j++) {
                for (int k = 0; k < firstMatrix[0].length; k++) {
                    result[i][j] += firstMatrix[i][k] * secondMatrix[k][j];
                }
            }
        }

        return result;
    }
}
