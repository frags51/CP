import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;
public class b{
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
            byte[] buf = new byte[164]; // line length
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
        int n = s.nextInt();
        int m = s.nextInt();
        String[] r = new String[n];
        for(int blah=0; blah<n; blah++) r[blah] = s.readLine();
        int order = 0;//0: l to r
        int row_l = 0, u_row_f=0, u_row_l;
        int time=0;
        int i;
        for(i=0; i < n-1; i++){
            
            if(order==0){
                int j=0;
                while(true){
                    while(j<m+2 && r[i].charAt(j)=='0') j++;
                    if(j==m+2) break;
                    row_l = j;
                    time+= (j-row_l);
                    j++;
                }
                int c=0;
                while(c<m+2 && r[i+1].charAt(c) == '0') c++;
                u_row_f =c;
                u_row_l = c;
                while(true){
                   while(c<m+2 && r[i+1].charAt(c)=='0') c++;
                    if(j==m+2) break;
                    u_row_l=c;
                    c++; 
                }
                int t1 = row_l + u_row_f;
                int t2 = 2*m + 2 - row_l - u_row_l;
                if(Math.min(t1, t2) == t1){
                    order=0;
                    //row_l=0;
                    u_row_f=0;
                    u_row_l=0;
                    time+=row_l; //u_row_f will be added above.
                    row_l=0;
                }
                else{
                    order = 1;
                    //row_l=m+1;
                    u_row_f=0;
                    u_row_l=0;
                    time+= (m+1-row_l); 
                    row_l=m+1;
                }
            }
            else if(order==1){
                int j=m+1;
                while(true){
                    while(j>=0 && r[i].charAt(j)=='0') j--;
                    if(j==m+2) break;
                    row_l = j;
                    time+= (int) Math.abs(j-row_l);
                }
                int c=0;
                while(c<m+2 && r[i+1].charAt(c) == '0') c++;
                u_row_f =c;
                u_row_l = c;
                while(true){
                   while(c<m+2 && r[i+1].charAt(c)=='0') c++;
                    if(j==m+2) break;
                    u_row_l=c;
                    c++; 
                }
                int t1 = row_l + u_row_f;
                int t2 = 2*m + 2 - row_l - u_row_l;
                if(Math.min(t1, t2) == t1){
                    order=0;
                    //row_l=0;
                    u_row_f=0;
                    u_row_l=0;
                    time+=row_l; //u_row_f will be added above.
                    row_l=0;
                }
                else{
                    order = 1;
                    //row_l=m+1;
                    u_row_f=0;
                    u_row_l=0;
                    time+= (m+1-row_l); 
                    row_l=0;
                }
            }
            time++; //to climb stairs.
        }
        if(i==n-1){
            if(order==0){
               int j=0; 
               while(true){
                    while(j<m+2 && r[i].charAt(j)=='0') j++;
                    if(j==m+2) break;
                    row_l = j;
                    time+= (j-row_l);
                    j++;
                } 
            }
            else{
                int j=m+1;
                while(true){
                    while(j>=0 && r[i].charAt(j)=='0') j--;
                    if(j==m+2) break;
                    row_l = j;
                    time+= (int) Math.abs(j-row_l);
                }
            }

        }

        System.out.println(time);

    } //main


} //public class Contest