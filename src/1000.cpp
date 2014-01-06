/*
 * A + B Problem
 */

#include <stdio.h>
int main(int agrc, char* argv[]) {
	int a, b;

	while(scanf("%d %d", &a, &b) != EOF) {
		printf("%d\n", a + b);
	}
}