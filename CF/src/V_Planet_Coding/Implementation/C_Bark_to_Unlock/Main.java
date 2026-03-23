package V_Planet_Coding.Implementation.C_Bark_to_Unlock;

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
            try{
                String password = in.next();
                int n = in.nextInt();
                List<String>words = new ArrayList<>();
                for(int i = 0; i < n; i++){
                    words.add(in.nextLine());
                }

                boolean found = false;

                for(int i = 0; i < n; i++){
                    String word = words.get(i);
                    for(int j = 0; j < n; j++){
                        String formedWord = word.concat(words.get(j));
                        for(int k = 0; k < formedWord.length() - 1; k++){
                            String possiblePassword = formedWord.substring(k, k+2);
                            if(password.equals(possiblePassword)){
                                found = true;
                            }
                        }
                    }
                }

                if(found) sb.append("YES\n");
                else sb.append("NO\n");

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