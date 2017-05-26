#include <iostream>
using namespace std;
//#include <alogrithm>
/*

int gcd(int a, int b){
	int x = max(a, b);
	int y = min(a,b);

	if(y==0) return x;
	else return gcd(x%y, y);
}
*/

int main() {
	bool DEB = false;
	int t;
	cin>>t;
	for(int i=0; i<t; i++){
		int x, y, p, q;
		
		int found = 0;
		cin>>x>>y>>p>>q;
		double cur = (double) x/y;
		double req = (double) p/q;
		
		if(cur==req) {cout<<"0"<<endl;}
		else if(cur>req){ 
			int lo=y, hi=1000000000;
			int sub=0;
			int save_y = -1;
			while(lo<=hi){
				
				int mid = (lo+hi)/2;
				double now = (double) x/mid;
				double now_x = (double) (x+mid-y)/mid;
				if(DEB) cout<<"!!! lo: "<<lo<<", hi: "<<hi<<endl;
				
				if(now==req){
					sub = mid-y;
					cout<<sub<<endl;
					if(DEB) cout<<">>>>NOW=REQ.. XX: "<<x<<", YY: "<<mid;
					found = 1;
					goto A;
					break;
				}
				else if(now>req){
					lo = mid+1;
				}
				else if(now<req){

					int found2=0;
					int lo2=x; int hi2=x+mid-y;
					double d2= (double) hi2/mid;
					while(lo2<=hi2){
						if(DEB) cout<<"\t>>Now lo2: "<<lo2<<" , hi2: "<<hi2;
						if(DEB) cout<<", Y currently is: "<<mid<<endl;
						int mid2=(lo2+hi2)/2;
						double now2 = (double) mid2/mid;
						if(now2 ==req){
							sub=mid-y;
							//cout<<sub<<endl;
							found=1;
							found2=1;
							//goto A;
							save_y = mid;
							if(DEB) cout<<"\t\t\t\t\t SAVED HER!!!!"<<endl;
							break;
						}
						else if(now2<req){
							lo2=mid2+1;
						}
						else{//now2>req
							hi2=mid2-1;
						}//now2<req
					}//bserch2
					//if(found2==0) hi =mid-1;
					
					//if(d2 > req) hi-=1;
					//else hi = mid -1;
					hi=mid-1;
					if(DEB) cout<<"\t\t\td2: "<<d2<<" , req:  "<<req<<"hi2: "<<hi2<<" mid:"<<mid<<endl;
					//if(DEB) cout<<"\t\tHi = mid-1 done"<<endl;
				}
			} //bsearch 1
			if(found==0) cout<<"-1"<<endl;
			else if(found==1) cout<<save_y-y<<endl;
A:			;
		
		
		}//cur>req
		else{//cur<req
			int lo=y, hi=1000000000;
			int sub=0, sub2=0, save_x=0, save_y=0;
			if(DEB) cout<<"Entered cur < req; CUR: "<<cur<<endl;
			while(lo<=hi){
				int mid = (lo+hi)/2;
				double now = (double) x/mid;
				double now_x = (double) (x+mid-y)/mid;
				if(now_x==req){
					sub = mid-y;
					cout<<sub<<endl;
					found = 1;
					//break;
					goto B;
				}
				else if(now_x<req){
					lo = mid+1;
				}
				else if(now_x>req){
					int found2=0;
					int lo2=y; int hi2=1000000000;
					while(lo2<=hi2){
						int mid2=(lo2+hi2)/2;
						if(DEB) cout<<">>Entered 2nd Bsearch: lo2: "<<lo2<<endl;
						
						double now2 = (double) (x+mid-y)/mid2;
						if(now2 ==req){
							
							sub=mid2-y;
							//cout<<sub<<endl;
							if(DEB) cout<<"now2: "<<now2<<" , mid: "<<mid<<endl;
							found=1;
							found2=1;
							//goto B;
							//now search for a lower value of x
							save_x = x+mid-y;
							save_y = mid2;
							break;
						}
						else if(now2>req){
							found2=-1;
							lo2=mid2+1;
						}
						else{//now2>req
							found2=-1;
							hi2=mid2-1;
						}//now2<req
					}//bserch2
					//if(found2==0) hi =mid-1;
					hi = mid-1;
				}
			} //bsearch 1
			if(found==0) cout<<"-1"<<endl;
			else if(found==1) cout<<save_y-y<<endl;
B:			;
		}//cur<req
	}
	return 0;
}