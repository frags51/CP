import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.ArrayList;
public class gcac{
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
            
            int n = s.nextInt();
            int m = s.nextInt();
            int minSal[] = new int[n];
            for(int r=0; r<n; r++) minSal[r] = s.nextInt();
            int offSal[] = new int[m];
            int offJob[] = new int[m];
            for(int r=0; r<m; r++) {offSal[r]=s.nextInt(); offJob[r] = s.nextInt();}
            String[] qual = new String[n];
            for(int r=0; r<n; r++) qual[r]=s.readLine();
            long total_sal = 0;
            boolean[] hired = new boolean[m];
            int got_jobs = 0;
            int not_hire = 0;

            for(int i=0; i<n; i++){
                if(DEB) System.out.println(">>Candidate: "+i);
                int max_sal = 0;
                int max_sal_index = -1;
                for(int j = 0; j<m; j++){
                    if(qual[i].charAt(j)=='1' && offJob[j]>0 && offSal[j]>=minSal[i]) {

                        if(offSal[j] > max_sal){
                            max_sal = offSal[j];
                            max_sal_index = j;
                        }
                       
                    }

                    
                }//for inner
                if(max_sal_index != -1){
                        offJob[max_sal_index]--;
                        hired[max_sal_index] = true;
                        total_sal += (long) max_sal;
                        got_jobs++;
                        if(DEB) System.out.println("\t>>>>Got job @: "+max_sal_index);
                }
                    
            }//for outer
            for(int r = 0; r<m; r++) if(!hired[r]) not_hire++;
            System.out.println(got_jobs+" "+total_sal+" "+not_hire);
        } //test case loop

    } //main


} //public class Contest