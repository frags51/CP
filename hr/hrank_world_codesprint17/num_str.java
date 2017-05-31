import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.math.BigInteger;
public class num_str{
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
            byte[] buf = new byte[300005]; // line length
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
            byte[] buf = new byte[32]; // line length
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
 
    public static void main(String[]args) throws IOException{
        Reader s = new Reader(); //Initialize a reader!
        String in = s.readLine(); 
        boolean DEB=false;
        int k = s.nextInt();
        
        int b = s.nextInt();
       
        int m = s.nextInt();
        
        
        long sum = 0;
        if(DEB) System.out.println(">> Before loop");
        String p = in.substring(0, 0+k);
        BigInteger val = new BigInteger(p, b);
        BigInteger _m = new BigInteger(String.valueOf(m));
        BigInteger modd = val.remainder(_m);
        sum+=modd.longValue();

        //REMOVE ALL BELOW this
        //Create an array of bigints: Store {1,2,3...b-1}^k mod m
        //CALCULATE ONCE, then just add -First^k + last mod m!
        for(int i=k; i<in.length(); i++){
            /*
            if(DEB) System.out.println(">> I:"+i);
            String p = in.substring(i, i+k);
            /*            long val=0;
            int r = p.length();
            for(int j=0; j<r; j++){
                val+=((Character.getNumericValue(p.charAt(r-j-1))) * (Math.pow((double)b,(double) j)));
            }
            
            //long val=Long.parseUnsignedLong(p, b);
            //int val = Integer.parseUnsignedInt(p, b);
            BigInteger val = new BigInteger(p, b);
            BigInteger _m = new BigInteger(String.valueOf(m));
            if(DEB) System.out.println("\t>>>>> "+p);
            if(DEB) System.out.println("\t>>>>> "+val);

            //int r = p.length();
            
            sum+=modd.intValue();
            */
            
        }//for

        System.out.println(sum);
    } //main


} //public class Contest