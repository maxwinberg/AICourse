import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by MaxWinLaptop on 2016-09-07.
 */
public class HMM4 {

    private int n;
    private int k;
    private double[][] A;
    private double[][] B;
    private double[][] pi;
    private int[] observations;

    private double[][] delta;
    private int[][] psi;
    private BufferedReader reader;

    public HMM4(){

        try{
            //Retrieving the input first line which has information about Matrix A
            reader = new BufferedReader(new InputStreamReader(System.in));

            A = MatrixHandler.retrieveMatrix(reader.readLine().split(" "));
            n = A.length;
            B = MatrixHandler.retrieveMatrix(reader.readLine().split(" "));
            k = B[0].length;
            pi = MatrixHandler.retrieveMatrix(reader.readLine().split(" "));


            int[] totalObs = MatrixHandler.retrieveVector(reader.readLine().split(" "));
            int size;
            if(totalObs.length > 500){
                size = 500;
            }else{
                size = totalObs.length;
            }
            observations = new int[size];
            for(int i = 0; i < observations.length; i++){
                observations[i] = totalObs[i];
            }


            double highestThreashold = 0;
            double[][] alphaM;
            ArrayList<double[][]> digamma;
            double[][] gamma;
            double[][] newA;
            double[][] newB;
            double[][] newPi;
            int count = 0;
            //MatrixHandler.vectorPrint(observations);
            do{
                alphaM = new double[observations.length][A.length];
                MatrixHandler.alpha(alphaM, A, B, pi, observations);
                double [][] betaM =MatrixHandler.beta(A, B, observations);
                digamma = MatrixHandler.digamma(alphaM, betaM, A, B, observations);
                //System.out.println("DIGAMMA:");
                //MatrixHandler.printMatrix(digamma.get(0));
                gamma = MatrixHandler.gamma(digamma);
                //System.out.println("GAMMA:");
                //MatrixHandler.printMatrix(gamma);
                newA = MatrixHandler.trainAMatrix(digamma, gamma);
                newB = MatrixHandler.trainBMatrix(gamma, observations, B[0].length);
                newPi = MatrixHandler.trainpiMatrix(gamma);

                highestThreashold=Math.max(MatrixHandler.deltaMax(A, newA) , MatrixHandler.deltaMax(B,newB));

                A=newA;
                B = newB;
                pi = newPi;
                //System.out.println("Threashold: " + highestThreashold);
                count++;
                if(count > 100){
                    System.out.println("Breaking after 1000");
                    break;
                }

            }while(highestThreashold > 0.000000001);

            MatrixHandler.outputMatrix(A);
            MatrixHandler.outputMatrix(B);
            //MatrixHandler.outputMatrix(pi);

        }catch(Exception e){
             System.out.println("nooooo!");
        }
    }

    public static void main(String[] args){
        new HMM4();
    }

}
