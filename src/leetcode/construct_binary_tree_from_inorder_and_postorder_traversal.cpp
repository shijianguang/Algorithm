/**
 * Problem link: https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal
 *
 * Given inorder and postorder traversal of a tree, construct the binary tree.
 *
 * Note:
 * You may assume that duplicates do not exist in the tree.
 */

#include <iostream>
#include <vector>

using namespace std;

struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};

class Solution {
    public:
        TreeNode* buildTree(vector<int>& inorder, vector<int>& postorder) {
            return helper(inorder, 0, inorder.size(), postorder, 0, postorder.size());
        }

        TreeNode* helper(vector<int>& inorder, int istart, int iend, vector<int>& postorder, int pstart, int pend) {
            if(pend == pstart) {
                return NULL;
            }

            int val = postorder[pend - 1];
            TreeNode *node = new TreeNode(val);
            int inorder_index = istart;
            for( ; inorder_index < iend ; ++ inorder_index) {
                if(inorder[inorder_index] == val) {
                    break;
                }
            }

            int istart_left = istart;
            int iend_left = inorder_index;
            int istart_right = inorder_index + 1;
            int iend_right = iend;
            int pstart_left = pstart;
            int pend_left = pstart + (iend_left - istart_left);
            int pstart_right = pend_left;
            int pend_right = pend - 1;

            TreeNode* left = helper(inorder, istart_left, iend_left, postorder, pstart_left, pend_left);
            TreeNode* right = helper(inorder, istart_right, iend_right, postorder, pstart_right, pend_right);
            node->left = left;
            node->right = right;
            return node;
        }
};
