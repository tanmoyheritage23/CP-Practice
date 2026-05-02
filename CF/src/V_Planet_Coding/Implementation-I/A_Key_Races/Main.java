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

        while (true) {
            try {
                int s = in.nextInt();
                int v1 = in.nextInt();
                int v2 = in.nextInt();
                int t1 = in.nextInt();
                int t2 = in.nextInt();

                int time1 = 2 * t1 + s * v1;
                int time2 = 2 * t2 + s * v2;

                if (time1 < time2) {
                    sb.append("First\n");
                } else if (time1 > time2) {
                    sb.append("Second\n");
                } else {
                    sb.append("Friendship\n");
                }

            } catch (Exception e) {
                break; // ✅ stop at EOF
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
                try {
                    String line = br.readLine();
                    if (line == null) throw new RuntimeException(); // EOF
                    st = new StringTokenizer(line);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return st.nextToken();
        }

        int nextInt() { return Integer.parseInt(next()); }
    }
}