import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;
public class pe2{
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
            byte[] buf = new byte[200000]; // line length
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
        for(int _t=0; _t<t; _t++){
            int n = s.nextInt();
            String u = s.readLine();
            String d = s.readLine();
            boolean DEB = false;
            int us1 = 0, us2 = 0, ds1=0, ds2=0;

            while(us1<u.length() && u.charAt(us1)== '.') us1++;
            while(ds1<d.length() && d.charAt(ds1)== '.') ds1++;

            int fence = 0;
            if(us1==ds1 && us1 == n) System.out.println(fence);
            else if(ds1 == n){
                while(true){
                    us2=us1+1;
                    while(us2<n && u.charAt(us2)=='.') us2++;
                    if(us2==n) break;
                    fence++;
                    us1 = us2;
                }
                System.out.println(fence);
            }
            else if(us1 == n){
                while(true){
                    ds2=ds1+1;
                    while(ds2<n && d.charAt(ds2)=='.') ds2++;
                    if(ds2==n) break;
                    fence++;
                    ds1 = ds2;
                }
                System.out.println(fence);
            }
            else {
                fence++; //horizontal fence.
                //get 2nd snakes
                us2=us1+1;
                ds2=ds1+1;
                while(us2<n && u.charAt(us2)=='.') us2++;
                while(ds2<n && d.charAt(ds2)=='.') ds2++;
                if(DEB) System.out.println(">>> 1: us2: "+us2+" ds2: "+ds2);
                // if 2nd snakes exist.
                while(us2<n && ds2 <n){
                    if((us1 >= ds2)){
                        fence++;
                        ds1=ds2;
                        ds2++;
                        while(ds2<n && d.charAt(ds2)=='.') ds2++;
                    }
                    else if(ds1>=us2){
                        fence++;
                        us1=us2;
                        us2++;
                        while(us2<n && u.charAt(us2)=='.') us2++;
                    }
                    else{
                        fence++;
                        us1=us2;
                        ds1=ds2;
                        us2++;
                        ds2++;   
                        while(us2<n && u.charAt(us2)=='.') us2++; 
                        while(ds2<n && d.charAt(ds2)=='.') ds2++;
                    }                
                    
                }
                if(DEB) System.out.println(">>> 2");
                while(true){// 
                    us2=us1+1;
                    while(us2<n && u.charAt(us2)=='.') us2++;
                    if(us2==n) break;
                    fence++;
                    us1 = us2;
                }
                if(DEB) System.out.println(">>> 3");
                while(true){
                    ds2=ds1+1;
                    while(ds2<n && d.charAt(ds2)=='.') ds2++;
                    if(ds2==n) break;
                    fence++;
                    ds1 = ds2;
                }
                System.out.println(fence);
            }       
        }//Test

    } //main


} //public class Contest