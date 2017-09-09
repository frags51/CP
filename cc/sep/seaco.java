// Working program with FastReader
import static java.lang.System.out;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;
 
public class seaco{
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
            int[] num_arr= new int[n+1];
            int m = s.nextInt();
            Trip[] arr = new Trip[m+1];
            for(int j=1; j<=m; j++){
                int a = s.nextInt();
                int b = s.nextInt();
                int c = s.nextInt();
                arr[j] = new Trip(n, a, b, c);
            }
            for(int j=1; j<=m; j++) Trip.exec(arr[j], arr, num_arr);
            for(int i=1; i<=n; i++) out.print(num_arr[i]+" ");
            out.println("");
        }//test case loop
    } //main


} //contest

class Trip{
    static final int modder = (int) Math.pow(10,9) + 7;
    
    int ty;
    int li;
    int ri;
    
    public Trip(int n, int ty, int li, int ri){
        
        this.ty = ty;
        this.li = li;
        this.ri = ri;
    }
    public static void exec(Trip cmd, Trip [] arr, int[] na){
        if(cmd.ty==1){
            for(int i=cmd.li; i<=cmd.ri; i++) {na[i]++; na[i]%=Trip.modder;}
        }
        else{
            for(int i = cmd.li; i<=cmd.ri; i++){
                Trip.exec(arr[i], arr, na);
            }
        }
    }
}//Trip