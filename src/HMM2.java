import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Created by MaxWinLaptop on 2016-09-04.
 */
public class HMM2 {


    private int n;
    private int k;
    private double[][] A;
    private double[][] B;
    private double[][] pi;
    private int[] observations;

    private double[][] probabilities;
    private BufferedReader reader;
    //Checkout DI-gamma and gamma algorith,

    public HMM2(){

        try{
            //Retrieving the input first line which has information about Matrix A

            reader = new BufferedReader(new InputStreamReader(System.in));
            A = MatrixHandler.retrieveMatrix(reader.readLine().split(" "));
            n = A.length;
            B = MatrixHandler.retrieveMatrix(reader.readLine().split(" "));
            k = B[0].length;
            pi = MatrixHandler.retrieveMatrix(reader.readLine().split(" "));
            observations = MatrixHandler.retrieveVector(reader.readLine().split(" "));

            probabilities = new double[observations.length][A.length];

            //printMatrix(A);
            //printMatrix(B);
            //printMatrix(pi);
            double[][] results = (MatrixHandler.multiplyMatrix(MatrixHandler.multiplyMatrix(pi, A), B));
            //printMatrix(results);
            double sum = MatrixHandler.alpha(probabilities, A, B, pi, observations);

            System.out.println(sum);
            //System.out.println("The number: " + sum);
            //printMatrix(probabilities);
        }catch(Exception e){
           // System.out.println("nooooo!");
        }
    }


    public static void main(String[] args){

        new HMM2();
    }
}
