import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by MaxWinLaptop on 2016-09-12.
 */
public class GradeCFinal {

    private int n;
    private int k;
    private double[][] A;
    private double[][] B;
    private double[][] pi;
    private int[] observations;

    private double[][] delta;
    private int[][] psi;
    private BufferedReader reader;
    private long startTime;


    public GradeCFinal(){
        startTime = System.currentTimeMillis();
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

            observations = MatrixHandler.retrieveVector(reader.readLine().split(" "));

            double highestThreashold = 0;
            double[][] alphaM;
            double[][] betaM;
            ArrayList<double[][]> digamma;
            double[][] gamma;
            double[][] newA;
            double[][] newB;
            double[][] newPi;
            double oldlogProb=Double.NEGATIVE_INFINITY;

            //A=new double[][]{{0.5, 0.5}, {0.6, 0.4}};
            //A=new double[][] {{0.3, 0.3, 0.3,  0.1}, {0.2, 0.2,  0.5,  0.1}, {0.4, 0.4, 0.2, 0},{0, 0.3, 0.4, 0.3}};
            A =new double[][]{{0.69, 0.06, 0.25}, {0.09, 0.81, 0.1},{0.2, 0.31, 0.49}};
            //A=new double[][] {{0.3, 0.3, 0.07,  0.1, 0.23}, {0.2, 0.2,  0.19,  0.1, 0.31}, {0.4, 0.23, 0.2, 0, 0.37},{0, 0.12, 0.35, 0.3, 0.18 }, {0.1, 0.03, 0.53, 0.23, 0.11}};
            n = A.length;
            B = new double[][]{{0.7, 0.19, 0.11, 0}, {0.1, 0.39, 0.29, 0.22}, {0, 0.1, 0.21, 0.69}};
            k = B[0].length;
            pi = new double [][]{{1.0,0, 0}};



            int count = 0;
            //MatrixHandler.vectorPrint(observations);
            double[] c = new double[observations.length];
            double logProb;
            do {
                logProb = 0;
                alphaM = new double[observations.length][A.length];
                MatrixHandler.alpha(alphaM, A, B, pi, observations, c);
                betaM = MatrixHandler.beta(A, B, observations, c);
                digamma = MatrixHandler.digamma(alphaM, betaM, A, B, observations);
                //System.out.println("DIGAMMA:");
                //MatrixHandler.printMatrix(digamma.get(0));
                gamma = MatrixHandler.gamma(digamma);
                //System.out.println("GAMMA:");
                //MatrixHandler.printMatrix(gamma);
                newA = MatrixHandler.trainAMatrix(digamma, gamma);
                newB = MatrixHandler.trainBMatrix(gamma, observations, B[0].length);
                newPi = MatrixHandler.trainpiMatrix(gamma);

               // highestThreashold = Math.max(MatrixHandler.deltaMax(A, newA), MatrixHandler.deltaMax(B, newB));

                A = newA;
                B = newB;
                pi = newPi;

                count++;
                /*
                if (count > 10000) {
                    System.out.println("Breaking after " + count);
                    break;
                }*/


                for(int i=0; i < observations.length; i ++ ){
                    logProb=logProb+Math.log10(c[i]);
                }
                logProb = -logProb;
                //System.out.println("LOGPROB: " + logProb + "  OLD LOG: " + oldlogProb);
                if(count<10000 && logProb > oldlogProb){
                    oldlogProb = logProb;
                }
                else{
                    System.out.println("Count value: " + count);
                    break;
                }

            } while (true);
            //} while (highestThreashold > 0.000000001);

            int[] shortenedObsSeq = new int[200];
            for(int i = 0; i < shortenedObsSeq.length; i++){
                shortenedObsSeq[i] = observations[i];
            }

            double modelProbability = MatrixHandler.alphaWithoutNormalization(alphaM, A, B, pi, shortenedObsSeq);

            System.out.println("ModelProbability: " + MatrixHandler.alphaWithoutNormalization(alphaM, A, B, pi, shortenedObsSeq));
            MatrixHandler.outputMatrix(A);
            MatrixHandler.outputMatrix(B);
            MatrixHandler.outputMatrix(pi);
            //MatrixHandler.outputMatrix(pi);
            //Build the original alpha without normalization.
        }catch(Exception e){
            System.out.println("nooooo!");
        }
        System.out.println("Seconds: " + ((System.currentTimeMillis() - startTime)/1000));
    }


    public static void main(String[] args){

        new GradeCFinal();
    }
}
