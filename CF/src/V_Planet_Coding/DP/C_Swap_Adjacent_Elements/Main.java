package V_Planet_Coding.DP.C_Swap_Adjacent_Elements;

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
            int[] arr = new int[n+1];
            for(int i = 1; i <= n; i++){
                arr[i] = in.nextInt();
            }
            String str = in.next();

            boolean possible = true;

            for(int i = 1; i <= n; i++){
                int min = arr[i];
                int max = arr[i];
                int l = i;

                while(i < n && str.charAt(i - 1) == '1'){
                    i++;
                    min = Math.min(min, arr[i]);
                    max = Math.max(max, arr[i]);
                }

                int r = i;

                if(min != l || max != r){
                    possible = false;
                    break;
                }
            }

            sb.append(possible ? "YES" : "NO").append("\n");

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