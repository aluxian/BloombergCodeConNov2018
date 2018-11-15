import java.io.*;

public class Problem {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        // n = locations = vertices
        // m = paths = edges

        int n, m;
        {
            String[] parts = in.readLine().split(" ");
            n = Integer.parseInt(parts[0]);
            m = Integer.parseInt(parts[1]);
        }

        long[][] dist = new long[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                dist[i][j] = Long.MAX_VALUE;
            }
        }

        for (int i = 0; i < m; i++) {
            String[] edge = in.readLine().split(" ");
            int u = Integer.parseInt(edge[0]);
            int v = Integer.parseInt(edge[1]);
            long w = Long.parseLong(edge[2]);
            if (dist[u][v] == 0) {
                dist[u][v] = w;
            } else {
                dist[u][v] = Math.min(dist[u][v], w);
            }
            dist[v][u] = dist[u][v];
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    dist[i][j] = Math.min(dist[i][j], Math.max(0, dist[i][k] + dist[k][j]));
                }
            }
        }

        out.println();
        out.println();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                out.print(dist[i][j] + "\t");
            }
            out.println();
        }
        out.println();
        out.flush();

        int q = Integer.parseInt(in.readLine());
        for (int i = 0; i < q; i++) {
            String[] query = in.readLine().split(" ");
            int from = Integer.parseInt(query[0]);
            int to = Integer.parseInt(query[1]);
            out.println(dist[from][to]);
        }

        out.flush();
        out.close();
    }
}
