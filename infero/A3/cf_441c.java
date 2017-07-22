import java.util.*;
import java.io.*;
public class cf_441c{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int m = Integer.parseInt(line[1]);
        int k = Integer.parseInt(line[2]);
        int sz = n*m/k;
        int x=1, y=1;
        for(int i=1; i < k; i++){//run k-1 times
            System.out.print(sz);

            for(int j=1; j<=sz; j++){//sz times 
                System.out.print(" "+x+" "+y);
                if(x%2==1){
                    y++;
                    if(y>m){
                        y--;
                        x++;
                    }
                }
                else{
                    y--;
                    if(y<1){
                        y++;
                        x++;
                    }
                }
            }//for j;
            System.out.println();
        }//for i

        int left = n*m - (k-1)*sz;
        System.out.print(left);
        for(int i=1; i<=left; i++){
            System.out.print(" "+x+" "+y);
                if(x%2==1){
                    y++;
                    if(y>m){
                        y--;
                        x++;
                    }
                }
                else{
                    y--;
                    if(y<1){
                        y++;
                        x++;
                    }
                }
        }
        System.out.println();
    }//main
}