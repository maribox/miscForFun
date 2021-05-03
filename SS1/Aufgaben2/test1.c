#include <stdio.h>

int main() {
	int i = 0;
	int x, y;
	int diff = 10;

	for (int bitsize = 1; bitsize < 100; bitsize ++) {
		int size = (1 << bitsize) - 1;
		printf("Bitsize %d: %d with next %d\n",bitsize, size, size+1);
	
	}
	

}
