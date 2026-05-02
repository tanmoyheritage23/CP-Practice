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
        while(true){
            try{
                int h = in.nextInt();
                int m = in.nextInt();
                int s = in.nextInt();
                int t1 = in.nextInt();
                int t2 = in.nextInt();

                // Convert all to continuous positions
                double hour = h % 12 + m / 60.0 + s / 3600.0;  // 0.5125
                double minute = m / 5.0 + s / 300.0; // 6.15
                double second = s / 5.0; // 9

                // Normalize t1 and t2 (12 → 0)
                double p1 = t1 % 12; // 3
                double p2 = t2 % 12; // 11

                // Store all points
                List<Double> list = new ArrayList<>();
                list.add(hour);
                list.add(minute);
                list.add(second);
                list.add(p1);
                list.add(p2);

                Collections.sort(list);

                int idx1 = list.indexOf(p1);
                int idx2 = list.indexOf(p2);

                // Check if adjacent in circular order
                int diff = Math.abs(idx1 - idx2);

                if (diff == 1 || diff == 4) {
                    sb.append("YES\n");
                } else {
                    sb.append("NO\n");
                }

            }catch(Exception ex){
                break;
            }
            
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
                try { st = new StringTokenizer(br.readLine()); }
                catch (IOException e) { throw new RuntimeException(e); }
            }
            return st.nextToken();
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
