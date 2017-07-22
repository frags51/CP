//Hackerrank Count-luck
//DFS -> recursion
import java.io.*;
import java.util.*;
public class to{
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
        int[] pre = new int[n];
        int[] post = new int[n];
        int[] in = new int[n];
        for(int i=0; i<n; i++) pre[i] = s.nextInt();
        for(int i=0; i<n; i++) post[i] = s.nextInt();
        for(int i=0; i<n; i++) in[i] = s.nextInt();
        Node root = Tree.makeTree(pre, in, 0, n-1);
        Tree.__post(root, post);
        if(!Tree.found) System.out.println("no");
        else System.out.println("yes");
    } //main
} //public class Contest

class Node{
    public int num;
    public Node left, right;
    public Node(int a){
        this.num = a;
        this.left=null;
        this.right=null;
    }
}
class Tree{
    public static int pi = 0;
    public static int poi = 0;
    public static boolean found = true;
    
    public static Node makeTree(int[] pre, int[] in, int lo, int hi){
        if(lo>hi) return null;
        Node tNode = new Node(pre[pi++]);
        if(lo==hi) return tNode;
        int i=0;
        for(int r=lo; r<=hi; r++) if(in[r] == tNode.num) i = r; 
        tNode.left = makeTree(pre, in, lo, i-1);
        tNode.right = makeTree(pre, in, i+1, hi);

        return tNode;
    } //maketree
    public static int __post(Node root, int[] post){
        if(root == null) return -1;
        __post(root.left, post);
        __post(root.right, post);
        if(root.num!=post[poi++]){found=false;}
        return 0;
    }
}//class tree