// Working program with FastReader
import static java.lang.System.out;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;
 
public class chefcoun{
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
            int n = s.nextInt();
            long m = ((long)Math.pow(2,32) - (long)3);
            //out.println("DEB:: val:"+((long)Math.pow(2,32) - (long)3)+" ; m: "+m+" ; m/n: "+(int)(m/n));
            long r = m % (n-1); 
            m = (m/(n-1));
            out.print("1 ");
            if(r%3==0) {
                for(int i = 1; i<n-3; i++) out.print(m+" ");
                out.print(m+(r/3)+" ");out.print(m+(r/3)+" ");out.print(m+(r/3));
            }
            else if(r%3==1){
                for(int i = 1; i<n-3; i++) out.print(m+" ");
                out.print(m+(r/3)+" ");out.print(m+(r/3)+" ");out.print(m+(r/3)+1);
            }
            else{
                for(int i = 1; i<n-3; i++) out.print(m+" ");
                out.print(m+(r/3)+" ");out.print(m+(r/3)+1+" ");out.print(m+(r/3)+1);
            }
        
            out.println();
        } //test case loop
    } //main
} //contest