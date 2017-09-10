// Working program with FastReader
import static java.lang.System.out;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.*;
public class c{
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
        //boolean deb=false;
        int n = s.nextInt();
        int[] a = new int[n+1];
        int[] b = new int[n+1];
        int[] c = new int[n+1];
        int[] d = new int[n+1];
        int[] e = new int[n+1];
        for(int i=1; i<=n; i++){
            a[i] = s.nextInt();
            b[i] = s.nextInt();
            c[i] = s.nextInt();
            d[i] = s.nextInt();
            e[i] = s.nextInt();
        } //scan for

        
    } //main
    
} //contest