package V_Planet_Coding.DFS.B_Sleepy_Game;

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
            // solve here
            int n = in.nextInt();
            int m = in.nextInt();

            List<List<Integer>> adj = new ArrayList<>();
            for(int i = 0; i < n; i++){
                adj.add(new ArrayList<>());
            }
            int[] outDegree = new int[n];

            for(int i = 0; i < n; i++){
                int edges = in.nextInt();
                outDegree[i] = edges;
                for (int j = 0; j < edges; j++){
                    int node = in.nextInt() - 1;
                    adj.get(i).add(node);
                }
            }

            int stNode = in.nextInt() - 1;

            int[][] dp = new int[n][2];
            int[][] vis = new int[n][2];
            int[][] pathVis = new int[n][2];
            int[][] children = new int[n][2];

            for(int i = 0; i < n; i++){
                Arrays.fill(children[i], -1);
            }

            dp[stNode][0] = gameResult(stNode,vis,pathVis,dp,adj,0,children);

            if(dp[stNode][0] == 2){
                sb.append("Win\n");
                int currentNode = stNode;
                int currentTurn = 0;
                while(currentNode != -1){
                    sb.append(currentNode + 1).append(" ");
                    currentNode = children[currentNode][currentTurn];
                    currentTurn = 1 - currentTurn;
                }
                sb.append("\n");

            }else if(dp[stNode][0] == 1){
                sb.append("Draw\n");
            }else{
                sb.append("Lose\n");
            }



        }

        out.print(sb);
        out.flush();
    }

    private static int gameResult(
            int node,
            int[][] vis,
            int[][] pathVis,
            int[][] dp,
            List<List<Integer>> adj,
            int turn,
            int[][] children
    ) {

        vis[node][turn] = 1;
        pathVis[node][turn] = 1;

        // Base case: no outgoing edges
        if (adj.get(node).size() == 0) {

            pathVis[node][turn] = 0;

            // Petya cannot move -> Lose
            if (turn == 0) return dp[node][turn] = 0;

            // Vasya cannot move -> Win
            return dp[node][turn] = 2;
        }

        // Default worst case
        dp[node][turn] = 0;

        for (int neighbour : adj.get(node)) {

            int nextTurn = 1 - turn;

            // Cycle => Draw possible
            if (pathVis[neighbour][nextTurn] == 1) {
                if(1 > dp[node][turn]) {
                    dp[node][turn] = 1;
                    children[node][turn] = neighbour;
                }
                continue;
            }

            if (vis[neighbour][nextTurn] == 0) {
                dp[neighbour][nextTurn] =
                        gameResult(neighbour, vis, pathVis, dp, adj, nextTurn, children);
            }

            // Best outcome priority:
            // Win (2) > Draw (1) > Lose (0)
            if(dp[neighbour][nextTurn] > dp[node][turn]){
                dp[node][turn] = dp[neighbour][nextTurn];
                children[node][turn] = neighbour;
            }
        }

        pathVis[node][turn] = 0;

        return dp[node][turn];
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
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
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
    

