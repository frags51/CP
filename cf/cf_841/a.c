#include <stdio.h>

int main(){
    int n, k;
    scanf("%d%d", &n, &k);
    char s[n+1];
    int col[26];
    for(int i=0; i<26; i++) col[i]= 0;
    
    scanf("%s", s);

    for(int r = 0; r <= n; r++) col[s[r] - 'a']++;
    for(int r = 0; r < 26; r++) {if(col[r] > k) {printf("NO\n"); return 0;}}

    printf("YES\n");
    return 0;
    
}