import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;
public class chefmovr{
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
            byte[] buf = new byte[1009]; // line length
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
                if (Character.isWhitespace(c)) //care ADD ANY WHITESPACE CHAR??
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
        boolean DEB = false;
        int t = s.nextInt();
        for(int _t = 0; _t < t; _t++){
            long n = s.nextInt();
            long d = s.nextInt();
            long[] num = new long[(int)n+1];
            boolean found = true;
            long sum = 0;
            for(int r = 1; r<=n; r++) {num[r] = s.nextLong(); sum+= (long)num[r];}
            double avg = (double) sum/((double) n);
            
            if(DEB) System.out.println(">>>avg: "+avg+" , sum: "+sum);
            if(sum%n!=0) found = false; // no average.
            sum/=n;
            long ops = 0;
            for(int i=1; i<=(int)n-d; i++){
                long change = sum - num[i];
                num[i]+=change;
                num[i+(int)d]-=change;
                ops += (long) Math.abs(change);
                            if(DEB) System.out.println(">>>\tFor i: "+i+" change: "+change+" , ops: "+ops);
                //if(num[i+(int)d] <= 0) found = false;
            } // for loop
            for(int i=1; i<=(int)n; i++) if(num[i]!=sum) found = false;
            if(found) System.out.println(ops);
            else System.out.println("-1");
        } //test case loop

    } //main


} //public class Contest