import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Arrays;
public class pe4{
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
        int t = s.nextInt();
        boolean DEB = true;
        for(int blah=0; blah<t; blah++){
            int n = s.nextInt();
            Snake[] a = new Snake[n+1];
            a[0]= null;
            for(int rr=1; rr<=n; rr++){
                a[rr] = new Snake(s.nextInt(),s.nextInt(),s.nextInt(),s.nextInt());
            }
            int sx = 0, cx = 0, sy=0, cy=0;
            for(int i =1; i<n;i++){
                for(int j=i+1; j<=n; j++){
                    if(!a[i].overlapX(a[j])){
                        if(a[i].x2<a[j].x1) sx = sx + a[i].x2 + a[j].x1;
                        else sx = sx + a[i].x1 + a[j].x2;
                        cx+=2;
                        
                    }
                    if(!a[i].overlapY(a[j])){
                        if(a[i].y2<a[j].y1) sy = sy + a[i].y2 + a[j].y1;
                        else sy = sy + a[i].y1 + a[j].y2;
                        cy+=2;
                        
                    } 

                }//inner for.
            }//outer for
            if(cx!=0) sx=(int)Math.ceil((double) sx/cx);
            if(cy!=0) sy=(int)Math.ceil((double) sy/cy); 
            int ix = 0,iy = 0, xmc=0,ymc = 0;
            int dx=0, dy=0, dt=0;
            for(int i=1; i<=n; i++){
                int dt_temp=0;
                if(cx!=0 && !a[i].overlapX(new Snake(sx, sx, sx, sx))) {
                    if(a[i].x2 < sx) {dt_temp+=(sx-a[i].x2);if(dx < (sx-a[i].x2)) {dx = (sx-a[i].x2); ix = i; xmc=1;} else if(dx==(sx-a[i].x2)) xmc++; }
                    else {dt_temp+=(a[i].x1-sx);if(dx < (a[i].x1-sx)) {dx = (a[i].x1-sx); ix = i; xmc=1;} else if(dx==(a[i].x1)-sx) xmc++; }
                    //if(DEB) System.out.println(">>>>NotOverlapX : i"+ i+" and sx: "+sx);
                }
                if(cy!=0 && !a[i].overlapY(new Snake(sy, sy, sy,sy))) {
                    if(a[i].y2 < sy) {dt_temp+=(sy-a[i].y2);if(dy < (sy-a[i].y2)) {dy = (sy-a[i].y2); iy = i; ymc=1;} else if(dy==(sy-a[i].y2)) ymc++; }
                    else {dt_temp+=(a[i].y1-sy);if(dy < (a[i].y1-sy)) {dy = (a[i].y1-sy); iy = i; ymc=1;} else if(dy==(a[i].y1-sy)) ymc++; }
                    //if(DEB) System.out.println(">>>>NotOverlapY : i"+ i+" and sy: "+sy);
                }
                dt = dt >= dt_temp ? dt : dt_temp;
                if(DEB) System.out.println(">>>dx: "+dx+" dy"+dy+" d_temp: "+dt_temp+" dt: "+dt);
            }
            System.out.println(dx>=dy?dx:dy);    
        }//Test loop

    } //main


} //public class Contest

class Snake{
    public int x1, x2, y1, y2;
    Snake(int x1, int y1, int x2, int y2){ //Head is lesser numerically
   
        this.x1=Math.min(x1,x2);
        this.x2=Math.max(x1,x2);
        this.y1=Math.min(y1,y2);
        this.y2=Math.max(y1,y2);
    }
    public boolean overlapX(Snake b){
        if((this.x1 <= b.x2) && (b.x1 <= this.x2)) return true;
        else return false;
    }
    public boolean overlapY(Snake b){
         if((this.y1<=b.y2) && (b.y1 <= this.y2)) return true;
         else return false;
    }
    public String toString(){
        return new String("||>> "+this.x1+", "+this.y1+", "+this.x2+", "+this.y2+" ");
    }
}