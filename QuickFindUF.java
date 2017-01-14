import edu.princeton.cs.algs4.StdIn;

/**
 * Created by mengzi on 2017/1/5.
 * component[i] represent the component of i.
 * component[i] == component[j] means i and j are in the same component.
 * Test data file: \algs4-data\tinyUF.txt
 *                 \algs4-data\mediumUF.txt
 *                 \algs4-data\largeUF.txt
 */
public class QuickFindUF {

    private int[] component;
    // declare an array of the component of i;
    private int[] size;

    public QuickFindUF(int N) {
        component = new int[N];
        size = new int[N];
        for (int i = 0; i < N; i++) {
            component[i] = i;
            size[i] = 1;
        }
    }

    public void union(int p, int q) {
        int pComponent = component[p];
        int qComponent = component[q];
        if (pComponent == qComponent) return;
        int pSize = size[p];
        int qSize = size[q];
        if (pSize > qSize) {
            for (int i = 0; i < size.length; i++) {
                if (component[i] == qComponent) component[i] = pComponent;
            }
        } else {
            for (int i = 0; i < size.length; i++) {
                if (component[i] == pComponent) component[i] = qComponent;
            }
        }
        size[p] = pSize + qSize;
        size[q] = pSize + qSize;
    }

    public boolean connected(int p, int q) {
        return component[p] == component[q];
    }

    public static void main(String[] args) {
        int N = StdIn.readInt();
        QuickFindUF uf = new QuickFindUF(N);
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
