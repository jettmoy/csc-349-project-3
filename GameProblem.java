import java.util.*;
import java.io.*;
import java.lang.*;

public class GameProblem {
    public static void main(String[] args) {

        // Open input file
        Scanner sc = new Scanner(System.in);
        System.out.print("Input file: ");

        try {
            File input = new File(sc.nextLine());
            sc.close();
            Scanner scanner = new Scanner(input);

            /*
             * Prompt the user to enter the name of the file containing the data for your
             * problem, input the file-name and set up a scanner to read from that file. 2.
             * Read values from the file: these are the data you will pass to game method as
             * parameter-values. Your file content will be as follows (values on the same
             * line will be separated by ≥1 spaces):
             * 
             * - the first line contains two integers:
             *      values of n and m (i.e. number of rows and columns of A)
             * - then n lines will
             * follow, each containing m integers (a line represents one row of A)
             */

            int n = scanner.nextInt();
            int m = scanner.nextInt();

            int[][] A = new int[n][m];

            for (int i=0; i<n; i++) {
                for (int j=0; j<m; j++) {
                    A[i][j] = scanner.nextInt();
                }
            }

            game(n, m, A);

            scanner.close();
        } catch (Exception e) {
            System.out.println("Error parsing file : " + e);
            System.exit(1);
        }

    }

    static int[][] S;
    static char[][] R;
    public static void game(int n, int m, int[][]A) {
        S = new int[n][m];
        R = new char[n][m];
    }

    // i, j: find_optimal_solution when starting at A[i,j]
    private static void find_optimal_solution(int i, int j, int n, int m, int[][] A) {
       
        // if bottom right square (base case)
        if (i >= n && j>= m) {
            S[i][j] = A[i][j];
        } else {
            
        }

    }

    /*
     * To be able to track the rout, you need to define an auxiliary table R:
     * in each R[i,j] cell you will save info about the choice made in computing the
     * value of respective S[i,j]
     * 
     * – e.g. you can save “d” if the choice is to move
     * down, “r” if the choice is to move right, and “e” if you have to exit.
     */
    private static void print_optimal_route() {
    }
}