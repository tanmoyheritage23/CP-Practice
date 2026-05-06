package V_Planet_Coding.DFS.D_Love_Rescue;

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

            String s1 = in.next();

            String s2 = in.next();

            List<List<Integer>> graph = new ArrayList<>();

            boolean[] visited = new boolean[26];

            List<String> spells = new ArrayList<>();

        

            for (int i = 0; i < 26; i++) {

                graph.add(new ArrayList<>());

            }

        

            for (int i = 0; i < n; i++) {

                int u = s1.charAt(i) - 'a';

                int v = s2.charAt(i) - 'a';

                if (u != v) {

                    graph.get(u).add(v);

                    graph.get(v).add(u);

                }

            }


            for (int i = 0; i < 26; i++) {

                if (!visited[i]) {

                    dfs(i, graph, visited, spells);

                }

            }

        

            sb.append(spells.size()).append('\n');

            for (String spell : spells) {

                sb.append(spell).append('\n');

            }
        }

        out.print(sb);
        out.flush();
    }

    static void dfs(int node, List<List<Integer>> graph, boolean[] visited, List<String> spells) {

        visited[node] = true;

        for (int neighbor : graph.get(node)) {

            if (!visited[neighbor]) {

                spells.add((char)(node + 'a') + " " + (char)(neighbor + 'a'));

                dfs(neighbor, graph, visited, spells);

            }

        }

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