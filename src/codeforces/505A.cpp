/*
 * CodeForces_A._Mr._Kitayuta's_Gift.cpp
 *
 *  Created on: Jan 18, 2015
 *      Author: shijianguang
 */
#include "stdio.h"
#include "string.h"

#define DEFAULT_INPUT_LENGTH 20

bool find_missing_char(const char * input_string, int short_start, int short_end, int long_start, int long_end, char &missing_char, int &missing_index) {
	int short_index = short_end - 1;
	int long_index = long_start;

	bool must_match = (short_end - short_start) == (long_end - long_start);
	while(true) {
		if (short_index < short_start || long_index >= long_end)
			break;
		if (!must_match && (input_string[short_index] != input_string[long_index])) {
			missing_index = short_index;
			missing_char = input_string[long_index];
			must_match = true;
			++ long_index;
		}

		if (must_match && (input_string[short_index] != input_string[long_index])) {
			return false;
		}

		-- short_index;
		++ long_index;
	}

	if (short_index < short_start && long_index < long_end) {
		missing_index = short_start - 1;
		missing_char = input_string[long_index];
	}

	return true;
}

void output_result(const char * input_string, int missing_index, char missing_char) {
	if (missing_index == -1)
		printf("%c", missing_char);
	for (int i = 0 ; input_string[i] != '\0' ; ) {
		printf("%c", input_string[i]);
		if (i == missing_index)
			printf("%c", missing_char);
		++ i;
	}

	printf("\n");
}
int main(int argc, char **argv) {
	char input_string[DEFAULT_INPUT_LENGTH];

	scanf("%s", input_string);

	int input_string_len = strlen(input_string);

	int missing_index = 0;
	char missing_char = 'a';
	if (input_string_len & 0x1) {
		int split_pos = input_string_len >> 1;
		bool flag = find_missing_char(input_string, 0, split_pos, split_pos, input_string_len, missing_char, missing_index);
		if (flag == true)
		{
			output_result(input_string, missing_index, missing_char);
			return 0;
		}

		flag = find_missing_char(input_string, split_pos + 1, input_string_len, 0, split_pos + 1, missing_char, missing_index);
		if (flag == true)
		{
			output_result(input_string, missing_index, missing_char);
			return 0;
		}

		printf("NA\n");
	} else {
		int split_pos = input_string_len >> 1;
		bool flag = find_missing_char(input_string, 0, split_pos - 1, split_pos, input_string_len, missing_char, missing_index);
		if (flag == true)
		{
			output_result(input_string, missing_index, missing_char);
			return 0;
		}

		flag = find_missing_char(input_string, split_pos + 1, input_string_len, 0, split_pos, missing_char, missing_index);
		if (flag == true)
		{
			output_result(input_string, missing_index, missing_char);
			return 0;
		}

		flag = find_missing_char(input_string, 0, split_pos, split_pos, input_string_len, missing_char, missing_index);
		if (flag == true)
		{
			output_result(input_string, split_pos, 'a');
			return 0;
		}

		printf("NA\n");
	}

	return 0;
}
