package V_Planet_Coding.Implementation.B_Fafa_and_the_Gates;

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
            int n = in.nextInt();
            String str = in.nextLine();
            int silverNeeded = 0;
            int x = 0, y = 0;
            int prevKingdom = str.charAt(0) == 'U' ? 1 : 2;
            for(int i = 0; i < str.length(); i++){
                char move = str.charAt(i);
                if(move == 'U'){
                    y++;
                }else{
                    x++;
                }

                if(y > x){
                    
                    if(prevKingdom == 2){
                        silverNeeded++;
                        prevKingdom = 1;
                    }
                    

                }else if(x > y){
                    
                    if(prevKingdom == 1){
                        silverNeeded++;
                        prevKingdom = 2;
                    }
                }
            }
            sb.append(silverNeeded);
            sb.append("\n");
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