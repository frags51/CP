// Working program with FastReader
import static java.lang.System.out;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.*;
public class divfact{
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
    static final int MAX_SIZE = 50001;
    // isPrime[] : isPrime[i] is true if number is prime 
    // prime[] : stores all prime number less than N
    // SPF[] that store smallest prime factor of number
    // [for Exp : smallest prime factor of '8' and '16'
    // is '2' so we put SPF[8] = 2 , SPF[16] = 2 ]
    static Vector<Boolean>isprime = new Vector<Boolean>(MAX_SIZE);
    static Vector<Integer>prime = new Vector<Integer>();
    static Vector<Integer>SPF = new Vector<Integer>(MAX_SIZE);

      
    // method generate all prime number less then N in O(n)
    static void manipulated_seive(int N)
    {
        // 0 and 1 are not prime
        isprime.clear(); SPF.clear();
        for(int i=0; i<MAX_SIZE; i++) isprime.add(new Boolean(true));
        for(int j=0; j<MAX_SIZE; j++) SPF.add(new Integer(1));
        
        isprime.set(0, false);
        isprime.set(1, false);
         
        // Fill rest of the entries
        for (int i=2; i<N ; i++)
        {
            // If isPrime[i] == True then i is
            // prime number
            if (isprime.get(i))
            {
                // put i into prime[] vector
                prime.add(i);
      
                // A prime number is its own smallest
                // prime factor
                SPF.set(i,i);
            }
      
            // Remove all multiples of  i*prime[j] which are
            // not prime by making isPrime[i*prime[j]] = false
            // and put smallest prime factor of i*Prime[j] as prime[j]
            // [ for exp :let  i = 5 , j = 0 , prime[j] = 2 [ i*prime[j] = 10 ]
            // so smallest prime factor of '10' is '2' that is prime[j] ]
            // this loop run only one time for number which are not prime
            for (int j=0;
                 j < prime.size() &&
                 i*prime.get(j) < N && prime.get(j) <= SPF.get(i);
                 j++)
            {
                isprime.set(i*prime.get(j),false);
      
                // put smallest prime factor of i*prime[j]
                SPF.set(i*prime.get(j),prime.get(j)) ;
            }
        }
    } //sieve


    public static void main(String[] args)
    {
        FastReader s=new FastReader();
        int tc=s.nextInt();
        final int m = 1000000007;
        boolean deb=true;
        manipulated_seive(50001);
        /*if(deb){
            out.print(">>>");
            Iterator<Integer> it = prime.iterator();
            while(it.hasNext()) out.print(it.next()+" ");
            out.println("");
        }*/
        
        for(int _t=0; _t<tc; _t++){
            int n = s.nextInt(); 
            
            Iterator<Integer> itr = prime.iterator();
            long res = 1L;             
            while(itr.hasNext()){
                int p = itr.next();
                long pow = 0;
                long z = (long) p;
                for(;z<=n;){
                    pow+=(long)Math.floor((double)n/z);
                    z*=(long)p;
                }
                if(pow!=0) res = (res * (pow+1L))%m;
                else break;
                //if(deb) out.println(">>>Pow of ")
            }    
            out.println(res%m);
        } //test case loop
        
    } //main
} //contest

