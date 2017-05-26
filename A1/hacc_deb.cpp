#include <iostream>
#include <cstdlib>
using namespace std;
int _h(int r){
int x = r%7;
        if(x==1 || x==3 || x==6 || x==0) return 0;
        else return 1;
}
int main(){
    cout<<"Enter X: ";
    int x;
    cin>>x;
    for(int i=1; i<x+1; i++) cout<<"H(i^2): "<<_h(i*i)<<" i: "<<i<<endl;
    return 0;
}

