/**
 * Created on 7 Nov 2018 by happygirlzt
 *
 * Updated on 6 Dec 2018
 *
 * LeetCode #200. Number of Islands
 *
 */

public class NumberOfIslands {
    // BFS
    private class Point {
        int x; int y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int numIslands(char[][] grid) {
        // corner case
        if (grid == null || grid.length == 0) return 0;

        int m = grid.length;
        int n = grid[0].length;

        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    bfs(grid, i, j, m, n);
                    res++;
                }
            }
        }

        return res;
    }

    public void bfs(char[][] grid, int i, int j, int m, int n) {
        Queue<Point> q = new LinkedList<>();
        grid[i][j] = '0';
        q.offer(new Point(i, j));

        while (!q.isEmpty()) {
            Point p = q.poll();

            for (int[] dir : dirs) {
                int row = p.x + dir[0];
                int col = p.y + dir[1];

                if (row < 0 || col < 0 || row >= m || col >= n) continue;

                if (grid[row][col] == '1') {
                    q.offer(new Point(row, col));
                    grid[row][col] = '0';
                }
            }
        }
    }

    // DFS
    public int numIslands1(char[][] g) {
        if (g == null || g.length == 0) return 0;

        int m = g.length, n = g[0].length;
        boolean[][] visited = new boolean[m][n];
        int res = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (g[i][j] == '1' && !visited[i][j]) {
                    res ++;
                    helper(g, i, j, visited);
                }
            }
        }

        return res;
    }

    private void helper(char[][] g, int i, int j, boolean[][] visited) {
        if (i > g.length - 1 || i < 0 || j > g[0].length - 1 || j < 0 || visited[i][j] ||
            g[i][j] == '0') return;

        visited[i][j] = true;

        helper(g, i + 1, j, visited);
        helper(g, i - 1, j, visited);
        helper(g, i, j + 1, visited);
        helper(g, i, j - 1, visited);
    }

    // Union find
    public int numIslands2(char[][] g) {
        if (g == null || g.length == 0) return 0;

        int m = g.length, n = g[0].length;
        int[] parents = new int[m * n];
        Arrays.fill(parents, -1);

        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (g[i][j] == '1') {
                    int p = i * n + j;
                    parents[p] = p;
                    count++;

                    for (int[] d : dirs) {
                        int x = d[0] + i;
                        int y = d[1] + j;
                        int r = x * n + y;

                        if ( x < 0 || x >= m || y < 0 || y >= n ||
                             parents[r] == -1) continue;
                        int rp = find(parents, r);

                        if (rp != p) {
                            parents[p] = rp;
                            p = rp;
                            count--;
                        }
                    }
                }
            }
        }
        return count;
    }

    public int find(int[] parents, int n) {
        while (parents[n] != n) {
            parents[n] = parents[parents[n]];
            n = parents[n];
        }

        return n;
    }

    // Updated on 14 Feb 2019
    class DSU {
        int[] size;
        int[] parent;
        int count;
        
        public DSU(int N) {
            size = new int[N];
            parent = new int[N];
            
            Arrays.fill(size, 1);
            Arrays.fill(parent, -1);
            count = 0;
        }
        
        public int find(int x) {
            if (parent[x] == x) {
                return x;
            } else {
                parent[x] = find(parent[x]);
                return parent[x];
            }
        }
        
        public void union(int p, int q) {
            int i = find(p);
            int j = find(q);
            if (i == j) return;
            if (size[i] < size[j]) {
                parent[i] = j;
                size[j] += size[i];
            } else {
                parent[j] = i;
                size[i] += size[j];
            }
            
            count--;
        }
    }
    
    public int numIslands4(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int m = grid.length, n = grid[0].length;
        int N = m * n;
        int[] r = {1, -1, 0, 0};
        int[] c = {0, 0, 1, -1};
        DSU dsu = new DSU(N);
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    int a = i * n + j;
                    
                    dsu.parent[a] = a;
                    dsu.count++;
                    for (int k = 0; k < 4; k++) {
                        int rr = i + r[k];
                        int cc = j + c[k];
                        if (rr < 0 || cc < 0 || rr >= m || cc >= n || dsu.parent[rr * n + cc] == -1) continue;
                        int b = rr * n + cc;
                        dsu.union(a, b);
                    }
                }
            }
        }
        
        return dsu.count;
    }
}
