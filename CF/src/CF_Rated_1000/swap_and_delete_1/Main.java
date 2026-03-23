import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int T;
        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();
        StringBuilder builder = new StringBuilder();
        while(T-->0){
            String S = sc.next();
            int n = S.length();
            int zeros = 0; int ones = 0;
            for(int i=0;i<n;i++){
                if(S.charAt(i)=='0'){
                    zeros++;
                }else{
                    ones++;
                }
            }

            int tLength = 0;

            for(int i = 0; i < n; i++){
                if(S.charAt(i)=='0' && ones > 0){
                    ones--;
                    tLength++;

                }else if(S.charAt(i)=='1' && zeros > 0){
                    zeros--;
                    tLength++;

                }else break;
            }

            int minCost = n-tLength;
            builder.append(minCost).append("\n");
        }

        System.out.println(builder.toString());
        sc.close();
    }
}

