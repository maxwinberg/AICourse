import java.util.ArrayList;
import java.util.Random;

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

    public static double[][] sumMatrix(double[][] firstMatrix, double[][] secondMatrix){

        /* Create another 2d array to store the result using the original arrays' lengths on row and column respectively. */
        double[][] result = new double[firstMatrix.length][secondMatrix[0].length];

        /* Loop through each and get product, then sum up and store the value */
        for (int i = 0; i < firstMatrix.length; i++) {
            for (int j = 0; j < firstMatrix[0].length; j++) {
                result[i][j] = firstMatrix[i][j] + secondMatrix[i][j];
            }
        }

        return result;
    }

    public static double[][] divideMatrix(double[][] matrix, double divide){

        /* Create another 2d array to store the result using the original arrays' lengths on row and column respectively. */
        double[][] result = new double[matrix.length][matrix[0].length];

        /* Loop through each and get product, then sum up and store the value */
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                result[i][j] = matrix[i][j]/divide;
            }
        }

        return result;
    }

    public static int[] randomObservationSequence(int[] totalObservations, int sequenceSize) throws IndexOutOfBoundsException{
        if(sequenceSize > totalObservations.length){
            throw new IndexOutOfBoundsException("To large sequenceSize");
        }
        Random random = new Random();
        int startIndex = random.nextInt(totalObservations.length - sequenceSize);
        int[] sequence = new int[sequenceSize];
        for(int i = 0; i < sequenceSize; i++){
            sequence[i] = totalObservations[startIndex+i];
        }
        return sequence;
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

    public static void outputMatrix(double[][] matrix) {
        System.out.print(matrix.length + " " + matrix[0].length + " ");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
        }
        System.out.println();
    }

    public static double[][] trainAMatrix(ArrayList<double[][]> digamma, double[][] gamma){
        double[][] trainedAMatrix = new double[digamma.get(0).length][digamma.get(0).length];
        double digammaSum, gammaSum;
        for(int i = 0; i < digamma.get(0).length; i++){
            for(int j = 0; j < digamma.get(0).length; j++){
                digammaSum = gammaSum = 0;
                for(int t = 0; t < digamma.size(); t++){
                    digammaSum += digamma.get(t)[i][j];
                    gammaSum += gamma[t][i];
                }

                trainedAMatrix[i][j]=digammaSum/gammaSum;
            }
        }
        return  trainedAMatrix;

    }


    public static double indicatior(Boolean input){
        if(input ==true){
            return 1.0;
        }
        return 0.0;
    }

    public static double[][] trainBMatrix( double[][] gamma, int[] observations, int K){
        double[][] trainedBMatrix = new double[gamma[0].length][K];
        double nSum, gammaSum;
        for(int j = 0; j < gamma[0].length; j++){
            for(int k = 0; k < K; k++){
                nSum = gammaSum = 0.0;
                for(int t = 0; t < gamma.length; t++){
                    nSum += indicatior(observations[t]==k)*gamma[t][j];
                    gammaSum += gamma[t][j];
                }
                trainedBMatrix[j][k]=nSum/gammaSum;
            }
        }
        return  trainedBMatrix;
    }

    public static double[][] trainpiMatrix(double[][] gamma){
        double [][] trainPiMatrix= new double[1][gamma[0].length];
        for (int i=0; i < gamma[0].length; i++){
            trainPiMatrix[0][i]=gamma[0][i];
        }
        return trainPiMatrix;
    }

    public static double deltaMax(double[][] m1, double[][] m2){
        double[][] delta = new double[m1.length][m1[0].length];
        double max=0;
        for(int row = 0; row < m1.length; row++){
            for(int column = 0; column < m1[0].length; column++){
                if(m1[row][column] - m2[row][column]>0) {
                    delta[row][column] = m1[row][column] - m2[row][column];
                }else {
                    delta[row][column] = m2[row][column] - m1[row][column];
                }
                if(delta[row][column]>max){
                    max=delta[row][column];
                }

            }
        }

        return max;
    }


    public static ArrayList<double[][]> digamma(double[][] alphaM, double[][] betaM, double[][] A, double[][] B, int[] observations){
        ArrayList<double[][]> digamma = new ArrayList<double[][]>();

        double alphaSum = 0;
        for(int k = 0; k < alphaM[0].length; k++){
            alphaSum += alphaM[observations.length-1][k];
        }
        for(int t = 0; t < observations.length-1; t++){//Here we took away one
            digamma.add(t, new double[A.length][A.length]);
            for(int i = 0; i < A.length; i++){
                for(int j = 0; j < A.length;j++){
                    digamma.get(t)[i][j]=(alphaM[t][i]*A[i][j]*B[j][observations[t+1]]*betaM[t+1][j])/alphaSum;
                }

            }
        }

        return digamma;
    }

    public static double[][] gamma(ArrayList<double[][]> digamma){

        double[][] gamma = new double[digamma.size()][digamma.get(0).length];//Matrix T x N
        double rowSum=0.0;
        for(int t = 0; t < digamma.size(); t++){
            for(int i = 0; i < digamma.get(0).length; i++){
                rowSum=0.0;
                for(int j = 0; j <digamma.get(0).length; j++){
                    rowSum=rowSum+digamma.get(t)[i][j];
                }
                gamma[t][i]=rowSum;

            }
        }

        return gamma;
    }


    public static double[][] beta(double[][] A, double[][] B, int[] observations, double[] c){

        double[][] betaM = new double[observations.length][A.length];

        //Initializing the dynamic programming procedure
        for(int i = 0; i < A.length; i++){
            betaM[observations.length-1][i] = c[c.length-1];
        }

        double sum = 0;
        for(int t = observations.length-2; t > -1; t--){

            for(int i = 0; i < A.length; i++){
                sum = 0;

                for(int j = 0; j < A.length; j++){
                    sum += betaM[t+1][j] * B[j][observations[t+1]] * A[i][j];

                }
                betaM[t][i] = sum;
                betaM[t][i] = c[t]*betaM[t][i];
            }

        }
        return betaM;
    }

    public static double alpha(double[][] alphaM, double[][] A, double[][] B, double[][] pi, int[] observations, double[] c){

        //Initializing the dynamic programming procedure
        c[0]=0;

        for(int i = 0; i < A.length; i++){
            alphaM[0][i] = pi[0][i] * B[i][observations[0]];
            c[0]=c[0]+alphaM[0][i];
        }

        c[0]=1/c[0];

        for(int i = 0; i < A.length; i++){
            alphaM[0][i] = c[0]* alphaM[0][i];
        }



        double sum = 0;
        for(int t = 0; t < observations.length-1; t++){
            c[t+1]=0;
            for(int i = 0; i < A.length; i++){
                sum = 0;

                for(int j = 0; j < A.length; j++){
                    sum += alphaM[t][j] * A[j][i];

                }

                alphaM[t+1][i] = sum * B[i][observations[t+1]];
                c[t+1]=c[t+1]+alphaM[t+1][i];
            }
            c[t+1]=1/c[t+1];
            for(int i = 0; i < A.length; i++){


                alphaM[t+1][i] = c[t+1]*alphaM[t+1][i];
            }

        }

        sum = 0;
        for(int k = 0; k < alphaM[0].length; k++){
            sum += alphaM[observations.length-1][k];
        }
        return sum;
    }

    public static double alphaWithoutNormalization(double[][] alphaM, double[][] A, double[][] B, double[][] pi, int[] observations){

        //Initializing the dynamic programming procedure
        for(int i = 0; i < A.length; i++){
            alphaM[0][i] = pi[0][i] * B[i][observations[0]];
        }

        double sum = 0;
        for(int t = 0; t < observations.length-1; t++){
            for(int i = 0; i < A.length; i++){
                sum = 0;

                for(int j = 0; j < A.length; j++){
                    sum += alphaM[t][j] * A[j][i];
                }
                alphaM[t+1][i] = sum * B[i][observations[t+1]];
            }
        }
        sum = 0;
        for(int k = 0; k < alphaM[0].length; k++){
            sum += alphaM[observations.length-1][k];
        }
        return sum;
    }

    public static int[] viterbi(double[][] delta, int[][] psi, double[][] A, double[][] B, double[][] pi, int[] observations){

        //Initializing the dynamic programming procedure
        for(int i = 0; i < A.length; i++){
            delta[0][i] = pi[0][i]*B[i][observations[0]];
            psi[0][i] = -1;
        }

        //Recursion calculating the probability and indexes
        double[] temp = new double[A.length];
        for(int t = 1; t < observations.length; t++){

            for(int i = 0; i < A.length; i++){

                for(int j = 0; j < A.length; j++){
                    temp[j] = delta[t-1][j] * A[j][i]*B[i][observations[t]];
                }

                psi[t][i] = maximumIndex(temp);
                delta[t][i] = temp[psi[t][i]];
            }
        }

        // stop here
        int [] path= new int[observations.length];
        path[observations.length-1]= maximumIndex(delta[delta.length-1]);

        // find the path

        for (int t=observations.length-2; t >-1; t--){
            path[t]=psi[t+1][path[t+1]];
        }

        MatrixHandler.vectorPrint(path);
        return path;

    }


    public static void vectorPrint(int [] vector){
        for(int i=0; i< vector.length; i++){
            System.out.print(vector[i]+" ");
        }
    }

    public static int maximumIndex(double[] vector){
        int biggest = 0;
        for(int i = 1; i < vector.length; i++){
            if(vector[biggest] < vector[i]){
                biggest = i;
            }
        }
        return biggest;
    }
}




