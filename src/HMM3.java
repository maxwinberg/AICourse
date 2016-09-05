import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by MaxWinLaptop on 2016-09-05.
 */
public class HMM3 {

    private int n;
    private int k;
    private double[][] A;
    private double[][] B;
    private double[][] pi;
    private int[] observations;

    private double[][] delta;
    private int[][] psi;
    private BufferedReader reader;
    //Checkout DI-gamma and gamma algorith,

    public HMM3(){

        try{
            //Retrieving the input first line which has information about Matrix A
            reader = new BufferedReader(new InputStreamReader(System.in));

            A = MatrixHandler.retrieveMatrix(reader.readLine().split(" "));
            n = A.length;
            B = MatrixHandler.retrieveMatrix(reader.readLine().split(" "));
            k = B[0].length;
            pi = MatrixHandler.retrieveMatrix(reader.readLine().split(" "));
            observations = MatrixHandler.retrieveVector(reader.readLine().split(" "));

            delta = new double[observations.length][A.length];
            psi = new int[observations.length][A.length];

            int[] path = MatrixHandler.viterbi(delta, psi, A, B, pi, observations);


        }catch(Exception e){
            // System.out.println("nooooo!");
        }
    }


    public static void main(String[] args){

        new HMM3();
    }

}
