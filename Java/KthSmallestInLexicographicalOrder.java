/**
 * Created on 9 Apr 2019 by happygirlzt
 *
 * LeetCode #440. K-th Smallest in Lexicographical Order
 *
 */

class Solution {
    public int findKthNumber(int n, int k) {
        int cur = 1;
        k = k - 1;
        while (k > 0) {
            int steps = calSteps(n, cur, cur + 1);
            if (steps <= k) {
                cur += 1;
                k -= steps;
            } else {
                cur *= 10;
                k -= 1;
            }
        }

        return cur;
    }

    private int calSteps(int n, long n1, long n2) {
        int steps = 0;
        while (n1 <= n) {
            steps += Math.min(n + 1, n2) - n1;
            n1 *= 10;
            n2 *= 10;
        }

        return steps;
    }
}
