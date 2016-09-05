import java.io.BufferedReader;
import java.io.InputStreamReader;
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
    private BufferedReader reader;

    public HMM1(){

        try{
            //Retrieving the input first line which has information about Matrix A
            //Scanner scan = new Scanner(new File("sample_00.in"));
            reader = new BufferedReader(new InputStreamReader(System.in));
            A =  MatrixHandler.retrieveMatrix(reader.readLine().split(" "));
            n = A.length;
            B =  MatrixHandler.retrieveMatrix(reader.readLine().split(" "));
            k = B[0].length;
            pi = MatrixHandler.retrieveMatrix(reader.readLine().split(" "));
            /*
            printMatrix(A);
            printMatrix(B);
            printMatrix(pi);
            */
            double[][] results = (MatrixHandler.multiplyMatrix(MatrixHandler.multiplyMatrix(pi, A), B));
            //printMatrix(results);

            System.out.print(results.length +  " " + results[0].length);
            for(int i = 0; i < results[0].length; i++){
                System.out.print(" " + results[0][i]);
            }
        }catch(Exception e){
            System.out.println("nooooo!");
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
