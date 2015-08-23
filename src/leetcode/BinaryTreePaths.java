/**
 * Problem link: https://leetcode.com/problems/binary-tree-paths
 *
 * Given a binary tree, return all root-to-leaf paths.
 *
 * For example, given the following binary tree:
 *
 *    1
 *  /   \
 * 2     3
 *  \
 *    5
 * All root-to-leaf paths are:
 *
 *  ["1->2->5", "1->3"]
 * 
 */
import java.util.*;

public class BinaryTreePaths {
    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node5 = new TreeNode(5);
        node2.right = node5;
        node1.left = node2;
        node1.right = node3;

        BinaryTreePaths solution = new BinaryTreePaths();
        List<String> result = solution.binaryTreePaths(node1);

        assert result.size() == 2;
        assert result.get(0).equals("1->2->5");
        assert result.get(1).equals("1->3");
    }

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if(root == null) {
            return result;
        }

        List<String> helperArray = new ArrayList<>();
        helper(root, helperArray, result);
        return result;
    }

    private void helper(TreeNode node, List<String> helperArray, List<String> result) {
        helperArray.add(String.valueOf(node.val));
        if(node.left == null && node.right == null) {
            result.add(join(helperArray, "->"));
            helperArray.remove(helperArray.size() - 1);
            return;
        }
        if(node.left != null) {
            helper(node.left, helperArray, result);
        }
        if(node.right != null) {
            helper(node.right, helperArray, result);
        }

        helperArray.remove(helperArray.size() - 1);

        return;
    }

    private String join(List<String> array, String conjunction) {
        if(array == null) {
            return "";
        }
        if(conjunction == null) {
            conjunction = "";
        }

        boolean first = true;
        StringBuffer buffer = new StringBuffer();
        for(String element : array) {
            if(first) {
                buffer.append(element);
                first = false;
            } else {
                buffer.append(conjunction)
                    .append(element);
            }
        }

        return buffer.toString();
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

}
