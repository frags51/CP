// Working program with FastReader
import static java.lang.System.out;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.*;
public class aps{
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
    static final int MAX_SIZE = (int)10E5;

    // method generate all prime number less then N in O(n)
    

    public static void main(String[] args)
    {
        FastReader s=new FastReader();
        int tc=s.nextInt();
        //final int m = 1000000007;

        boolean deb=false;
        int[] a=new int[(int)10E7+1]; a[0]=a[1]=1;
        int[] isPrime = new int[(int)10E7+1];
        for (int i = 2; i <= 10E7; i++) {
            isPrime[i] = 0;
        }

        // mark non-primes <= n using Sieve of Eratosthenes
        for (int factor = 2; factor*factor <= (int)10E7; factor++) {

            if(isPrime[factor]==0) {isPrime[factor]=factor;
                for (int j = factor; factor*j <= (int)10E7; j++) {
                    isPrime[factor*j] = factor;
                }
            }
            a[factor] = a[factor-1]+isPrime[factor];
        }
        if(deb) out.println(">>> SUVE DONE!");
        /*if(deb){
            out.print(">>>");
            Iterator<Integer> it = prime.iterator();
            while(it.hasNext()) out.print(it.next()+" ");
            out.println("");
        }*/
        
        for(int _t=0; _t<tc; _t++){
            int n = s.nextInt(); 
            out.println(a[n]-1);
        } //test case loop
        
    } //main
} //contest

