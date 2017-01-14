import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.Arrays;
/**
 * Created by mengzi on 2017/1/6.
 * just for test...
 */
public class Test {
    public int[][] sz;
    public int[] line;
    public Test(int n) {
        sz = new int[n][n];
        line = new int[n];
        // for (int i = 0; i < n; i++) sz[i] = 1;
    }
    public static void main(String[] args) {
        Test test = new Test(5);
        String hello = "Some thing comes out.";
        System.out.println(hello);
        StdOut.println(hello);
        System.out.println(Arrays.toString(args));

        int[] ints = {1, 2, 3};
        System.out.println(Arrays.toString(ints));
        // System.out.println();
        System.out.println(Arrays.toString(test.line));
        System.out.println(test.sz[2][2]);
        System.out.println(test.sz.length);
        // int n = StdIn.readInt();
        // System.out.println(n);
        double[] int2d = new double[5];
        for (int i = 0; i < 5; i++) {
            int2d[i] = i;
        }
        System.out.println(Arrays.toString(int2d));
        int x = 9;
        System.out.println(Math.sqrt(x));
        System.out.printf("%-25s = %f", "mean", Math.sqrt(x));

    }
}
