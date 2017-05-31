import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;
public class pre_el1{
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
        int t=s.nextInt();
TEST:   for(int blah=0; blah<t; blah++){
            int n = s.nextInt();
            String[] up=new String[2];
            up[0] = s.readLine();
            up[1] = s.readLine();
            int ui = 0;
            int di = 0;
            boolean DEB=false;
            while(ui<n && up[0].charAt(ui)=='.' ) ui++; //find first occurances of #
            while(di<n && up[1].charAt(di)=='.') di++;
            if(DEB) System.out.println("\t>>>>ui: "+ui+" di: "+di);
            if(ui==di) {
                // of type: ....#blah
                //          ....#blah
                while(ui<n && (up[0].charAt(ui) == up[1].charAt(ui)) && (up[0].charAt(ui)=='#')) {ui++; di++;}
                // ui == di still
                // ..#####blah
                // ..#####blah
                while(ui<n && (up[0].charAt(ui) == up[1].charAt(ui)) && (up[0].charAt(ui)=='.')) {ui++; di++;}
                // ui == di still
                
                // ..#####...
                // ..#####...
                if(ui==n) {System.out.println("yes");if(DEB) System.out.println("\t>>>>ui==n"); continue TEST;}
                // ..#####...#  ; ..####...
                // ..#####...#  ; ..####..#              
                else if(up[0].charAt(ui-1)!='#' && up[1].charAt(ui-1)!='#'){
                    if(DEB) System.out.println("\t>>>>up[0].charAt(ui-1)!='#' && up[1].charAt(ui-1)!='#'");
                    System.out.println("no"); continue TEST;
                }
                // ..###.blah
                // ..####blah 
                else if(up[1].charAt(ui)=='#'){ //CARE index out of bound ERRORS HERE
                    //Note in first cell, there is no # beneath it. (case covered above)
                    if(DEB) System.out.println("\t>>>>up[1].charAt(ui-1)=='#'");
                    int entry = 0; // 0->Horiz, 1: Vert;
                    int read = 1; // 0 : UP, 1: low
                    ui=di; ui++;  // ui is significant variable
                    //  Incase it ends here.
                    //  
                    if(ui==n) {System.out.println("yes"); continue TEST;} 
                    while(true){
                        if(ui==n) {System.out.println("yes"); continue TEST;}
                        if(up[read].charAt(ui)=='.') break;
                        // ##blah
                        // .#blah
                        else if(up[(read+1)%2].charAt(ui)=='#') {read=((read+1)%2); entry = 1; ui++;}
                        else{
                            ui++;
                        }

                    }   //NAVIGATING LOOP  
                    //Now check if any # remaining.
                    while(ui<n && (up[0].charAt(ui) == up[1].charAt(ui)) && (up[0].charAt(ui)=='.')) {ui++;}
                    if(ui==n) {System.out.println("yes"); continue TEST;}
                    else {System.out.println("no"); continue TEST;} 
                } //else if ends
                // ..####blah
                // ..###. blah
                else if(up[0].charAt(ui)=='#'){
                    //Note in first cell, there is no # beneath it. (case covered above)
                    int entry = 0; // 0->Horiz, 1: Vert;
                    int read = 0; // 0 : UP, 1: low
                    ui++; di=ui; // ui is significant variable
                    //  Incase it ends here.
                    //  
                    if(ui==n) {System.out.println("yes"); continue TEST;} 
                    while(true){
                        if(ui==n) {System.out.println("yes"); continue TEST;}
                        if(up[read].charAt(ui)=='.') break;
                        // ##blah
                        // .#blah
                        else if(up[(read+1)%2].charAt(ui)=='#') {read=((read+1)%2); entry = 1; ui++;}
                        else{
                            ui++;
                        }

                    }   //NAVIGATING LOOP  
                    //Now check if any # remaining.
                    while(ui<n && (up[0].charAt(ui) == up[1].charAt(ui)) && (up[0].charAt(ui)=='.')) {ui++;}
                    if(ui==n) {System.out.println("yes"); continue TEST;}
                    else {System.out.println("no"); continue TEST;} 
                }
            } //ui == di
            else if(ui < di){ //# found first in upper string.
                //Note in first cell, there is no # beneath it. (case covered above)
                int entry = 0; // 0->Horiz, 1: Vert;
                int read = 0; // 0 : UP, 1: low
                ui++; di=ui; // ui is significant variable
                //  Incase it ends here.
                //  
                if(ui==n) {System.out.println("yes"); continue TEST;} 
                while(true){
                    if(ui==n) {System.out.println("yes"); continue TEST;}
                    if(up[read].charAt(ui)=='.') break;
                    // ##blah
                    // .#blah
                    else if(up[(read+1)%2].charAt(ui)=='#') {read=((read+1)%2); entry = 1; ui++;}
                    else{
                        ui++;
                    }

                }   //NAVIGATING LOOP  
                //Now check if any # remaining.
                while(ui<n && (up[0].charAt(ui) == up[1].charAt(ui)) && (up[0].charAt(ui)=='.')) {ui++;}
                if(ui==n) {System.out.println("yes"); continue TEST;}
                else {System.out.println("no"); continue TEST;} 

                  
            }
            else if(ui > di){
                //Note in first cell, there is no # beneath it. (case covered above)
                int entry = 0; // 0->Horiz, 1: Vert;
                int read = 1; // 0 : UP, 1: low
                ui=di; ui++; ; // di is significant variable
                //  Incase it ends here.
                //  
                if(ui==n) {System.out.println("yes"); continue TEST;} 
                while(true){
                    if(ui==n) {System.out.println("yes"); continue TEST;}
                    if(up[read].charAt(ui)=='.') break;
                    // ##blah
                    // .#blah
                    else if(up[(read+1)%2].charAt(ui)=='#') {read=((read+1)%2); entry = 1; ui++;}
                    else{
                        ui++;
                    }

                }   //NAVIGATING LOOP  
                //Now check if any # remaining.
                while(ui<n && (up[0].charAt(ui) == up[1].charAt(ui)) && (up[0].charAt(ui)=='.')) {ui++;}
                if(ui==n) {System.out.println("yes"); continue TEST;}
                else {System.out.println("no"); continue TEST;} 
            }
        }//test loop

    } //main


} //public class Contest
