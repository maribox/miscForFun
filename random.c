#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <unistd.h>

int main() { 
	int repeats;
	int ones = 0, twos = 0, threes = 0, fours = 0, fives = 0, sixes = 0;
	int temp;
	printf("How often should the dice be rolled?\n");
	scanf("%d", &repeats);
	while (repeats < 10000) {
		printf("That is not very representative. Try a number greater than 10000.\n");
		printf("How often should the dice be rolled?\n");
		scanf("%d", &repeats);
	}
	printf("Rolling the dice %d times.\n", repeats);
	srand(time(NULL) ^ getpid());
	for (int i = 0; i < repeats; i++){
		temp =  (rand()%6) + 1;
		// printf("%d", temp);
		switch (temp) {
			case 1:
				ones++;
			break;
			case 2:
				twos++;
			break;
			case 3:
				threes++;
			break;
			case 4:
				fours++;
			break;
			case 5:
				fives++;
			break;
			case 6:
				sixes++;
			break;
			default:
				printf("Shut the fuck up or I will fucking laser you with alien fucking eyes and explode your fucking head.\n");
		}		
		// printf("Dice no.%d: %d\n", i, temp);
	}
	int maxnumber;
	if (ones > maxnumber) {
		maxnumber = ones;
	}
	if (twos > maxnumber) {
		maxnumber = twos;
	}
	if (threes > maxnumber) {
		maxnumber = threes;
	}	
	if (fours > maxnumber) {
		maxnumber = fours;
	}
	if (fives > maxnumber) {
		maxnumber = fives;
	}
	if (sixes > maxnumber) {
		maxnumber = sixes;
	}
	float numberdivision = maxnumber / 60; 
	printf("%d | %d | ", 1, ones);
	for (int i = 0; i < (ones / numberdivision); i++) {
		printf("[]");
	}
	printf("\n%d | %d | ", 2, twos);
	for (int i = 0; i < (twos / numberdivision); i++) {
		printf("[]");

	}
	printf("\n%d | %d | ", 3, threes);
	for (int i = 0; i < (threes / numberdivision); i++) {
		printf("[]");
	}
	printf("\n%d | %d | ", 4, fours);
	for (int i = 0; i < (fours / numberdivision); i++) {
		printf("[]");
	}
	printf("\n%d | %d | ", 5, fives);
	for (int i = 0; i < (fives / numberdivision); i++) {
		printf("[]");
	}
	printf("\n%d | %d | ", 6, sixes);
	for (int i = 0; i < (sixes / numberdivision); i++) {
		printf("[]");
	}
	printf("\nFinished!\n");

}

		
