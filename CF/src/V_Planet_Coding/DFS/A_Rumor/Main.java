package V_Planet_Coding.DFS.A_Rumor;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        InputStream inputStream;
        PrintStream outputStream;

        try {
            inputStream = new FileInputStream("input.txt");
            outputStream = new PrintStream(new FileOutputStream("output.txt"));
        } catch (FileNotFoundException e) {
            inputStream = System.in;
            outputStream = System.out;
        }

        FastReader in = new FastReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);

        StringBuilder sb = new StringBuilder();

        while (in.hasNext()) {
            int n = in.nextInt();
            int m = in.nextInt();

            long[] gold = new long[n];
            for (int i = 0; i < n; i++) {
                gold[i] = in.nextLong();
            }

            List<List<Integer>> adj = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                adj.add(new ArrayList<>());
            }

            for (int i = 0; i < m; i++) {
                int u = in.nextInt() - 1;
                int v = in.nextInt() - 1;

                adj.get(u).add(v);
                adj.get(v).add(u);
            }

            int[] vis = new int[n];
            long totalGoldSpent = 0;

            for (int i = 0; i < n; i++) {
                if (vis[i] == 0) {
                    totalGoldSpent += dfs(i, adj, vis, Long.MAX_VALUE, gold);
                }
            }

            sb.append(totalGoldSpent).append('\n');
        }

        out.print(sb);
        out.flush();
    }

    private static long dfs(int node, List<List<Integer>> adj, int[] vis, long goldSpent, long[] gold ){
        vis[node] = 1;

        goldSpent = Math.min(gold[node], goldSpent);

        for(int adjNode: adj.get(node)){
            if(vis[adjNode] == 0){
                goldSpent = dfs(adjNode, adj, vis, goldSpent, gold);
            }
        }

        return goldSpent;
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader(InputStream inputStream) {
            br = new BufferedReader(new InputStreamReader(inputStream));
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    String line = br.readLine();
                    if (line == null) throw new NoSuchElementException();
                    st = new StringTokenizer(line);
                }
                catch (IOException e) { throw new RuntimeException(e); }
            }
            return st.nextToken();
        }

        boolean hasNext() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    String line = br.readLine();
                    if (line == null) return false;
                    st = new StringTokenizer(line);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return true;
        }

        int nextInt() { return Integer.parseInt(next()); }
        long nextLong() { return Long.parseLong(next()); }
        double nextDouble() { return Double.parseDouble(next()); }
        String nextLine() {
            try { return br.readLine(); }
            catch (IOException e) { throw new RuntimeException(e); }
        }
    }
}