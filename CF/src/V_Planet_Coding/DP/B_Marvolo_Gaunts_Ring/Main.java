package V_Planet_Coding.DP.B_Marvolo_Gaunts_Ring;

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
            long[] constants = new long[3];
            for(int i = 0; i < 3; i++){
                constants[i] = in.nextLong();
            }

            List<Long>powerMultiplier = new ArrayList<>();
            for(int i = 0; i < n; i++){
                powerMultiplier.add(in.nextLong());
            }

            // fix j and try to find best prefix p.i(1<=i<=j) and suffix r.k(j<=k<=n)

            long maxPotion = Long.MIN_VALUE;

            long[] bestPrefix = new long[n];
            long[] bestSuffix = new long[n];
            long best = Long.MIN_VALUE;

            for(int i = 0; i < n; i++){
                best = Math.max(best, constants[0]*powerMultiplier.get(i));
                bestPrefix[i] = best;
            }

            best = Long.MIN_VALUE;

            for(int i = n - 1; i >= 0; i--){
                best = Math.max(best, constants[2]*powerMultiplier.get(i));
                bestSuffix[i] = best;
            }

            for(int i = 0; i < n; i++){
                maxPotion = Math.max(maxPotion, bestPrefix[i] + constants[1]*powerMultiplier.get(i) + bestSuffix[i]);
            }

            sb.append(maxPotion).append("\n");
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
