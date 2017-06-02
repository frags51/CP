#include <bits/stdc++.h>
using namespace std;
int main(){
	int t;
	cin>>t;
	for(int iter=0;iter<t;iter++){
		int n,m;
		cin>>n>>m;
		int node[n+1];
		int degree[n];
		for(int i=0;i<n;i++) {
			node[i]=0;
			degree[i]=0;
		}
		node[n]=0;
		int a[m];
		int b[m];
		for(int i=0;i<m;i++){
			cin>>a[i]>>b[i];
			node[a[i]]++;
			node[b[i]]++;
		}
		for(int i=0;i<n;i++){
			 degree[node[i+1]]++;
		}
		int s=0;
		for(int d=0;d<n;d++){
			if(s==n-1) cout<<s<<" ";
			else{ s=s+degree[d];
			if(s>=n-1) cout<<(n-1)<<" ";
			else cout<<s<<" ";}
		}
		cout<<endl;	
	}
}
			
