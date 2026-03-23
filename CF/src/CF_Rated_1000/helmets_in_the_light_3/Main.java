package CF_Rated_1000.helmets_in_the_light_3;

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

        int t = in.nextInt();
        StringBuilder sb = new StringBuilder();

        while (t-- > 0) {
            int n = in.nextInt();
            int p = in.nextInt();

            long[] villagers = new long[n];
            long[] cost = new long[n];

            for (int i = 0; i < n; i++) villagers[i] = in.nextLong();
            for (int i = 0; i < n; i++) cost[i] = in.nextLong();

            long[][] v = new long[n][2];
            for (int i = 0; i < n; i++) {
                v[i][0] = cost[i];
                v[i][1] = villagers[i];
            }

            Arrays.sort(v, Comparator.comparingLong(a -> a[0]));

            long already_shared = 1;
            long minCost = p;

            for (int i = 0; i < n && already_shared < n; i++) {
                long sharing_cost = v[i][0];
                long can_be_shared = v[i][1];

                if (sharing_cost > p) break;

                if (already_shared + can_be_shared >= n) {
                    minCost += (n - already_shared) * sharing_cost;
                    already_shared = n;
                } else {
                    minCost += can_be_shared * sharing_cost;
                    already_shared += can_be_shared;
                }
            }

            if (already_shared < n) {
                minCost += (n - already_shared) * p;
            }

            sb.append(minCost).append("\n");
        }

        out.print(sb);
        out.flush();
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
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            try {
                return br.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

