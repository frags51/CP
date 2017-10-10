// Working program with FastReader
import static java.lang.System.out;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;
 
public class panda{
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
        int t = s.nextInt();
        final int m = 1000003;
        long[] fact = new long[m+1];
        fact[0] = 1;
        for(int i=1; i<=m; i++){
            fact[i] = (((long)fact[i-1])*(long)i)%m;
        }
        for(int _t=0; _t<t; _t++){
            long n = s.nextLong();
            long x = s.nextLong();
            if(n>=m) out.println("0");
            else out.println(((long)fact[(int)n]*(long)(x%m))%m);
        } //test case loop

    } //main
} //contest