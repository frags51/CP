// Working program with FastReader
import static java.lang.System.out;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.*;
public class sepb{
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
        for(int _t=0; _t<t; _t++){
            String n = s.next();
            int[] nc = new int[10];
            boolean[] a = new boolean[26];
            for(int i=0; i<n.length();i++){
                if(nc[n.charAt(i)-'0'] < 2) nc[n.charAt(i)-'0']++;
            }      
            for(int i=65; i<=90; i++){
                int d1 = i%10;
                int d2 = i/10;
                d2 %=10;
                if(d1==d2 && nc[d1]>=2) out.print((char) i);
                else if(d1!=d2 && nc[d1]>=1 && nc[d2]>=1) out.print((char) i);

            }
            out.println("");
        } //test case
    } //main
} //contest