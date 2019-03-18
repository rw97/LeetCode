/**
 * Created on 18 Mar 2019 by happygirlzt
 *
 * LeetCode #686. Repeated String Match
 *
 */

class Solution {
    public int repeatedStringMatch(String A, String B) {
        StringBuilder sb = new StringBuilder();
        if (A.indexOf(B) != -1) return 1;

        int repeatedTimes = 0;
        while (sb.indexOf(B) == -1) {
            if (sb.length() > A.length() + B.length()) return -1;
            sb.append(A);
            repeatedTimes++;
        }
        return repeatedTimes;
    }
}
