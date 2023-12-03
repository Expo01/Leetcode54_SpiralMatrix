import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        System.out.println(new Solution().spiralOrder(matrix));
    }
}

// this only works for a 3x3 matrix since it will traverse all the way to R, then D then
// L, then when it starts to go up to the new cell, it will check to the right to the
// interior of the larger square insteads of continuing up
//class Solution {
//    List<Integer> ans = new ArrayList<>();
//    int[][] directions = {{0,1}, {1,0}, {0,-1}, {-1,0}};
//
//    public List<Integer> spiralOrder(int[][] matrix) {
//        Set<Integer> visited = new HashSet<>();
//
//        traverse(visited, matrix, 0,0);
//
//        return ans;
//    }
//
//    private void traverse(Set<Integer> visited, int[][] matrix, int row, int col){
//        if ( row >= matrix.length || row < 0 ||
//                col >= matrix[0].length || col < 0 || visited.contains(matrix[row][col])){
//            return;
//        } else{
//            visited.add(matrix[row][col]);
//            ans.add(matrix[row][col]);
//
//            for(int[] dir: directions){
//                traverse(visited,matrix, row + dir[0], col + dir[1]);
//            }
//        }
//    }
//}

// my solutiion, works 22/25 then TLE
class Solution {
    List<Integer> ans = new ArrayList<>();
    int[][] directions = {{0,1}, {1,0}, {0,-1}, {-1,0}};

    public List<Integer> spiralOrder(int[][] matrix) {
        Set<Integer> visited = new HashSet<>();
        int row = 0;
        int col = -1;
        int dirIndex = 0;

        while(visited.size() < matrix.length * matrix[0].length){
            int[] dir = directions[dirIndex];
            row += dir[0];
            col+= dir[1];

            while(row >= 0 && row < matrix.length && col >=0 && col < matrix[0].length
                    && !visited.contains(matrix[row][col])){
                visited.add(matrix[row][col]);
                ans.add(matrix[row][col]);
                System.out.println(Arrays.toString(visited.toArray()));
                row += dir[0];
                col+= dir[1];
            }
            row -= dir[0];
            col-= dir[1];

            dirIndex++;
            dirIndex %= 4;
        }

        System.out.println(Arrays.toString(ans.toArray()));
        return ans;
    }
}

// modified version of my solution avoiding extra space channing visited to just modifying cell val to non-valid #
// beats 100 and 22%
class Solution {
    List<Integer> ans = new ArrayList<>();
    int[][] directions = {{0,1}, {1,0}, {0,-1}, {-1,0}};

    public List<Integer> spiralOrder(int[][] matrix) {
        Set<Integer> visited = new HashSet<>();
        int row = 0;
        int col = -1;
        int dirIndex = 0;


        while(ans.size() < matrix.length * matrix[0].length){
            int[] dir = directions[dirIndex];
            row += dir[0];
            col+= dir[1];

            while(row >= 0 && row < matrix.length && col >=0 && col < matrix[0].length
                    && matrix[row][col] != 101){
                ans.add(matrix[row][col]);
                matrix[row][col] = 101;
                row += dir[0];
                col+= dir[1];
            }
            row -= dir[0];
            col-= dir[1];

            dirIndex++;
            dirIndex %= 4;
        }

        return ans;
    }
}

class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        int rows = matrix.length;
        int columns = matrix[0].length;
        //boundaries
        int up = 0;
        int left = 0;
        int right = columns - 1;
        int down = rows - 1;

        while (result.size() < rows * columns) {
            // Traverse from left to right.
            for (int col = left; col <= right; col++) {
                result.add(matrix[up][col]);
            }
            // Traverse downwards.
            for (int row = up + 1; row <= down; row++) {
                result.add(matrix[row][right]);
            }
            // Make sure we are now on a different row.
            if (up != down) {
                // Traverse from right to left.
                for (int col = right - 1; col >= left; col--) {
                    result.add(matrix[down][col]);
                }
            }
            // Make sure we are now on a different column.
            if (left != right) {
                // Traverse upwards.
                for (int row = down - 1; row > up; row--) {
                    result.add(matrix[row][left]);
                }
            }
            left++;
            right--;
            up++;
            down--;
        }

        return result;
    }
}



//traversal sequence: R,D,L,U. boolean[] of length mXn for visited