// Working program with FastReader
import static java.lang.System.out;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.*;
import java.math.BigInteger; 
public class marbles{
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
        int tc=s.nextInt();
        for(int _t=0; _t<tc; _t++){
            int n = s.nextInt(); int k = s.nextInt();
            out.println(ncr(n-1, k-1));
        }
        
    } //main

    public static BigInteger ncr(int n, int r){
        BigInteger res=new BigInteger("1");
        BigInteger rd = new BigInteger("1");
        if(r < n-r) r = n-r;
        for(int j=n; j>r; j--) res=res.multiply(new BigInteger(new Integer(j).toString()));
        for(int j=1; j<=(n-r); j++) rd=rd.multiply(new BigInteger(new Integer(j).toString()));
        return res.divide(rd);
    }
} //contest