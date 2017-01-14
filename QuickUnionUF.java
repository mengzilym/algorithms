import edu.princeton.cs.algs4.StdIn;
/**
 * Created by mengzi on 2017/1/5.
 * Treat each connected component as a tree.
 * Each element has a parent and finally a root. (could be itself)
 * parent[parent[i[...]]] == the root of element i.
 * And two elements with the same root are in one component.
 * Test data file: \algs4-data\tinyUF.txt
 *                 \algs4-data\mediumUF.txt
 *                 \algs4-data\largeUF.txt
 */
public class QuickUnionUF {
    private int[] parent;

    public QuickUnionUF(int N) {
        parent = new int[N];
        for (int i = 0; i < N; i++) parent[i] = i;
    }

    private int root(int i) {
        while (i != parent[i]) i = parent[i];
        return i;
    }

    public void union(int p, int q) {
        int pRoot = root(p);
        int qRoot = root(q);
        parent[pRoot] = qRoot;
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public static void main(String[] args) {
        int N = StdIn.readInt();
        QuickUnionUF uf = new QuickUnionUF(N);
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
