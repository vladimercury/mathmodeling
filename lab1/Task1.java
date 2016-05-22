package lab1;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by vladimercury on 4/29/16.
 */
public class Task1 {
    private final int DEGREE = 52;
    private final double STEP = 0.25;
    private final double MAX = 5;

    private double difference(double a, double b){
        return Math.abs((a - b) / a * 100);
    }

    public void sqrtSqr52(){
        System.out.printf("%-4s  %-21s  %-17s  %-6s\n", "WAS", "ROOT", "DEGREE", "ERROR");

        for (double k = STEP; k <= MAX; k += STEP){
            double x = k;
            System.out.printf("%.2f ", k);

            for (int i = 0; i < DEGREE; i++){
                x = Math.sqrt(x);
            }
            System.out.printf(" %.15e ", x);

            for (int i = 0; i < DEGREE; i++){
                x = x * x;
            }
            System.out.printf(" %.15f ", x);

            System.out.printf(" %.2f%%\n", difference(k, x));
        }
    }

    public void mySqrtSqr52(){
        System.out.printf("%-4s  %-21s  %-17s  %-6s\n", "WAS", "ROOT", "DEGREE", "ERROR");

        for (double k = STEP; k <= MAX; k += STEP){
            double x = k > 1 ? k - 1 : k;
            System.out.printf("%.2f ", k);

            for (int i = 0; i < DEGREE; i++){
                x = x / 2 - x * x / 8 + x * x * x / 16;
            }
            System.out.printf(" %.15e ", x);

            for (int i = 0; i < DEGREE; i++){
                x = x * x + 2 * x;
            }
            x = k > 1 ? x + 1 : x;
            System.out.printf(" %.15f ", x);

            System.out.printf(" %.2f%%\n", difference(k, x));
        }
    }

    public static void main(String[] args) {
        Task1 task = new Task1();
        task.sqrtSqr52();
        task.mySqrtSqr52();
    }
}
