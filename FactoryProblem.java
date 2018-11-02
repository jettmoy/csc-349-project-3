/*
   Jett Moy - jlmoy
   Andrew Exton - aexton
   November 1, 2018
   ALGORITHMS - Project 3 Part 1
*/

import java.util.*;
import java.io.*;

public class FactoryProblem {

   public static void main(String args[]) {

      // Get input file name
      Scanner sc = new Scanner(System.in);
      System.out.print("Input file: ");

      try {
         // Open input file
         Scanner scanner = new Scanner(new File(sc.nextLine()));
         sc.close();

         // PARSE INPUT

         int n = scanner.nextInt();

         // entry time
         int[] e = {scanner.nextInt(), scanner.nextInt()};

         // exit time
         int[] x = {scanner.nextInt(), scanner.nextInt()};

         // station time
         int[] a1 = new int[n], a2 = new int[n];
         for (int i = 0; i < n; i++)
            a1[i] = scanner.nextInt();
         for (int i = 0; i < n; i++)
            a2[i] = scanner.nextInt();

         // check if one station - edge case
         if (n == 1) {
            Solution s = fastestTime(a1, a2, null, null, e, x, n); // won't enter for loop in fastestTime func (will just compute entry + station + exit time)
            int[] route = calcOptimalRoute(s, n);
            printOptimalSol(n, s.f_, route);
            System.exit(0);
         }

         // transfer time
         int[] t1 = new int[n - 1], t2 = new int[n - 1];
         for (int i = 0; i < n - 1; i++)
            t1[i] = scanner.nextInt();
         for (int i = 0; i < n - 1; i++)
            t2[i] = scanner.nextInt();

         scanner.close();

         // CALCULATE OPTIMAL SOLUTION
         Solution s = fastestTime(a1, a2, t1, t2, e, x, n);
         int[] route = calcOptimalRoute(s, n);
         printOptimalSol(n, s.f_, route);

      } catch (FileNotFoundException fe) {
         System.out.println("ERROR: FILE NOT FOUND");
         System.exit(1);
      } catch (Exception e) {
         System.out.println("ERROR:" + e);
         System.exit(1);
      }
   }

   // returned object from fastestTime func containing elemnts of optimal solution
   static class Solution {
      int f_, l_;
      int[] l1, l2;

      public Solution(int f_, int l_, int[] l1, int[] l2) {
         this.f_ = f_;
         this.l_ = l_;
         this.l1 = l1;
         this.l2 = l2;
      }
   }

   // main algorithm for bottom-up iterative approach to calculate fastest time through assembly line
   public static Solution fastestTime(int[] a1, int[] a2, int[] t1, int[] t2, int[] e, int[] x, int n) {
      int[] f1 = new int[n], f2 = new int[n];
      int[] l1 = new int[n], l2 = new int[n];
      int f_, l_;
      f1[0] = e[0] + a1[0];         // calculate first cells of f1 & f2
      f2[0] = e[1] + a2[0];

      for (int i = 1; i < n; i++) {
         // f1
         // compare cost from l1 vs from l2 + transfer time
         if (f1[i - 1] + a1[i] <= f2[i - 1] + t2[i - 1] + a1[i]) {
            // line 1 faster
            f1[i] = f1[i - 1] + a1[i];
            l1[i] = 1;
         }
         else {
            // line 2 faster
            f1[i] = f2[i - 1] + t2[i - 1] + a1[i];
            l1[i] = 2;
         }
         // f2
         if (f2[i - 1] + a2[i] <= f1[i - 1] + t1[i - 1] + a2[i]) {
            //line 2 faster
            f2[i] = f2[i - 1] + a2[i];
            l2[i] = 2;
         }
         else {
            // line 1 faster
            f2[i] = f1[i - 1] + t1[i - 1] + a2[i];
            l2[i] = 1;
         }
      }
      // calc & compare exit times/solution
      if (f1[n - 1] + x[0] <= f2[n - 1] + x[1]) {
         f_ = f1[n - 1] + x[0];
         l_ = 1;
      }
      else {
         f_ = f2[n - 1] + x[1];
         l_ = 2;
      }

      return new Solution(f_, l_, l1, l2);
   }

   // recreates route of optimal solution
   public static int[] calcOptimalRoute(Solution s, int n) {
      // backtrack to calculate optimal route
      int[] route = new int[n + 1];
      route[n] = s.l_;
      for (int i = n - 1; i > 0; i--)
         route[i] = route[i + 1] == 1 ? s.l1[i] : s.l2[i];

      return route;
   }

   // prints the fastest time through assembly line and the optimal route
   public static void printOptimalSol(int n, int f_, int[] route) {
      // print fastest time
      System.out.println("\nFastest time is: " + f_ + "\n");

      // print route
      System.out.println("The optimal route is:");
      for (int i = 1; i <= n; i++)
         System.out.println("station " + i + ", line " + route[i]);

   }
}
