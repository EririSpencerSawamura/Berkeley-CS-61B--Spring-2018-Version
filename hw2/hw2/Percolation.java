package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int N;
    private int numberOfOpenSites;
    private int virtualTopSiteIndex;
    private int virtualBottomSiteIndex;
    private boolean[][] grid;
    private WeightedQuickUnionUF UF;
    private WeightedQuickUnionUF noBackWashUF;

    /** Creates an N-by-N grid, with all sites initially blocked. */
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("N should be a positive integer!");
        }

        this.N = N;
        grid = new boolean[N][N];
        numberOfOpenSites = 0;
        virtualTopSiteIndex = 0;
        virtualBottomSiteIndex = N * N + 1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = false;
            }
        }

        UF = new WeightedQuickUnionUF(N * N + 2);
        noBackWashUF = new WeightedQuickUnionUF(N * N + 2);
    }

    /** Get the index of the site, iterated from the top-left to the bottom-right. */
    private int getIndex(int row, int col) {
        return (row * N) + col;
    }

    /** Opens the site (row, col) if it is not open already. */
    public void open(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            throw new IndexOutOfBoundsException("Row and col should be in range (0, N - 1).");
        }

        // Only modify the grid when the site is not already open.
        if (!grid[row][col]) {
            grid[row][col] = true;
            numberOfOpenSites++;

            int currIndex = getIndex(row, col);
            if (currIndex <= N) {
                UF.union(virtualTopSiteIndex, currIndex);
                noBackWashUF.union(virtualTopSiteIndex, currIndex);
            }
            // Try to connect to four neighbors.
            connectTop(row, col);
            connectBottom(row, col);
            connectLeft(row, col);
            connectRight(row, col);

            // If the site is on the bottom row, connect it to the virtual bottom site.
            if (currIndex >= N * N - N + 1 && currIndex <= N * N) {
                UF.union(virtualBottomSiteIndex, currIndex);
            }
        }

    }

    /** Tells whether the site (row, col) is open. */
    public boolean isOpen(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            throw new IndexOutOfBoundsException("Row and col should be in range (0, N - 1).");
        }

        return grid[row][col];
    }

    /** Tells whether the site (row, col) is full. */
    public boolean isFull(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            throw new IndexOutOfBoundsException("Row and col should be in range (0, N - 1).");
        }

        int currIndex = getIndex(row, col);
        return isOpen(row, col) && noBackWashUF.connected(virtualTopSiteIndex, currIndex);
    }

    /** Returns the number of open sites. */
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    /** Tells whether the system percolates. */
    public boolean percolates() {
        return UF.connected(virtualTopSiteIndex, virtualBottomSiteIndex);
    }

    private void connectTop(int row, int col) {
            if (row > 0 && isOpen(row - 1, col)) {
                int currIndex = getIndex(row, col);
                int targetIndex = getIndex(row - 1, col);
                UF.union(currIndex, targetIndex);
                noBackWashUF.union(currIndex, targetIndex);
            }
    }

    private void connectBottom(int row, int col) {
            if (row < N - 1 && isOpen(row + 1, col)) {
                int currIndex = getIndex(row, col);
                int targetIndex = getIndex(row + 1, col);
                UF.union(currIndex, targetIndex);
                noBackWashUF.union(currIndex, targetIndex);
            }
    }

    private void connectLeft(int row, int col) {
            if (col > 0 && isOpen(row, col - 1)) {
                int currIndex = getIndex(row, col);
                int targetIndex = getIndex(row, col - 1);
                UF.union(currIndex, targetIndex);
                noBackWashUF.union(currIndex, targetIndex);
            }

    }

    private void connectRight(int row, int col) {
            if (col < N - 1 && isOpen(row, col + 1)) {
                int currIndex = getIndex(row, col);
                int targetIndex = getIndex(row, col + 1);
                UF.union(currIndex, targetIndex);
                noBackWashUF.union(currIndex, targetIndex);
            }
    }

    /** Used for unit testing. */
    public static void main(String[] args) {
        Percolation test = new Percolation(3);
        test.open(0, 2);
        test.open(1, 2);
        test.open(2, 2);
        test.open(2, 0);
        test.open(2, 0);
        test.open(2, 0);
        test.open(2, 0);

        System.out.println(test.numberOfOpenSites());
        System.out.println(test.percolates());
        System.out.println(test.isFull(2, 0));
    }

}
