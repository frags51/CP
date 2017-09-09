// Working program with FastReader
import static java.lang.System.out;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.*;
public class b{
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
        int n = s.nextInt();
        int m = s.nextInt();
        boolean deb=true;
        int[][] matrix = new int[n][m];
        boolean[][] vis = new boolean[n][m];
        boolean[][] rs = new boolean[2][n];
        boolean[][] cs = new boolean[2][m];
        for(int i=0; i<n;i++) for(int j=0; j<m; j++) matrix[i][j] = s.nextInt();
        int nsets=0;
        Deque<Pair> q = new LinkedList<Pair>();
        
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(!vis[i][j]){
                    
                    q.addLast(new Pair(i,j));
                
                
                while(q.peekFirst()!=null){
                    int nr=1, nc=1;
                    Pair p = q.peekFirst();
                    if(deb) out.println("Entered: "+p.x+", "+p.y);
                    q.removeFirst();
                    //vis[p.x][p.y]=true;
                    for(int k=0; k<n; k++){
                        if(!vis[k][j] &&k!=i && matrix[k][j]==matrix[i][j]){
                            vis[k][j] = true;
                            q.addLast(new Pair(k, j));
                            nr++;
                        }
                    }
                    for(int k=0; k<m; k++){
                        if(!vis[i][k] &&k!=j && matrix[i][k]==matrix[i][j]){
                            vis[i][k] = true;
                            q.addLast(new Pair(i, k));
                            nc++;
                        }
                    }
                if(!vis[p.x][p.y]) nsets+=(int) (Math.pow(2, nr+nc-1)-1);
                if(deb && !vis[p.x][p.y]) out.println(">>>DEB: addedto nsets: "+(int) (Math.pow(2, nr+nc-1)-1));
                vis[p.x][p.y]=true;
                } //while
                 } //if
            } //inner for
        } //out for

        out.println(nsets);
    } //main
    
    /*
    public static void dfs(int[][] m, boolean[][] f, int r, int c, int col, int N, int M, int nsets, boolean[2][n] rs, boolean[2][m] cs){
        f[r][c] = true;
        int rc=1, cc=1;
        for(int i=0; i<N;i++){
            if(i!=r && m[i][c]==col &&!f[i][c]) {dfs(m,f,i,c,col,N,M,nsets,rs,cs); rs[col][i]=true;rc++;}
            if()
        }
        for(int i=0; i<M;i++){
            if(i!=c && m[r][i]==col && !f[r][i]) {dfs(m,f,r,i,col,N,M, nsets,rs, cs);cs[col][i]=true;cc++;}
        }
    }
    */

} //contest

class Pair{
    public int x;
    public int y;
    public Pair(int a, int b){
        this.x=a;
        this.y=b;
    }
}        
        /*
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(!vis[i][j]){
                    int col = matrix[i][j];
                    dfs(matrix, vis, i, j, col, n, m);
                    nsets++;
                } //if !vis
            }//j for
        } //outf or
        */