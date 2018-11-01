import java.util.*;
import java.io.*;
import java.lang.*;

public class FactoryProblem {
   public static void main(String args[]) {

      // Open input file
      Scanner sc = new Scanner(System.in);
      System.out.print("Input file: ");

      try {
         File input = new File(sc.nextLine());
         sc.close();
         Scanner scanner = new Scanner(input);

         int n = scanner.nextInt();
         System.out.println(n);

         // entry time
         int[] e = {scanner.nextInt(), scanner.nextInt()};
         System.out.println(e[0] + " " + e[1]);

         // one station check
         if (scanner.hasNext()) {
            // exit time
            int[] x = {scanner.nextInt(), scanner.nextInt()};
            System.out.println(x[0] + " " + x[1]);

            // station time
            int[] a1 = new int[n];
            int[] a2 = new int[n];
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
            int[] t1 = new int[n-1];
            int[] t2 = new int[n-1];
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
         }
         scanner.close();
      } catch (Exception e) {
         System.out.println("Error parsing file : " + e);
         System.exit(1);
      }
   }
}
