/**
 * Created on 12 Dec 2018 by happygirlzt
 *
 * LeetCode #394. Decode String
 *
 */

class Solution {
    private int i = 0;

    public String decodeString(String s) {
        StringBuilder sb = new StringBuilder();
        int num = 0;

        for (; i < s.length(); i++) {
            // while (++i < s.length()) {
            char c = s.charAt(i);

            if (c == '[') {
                i++;
                String str = decodeString(s);
                for (int k = 0; k < num; k++) {
                    sb.append(str);
                }
                num = 0;
            } else if (c == ']') {
                return sb.toString();
            } else if (c >= '0' && c <= '9') {
                num = num * 10 + c - '0';
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }
}
