import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class cf_251a{
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
        boolean DEB = false;
        Reader s = new Reader(); //Initialize a reader!
        int n =s.nextInt();
        int k = s.nextInt();
        Integer[] a = new Integer[n];
        long sum=0;
        for(int lol=0; lol<n; lol++) a[lol] = new Integer(s.nextInt());
        java.util.Arrays.sort(a);
        for(int i=0; i<n-2; i++){
            int b = Bsearch.up_lim(a[i]+k, a, i+2, n-1);
            sum+=(long)((long)(b-i-1)*(b-i-2)/2L);
            if(DEB) System.out.println(">>>> i: "+i+" b-1: "+(b-1)+" added: "+((b-i-1)*(b-i-2)/2));
        }
        System.out.println(sum);
    } //main


} //public class Contest

class Bsearch{
  public static <T extends Comparable<? super T>> int bs(T key, T[] arr, int lo, int hi){
    while(lo<=hi){
      int mid = (int) (lo + (hi-lo)/2); //Wrong rounding in case of negative numbers

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
      int mid =  (int) (lo + (hi-lo)/2);

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
      int mid = (int) (lo + (hi-lo)/2);

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
