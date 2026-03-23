package CF_Rated_1000.raspberries_2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();  // number of test cases

        StringBuilder sb = new StringBuilder();

        while (t-- > 0) {
            int n = sc.nextInt();
            int k = sc.nextInt();
            int[] arr = new int[n];

            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }

            int even_count = 0;
            int ans = Integer.MAX_VALUE;

            for (int i = 0; i < n; i++) {
                if (arr[i] % 2 == 0) even_count++;
                if (arr[i] % k == 0) ans = 0;
                ans = Math.min(ans, k - arr[i] % k);
            }

            if (k == 4) {
                if (even_count >= 2) {
                    ans = Math.min(ans, 0);
                } else if (even_count == 1) {
                    ans = Math.min(ans, 1);
                } else {
                    ans = Math.min(ans, 2);
                }
            }

            sb.append(ans).append("\n");
        }

        System.out.println(sb);
        sc.close();
    }
}