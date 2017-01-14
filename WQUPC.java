import edu.princeton.cs.algs4.StdIn;
/**
 * Created by mengzi on 2017/1/5.
 * Similar to QuickUnionUF, with size[] to record the size of each component,
 * and with Path Compression.
 * Test data file: \algs4-data\tinyUF.txt
 *                 \algs4-data\mediumUF.txt
 *                 \algs4-data\largeUF.txt
 */
public class WQUPC {
    private int[] parent;
    private int[] size;

    public WQUPC(int N) {
        parent = new int[N];
        size = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    // Path compression when searching the root of element i.
    private int root(int i) {
        while (parent[i] != i) {
            parent[i] = parent[parent[i]];
            i = parent[i];
        }
        return i;
    }

    public void union(int p, int q) {
        int pRoot = root(p);
        int qRoot = root(q);
        if (pRoot == qRoot) return;
        if (size[pRoot] < size[qRoot]) {
            parent[pRoot] = qRoot;
            size[qRoot] += size[pRoot];
        } else {
            parent[qRoot] = pRoot;
            size[pRoot] += size[qRoot];
        }
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public static void main(String[] args) {
        int N = StdIn.readInt();
        WQUPC uf = new WQUPC(N);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (!uf.connected(p, q)) {
                uf.union(p, q);
                System.out.println(p + " " + q);
            }
        }
    }
}
