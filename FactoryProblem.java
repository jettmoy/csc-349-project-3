import java.util.*;
import java.io.*;
import java.lang.*;

public class FactoryProblem {
   public static void main(String args[]) {

      // Open input file
      Scanner sc = new Scanner(System.in);
      System.out.print("Input file: ");

      File input = new File(sc.nextLine());
      sc.close();

      try {
         Scanner scanner = new Scanner(input);

      int n = scanner.nextInt();
      System.out.println(n);

      // entry time
      int[] e = {scanner.nextInt(), scanner.nextInt()};
      System.out.println(e[0] + " " + e[1]);

      // one station check
      if (!scanner.hasNextInt()) {
         //edge case
      }

      // exit time
      int[] x = {scanner.nextInt(), scanner.nextInt()};
      System.out.println(x[0] + " " + x[1]);

      // station time
      int[] a1 = new int[n], a2 = new int[n];
      for (int i = 0; i < n; i++)
         a1[i] = scanner.nextInt();
      for (int i = 0; i < n; i++)
         a2[i] = scanner.nextInt();
      for (int z : a1)
         System.out.print(z + " ");
      System.out.println();
      for (int z : a2)
         System.out.print(z + " ");
      System.out.println();

      // transfer time
      int[] t1 = new int[n-1], t2 = new int[n-1];
      for (int i = 0; i < n - 1; i++)
         t1[i] = scanner.nextInt();
      for (int i = 0; i < n - 1; i++)
         t2[i] = scanner.nextInt();
      for (int z : t1)
         System.out.print(z + " ");
      System.out.println();
      for (int z : t2)
         System.out.print(z + " ");
      System.out.println();

      scanner.close();

      fastestWay(a1, a2, t1, t2, e, x, n);

   } catch (FileNotFoundException e) {
         System.out.println("FILE ERROR.");
         System.exit(1);
      }
   }

   public static void fastestWay(int[] a1, int[] a2, int[] t1, int[] t2, int[] e, int[] x, int n) {
      int[] f1 = new int[n], f2 = new int[n];
      int[] l1 = new int[n - 1], l2 = new int[n - 1];
      int fC, lC;
      f1[0] = e[0] + a1[0];
      f2[0] = e[1] + a2[1];
      for (int i = 1; i < n; i++) {
         if (f1[i - 1] + a1[i] <= f2[i - 1] + t2[i] + a1[i]) {
            // line 1 faster
            f1[i] = f1[i - 1] + a1[i];
            l1[i] = 1;
         }
         else {
            // line 2 faster
            f1[i] = f2[i - 1] + t2[i] + a1[i];
            l1[i] = 2;
         }
         if (f2[i - 1] + a2[i] <= f1[i - 1] + t1[i] + a1[i]) {
            //line 2 faster
            f2[i] = f2[i - 1] + a2[i];
            l2[i] = 2;
         }
         else {
            // line 1 faster
            f2[i] = f1[i - 1] + t1[i] + a2[i];
            l2[i] = 1;
         }
      }

      // calc exit times
      if (f1[n] + x[0] <= f2[n] + x[1]) {
         fC = f1[n] + x[0];
         lC = 1;
      }
      else {
         fC = f2[n] + x[1];
         lC = 2;
      }

      int line = lC;
      System.out.println("line " + line + ", station " + n);
      for (int i = n; i >= 2; i--) {
         line = line == 1 ? a1[i] : a2[i];
         System.out.println("line " + line + ", station " + (i - 1));
      }

   }
}
