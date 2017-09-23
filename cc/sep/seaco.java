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
        boolean deb = false;
        int t = s.nextInt();
        for(int _t=0; _t<t; _t++){
            int n = s.nextInt();
            int m = s.nextInt();
            long[] num_arr= new long[m+2];
            long[] cmd_arr = new long[m+2];
            long[] ans = new long[n+2];
            Trip[] cmd = new Trip[m+1];
            for(int j=1; j<=m; j++){
                int a = s.nextInt();
                int b = s.nextInt();
                int c = s.nextInt();
                cmd[j] = new Trip(n, a, b, c);
            }
            for(int j=m; j>=1; j--){
                num_arr[j] +=num_arr[j+1];
                if(cmd[j].ty==2){
                    num_arr[j]++;
                    num_arr[j-1]--;
                    num_arr[cmd[j].ri]+=num_arr[j];
                    num_arr[cmd[j].li-1]-=num_arr[j];
                    
                }
            } //loop from back
            if(deb) for(int j=1; j<=m; j++) {out.print("\nNUM_AR: "+j+" ");out.print(num_arr[j]+ " ");out.println("");}
 
            for(int j=m; j>=1; j--){
                if(cmd[j].ty==1){
                    ans[cmd[j].li]+=(num_arr[j]+1);
                    ans[cmd[j].ri+1]-=(num_arr[j]+1);
                }
            } //loop from back
 
            for(int j=2; j<=n; j++){
                ans[j] = ans[j-1] + ans[j];
            }
            for(int j=1; j<=n; j++) ans[j]%=Trip.modder;
            for(int j=1; j<=n; j++) out.print(ans[j]+" ");
            
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