#include <bits/stdc++.h>
using namespace std;

bool vicinity(char tof,int *i,int *j,int r,int s, bool **visited,char **a){
	if(*j<s && a[*i][*j+1]==tof && visited[*i][*j+1]==false){
		(*j)++; 
		return true;
	}
	if( *j>0 && a[*i][*j-1]==tof && visited[*i][*j-1]==false){
		(*j)--;
		return true;
	}
	if(*i>0 && *j>0 && a[*i-1][*j-1]==tof && visited[*i-1][*j-1]==false){
		(*i)--; (*j)--;
		return true;
	}
	if(*i>0 && a[*i-1][*j]==tof && visited[*i-1][*j]==false){
		(*i)--;
		return true;
	}
	if(*i>0 && *j<s && a[*i-1][*j+1]==tof && visited[*i-1][*j+1]==false){
		(*i)--; (*j)++;
		return true;
	}
	if(*i<r && a[*i+1][*j] ==tof && visited[*i+1][*j]==false){
		(*i)++;
		return true;
	}
	if(*i<r && *j>0 && a[*i+1][*j-1]==tof && visited[*i+1][*j-1]==false){
		(*i)++; (*j)--;
		return true;
	}
	if(*i<r && *j<s && a[*i+1][*j+1]==tof && visited[*i+1][*j+1]==false){
		(*i)++; (*j)++;
		return true;
	}
	return false;	
}


bool back_track(char a,int* i,int *j, int r,int s,int* level,bool **visited,char **aa){
	char tof;
	(*level)++;
	visited[*i][*j]=true;
	int cpi=*i,cpj=*j;
	if(a=='A') tof='L';
	if(a=='L' && *level==2) tof='L';
	if(a=='L' && *level==3) tof='I';
	if(a=='I') tof='Z';
	if(a=='Z' && *level==5) tof='Z';
	if(a=='Z' && *level==6) tof='W';
	if(a=='W') tof='E';
	if(a=='E') tof='L';
	if(a=='L' && *level==9) tof='L';
 	while(vicinity(tof,i,j,r,s,visited,aa)){
		visited[*i][*j]=true;
		if(*level==9) return true;
		bool a=back_track(tof,i,j,r,s,level,(bool**)&visited[0],(bool**)&aa[0]);
		if(a==true) return true;	 
	}
	visited[cpi][cpj]=false;
	(*level)--;
	return false;
}

int main(){
	int t;
	cin>>t;
	while(t--){
		int r,s;
		cin>>r>>s;
		char a[r][s];
		for(int i=0;i<r;i++){
			for(int j=0;j<s;j++){
				cin>>a[i][j];
			}
		}
		bool flag=0;
		int level=0;
		bool visited[r][s];
		for(int i=0;i<r;i++){
			for(int j=0;j<s;j++){
				bool a=back_track('A',&i,&j,r,s,&level,visited,a);
				if(a==true) {
					cout<<"YES\n";
					flag=1;
				}
			}
		}
		if(flag==0) cout<<"NO\n";
	}
}