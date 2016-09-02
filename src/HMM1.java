import java.util.Scanner;
import java.io.File;

/**
 * Created by zwq on 2016/9/2.
 */
public class HMM1 {

    private int n;
    private int k;
    private double[][] A;
    private double[][] B;
    private double[][] pi;

    public HMM1(){

        try{
            //Retrieving the input first line which has information about Matrix A
            Scanner scan = new Scanner(new File("sample_00.in"));
            A = retrieveMatrix(scan);
            System.out.println("hej");
            n = A.length;
            B = retrieveMatrix(scan);
            System.out.println("hej2");
            k = B[0].length;
            pi = retrieveMatrix(scan);
            System.out.println("hej3");
            printMatrix(A);
            System.out.println("hej4");
            printMatrix(B);
            System.out.println("hej5");
            printMatrix(pi);
            double[][] results = MatrixHandler.multiplyMatrix(MatrixHandler.multiplyMatrix(pi, A), B);
            printMatrix(results);


        }catch(Exception e){
            System.out.println("nooooo!");
        }
    }

    public double[][] retrieveMatrix(Scanner scan){
        String[] input = scan.nextLine().split(" ");
        for(int k = 0; k < input.length; k++){

            System.out.print(input[k] + ", ");
        }
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

    public void printMatrix(double[][] matrix){

        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void function(){

    }

    public static void main(String[] args){

        new HMM1();
        /*
        System.out.println("Lets rock AI!");
        try{

            /*
            int n = 4;
            int k = 3;


            double[][] results = MatrixHandler.multiplyMatrix(MatrixHandler.multiplyMatrix(pi, A), B);



        }catch(Exception e) {
            System.out.println("Something went terribly wrong :(");
        }
        */
    }
}
