import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;
public class cf_816a{
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
        boolean DEB = false;
        StringBuilder in = new StringBuilder(s.readWord());
        int h = Integer.parseInt(in.substring(0, 2));
        int m  = Integer.parseInt(in.substring(3, 5));
        StringBuilder rev2 = new StringBuilder(in);
        StringBuilder rev = rev2.reverse();
        
        int rm = Integer.parseInt(rev.substring(4,6));
        if(DEB) System.out.println(in);
        if(DEB) System.out.println(rev);
        
        if((in.toString()).equals(rev.toString())) System.out.println("0");
        else if(h<5){ // h==0,1,...4
            
            if(m <= rm) System.out.println(rm-m);
            else System.out.println(rm-m+60+10);
        }
        else if(h==5){
            if(m <= rm) System.out.println(rm-m);
            else System.out.println(240+60-m+1);
        }
        else if(h>=6 && h<10){
            System.out.println((9-h)*60 + 61 - m);
        }
        else if(h<15){
            if(m <= rm) System.out.println(rm-m);
            else System.out.println(rm-m+60+10);
        }
        else if(h==15){
            if(m <= rm) System.out.println(rm-m);
            else System.out.println(240+60-m+2);
        }
        else if(h<20){
            System.out.println((19-h)*60 + 62 - m);
        }
        else if(h < 23){
            if(m <= rm) System.out.println(rm-m);
            else System.out.println(rm-m+60+10);
        }
        else if(h==23){
            if(m <= rm) System.out.println(rm-m);
            else System.out.println(60 - m);
        }

    } //main


} //public class Contest