import java.io.File;
import java.util.Scanner;

/**
 * Created by MaxWinLaptop on 2016-09-04.
 */
public class HMM2 {

    private int n;
    private int k;
    private float[][] A;
    private float[][] B;
    private float[][] pi;
    private int[] observations;

    private float[][] probabilities;

    public HMM2(){

        try{
            //Retrieving the input first line which has information about Matrix A
            //Scanner scan = new Scanner(new File("hmm2_01.in"));
            Scanner scan = new Scanner(new File("playtime.txt"));
            A = retrieveMatrix(scan);
            n = A.length;
            B = retrieveMatrix(scan);
            k = B[0].length;
            pi = retrieveMatrix(scan);
            observations = retrieveObservations(scan);
            probabilities = new float[observations.length][A.length];

            printMatrix(A);
            printMatrix(B);
            printMatrix(pi);
            float[][] results = (MatrixHandler.multiplyMatrix(MatrixHandler.multiplyMatrix(pi, A), B));
            printMatrix(results);
            float sum = alpha();
            System.out.println("The number: " + sum);
            printMatrix(probabilities);
        }catch(Exception e){
            System.out.println("nooooo!");
        }
    }

    private float alpha(){

        //Initializing the dynamic programming procedure
        for(int i = 0; i < A.length; i++){
            probabilities[0][i] = pi[0][i] * B[i][observations[0]];
        }

        float sum = 0;
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

    private int[] retrieveObservations(Scanner scan){

        String[] input = scan.nextLine().split(" ");

        for(int k = 0; k < input.length; k++){
            System.out.print(input[k] + ", ");
        }

        int[] observations = new int[Integer.parseInt(input[0])];
        for(int i = 0; i < input.length-1; i++){
            observations[i] = Integer.parseInt(input[i+1]);
        }

        return observations;
    }

    private float[][] retrieveMatrix(Scanner scan){
        String[] input = scan.nextLine().split(" ");

        for(int k = 0; k < input.length; k++){
            System.out.print(input[k] + ", ");
        }

        int rows = Integer.parseInt(input[0]);
        int columns = Integer.parseInt(input[1]);


        //Loading the matrix A
        float[][] matrix = new float[rows][columns];
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                matrix[i][j] = Float.parseFloat(input[(i*columns) + j + 2]);
            }
        }

        return matrix;
    }

    public void printMatrix(float[][] matrix){

        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args){

        new HMM2();
    }
}
