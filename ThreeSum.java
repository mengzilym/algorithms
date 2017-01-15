import edu.princeton.cs.algs4.StdIn;
import java.util.Arrays;

public class ThreeSum {
    public static int bruteForceCount(int[] nums) {
        int N = nums.length;
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                for (int k = j + 1; k < N; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        cnt++;
                    }
                }
            }
        }
        return cnt;
    }

    /**
     * Can`t deal with array contains duplicate integers.
     * Cause there are no suitable data files, the following method
     * hasn`t been tested.
     */
    public static int binarySearchCount(int[] nums) {
        int N = nums.length;
        Arrays.sort(nums);
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                int key = -(nums[i] + nums[j]);
                int lo = 0;
                int hi = N - 1;
                while (lo <= hi) {
                    int mid = (hi + lo) / 2;
                    if (key < nums[mid]) hi = mid - 1;
                    else if (key > nums[mid]) lo = mid + 1;
                    else if (mid > j) {
                        cnt++;
                        break;
                    }
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        int[] nums = StdIn.readAllInts();
        // record running time for bruteForceCount(BFC)
        final long startTimeBFC = System.currentTimeMillis();
        int cntBFC = bruteForceCount(nums);
        final long endTimeBFC = System.currentTimeMillis();
        double bruteForceTime = (endTimeBFC - startTimeBFC) / 1000.0;
        System.out.println("Brute-force count: " + cntBFC + "; time: " + bruteForceTime);
        // record running time for binarySearchCount(BSC)
        final long startTimeBSC = System.currentTimeMillis();
        int cntBSC = binarySearchCount(nums);
        final long endTimeBSC = System.currentTimeMillis();
        double binarySearchTime = (endTimeBSC - startTimeBSC) / 1000.0;
        System.out.println("Binary search count: " + cntBSC + "; time: " + binarySearchTime);
    }
}
