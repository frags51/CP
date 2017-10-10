// Working program with FastReader
import static java.lang.System.out;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.*; 
public class anarc09a{
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
        int test_case=1;
        for(String in = s.next(); in.charAt(0)!='-'; in=s.next()){
            Deque<Integer> st = new LinkedList<Integer>();
            Deque<Integer> st2 = new LinkedList<Integer>();

            for(int i=0; i<in.length(); i++){
                if(in.charAt(i)=='{') st.push(1);
                else if(st.size()>0) {st.pop();}
                else {st2.push(1);st.push(1);}                
            } //inner for
            out.println(test_case+". "+(st.size()/2 + st2.size()));
            test_case++;
        }
        
    } //main
} //contest