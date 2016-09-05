

/**
 * Created by MaxWinLaptop on 2016-09-02.
 */
public class MatrixHandler {

    public static double[][] multiplyMatrix(double[][] firstMatrix, double[][] secondMatrix){

        /* Create another 2d array to store the result using the original arrays' lengths on row and column respectively. */
        double[][] result = new double[firstMatrix.length][secondMatrix[0].length];

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


    public static int[] retrieveVector(String[] input){


        /*
        for(int k = 0; k < input.length; k++){
            System.out.print(input[k] + ", ");
        }
        */
        int[] vector = new int[Integer.parseInt(input[0])];
        for(int i = 0; i < input.length-1; i++){
            vector[i] = Integer.parseInt(input[i+1]);
        }

        return vector;
    }



    public static double[][] retrieveMatrix(String[] input){

        /*
        for(int k = 0; k < input.length; k++){

            System.out.print(input[k] + ", ");
        }
        */

        int rows = Integer.parseInt(input[0]);
        int columns = Integer.parseInt(input[1]);

        //Loading the matrix A
        double[][] matrix = new double[rows][columns];
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                matrix[i][j] = Double.parseDouble(input[(i*columns) + j + 2]);
            }
        }

        return matrix;
    }

    public static void printMatrix(double[][] matrix){

        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static double alpha(double[][] probabilities, double[][] A, double[][] B, double[][] pi, int[] observations){

        //Initializing the dynamic programming procedure
        for(int i = 0; i < A.length; i++){
            probabilities[0][i] = pi[0][i] * B[i][observations[0]];
        }

        double sum = 0;
        for(int t = 0; t < observations.length-1; t++){

            for(int i = 0; i < A.length; i++){
                sum = 0;

                for(int j = 0; j < A.length; j++){
                    sum += probabilities[t][j] * A[j][i];

                }
                probabilities[t+1][i] = sum * B[i][observations[t+1]];
            }

        }
        sum = 0;
        for(int k = 0; k < probabilities[0].length; k++){
            sum += probabilities[observations.length-1][k];
        }
        return sum;
    }

}
