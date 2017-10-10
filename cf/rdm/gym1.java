// Working program with FastReader
import static java.lang.System.out;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;
 
public class gym1{
    static class FastReader{
        BufferedReader br;
        StringTokenizer st;
 
        public FastReader()
        {
            br = new BufferedReader(new
                     InputStreamReader(System.in));
        }
 
        String next()
        {
            while (st == null || !st.hasMoreElements())
            {
                try
                {
                    st = new StringTokenizer(br.readLine());
                }
                catch (IOException  e)
                {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
 
        int nextInt()
        {
            return Integer.parseInt(next());
        }
 
        long nextLong()
        {
            return Long.parseLong(next());
        }
 
        double nextDouble()
        {
            return Double.parseDouble(next());
        }
 
        String nextLine()
        {
            String str = "";
            try
            {
                str = br.readLine();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            return str;
        }
    }
 
    public static void main(String[] args)
    {
        FastReader s=new FastReader();
        int  t = s.nextInt();
        for(int _t=0; _t<t; _t++){
            int n = s.nextInt();
            int m = s.nextInt();
            int k = s.nextInt();
            int[][] mat = new int[n][m];
            int[][] paint = new int[n][m];
            boolean poss = false;
            boolean poss2 = true;
            int cnt=0, pc=-1;
            for(int i=0; i<n; i++) for(int j=0; j<m; j++) paint[i][j]=-1;
            for(int i=0; i<n; i++) for(int j=0; j<m; j++) {mat[i][j] = s.nextInt();
                  
                if(mat[i][j]==0) {cnt = 0;pc=-1;continue;}
                else {cnt++;pc++;}
                
                if(cnt>=1 && i >=1){
                    if(paint[i-1][j]!=-1) pc = paint[i-1][j]+1;
                }//i>=1 and started a segment just now.
                paint[i][j] = (pc%k);
                if(cnt>k) poss2=false;
                else pc%=k;  
                if(j==m-1) {cnt=0;pc=-1;}          
            }
            for(int j=0; j<m; j++) {boolean[] check = new boolean[k]; for(int i=0; i<n ;i++){
                
                if(mat[i][j]==0) {cnt = 0;pc=-1; for(int x=0; x<k;x++) check[x]=false; continue;}
                else {cnt++;pc++;}
                
                if(paint[i][j]!=-1 && check[paint[i][j]]) poss2=false;
                else if(paint[i][j]!=-1) check[paint[i][j]] = true;
                
                if(cnt>k) poss2=false;
                else pc%=k;     
                if(i==n-1){cnt=0; pc=-1;}
            }
            }
            if(!poss2) {out.println("NO");
            for(int i=0; i<n; i++){
                    for(int j=0; j<m; j++){
                        out.print((paint[i][j]+1)+" ");
                    }
                    out.println();
                }
            
            }
            else{
                out.println("YES");
                for(int i=0; i<n; i++){
                    for(int j=0; j<m; j++){
                        out.print((paint[i][j]+1)+" ");
                    }
                    out.println();
                }
            } 
        }//test case
    } //main
} //contest
