// Working program with FastReader
import static java.lang.System.out;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;
 
public class cf_556a{
    static class FastReader{
        BufferedReader br;
        StringTokenizer st;
 
        public FastReader()
        {
            br = new BufferedReader(new
                     InputStreamReader(System.in));
        }
 
        String next()
        {
            while (st == null || !st.hasMoreElements())
            {
                try
                {
                    st = new StringTokenizer(br.readLine());
                }
                catch (IOException  e)
                {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
 
        int nextInt()
        {
            return Integer.parseInt(next());
        }
 
        long nextLong()
        {
            return Long.parseLong(next());
        }
 
        double nextDouble()
        {
            return Double.parseDouble(next());
        }
 
        String nextLine()
        {
            String str = "";
            try
            {
                str = br.readLine();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            return str;
        }
    }
 
    public static void main(String[] args)
    {
        FastReader s=new FastReader();
        int n = s.nextInt();
        String num = s.nextLine();
        int[] count = new int[2];
        int i=0, del_cnt = 0;
        if(num.charAt(0)=='0'){
            while(i<n){
                while(i<n && num.charAt(i)=='0'){
                    count[0]++;
                    i++;
                }
                while(i<n && num.charAt(i)=='1'){
                    count[1]++;
                    i++;
                }
                int d= Math.min(count[0], count[1]);
                del_cnt+=(2*d);
                count[0]-=d;
                count[1]-=d;

            }
        } //if
        else{
            while(i<n){
                while(i<n && num.charAt(i)=='1'){
                    count[1]++;
                    i++;
                }
                while(i<n && num.charAt(i)=='0'){
                    count[0]++;
                    i++;
                }
                int d= Math.min(count[0], count[1]);
                del_cnt+=(2*d);
                count[0]-=d;
                count[1]-=d;

            }
        }
        out.println(n-del_cnt);

    }// main
}