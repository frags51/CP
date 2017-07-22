import java.util.*;
import java.io.*;
public class cf_217a{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        ArrayList<Point> points = new ArrayList<Point>(n);
        ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
        for(int i = 0; i < n; i++) adj.add(new ArrayList<Integer>());
        for(int i =0; i<n; i++){
            String[] line = br.readLine().split(" ");
            int a = Integer.parseInt(line[0]);
            int b = Integer.parseInt(line[1]);
            points.add(new Point(a, b));
            for(int j=0; j < points.size()-1; j++){
                Point p = points.get(j);
                if(a==p.x || b==p.y){
                    adj.get(j).add(points.size()-1);
                    adj.get(points.size()-1).add(j);
                }
            }
        }//scan.
        int comp = 0;
        boolean[] vis = new boolean[n];
        for(int i =0; i<n; i++){
            final Deque<Integer> q = new ArrayDeque<Integer>();
            if(!vis[i]) {q.add(i); vis[i]=true; comp++;}
            while(!q.isEmpty()){
                int c = q.removeFirst();
                Iterator<Integer> itr = adj.get(c).iterator();
                while(itr.hasNext()){
                    int r = (Integer) itr.next().intValue();
                    if(!vis[r]){
                        vis[r] = true;
                        q.add(r);
                    }
                }
            }//q is not empty
        }
        System.out.println(comp-1); 
    }//main
}//Class

class Point{
    public int x;
    public int y;

    public Point(int a, int b){
        this.x = a;
        this.y = b;
    }
}