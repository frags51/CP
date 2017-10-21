// Working program with FastReader
import static java.lang.System.out;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;
 
public class mex{
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
            int k = s.nextInt();
            boolean[] val = new boolean[200001];
            for(int i=0; i<n; i++) val[s.nextInt()] = true;
            for(int i=0, j=0; i<200001 && j<k; i++) {if(val[i]==false) {val[i] = true; j++;}} 
            for(int i=0; i<200001; i++) if(val[i]==false){out.println(i); break;};
            
        } //test case loop
    } //main
} //contest