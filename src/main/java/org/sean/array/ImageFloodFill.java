package org.sean.array;

// 733. Flood Fill
public class ImageFloodFill {
    boolean[][] visited;
    int[][] diffs = new int[][] {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image == null || image.length == 0) return new int[0][0];

        int rowCnt = image.length;
        int colCnt = image[0].length;

        visited = new boolean[rowCnt][colCnt];

        dfs(image, sr, sc, image[sr][sc], newColor);

        return image;
    }

    private void dfs(int[][] image, int sr, int sc, int target, int newColor) {
        if ((sr >= 0 && sr < image.length)
                && (sc >= 0 && sc < image[0].length)
                && !visited[sr][sc]) {

            visited[sr][sc] = true;

            if (image[sr][sc] == target) {
                image[sr][sc] = newColor;

                for (int[] diff : diffs) {
                    dfs(image, sr + diff[0], sc + diff[1], target, newColor);
                }
            }
        }
    }
}
