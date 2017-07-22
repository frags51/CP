//Hackerrank Count-luck
//DFS -> recursion
import java.io.*;
import java.util.*;
public class aiw{
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
        int t = s.nextInt();
        for(int r = 0; r<t; r++){
            int n = s.nextInt();
            int m = s.nextInt();
            Dfs d = new Dfs(n,m);
            char[][] row = new char[n][m];
            int R=-1, C=-1;
            for(int tt=0; tt<n;tt++){
                String a = s.readLine();
                if(a.indexOf('A')!=-1) {R = tt; C = a.indexOf('A');}
                row[tt] = a.toCharArray();
            }
            //scanning done.
            if(R==-1) {System.out.println("NO"); return;}
            //d.vis2[R][C]=true;
            for(int i = 0; i<n;i++){
                for(int j=0; j<n;j++){
                    if(row[i][j]=='A') d.go(row,i,j,n,m,1);
                    if(d.found) break;
                }
                if(d.found) break;
            }
            if (d.found) System.out.println("YES");
            else System.out.println("NO");
        } //test case

    } //main
} //public class Contest

class Dfs{
    
    public boolean found;
    public boolean[][] vis;
    public char[] word;
    //public short pos;
    public Dfs(int n, int m){
        found=false;
        //pos=0;
        word = ".ALLIZZWELL".toCharArray();
        vis = new boolean[n][m];
        
        for(int i=0; i<n; i++) vis[i] = new boolean[m];
    }
    public boolean go(char[][] a, int r, int c, int n, int m, int p){
            vis[r][c] = true;
            if(p==10) {this.found=true;vis[r][c]=false;return true;}
            else{
                if(r>0 &&!this.vis[r-1][c] && a[r-1][c]==this.word[p+1]) go(a, r-1,c,n,m,p+1); //up
                if(r>0 && c>0&&!this.vis[r-1][c-1]&&a[r-1][c-1]==this.word[p+1]) go(a, r-1,c-1,n,m,p+1); //UPPER_LEFT_CORNER
                if(r>0 && c<m-1 &&!this.vis[r-1][c+1]&& a[r-1][c+1]==this.word[p+1]) go(a, r-1,c+1,n,m,p+1); //upright;
                if(c>0&&!this.vis[r][c-1]&& a[r][c-1]==this.word[p+1]) go(a, r,c-1,n,m,p+1); //left
                if(c<m-1&&!this.vis[r][c+1]&& a[r][c+1]==this.word[p+1]) go(a, r,c+1,n,m,p+1); //right
                if(r<n-1 &&!this.vis[r+1][c]&& a[r+1][c]==this.word[p+1]) go(a, r+1,c,n,m,p+1);//down
                if(r<n-1 && c>0 &&!this.vis[r+1][c-1]&& a[r+1][c-1]==this.word[p+1]) go(a, r+1,c-1,n,m,p+1);//down left
                if(r<n-1 && c<m-1 &&!this.vis[r+1][c+1]&& a[r+1][c+1]==this.word[p+1]) go(a, r+1,c+1,n,m,p+1);//down-right
            }
            vis[r][c]=false;    
            return false;
        }
    }//clas dfs
//    
