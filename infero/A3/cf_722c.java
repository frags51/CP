//
//DSU -- RVERSE
import java.io.*;
import java.util.*;
public class cf_722c{
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
        boolean DEB = false;
        int n = s.nextInt();
        UnionFind g = new UnionFind(n);
        for(int i = 0; i < n; i++) g.setSize.set(i, (long)s.nextInt());
        Deque<Integer> stack = new LinkedList<Integer>();
        Deque<Long> ans = new LinkedList<Long>();
        for(int i = 0; i < n; i++) stack.push(s.nextInt());
        long max = 0L;
        for(int i=0; i < n; i++){
            ans.push(max);
            int id = stack.pop()-1;
            g.act.set(id, true);
            
            if((id>0 && g.act.get(id-1))) {g.unionSet(id, id-1);}
            if(id<n-1 && g.act.get(id+1)) g.unionSet(id, id+1);
            long sz = g.sizeOfSet(id);
            max = max > sz ? max : sz;
            if(DEB) System.out.println("Sz: "+sz+" for id= "+id);
        }
        while(ans.peek()!=null) System.out.println(ans.pop());
    } //main
} //public class Contest

class UnionFind {                                              // OOP style
  public Vector<Integer> p, rank;
  public int numSets;
  public Vector<Long> setSize;
  public Vector<Boolean> act;

  public UnionFind(int N) {
    p = new Vector<Integer>(N); //p for parent
    rank = new Vector<Integer>(N);
    setSize = new Vector<Long>(N);
    numSets = N;
    act = new Vector<Boolean>(N);
    for (int i = 0; i < N; i++) {
      p.add(i);
      rank.add(0);
      setSize.add(1L);
      act.add(false);
    }
  }

  public int findSet(int i) { 
    if (p.get(i) == i) return i;
    else {
      int ret = findSet(p.get(i)); p.set(i, ret); //path compression
      return ret; } }

  public Boolean isSameSet(int i, int j) { return findSet(i) == findSet(j); }

  public void unionSet(int i, int j) { 
    if (!isSameSet(i, j)) { numSets--; 
    int x = findSet(i), y = findSet(j);
    // rank is used to keep the tree short
    if (rank.get(x) > rank.get(y)) { p.set(y, x); setSize.set(x, (long)setSize.get(x) + setSize.get(y)); }
    else                           { p.set(x, y); setSize.set(y, (long)setSize.get(y) + setSize.get(x));
                                     if (rank.get(x) == rank.get(y)) rank.set(y, rank.get(y) + 1); } } }
  public int numDisjointSets() { return numSets; }
  public long sizeOfSet(int i) { return setSize.get(findSet(i)); }
}
