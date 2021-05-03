#include <stdio.h>

int main() {
	char string[100];	
	printf("Give me an adjective: ");
	scanf("%99s", &string[0]);
	printf("Hello, %s world!\n", string);
	int huhn;
	printf("Give me your number: ");
	scanf("%d", &huhn);
	printf("You're number times two is %d\n", huhn*2);
	printf("LOL\n");
}

