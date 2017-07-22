import java.util.*;
import java.io.*;
public class cf_277a{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int m2 = Integer.parseInt(line[1]);
        ArrayList<Person> persons = new ArrayList<Person>(n);
        ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
        for(int i = 0; i < n; i++) adj.add(new ArrayList<Integer>());
        for(int i =0; i<n; i++){
            line = br.readLine().split(" ");
            int m = Integer.parseInt(line[0]);
            
            Person foo = new Person();
            if(m==0) {Person.zero++; foo.lang[0]=true;}
            for(int t=1; t<=m; t++) foo.lang[Integer.parseInt(line[t])] = true;
            persons.add(foo);
            for(int j=0; j < persons.size()-1; j++){
                Person bar = persons.get(j);
                for(int blah=1; blah<101; blah++) if(bar.lang[blah] && foo.lang[blah]){
                    adj.get(j).add(persons.size()-1);
                    adj.get(persons.size()-1).add(j);
                    break;
                }
            }
        }//scan.
        int comp = 0;
        boolean[] vis = new boolean[n];
        for(int i =0; i<n; i++){
            final Deque<Integer> q = new ArrayDeque<Integer>();
            if(!vis[i] && !persons.get(i).lang[0]) {q.add(i); vis[i]=true; comp++;}
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
        
        System.out.println((comp <=0 ? 0 : (comp-1))+Person.zero); 
    }//main
}//Class

class Person{
    static int zero = 0;
    public boolean[] lang = new boolean[101];
}