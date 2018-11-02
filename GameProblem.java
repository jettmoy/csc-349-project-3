/*
    Andrew Exton - aexton
    Jett Moy - jlmoy
    November 2nd, 2018
    ALGORITHMS - Project 3 Part 2
*/

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

        // corner base case
        S[n][m] = A[n][m];
        R[n][m] = 'e';
        
        // build last column solutions
        // last column -> move down OR exit
        // max{𝑆[𝑖+1, 𝑚],0}+𝐴[𝑖,𝑚]
        for (int i=m-1; i > 0; i--) {
            if (S[i+1][m] >= 0) {
                S[i][m] = S[i + 1][m] + A[i][m];
                R[i][m] = 'd';
            } else {
                S[i][m] = A[i][m];
                R[i][m] = 'e';
            }
        }

        // build last row solutions
        // last row -> move right OR exit
        // max{𝑆[𝑛,𝑗+1],0}+𝐴[𝑛,𝑗]
        for (int j=n-1; j>0; j--) {
            if (S[n][j+1] >= 0) {
                S[n][j] = S[n][j + 1] + A[n][j];
                R[n][j] = 'r';
            } else {
                S[n][j] = A[n][j];
                R[n][j] = 'e';
            }
        }

        for (int i=n-1; i>0; i--) {
            for (int j=m-1; j>0; j--) {
                // check whats better -> move down OR right
                // max{𝑆[𝑖+1, 𝑗],𝑆[𝑖,𝑗+1]}+𝐴[𝑖,𝑗]
                if (S[i + 1][j] >= S[i][j + 1]) {
                    S[i][j] = S[i + 1][j] + A[i][j];
                    R[i][j] = 'd';
                } else {
                    S[i][j] = S[i][j + 1] + A[i][j];
                    R[i][j] = 'r';
                }

            }
        }
    }

    private static void print_optimal_score() {
        int optimalScore = Integer.MIN_VALUE;
        
        for (int i=0; i<S.length; i++) {
            for (int j=0; j<S[0].length; j++) {
                if (S[i][j] > optimalScore) {
                    solX = j;
                    solY = i;
                    optimalScore = S[i][j];
                } 
            }
        }

        System.out.println("Best score: " + optimalScore);
    }

    private static void print_optimal_route(int n, int m, int[][] A) {

        int i = solY;
        int j = solX;

        while (i<=m+1 || j<=m+1) {
            char move = R[i][j];

            switch(move) {
                case 'd':
                    System.out.printf("[%d,%d] to ", i, j);
                    i++;
                    break;
                case 'r':
                    System.out.printf("[%d,%d] to ", i, j);
                    j++;
                    break;
                case 'e':
                    System.out.printf("[%d,%d] to exit\n", i, j);
                    return;
            }
        }

    }
}