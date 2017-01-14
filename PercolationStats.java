import edu.princeton.cs.algs4.StdRandom;
// import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdStats;
import java.util.Arrays;

public class PercolationStats {
    public double[] percThreshold;
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("n and trials must be >= 0.");
        }
        percThreshold = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation perc = new Percolation(n);
            while (!perc.percolates()) {
                int row = StdRandom.uniform(1, n+1);
                int col = StdRandom.uniform(1, n+1);
                perc.open(row, col);
            }
            percThreshold[i] = perc.numberOfOpenSites() / (n*n);
        }
    }
    
    public double mean() {
        return StdStats.mean(percThreshold);
    }

    public double stddev() {
        return StdStats.stddev(percThreshold);
    }

    public double confidenceLo() {
        return mean() - 1.96 * Math.sqrt(stddev() / percThreshold.length);
    }

    public double confidenceHi() {
        return mean() + 1.96 * Math.sqrt(stddev() / percThreshold.length);
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats percStats = new PercolationStats(n, trials);
        System.out.println(Arrays.toString(percStats.percThreshold));
        System.out.printf("%-23s = %f\n", "mean", percStats.mean());
        System.out.printf("%-23s = %f\n", "stddev", percStats.stddev());
        System.out.printf("%-23s = %f, %f\n", "95% confidence interval", percStats.confidenceLo(), percStats.confidenceHi());
    }
}
