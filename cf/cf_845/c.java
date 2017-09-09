// Working program with FastReader
import static java.lang.System.out;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Arrays;
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
        int n = s.nextInt();
        Pair[] t = new Pair[n];
        for(int i=0; i<n; i++) t[i]= new Pair(s.nextInt(), s.nextInt());
        Arrays.sort(t, (Pair p1, Pair p2) -> (p1.l == p2.l) ? p1.r-p2.r : p1.l - p2.l);
        //for(int r=0; r<n; r++) out.println(t[r].l+" "+ t[r].r);
        Pair[] tv = new Pair[2];
        int fl=0;
        tv[0] = new Pair(t[0].l, t[0].r);
        tv[1] = new Pair(0,0);
        if(n>=2 && t[1].l <= t[0].r) {tv[1].l = t[1].l; tv[1].r=t[1].r;}
        else if(n>=2 && t[1].l > t[0].r) {tv[0].l = t[1].l; tv[0].r=t[1].r; fl=1;}
        boolean found = true;
        if(n > 2){
            for(int i=2; i < n; i++){
                if(t[i].l <= tv[fl].r) {found=false; break;}
                tv[fl].l=t[i].l;
                tv[fl].r=t[i].r;
                fl += 1;
                fl = fl%2;
            }//for
        } // n > 2

        if(found) out.println("YES");
        else out.println("NO");
    } //main
} //contest

class Pair{
    int l;
    int r;
    public Pair(int a, int b){
        this.l=a;
        this.r=b;
    }
}