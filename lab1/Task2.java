package lab1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by vladimercury on 4/29/16.
 */
public class Task2 {
    private class Pair{
        public double avg;
        public double disp;

        public Pair(){
            avg = 0.0;
            disp = 0.0;
        }
        public String toString(){
            return "(" + avg + ", " + disp + ")";
        }
    }

    private double difference(double a, double b){
        return Math.abs((a - b) / a);
    }

    private Pair naivMeanDisp(List<Double> x){
        Pair res = new Pair();
        double sum1 = 0.0;
        double sum2 = 0.0;
        int n = x.size();


        for (int i = 0; i < n; i++){
            sum1 += x.get(i);
            sum2 += x.get(i) * x.get(i);
        }

        System.out.println(Math.ulp(x.get(0)));
        System.out.println(Math.ulp(x.get(0)*x.get(0)));
        System.out.println(Math.ulp(sum1));
        System.out.println(Math.ulp(sum2));

        res.avg = sum1 / n;
        res.disp = (sum2 - sum1 * sum1 / n) / (n - 1);
        return res;
    }

    private Pair updateMeanDisp(List<Double> x){
        Pair res = new Pair();
        double sum1 = 0.0;
        double sum2 = 0.0;
        int n = x.size();

        for (int i = 0; i < n; i++){
            sum1 += x.get(i);
        }
        res.avg = sum1 / n;

        for (int i = 0; i < n; i++){
            sum2 += (x.get(i) - res.avg) * (x.get(i) - res.avg);
        }
        res.disp = sum2 / (n - 1);
        return res;
    }

    private Pair welford(List<Double> x){
        Pair res = new Pair();
        int n = x.size();

        for (int i = 0; i < n; i++){
            double delta = x.get(i) - res.avg;
            res.avg += delta / (i + 1);
            res.disp += delta * (x.get(i) - res.avg);
        }
        res.disp /= n - 1;

        return res;
    }

    public void run(){
        List<Double> normalBig = new ArrayList<>();
        List<Double> normal = new ArrayList<>();
        Random random = new Random();
        double z = 0;

        for (int i = 0; i < 1000000; i++){
            z = random.nextDouble();
            normalBig.add(z + 10000000000.0);
            normal.add(z);
        }

        double avg = 0.0, disp = 0.0, avgN = 0.0, dispN = 0.0;
        Pair naivNormal = naivMeanDisp(normal);
        System.out.println("Naiv normal: " + naivNormal);
        System.out.println("eps: " + Math.ulp(naivNormal.avg) + " " + Math.ulp(naivNormal.avg*naivNormal.avg));
        System.out.println("otkl: " + Math.sqrt(naivNormal.disp));
        Pair naivBig = naivMeanDisp(normalBig);
        System.out.println("Naiv big: " + naivBig);
        System.out.println("Delta mean: " + difference(naivNormal.avg, naivBig.avg));
        System.out.println("Delta disp: " + difference(naivNormal.disp, naivBig.disp));
        Pair updDisp = updateMeanDisp(normalBig);
        System.out.println("Update mean disp: " + updDisp);
        System.out.println("Delta mean: " + difference(naivBig.avg, updDisp.avg));
        System.out.println("Delta disp: " + difference(naivNormal.disp, updDisp.disp));
        Pair welford = welford(normalBig);
        System.out.println("Welford: " + welford);
        System.out.println("Delta mean: " + difference(naivBig.avg, welford.avg));
        System.out.println("Delta disp: " + difference(naivNormal.disp, welford.disp));
    }

    public static void main(String[] args) {
        new Task2().run();
    }
}
