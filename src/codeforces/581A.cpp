/*
 * Problem link: http://codeforces.com/problemset/problem/581/A
 */

#include <stdio.h>

int main(int argc, char ** argv) {
    int red, blue;
    scanf("%d %d", &red, &blue);
    if(red > blue) {
        printf("%d %d\n", blue, (red - blue) >> 1);
    } else if (red < blue) {
        printf("%d %d\n", red, (blue - red) >> 1);
    } else {
        printf("%d %d\n", red, 0);
    }

    return 0;
}
