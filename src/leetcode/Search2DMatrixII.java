/**
 * Problem link: https://leetcode.com/problems/search-a-2d-matrix-ii
 *
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
 *
 * Integers in each row are sorted in ascending from left to right.
 * Integers in each column are sorted in ascending from top to bottom.
 * For example,
 *
 * Consider the following matrix:
 *
 * [
 *  [1,   4,  7, 11, 15],
 *  [2,   5,  8, 12, 19],
 *  [3,   6,  9, 16, 22],
 *  [10, 13, 14, 17, 24],
 *  [18, 21, 23, 26, 30]
 * ]
 * 
 * Given target = 5, return true.
 * Given target = 20, return false.
 */

public class Search2DMatrixII {
    public static void main(String[] args) {
        int[][] matrix = {{1, 4, 7, 11, 15},
                          {2, 5, 8, 12, 19},
                          {3, 6, 9, 16, 22},
                          {10, 13, 14, 17, 24},
                          {18, 21, 23, 26, 30}};

        Search2DMatrixII solution = new Search2DMatrixII();
        assert solution.searchMatrix(matrix, 5) == true;
        assert solution.searchMatrix(matrix, 20) == false;
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length;
        if(row < 1) {
            return false;
        }

        int col = matrix[0].length;
        int i = row - 1, j = 0;

        while(i >= 0 && j < col) {
            if(matrix[i][j] == target) {
                return true;
            } else if(matrix[i][j] < target) {
                ++ j;
            } else {
                -- i;
            }
        }

        return false;
    }
}
