import java.util.*;
import java.io.*;
import java.lang.*;
import java.math.*;

public class GameProblem {
    public static void main(String[] args) {

        // Open input file
        Scanner sc = new Scanner(System.in);
        System.out.print("Input file: ");

        try {
            File input = new File(sc.nextLine());
            sc.close();
            Scanner scanner = new Scanner(input);

            int n = scanner.nextInt();
            int m = scanner.nextInt();

            int[][] A = new int[n + 1][m + 1];

            for (int i=1; i<=n; i++) {
                for (int j=1; j<=m; j++) {
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
    static int solX = -1;
    static int solY = -1;
    public static void game(int n, int m, int[][] A) {
        S = new int[n + 1][m + 1];
        R = new char[n + 1][m + 1];
        find_optimal_solution(n, m, A);
        print_optimal_score();
        print_optimal_route(n, m, A);

    }

    private static void find_optimal_solution(int n, int m, int[][] A) {

        S[n][m] = A[n][m];
        
        // build last column solutions
        // last column -> move down OR exit
        // max{𝑆[𝑖+1, 𝑚],0}+𝐴[𝑖,𝑚]
        for (int i=m-1; i > 0; i--) {
            S[i][m] = Math.max(S[i + 1][m], 0) + A[i][m];
        }

        // build last row solutions
        // last row -> move right OR exit
        // max{𝑆[𝑛,𝑗+1],0}+𝐴[𝑛,𝑗]
        for (int j=n-1; j>0; j--) {
            S[n][j] = Math.max(S[n][j + 1], 0) + A[n][j];
        }

        print_2d_int_array(S);

        for (int i=n-1; i>0; i--) {
            for (int j=m-1; j>0; j--) {

                // check whats better -> move down OR right
                // max{𝑆[𝑖+1, 𝑗],𝑆[𝑖,𝑗+1]}+𝐴[𝑖,𝑗]
                S[i][j] = Math.max(S[i + 1][j], S[i][j + 1]) + A[i][j];

            }
        }

        print_2d_int_array(S);

    }

    private static void print_optimal_score() {
        int optimalScore = Integer.MIN_VALUE;
        
        for (int i=0; i<S.length; i++) {
            for (int j=0; j<S[0].length; j++) {
                if (S[i][j] > optimalScore) {
                    solX = j; // i think?
                    solY = i;
                    optimalScore = S[i][j];
                } 
            }
        }

        System.out.println("Best score: " + optimalScore);
    }

    /*
     * To be able to track the rout, you need to define an auxiliary table R:
     * in each R[i,j] cell you will save info about the choice made in computing the
     * value of respective S[i,j]
     * 
     * – e.g. you can save “d” if the choice is to move
     * down, “r” if the choice is to move right, and “e” if you have to exit.
     */
    private static void print_optimal_route(int n, int m, int[][] A) {

        int i = solY;
        int j = solX;
        
        while (i<m && j<m) {
            int previousValue = S[i][j] - A[i][j];

            if (previousValue == S[i+1][j]) {
                // downward move
                System.out.print("d ");
                i++;
            }

            if (previousValue == S[i][j+1]) {
                // right move
                System.out.print("r ");
                j++;
            }

        }
        System.out.print("e ");
    }

    private static void print_2d_int_array(int[][] A) {
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                System.out.print(A[i][j] + " ");
            }
            System.out.println();
        }
    }
}