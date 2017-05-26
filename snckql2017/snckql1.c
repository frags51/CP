//C99 strict
#include <stdio.h>
 
int main(void) {
	int test=0;
	scanf("%d", &test);
	for(int i=0; i<test; i++){
		int len=0;
		scanf("%d", &len);
		char rep[len+1];
	//1	//for(int t=0; t<len;t++) scanf("%c", &rep[t]);
        scanf("%s", rep); //2222222222222222222222222
		int c=0, fl_h=0, val=1;
		for(;c<len;){ //no increment here.
            //printf("Entered loop c: %d, rep: %c\n", c, rep[c]);
			if(rep[c]=='.') {c++;continue;}
			else if(rep[c]=='T' && fl_h == 0) {val=0;break;}
			else if(rep[c]=='T' && fl_h != 0) {c++; fl_h=0; continue;}
			else if(rep[c]=='H' && fl_h != 0) {val=0;break;}
			else if(rep[c]=='H' && fl_h == 0) {c++; fl_h=1;continue;}
 
		//printf("%c", rep[c++]);
		}
 
		if(val==0 || fl_h==1) printf("Invalid\n");
		else printf("Valid\n");
	}//for
} //main
