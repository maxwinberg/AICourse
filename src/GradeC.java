import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by MaxWinLaptop on 2016-09-09.
 */
public class GradeC {

    private int n;
    private int k;
    private double[][] A;
    private double[][] B;
    private double[][] pi;
    private int[] observations;

    private double[][] delta;
    private int[][] psi;
    private BufferedReader reader;

    public GradeC(){

        try{
            //Retrieving the input first line which has information about Matrix A
            reader = new BufferedReader(new InputStreamReader(System.in));
            // initilization


            A =new double[][]{ {0.54, 0.26, 0.20}, {0.19, 0.53, 0.28},{ 0.22, 0.18, 0.6}};
            n = A.length;
            B = new double[][]{ {0.5, 0.2, 0.11, 0.19}, { 0.22, 0.28, 0.23, 0.27},{  0.19, 0.21, 0.15, 0.45}};
            k = B[0].length;
            pi = new double [][]{{0.3 ,0.2, 0.5}};


            /*
            A =new double[][]{ {1/3, 1/3, 1/3}, {1/3, 1/3, 1/3},{1/3, 1/3, 1/3}};
            n = A.length;
            B = new double[][]{{0.25, 0.25, 0.25, 0.25}, {0.25, 0.25, 0.25, 0.25},{0.25, 0.25, 0.25, 0.25}};
            k = B[0].length;
            pi = new double [][]{{0.3 ,0.2, 0.5}};
            */


            /*
            A =new double[][]{ {1, 0, 0}, {0, 1, 0},{0, 0, 1}};
            n = A.length;
            B = new double[][]{ {0.5, 0.2, 0.11, 0.19}, { 0.22, 0.28, 0.23, 0.27},{  0.19, 0.21, 0.15, 0.45}};
            k = B[0].length;
            pi = new double [][]{{0 ,0, 1}};
            */

            /*
            A =new double[][]{ {0.69, 0.06, 0.25}, {0.2, 0.7, 0.1},{0.2, 0.2, 0.6}};
            n = A.length;
            B = new double[][]{ {0.69, 0.19, 0.12, 0}, { 0.11, 0.39, 0.29, 0.21},{  0.01, 0.09, 0.23, 0.67}};
            k = B[0].length;
            pi = new double [][]{{0.9 ,0.1, 0}};
*/

            int[] totalObs = MatrixHandler.retrieveVector(reader.readLine().split(" "));

            //observations = MatrixHandler.retrieveVector(reader.readLine().split(" "));

            double highestThreashold = 0;
            double[][] alphaM;
            ArrayList<double[][]> digamma;
            double[][] gamma;
            double[][] newA;
            double[][] newB;
            double[][] newPi;
            double[][] averageAMatrix = new double[3][3];
            double[][] averageBMatrix = new double[3][4];
            double[][] averagePiMatrix = new double[1][3];
            int samples = 500;

            for(int sample = 0; sample < samples; sample++) {
                System.out.println("Calculating Sample: " + sample);
                A =new double[][]{ {0.54, 0.26, 0.20}, {0.19, 0.53, 0.28},{ 0.22, 0.18, 0.6}};
                n = A.length;
                B = new double[][]{ {0.5, 0.2, 0.11, 0.19}, { 0.22, 0.28, 0.23, 0.27},{  0.19, 0.21, 0.15, 0.45}};
                k = B[0].length;
                pi = new double [][]{{0.3 ,0.2, 0.5}};
                observations = MatrixHandler.randomObservationSequence(totalObs, 250);

                int count = 0;
                //MatrixHandler.vectorPrint(observations);
                do {
                    alphaM = new double[observations.length][A.length];
                    MatrixHandler.alpha(alphaM, A, B, pi, observations);
                    double[][] betaM = MatrixHandler.beta(A, B, observations);
                    digamma = MatrixHandler.digamma(alphaM, betaM, A, B, observations);
                    //System.out.println("DIGAMMA:");
                    //MatrixHandler.printMatrix(digamma.get(0));
                    gamma = MatrixHandler.gamma(digamma);
                    //System.out.println("GAMMA:");
                    //MatrixHandler.printMatrix(gamma);
                    newA = MatrixHandler.trainAMatrix(digamma, gamma);
                    newB = MatrixHandler.trainBMatrix(gamma, observations, B[0].length);
                    newPi = MatrixHandler.trainpiMatrix(gamma);

                    highestThreashold = Math.max(MatrixHandler.deltaMax(A, newA), MatrixHandler.deltaMax(B, newB));

                    A = newA;
                    B = newB;
                    pi = newPi;
                    //System.out.println("Threashold: " + highestThreashold);
                    count++;
                    if (count > 1000) {
                        System.out.println("Breaking after 1000000");
                        break;
                    }

                } while (highestThreashold > 0.00000000000001);

                averageAMatrix = MatrixHandler.sumMatrix(averageAMatrix, A);
                averageBMatrix = MatrixHandler.sumMatrix(averageBMatrix, B);
                averagePiMatrix = MatrixHandler.sumMatrix(averagePiMatrix, pi);
            }

            averageAMatrix = MatrixHandler.divideMatrix(averageAMatrix, samples);
            averageBMatrix = MatrixHandler.divideMatrix(averageBMatrix, samples);
            averagePiMatrix = MatrixHandler.divideMatrix(averagePiMatrix, samples);

            MatrixHandler.outputMatrix(averageAMatrix);
            MatrixHandler.outputMatrix(averageBMatrix);
            MatrixHandler.outputMatrix(averagePiMatrix);
            //MatrixHandler.outputMatrix(pi);

        }catch(Exception e){
            System.out.println("nooooo!");
        }

    }

    public static void main(String[] args){
        GradeC C = new GradeC();

    }
}
