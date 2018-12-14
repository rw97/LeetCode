/**
 * Created on 14 Dec 2018 by happygirlzt
 *
 * LeetCode #938. Range Sum of BST
 *
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    int res;
    public int rangeSumBST(TreeNode root, int L, int R) {
        res = 0;
        dfs(root, L, R);
        return res;
    }

    private void dfs(TreeNode root, int L, int R) {
        // Do not forget the base case
        if (root == null) return;

        if (root.val >= L && root.val <= R) {
            res += root.val;
        }

        if (root.val > L) {
            dfs(root.left, L, R);
        }

        if (root.val < R) {
            dfs(root.right, L, R);
        }
    }
}
