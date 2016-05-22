package lab1;

import java.util.Scanner;

/**
 * Created by vladimercury on 4/30/16.
 */
public class Task3 {
    private Scanner scanner = new Scanner(System.in);
    private double EPSILON = Math.ulp(Double.MIN_VALUE);

    private int readInt() throws Exception{
        return scanner.nextInt();
    }

    private double delta(double a, double b){
        return Math.abs((a - b) / a * 100);
    }

    private long fact(int n){
        long result = 1;
        while (n > 1){
            result *= n--;
        }
        return result;
    }

    public void run(int x) {
        boolean negative = x < 0;
        x = Math.abs(x);

        double ex = 0.0;
        double emod = 1.0;
        int n = 1;

        while (true){
            ex += emod;
            emod = Math.pow(x, n) / fact(n);
            if (emod < EPSILON){
                break;
            }
            n++;
        }
        if (negative){
            ex = 1 / ex;
            x = -x;
        }
        double exp = Math.exp(x);
        System.out.printf("%3d  %.15e  %.15e  %.10f%%\n",x, ex, exp, delta(exp, ex));
    }

    public static void main(String[] args) {
        Task3 task = new Task3();
        int border = 20;
        System.out.printf("%-3s  %-21s  %-21s  %-14s\n", "E^", "OUR VALUE", "MATH.EXP VALUE", "DELTA");
        for (int i = -border; i <= border; i++){
            task.run(i);
        }
    }
}
