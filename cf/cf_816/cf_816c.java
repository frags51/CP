import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;
public class cf_816c{
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
        int n = s.nextInt();
        int m = s.nextInt();
        int[][] matrix = new int[n+1][m+1];
        int[] r_min = new int[n+1];
        for(int i = 0; i<n+1; i++) r_min[i]=0;
        int rows = 0;
        
        for(int i=1; i<n+1; i++){
            int fl = 0;
            for(int j = 1; j<m+1; j++) {
                
                matrix[i][j] = s.nextInt();
                if(fl!=0) r_min[i] = r_min[i] <= matrix[i][j] ? r_min[i] : matrix[i][j];
                else{
                    r_min[i] = matrix[i][j];
                    fl = 1;
                }
            }
            if(DEB) System.out.println("R_min:" +r_min[i]);
            rows+=r_min[i];
            for(int j = 1; j<m+1; j++) {
                matrix[i][j]-=r_min[i];                
            }
        }
        boolean pos = true;
        int cols = 0;
CH:     for(int j = 1; j<m+1; j++){
            for(int i=1; i<n; i++){
                if(matrix[i][j] == matrix[i+1][j]) continue;
                else{ pos = false; break CH;}
            }
            cols+=matrix[1][j];
        }
        if(!pos) System.out.println("-1");
        else{
            System.out.println(rows+cols);
            for(int i = 1; i<n+1; i++){
                for(int t=0; t<r_min[i]; t++) System.out.println("row "+i);
            }
            for(int j = 1; j<m+1; j++){
                for(int t=0; t<matrix[1][j]; t++) System.out.println("col "+j);
            }
        }

    } //main


} //public class Contest