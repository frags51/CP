//Spoj_pt07z
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.*;
public class pt07z{
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
        int n = s.nextInt();
        ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
        for(int i =0;i <n; i++) adj.add(new ArrayList<Integer>());
        for(int i=0; i<n-1;i++){
            int a = (s.nextInt()-1);
            int b = (s.nextInt()-1);
            adj.get(a).add(b);
            adj.get(b).add(a);
        }
        int max=0,m2=0;

        //DFS
        Deque<Integer> stack = new LinkedList<Integer>();
        stack.push(0);
        boolean[] vis = new boolean[n];
        int[] dist = new int[n];
        vis[0]=true;
        dist[0]=0;
        int mi = 0;
        
        
        while(!stack.isEmpty()){
            
            int p = stack.pop();
            Iterator<Integer> itr = adj.get(p).iterator();
            
            while(itr.hasNext()){
                int c = itr.next();
                
                if(!vis[c]){
                   
                    
                    vis[c] = true;
                    stack.push(c);
                    dist[c] = dist[p] + 1;
                    if(max <= dist[c]) {max=dist[c];mi=c;}
                }
            
            }
        }
        for(int i=0; i<n; i++){
            vis[i] = false;
            dist[i]=0;
        }
        stack.push(mi);
        vis[mi]=true;
        dist[mi]=0;
        while(!stack.isEmpty()){
            
            int p = stack.pop();
            Iterator<Integer> itr = adj.get(p).iterator();
            
            while(itr.hasNext()){
                int c = itr.next();
                
                if(!vis[c]){
                   
                    
                    vis[c] = true;
                    stack.push(c);
                    dist[c] = dist[p] + 1;
                    if(max <= dist[c]) {max=dist[c];mi=c;}
                }
            
            }
        }
        /*int ct = 0, m2=0;;
        for(int i=0; i <n; i++){
            if(dist[i]==max) ct++;
            if(ct!=0) m2 = m2 > dist[i] ? m2 : dist[i];
        }*/
        System.out.println(max);

    } //main


} //public class Contests