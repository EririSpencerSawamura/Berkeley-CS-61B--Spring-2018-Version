package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private int T;
    private double[] stats;
    /** Performs T independent experiments on an N-by-N grid. */
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }

        this.T = T;
        stats = new double[T];

        for (int i = 0; i < T; i++) {
            Percolation p = pf.make(N);
            stats[i] = makeStat(p, N);
        }
    }

    private double makeStat(Percolation p, int N) {
        while (!p.percolates()) {
            int row = StdRandom.uniform(N);
            int col = StdRandom.uniform(N);
            p.open(row, col);
        }

        return (double) p.numberOfOpenSites() / (N * N);
    }

    /** Samples mean of percolation threshold. */
    public double mean() {
        return StdStats.mean(stats);
    }

    /** Samples standard deviation of percolation threshold. */
    public double stddev() {
        return StdStats.stddev(stats);
    }

    /** Returns the low endpoint of 95% confidence interval. */
    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(T);
    }

    /** Returns the high endpoint of 95% confidence interval. */
    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(T);
    }
}
