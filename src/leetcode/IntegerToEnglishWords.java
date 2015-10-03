/**
 * Problem link: https://leetcode.com/problems/integer-to-english-words
 *
 * Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 231 - 1.
 *
 * For example,
 * 123 -> "One Hundred Twenty Three"
 * 12345 -> "Twelve Thousand Three Hundred Forty Five"
 * 1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 */

public class IntegerToEnglishWords {
    public static void main(String[] args) {
        IntegerToEnglishWords solution = new IntegerToEnglishWords();

        System.out.println(solution.numberToWords(1234567));

        assert solution.numberToWords(123).equals("One Hundred Twenty Three");
        assert solution.numberToWords(12345).equals("Twelve Thousand Three Hundred Forty Five");
        assert solution.numberToWords(1234567).equals("One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven");
        assert solution.numberToWords(10).equals("Ten");
        assert solution.numberToWords(100).equals("One Hundred");
        assert solution.numberToWords(100000).equals("One Hundred Thousand");
        assert solution.numberToWords(1000000).equals("One Million");
    }

    private static final int DIGIT_START = (int)'0';
    private static final String HUNDRED_STR = "Hundred";
    private static final String[] UNIT = new String[]{null, "Thousand", "Million", "Billion"};
    private static final String[] DIGIT_INT_STR = new String[]{"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    private static final String[] TENS_INT_STR = new String[]{"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    private static final String[] LESS_THAN_TENS_INT_STR = new String[]{"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};

    public String numberToWords(int num) {
        if(num == 0) {
            return "Zero";
        }
        char[] numberArray = new char[12];
        int numberArraySize = 0;

        int tmp = num;
        while(tmp != 0) {
            char number = (char)((tmp % 10) + (int)'0');
            tmp = tmp / 10;
            numberArray[numberArraySize ++] = number;
        }

        StringBuffer stringBuffer = new StringBuffer();

        int end = numberArraySize;
        for(int i = numberArraySize - 1 ; i >= 0 ; -- i) {
            if(i % 3 == 0) {
                fillBuffer(numberArray, i, end, UNIT[i / 3], stringBuffer);
                end = i;
            }
        }

        return stringBuffer.toString().trim();
    }

    void fillBuffer(char[] numberArray, int start, int end, String unit, StringBuffer buffer) {
        if(end == start) {
            return;
        }

        boolean appendUnit = false;

        if(end - start == 3 && numberArray[start + 2] != '0') {
            buffer.append(DIGIT_INT_STR[getDigit(numberArray[start + 2])])
                .append(" ")
                .append(HUNDRED_STR)
                .append(" ");
            appendUnit = true;
        }

        if(end - start >= 2) {
            if(numberArray[start + 1] == '1') {
                buffer.append(LESS_THAN_TENS_INT_STR[getDigit(numberArray[start])])
                    .append(" ");
                appendUnit = true;
            } else if(numberArray[start + 1] == '0') {
                if(numberArray[start] != '0') {
                    buffer.append(DIGIT_INT_STR[getDigit(numberArray[start])])
                        .append(" ");
                    appendUnit = true;
                }
            } else {
                buffer.append(TENS_INT_STR[getDigit(numberArray[start + 1])])
                    .append(" ");
                if(numberArray[start] != '0') {
                    buffer.append(DIGIT_INT_STR[getDigit(numberArray[start])])
                        .append(" ");
                }
                appendUnit = true;
            }
        }

        if(end - start == 1 && numberArray[start] != '0') {
            buffer.append(DIGIT_INT_STR[getDigit(numberArray[start])])
                .append(" ");
            appendUnit = true;
        }

        if(unit != null && appendUnit) {
            buffer.append(unit).append(" ");
        }
    }

    int getDigit(char c) {
        return (int)c - DIGIT_START;
    }
}
