import java.util.*;
import java.io.*;
public class cf_300b{
    public static void main(String[] args) throws IOException{
        boolean DEB = false;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int m2 = Integer.parseInt(line[1]);
        boolean poss = true;
        UnionFind graph = new UnionFind(n+1);
        
        for(int i =0; i<m2; i++){
            line = br.readLine().split(" ");
            int a = Integer.parseInt(line[0]);
            int b = Integer.parseInt(line[1]);
            graph.unionSet(a, b);
            if(graph.sizeOfSet(a) > 3) poss = false;
        }//scan.
        
        if(!poss) System.out.println("-1");
        else{
            poss = true;
            int l1=0, l2=0, l3=0;
            boolean[] added = new boolean[n+1];
            for(int i=1; i <= n; i++){
                int y = graph.findSet(i);
                int sz= graph.sizeOfSet(y);
                if(!added[y]){
                    added[y] = true;
                    if(sz == 3) l3++;
                    else if(sz==2) l2++;
                    else if(sz==1) l1++;
                    else poss=false;
                }
            }
            int r = l1-l2;
            if(r>=0 && r%3==0 && poss){
                if(DEB)System.out.println("----------------------l1: "+l1+" l2: "+l2+ " l3:+"+l3);
                boolean[] vis = new boolean[n+1];
                int t_count = 1;
                for(int i=1; i<=n; i++){
                    if(!vis[i]){
                        int sz = graph.sizeOfSet(i);
                        if(sz==3){
                            vis[i] = true;
                            System.out.print(i+" ");
                            short count = 1;
                            for(int j=i+1;j<=n && count<=3;j++){
                                if(graph.findSet(j)==graph.findSet(i)){count++;System.out.print(j);vis[j]=true;if(count==2) System.out.print(" ");}
                                
                            }//inner for
                            System.out.println();
                        }//size == 3
                        else if(sz==2){
                            if(DEB) System.out.println(">>>l2: "+ l2);
                            if(DEB) System.out.println(">>>i: "+ i);
                            l2--;
                            System.out.print(i+" ");
                            vis[i] = true;
                            short count=1;
                            for(int j=i+1;j<=n && count<=2;j++){
                                if(graph.findSet(j)==graph.findSet(i)){count++;System.out.print(j);vis[j]=true;}
                            }//inner for
                            while(t_count<=n&&(graph.sizeOfSet(t_count)!=1 || vis[t_count])) t_count++;
                            System.out.println(" "+t_count);
                            if(t_count<=n) vis[t_count]=true;
                            t_count = 1;
                        }
                        else if(l2==0){//sz==1
                            while(t_count<=n&&(graph.sizeOfSet(t_count)!=1 || vis[t_count])) t_count++;
                            System.out.print(t_count+" ");
                            if(t_count<=n) vis[t_count++] = true;
                            while(t_count<=n&&(graph.sizeOfSet(t_count)!=1 || vis[t_count])) t_count++;
                            System.out.print(t_count+" ");
                            if(t_count<=n) vis[t_count++] = true;
                            while(t_count<=n&&(graph.sizeOfSet(t_count)!=1 || vis[t_count])) t_count++;
                            System.out.println(t_count);
                            if(t_count<=n) vis[t_count++] = true;
                            t_count=1;
                        }
                    }//!vis i
                } //for loop
            }
            else {System.out.println("-1");}

        } 
    }//main
}//Class

class UnionFind {                                              // OOP style
  public Vector<Integer> p, rank, setSize;
  public int numSets;

  public UnionFind(int N) {
    p = new Vector<Integer>(N); //p for parent
    rank = new Vector<Integer>(N);
    setSize = new Vector<Integer>(N);
    numSets = N;
    for (int i = 0; i < N; i++) {
      p.add(i);
      rank.add(0);
      setSize.add(1);
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
    if (rank.get(x) > rank.get(y)) { p.set(y, x); setSize.set(x, setSize.get(x) + setSize.get(y)); }
    else                           { p.set(x, y); setSize.set(y, setSize.get(y) + setSize.get(x));
                                     if (rank.get(x) == rank.get(y)) rank.set(y, rank.get(y) + 1); } } }
  public int numDisjointSets() { return numSets; }
  public int sizeOfSet(int i) { return setSize.get(findSet(i)); }
}