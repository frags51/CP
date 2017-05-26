//Gives wrong answer on codeforces. Correct on my pc && IDEONE
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Arrays;
public class cf_777b{
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
            byte[] buf = new byte[10001]; // line length
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
                if (c == ' ')
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
 
        public void fillBuffer() throws IOException
        {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }
 
        public byte read() throws IOException
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
 
    public static void main(String... args) throws IOException{
        Reader s = new Reader(); //Initialize a reader!
        
        boolean DEB=false;
        int n = s.nextInt();
        
        /*
        int sher = s.nextInt();
        int mor = s.nextInt();

        Character[] sc = toCharArr(new Integer(sher).toString(), n);
        Character[] mc = toCharArr(new Integer(mor).toString(), n);

        */
        Character[] sc = toCharArr(s.readLine());
        Character[] mc = toCharArr(s.readLine());
        Arrays.sort(sc);
        Arrays.sort(mc);
        int a1=0, a2=0, lo=0, hi=(sc.length-1), res1=-1, res2=-1;
        if(DEB) System.out.println(">Len of char array: "+sc.length + " ARRAY: "+sc);
SC:     for(int i=0; i<sc.length; i++){
            if(DEB) System.out.println(" >>i: "+i+"lo: "+lo+" high: "+ hi+ " sc[i]:  "+sc[i]);
            res1 = Bsearch.low_lim(sc[i], mc, lo, hi);
            if(DEB) System.out.println("    >>>>res1: "+res1);
            if(res1 < sc.length) {a1++; lo=res1+1; }
            else break;
        } //for loop sc
        System.out.println(n-a1);
        lo = 0;
MC:     for(int i=0; i<sc.length; i++){
            res2 = Bsearch.up_lim(sc[i], mc, lo, hi);
            if(res2 < sc.length) {a2++; lo=res2+1; }
            else break;
        } //for loop mc      
        System.out.println(a2);
    } //main

    static Character[] toCharArr(String a){
        if(a==null) return null;
        int LEN = a.length();
        Character[] ret = new Character[LEN];
        //int rr=0;
        //for(int kk=LEN; kk<n;kk++, rr++) ret[rr] = new Character("0".charAt(0)); //fill initial zeroes
        
        for(int i=0; i<LEN; i++){
            //System.out.println(">>>>> rr =="+rr +" LEN= "+LEN+" i== "+ i);
            ret[i] = new Character(a.charAt(i));
        }
        return ret;
    }

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
