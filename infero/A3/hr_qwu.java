//Hackerrank: Snakes and ladders Quickest Way up
//0-1 BFS
//Spoj_benefactor
//BFS + BFS  -> Get longest path in weighted tree.
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.*;
public class hr_qwu{
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
        int t= s.nextInt();
        boolean DEB = false;
        for(int foo=0; foo<t;foo++){
        
        ArrayList<ArrayList<Pair>> adj = new ArrayList<ArrayList<Pair>>();
        for(int i =0;i <100; i++) adj.add(new ArrayList<Pair>());
        
        int n = s.nextInt();
        for(int i=0; i<n;i++){
            int a = (s.nextInt()-1);
            int b = (s.nextInt()-1);
            
            adj.get(a).add(new Pair(b, 0));
            
        }
        int m = s.nextInt();
        for(int i=0; i<m;i++){
            int a = (s.nextInt()-1);
            int b = (s.nextInt()-1);
            
            adj.get(a).add(new Pair(b, 0));
            
        }
        for(int r = 0; r<100; r++){
            for(int j = 1; j<=6; j++){
                int bd = r+j;
                if(DEB) System.out.println("\t\t>>>>bd: "+bd);
                if(bd < 100) adj.get(r).add(new Pair(bd, 1));
            }

        }

        //BFS
        boolean found = false;
        Deque<Integer> stack = new LinkedList<Integer>();
        stack.addLast(0);
        //boolean[] vis = new boolean[n];
        int[] dist = new int[100];
        for(int i=0; i<100; i++) dist[i] = Integer.MAX_VALUE;
        dist[0] = 0;
        while(!stack.isEmpty()){
            int p = stack.removeFirst();
            if(DEB) System.out.println(">>>P: "+p);
            if(p==99) {found = true;}
            Iterator<Pair> itr = adj.get(p).iterator();
            boolean flag = false;
            while(itr.hasNext()){
                Pair c = itr.next();
                if(c.y==0) {flag = true;break;}
            }
            itr = adj.get(p).iterator();
            while(itr.hasNext()){
                Pair c = itr.next();
                    if(DEB) System.out.println(">>>@C: "+c.x+" DIST: "+dist[c.x]);
                    
                if(flag){
                    if(c.y==0){
                    if(dist[c.x] > dist[p]+c.y){
                    dist[c.x]=dist[p]+c.y;
                    if(c.y==0) stack.addFirst(c.x);
                    else stack.addLast(c.x);
                    }
                    }
                    else continue;
                }//flag
                else{
                    if(dist[c.x] > dist[p]+c.y){
                    dist[c.x]=dist[p]+c.y;
                    if(c.y==0) stack.addFirst(c.x);
                    else stack.addLast(c.x);
                }
                }//flag
            }
        }
        if(found) System.out.println(dist[99]);
        else System.out.println("-1");
        } //test case loop
    } //main


} //public class Contests

class Pair{
    public int x;
    public int y;
    public Pair(int a, int b){
        this.x = a;
        this.y = b;
    }
}