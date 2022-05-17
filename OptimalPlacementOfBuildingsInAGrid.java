import java.util.LinkedList;
import java.util.Queue;

/*
Approach: Attempted once discussed in the class
 */
public class OptimalPlacementOfBuildingsInAGrid {
    static int minDistance = Integer.MAX_VALUE;
    public static int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public static int findMinDistance(int H, int W, int n) {
        int[][] emptyPlot = new int[H][W];
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                emptyPlot[i][j] = -1;
            }
        }
        backtrack(emptyPlot, 0, 0, n, H, W);
        return minDistance;
    }

    /*
    BFS in order to find out the minimum distance from every point
     */

    private static void bfs(int[][] emptyPlot, int H, int W) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[H][W];
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (emptyPlot[i][j] == 0) {
                    q.add(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }
        int dist = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int k = 0; k < size; k++) {
                int[] curr = q.poll();
                for (int[] dir : directions) {
                    int x = dir[0] + curr[0];
                    int y = dir[1] + curr[1];
                    if (x >= 0 && x < H && y >= 0 && y < W && !visited[x][y]) {
                        q.add(new int[]{x, y});
                        visited[x][y] = true;
                    }
                }
            }
            dist++;
        }
        minDistance = Math.min(minDistance, dist - 1);
    }

    private static void backtrack(int[][] emptyPlots, int r, int c, int n, int H, int W) {
        // base
        if (n == 0) {
            bfs(emptyPlots, H, W);
            return;
        }
        // logic
        if (c >= W) {
            r++;
            c = 0;
        }
        for (int i = r; i < H; i++) {
            for (int j = c; j < W; j++) {
                //action
                emptyPlots[i][j] = 0; //put the building
                // recurse
                backtrack(emptyPlots, i, j + 1, n - 1, H, W);
                // backtrack
                emptyPlots[i][j] = -1;
            }
            c = 0;
        }
    }
    public static void main(String[] args) {
        System.out.println("Optimal Placement of buildings: "+findMinDistance(3, 2, 1));
    }
}
