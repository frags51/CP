// Working program with FastReader
import static java.lang.System.out;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.*;
public class b{
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
        int ax = s.nextInt();
        int ay = s.nextInt();
        int bx = s.nextInt();
        int by = s.nextInt();
        int cx = s.nextInt();
        int cy = s.nextInt();

        long sa = (long) Math.sqrt(Math.pow(1L*ax-1L*bx, 2)+Math.pow(1L*ay-1L*by, 2));
        long sb = (long) Math.sqrt(Math.pow(1L*cx-1L*bx, 2)+Math.pow(1L*cy-1L*by, 2));
        double m1 = ((double)by-ay)/((double)bx-ax);
        double m2 = ((double)cy-by)/((double)cx-bx);
        if(m1==m2) out.println("NO");
        else if(sa==sb) out.println("YES");
        else out.println("NO");

    } //main
} //contest