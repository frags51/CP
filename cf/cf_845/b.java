// Working program with FastReader
import static java.lang.System.out;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Arrays;
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

        String num = s.next();
        int[] a = new int[6];
        for(int i=0; i<6; i++) a[i] = Integer.parseInt(num.substring(i,i+1));
        int s1 = a[0]+a[1]+a[2];
        int s2 = a[3]+a[4]+a[5];
        Arrays.sort(a, 0,3);
        Arrays.sort(a, 3,6);
        int diff = s2 - s1;
        int c1=0, c2=0;
        if(s2==s1) ;
        else if(s2>s1){
            if(s2-s1 <= (9-a[0])) c1 = 1;
            else if(s2-s1 <= (18-a[0]-a[1])) c1 = 2;
            else c1 =3;

            if(s2-s1 <= (a[3])) c2=1;
            else if(s2-s1 <=(a[3]+a[4])) c2=2;
            else c2=3;        
        }//elif
        else{
            if(s1-s2 <= (9-a[3])) c1 = 1;
            else if(s1-s2 <= (18-a[4]-a[3])) c1 = 2;
            else c1 =3;

            if(s1-s2 <= (a[0])) c2=1;
            else if(s1-s2 <=(a[0]+a[1])) c2=2;
            else c2=3;  
        }
        out.println(Math.min(c1, c2));
    } //main
} //contest