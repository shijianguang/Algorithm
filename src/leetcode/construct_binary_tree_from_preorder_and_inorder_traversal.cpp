/**
 * Problem link: https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal
 *
 * Given preorder and inorder traversal of a tree, construct the binary tree.
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
        TreeNode* buildTree(vector<int>& preorder, vector<int>& inorder) {
            return helper(preorder, 0, preorder.size(), inorder, 0, inorder.size());
        }

        TreeNode* helper(vector<int>& preorder, int pstart, int pend, vector<int>& inorder, int istart, int iend) {
            if(pend == pstart) {
                return NULL;
            }

            int val = preorder[pstart];
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
            int pstart_left = pstart + 1;
            int pend_left = pstart_left + (iend_left - istart_left);
            int pstart_right = pend_left;
            int pend_right = pend;

            TreeNode* left = helper(preorder, pstart_left, pend_left, inorder, istart_left, iend_left);
            TreeNode* right = helper(preorder, pstart_right, pend_right, inorder, istart_right, iend_right);
            node->left = left;
            node->right = right;
            return node;
        }
};
