// import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
// import edu.princeton.cs.algs4.StdRandom;


public class Percolation {
    private int[][] grid;
    // private int[][] parent;
    private int count = 0;
    private WeightedQuickUnionUF uf;

    /**
     * Create n-by-n grid, with all sites blocked which is represented by 0.
     */
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("The length of the side of grid must larger than 0.");
        }
        grid = new int[n][n];

        /**
         * Create two virtual sites respectively at the beginning and end of
         * the grid. Sites in the first row will be connected to the beginning
         * virtual site, and sites in the last row will be connected to the end
         * virtual site in the constructor method.
         */
        uf = new WeightedQuickUnionUF(n * n + 2);
        for (int i = 1; i <= n; i++) uf.union(0, i);
        for (int i = (n - 1) * n + 1; i <= n * n; i++) uf.union(n * n + 1, i);
    }

    private void validate(int row, int col) {
        int n = grid.length;
        if (row < 1 || row > n || col < 1 || col > n) {
            throw new IndexOutOfBoundsException("index must between 1 and "+ n);
        }
    }

    /**
     * Given the row and column of a site, calculate its corresponding integer,
     * which will be used in WeightedQuickUnionUF algorithm.
     */
    private int getIndex(int row, int col) {
        return (row - 1) * grid.length + col;
    }

    /**
     * Open site (row, col) if it`s not open already.
     * When a blocked site is open, connect it with surrounding open sites.
     */
    public void open(int row, int col) {
        validate(row, col);
        if (grid[row-1][col-1] == 1) return;

        grid[row-1][col-1] = 1; // open a site by changing it from 0 to 1.
        count++;

        int thisSiteIndex = getIndex(row, col);
        int n = grid.length;
        if ((row - 1) > 0) {
            if (isOpen(row - 1, col)) {
                int upSiteIndex = getIndex(row - 1, col);
                uf.union(thisSiteIndex, upSiteIndex);
            }
        }
        if ((col + 1) < n) {
            if (isOpen(row, col + 1)) {
                int rightSiteIndex = getIndex(row, col + 1);
                uf.union(thisSiteIndex, rightSiteIndex);
            }
        }
        if ((row + 1) < n) {
            if (isOpen(row + 1, col)) {
                int downSiteIndex = getIndex(row + 1, col);
                uf.union(thisSiteIndex, downSiteIndex);
            }
        }
        if ((col - 1) > 0) {
            if (isOpen(row, col - 1)) {
                int leftSiteIndex = getIndex(row, col - 1);
                uf.union(thisSiteIndex, leftSiteIndex);
            }
        }
    }

    public boolean isOpen(int row, int col) {
        validate(row, col);
        return grid[row - 1][col - 1] == 1;
    }

    public boolean isFull(int row, int col) {
        if (isOpen(row, col)) {
            int siteIndex = getIndex(row, col);
            return uf.connected(siteIndex, 0);
        }
        return false;
    }

    public int numberOfOpenSites() {
        return count;
    }
    
    public boolean percolates() {
        int n = grid.length;
        if (n == 1) return isOpen(1, 1);
        return uf.connected(0, n * n + 1);
    }

    public static void main(String[] args) {
        Percolation peron = new Percolation(Integer.parseInt(args[0]));
    }
}
