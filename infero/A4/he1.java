// Working program with FastReader
import static java.lang.System.out;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.*;
import java.math.BigInteger; 
public class he1{
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
            int n = s.nextInt(); 
            int[] arr = new int[n+1]; //1 indexed array.
            int ptr=2;
            int j = 1;
            if(n>2) {arr[1]=n/2+1; for(int i=1; i<=n/2; i++){
               // out.println(">>> Ptr: "+ptr+" i: "+i+" n/2: "+n/2);
               arr[ptr++]=i;
               if(ptr<=n)arr[ptr++]=n-i+1;
                
            }}
            //for(int r=1;r<=n; r++) out.print(arr[r]+" ");
            //out.println();
            
            long res=0L;
            for(int i=1; i<n; i++){
                res+=(long)Math.abs(arr[i]-arr[i+1]);

            }
            if(res==0) res = 1L;
            out.println(res);
        }
        
    } //main

} //contest