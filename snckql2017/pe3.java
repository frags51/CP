import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Arrays;
public class pe3{
        static class Reader
    {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;
 
        public Reader()
        {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }
 
        public Reader(String file_name) throws IOException
        {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }
 
        public String readLine() throws IOException
        {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1)
            {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }
        /*
            Added by me.
        */
        public String readWord() throws IOException
        {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1)
            {
                if (c == ' ' || c=='\n') //care ADD ANY WHITESPACE CHAR??
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }
        public int nextInt() throws IOException
        {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do
            {
                ret = ret * 10 + c - '0';
            }  while ((c = read()) >= '0' && c <= '9');
 
            if (neg)
                return -ret;
            return ret;
        }
 
        public long nextLong() throws IOException
        {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }
 
        public double nextDouble() throws IOException
        {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
 
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
 
            if (c == '.')
            {
                while ((c = read()) >= '0' && c <= '9')
                {
                    ret += (c - '0') / (div *= 10);
                }
            }
 
            if (neg)
                return -ret;
            return ret;
        }
 
        private void fillBuffer() throws IOException
        {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }
 
        private byte read() throws IOException
        {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }
 
        public void close() throws IOException
        {
            if (din == null)
                return;
            din.close();
        }
    } //static class Reader
 
    public static void main(String[] args) throws IOException{
        Reader s = new Reader(); //Initialize a reader!
        int t = s.nextInt();
        for(int blah=t; blah<t; blah++){
            int n = s.nextInt();
            int m = s.nextInt();
            int u=0, v=0;
            int[] d = new int[n+1]; //1-indexed array. Intialized to 0.
            for(int i=1; i<=n; i++){
                u = s.nextInt();
                v = s.nextInt();
                d[u]++;
                d[v]++;
            }
            Arrays.sort(d);
            int r;
            for(r=0; r<n; r++){
                int l = Bsearch.low_lim(r, d, 1, n);// 1-indexed array
                int h = Bsearch.up_lim(r,d,1,n);
                if(l==h && l==1){ //all elements have degree > r
                    System.out.println("0 ");
                }
                else if(l==h && l==n+1){ //Few elements with deg < r
                    System.out.println((l-2)+" ");
                }
                else if(l==h){
                    
                }
            }
        }//Test loop

    } //main


} //public class Contest

class Bsearch{
  public static <T extends Comparable<? super T>> int bs(T key, T[] arr, int lo, int hi){
    while(lo<=hi){
      int mid = (int) (lo + hi)/2;

      int u = key.compareTo(arr[mid]);
      if(u == 0){
        return mid;
      }
      else if(u < 0){
        hi = mid-1;
      }
      else if(u > 0){
        lo = mid+1;
      }

    } //while loop
    return -1;
  } //bs

  public static <T extends Comparable<? super T>> int low_lim(T key, T[] arr, int lo, int hi){
    while(lo<=hi){
      int mid = (int) (lo + hi)/2;

      int u = key.compareTo(arr[mid]);
      
      if(u <= 0){ //keep moving left in case element is found once.
        hi = mid-1;
      }
      else if(u > 0){
        lo = mid+1;
      }

    } //while loop
    return lo;
  } //low_lim

  public static <T extends Comparable<? super T>> int up_lim(T key, T[] arr, int lo, int hi){
    while(lo<=hi){
      int mid = (int) (lo + hi)/2;

      int u = key.compareTo(arr[mid]);

      if(u < 0){
        hi = mid-1;
      }
      else if(u >= 0){
        lo = mid+1;
      }

    } //while loop
    return lo;
  } //low_lim
}//class ends
