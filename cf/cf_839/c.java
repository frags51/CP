import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.*;
public class c{
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
            byte[] buf = new byte[600]; // line length
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
            byte[] buf = new byte[600]; // line length
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
        Reader s = new Reader(); //Init a reader!
        int n = s.nextInt();
        ArrayList[] adj = new ArrayList[n+1];
            for(int i = 1; i < n+1; i++) adj[i] = new ArrayList<Integer>();
            for(int i = 1; i < n; i++){
                int a = s.nextInt();
                int b = s.nextInt();
                adj[a].add((b));
                adj[b].add((a));
                
            }
            int root = 1;
            int[] dist = new int[n+1];
            boolean[] vis = new boolean[n+1];
            //bfs
            Deque<Integer> q = new LinkedList<Integer>();
            q.addLast(root);
            vis[root] = true;
            dist[root] = 0;
            double[] pp = new double[n+1];
            for(int r=0; r<=n; r++) pp[r] = (double) 1;
            double cp=1;
            double exp =0.0000000;
            boolean deb = false;
            while(q.peekFirst()!=null){
                int p = q.peekFirst();
                q.removeFirst();
                double nChild=0;
                Iterator itr = adj[p].iterator();
                while(itr.hasNext()){
                    int c =((Integer) itr.next());
                    if(!vis[c]) nChild++;
                }

                double prob=0;
                if(nChild==(double)0) {exp+=(dist[p] * pp[p]); if(deb) System.out.println(">>>@leaf: "+p);}
                else { prob = (double)1/nChild;}
                
                itr = adj[p].iterator();
                while(itr.hasNext()){
                    int c = ((Integer)itr.next());
                    if(!vis[c]){
                        pp[c]*=(prob*pp[p]);
                        dist[c] = dist[p] + 1;
                        vis[c] = true;
                        q.addLast(c);
                    }
                }
            }
        System.out.printf("%.15f%n", exp);           
    } //main


} //public class Contest