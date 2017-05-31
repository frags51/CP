import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;
public class hacc{
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
            byte[] buf = new byte[64]; // Word length
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
 
    public static void main(String[] args) throws IOException{
        Reader s = new Reader(); //Initialize a reader!
        
        int n = s.nextInt();
        boolean DEB = false;
        /*boolean[] h = new boolean[n*n*n*n + 1];
        h[1] = false;
        h[2] = true;
        h[3] = false;
        for(int i = 4; i< (n*n*n*n)+1; i++){
            int a = h[i-1] ? 1 : 0;
            int b = h[i-3] ? 1 : 0;
            h[i] = ((a+b)==1) ? false:true;
        } // store even/odd
        */
        int test=0;
                    int ch1 = 0,ch2=0, ch3=0;
            for(int i=1; i<=n; i++){
                        for(int j=1; j<=n; j++){
                            if(_h((long)i*i*j*j) != _h((long)j*j*(n+1-i)*(n+1-i))) {ch1++;}              
                        } // for j
            } // for i
            for(int i=1; i<=n; i++){
                        for(int j=1; j<=n; j++){
                            if( _h((long) i*i*j*j) != _h((long) (n+1-j)*(n+1-j)*(n+1-i)*(n+1-i))) ch2++;
                        } // for j
            } // for i
            for(int i=1; i<=n; i++){
                        for(int j=1; j<=n; j++){
                            if(_h((long) i*i*j*j) != _h((long) (n+1-j)*(n+1-j)*i*i)) ch3++;
                        } // for j
            } // for i
        test = s.nextInt();
 TEST:  for(int _count=0; _count<test; _count++){
            int ch = 0; //chnged
            int angle = s.nextInt();
            int rem = (angle/90) % 4;

            switch(rem){    
                case 0: ch = 0;
                break;

                case 1:{
                    ch=ch1;
                } // case 1
                break;
                case 2:{
                    ch=ch2;
                }
                break;
                case 3:{
                    ch=ch3;
                }
                break;
            } //switch rem

            System.out.println(ch);
        } //TEST while loop

    } //main
    static int blah=0;
    public static int _h(long r){
        long x = r%7;
        if(x==1 || x==3 || x==6 || x==0) return 0;
        else return 1;
        
    }

} //public class Contest