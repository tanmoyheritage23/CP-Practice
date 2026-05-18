package V_Planet_Coding.DP.A_Vacations;

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

            int[] days = new int[n];
            for(int i = 0; i < n; i++){
                days[i] = in.nextInt();
            }

            // dp[i][j] = min days vasya can take rest till day i if on day i he choose j activity.
            // j -> 0(rest), -> 1(contest), -> 2(gym)
            int[][] dp = new int[n][3];

            final int INF = 100000;

            for(int i = 0; i < n; i++){
                Arrays.fill(dp[i], INF);
            }

            // base case
            dp[0][0] = 1;

            if(days[0] == 1 || days[0] == 3) dp[0][1] = 0;

            if(days[0] == 2 || days[0] == 3) dp[0][2] = 0;

            for(int i = 1; i < n; i++){
                dp[i][0] = Math.min(dp[i-1][0], Math.min(dp[i-1][1],dp[i-1][2])) + 1;

                if(days[i] == 1 || days[i] == 3){
                    dp[i][1] = Math.min(dp[i-1][0],dp[i-1][2]);
                }

                if(days[i] == 2 || days[i] == 3){
                    dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]);
                }
            }

            int answer = Math.min(dp[n-1][0], Math.min(dp[n-1][1], dp[n-1][2]));
            sb.append(answer).append("\n");
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
