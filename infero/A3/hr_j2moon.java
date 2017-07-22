import java.util.*;
import java.io.*;
public class hr_j2moon{

    public static void main(String[] args) throws IOException{
        //Reader s = new Reader(); //Initialize a reader!
        //int n = s.nextInt();
        //int r = s.nextInt();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
       
        String[] line = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int r = Integer.parseInt(line[1]);
        List<List<Integer>> adj = new ArrayList<List<Integer>>();
        for(int i = 0; i < n; ++i){
            adj.add(new ArrayList<Integer>());
        }
        for(int k = 0; k <r ; k++){
            line = br.readLine().split(" ");
            final int a = Integer.parseInt(line[0]);
            final int b = Integer.parseInt(line[1]);
            adj.get(a).add(new Integer(b));
            adj.get(b).add(new Integer(a));
        }

        boolean[] vis = new boolean[n];
        List<Integer> sizes = new ArrayList<Integer>();
        
        for(int i=0; i<n; i++){
            final Deque<Integer> q = new LinkedList<Integer>();
            int country_size = 0;
            //BFS
            if(!vis[i]) {q.add(i); vis[i] = true;country_size++;}
            
            while(!q.isEmpty()){

                int p = q.removeFirst();
                Iterator<Integer> itr = adj.get(p).iterator();
                    while(itr.hasNext()){
                    int c = ((Integer)itr.next());
                    if(!vis[c]){
                        //System.out.println(">>>Visited: "+c);
                        country_size++;
                        vis[c] = true;
                        q.addLast(c);
                    }
                }
                /*if(!vis[p]){
                    System.out.println(">>>Visited: "+p);
                    vis[p] = true;
                    country_size++;
                    q.addAll(adj.get(i));
                }*/
            }
            //System.out.println("Component finished");
            if(country_size > 0) sizes.add(country_size);
        }
    long numPairs = 0L;
    long numPartners = n;
    for(int countrySize : sizes){
        //System.out.println("CSize: "+countrySize);
      numPairs += countrySize * (numPartners -= countrySize);
    }
    
    //Print output
    System.out.println(numPairs);
    
    } //main


} //public class Contest

