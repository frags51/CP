import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;
public class cf_818b{
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
        int n = s.nextInt();
        int m = s.nextInt();
        int[] l = new int[m+1];
        for(int jj = 1; jj <=m; jj++) l[jj] = s.nextInt();
        int[] p = new int[n+1];
        int[] ed = new int[n+1];
        boolean DEB = false;
        boolean poss = true;
        for(int i = 1; i<m; i++){ // i<= m-1
            int add = l[i+1] - l[i] > 0 ? l[i+1] - l[i] : l[i+1] - l[i] + n;  
            if(ed[add] == 0 || p[l[i]] == add){
                ed[add] = 1;
                p[l[i]] = add;
                if(DEB) System.out.println(">>>Add: "+add+" @index: = "+l[i]);
            }
            else{
                if(DEB) System.out.println(">>>Can't Add: "+add+" @index: = "+l[i]);
                poss = false;
                break;
            }
        }//for
        if(poss){
            for(int i = 1; i <=n ; i++){
                if(p[i] != 0) continue;
                else{
                    for(int j = 1; j <=n; j++){
                        if(ed[j] == 1) continue;
                        else{
                            p[i] = j;
                            ed[j] = 1;
                            break;
                        }
                    } //inner else for
                }

                if(p[i] == 0){
                    poss = false;
                    break; //outer for
                }//if p[i] still equals 0
            } //outer for
        }//if poss

        if(!poss) System.out.print("-1");
        else for(int k = 1; k<=n; k++) System.out.print(p[k]+" ");
        System.out.println();
    } //main

   
} //public class Contest