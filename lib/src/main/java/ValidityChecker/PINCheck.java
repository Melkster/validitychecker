package ValidityChecker;

/**
 * {@link ValidityCheck} for checking if data a personal identification number
 * (PIN).
 *
 * Assumes that PINs consist of 12 digits.
 */
public class PINCheck implements ValidityCheck {
    /**
     * Accepted length of PIN
     */
    private int PINLength = 12;

    public synchronized boolean validate(Long data) {
        return validate(String.valueOf(data));
    }

    /**
     * Checks if the data is a personal identification number.
     *
     * @param data The data to validate
     * @return Returns true if data is a personal identification number, otherwise
     *         false
     */
    public synchronized boolean validate(Object data) {
        if (data instanceof String) {
            String substring = (String) data;
            substring = substring.replaceAll("\\s", "").replaceAll("-", "");

            if (substring.length() != PINLength || !containsOnlylDigits(substring)) {
                return false;
            }

            int lastDigit = getIntFromStringIndex(substring, PINLength - 1);
            substring = substring.substring(2, PINLength - 1);
            return substringIsValidPIN(substring, lastDigit);
        } else if (data instanceof Long) {
            return validate(String.valueOf(data));
        } else {
            return false;
        }
    }

    /**
     * Checks if substring is a valid personal identification number (PIN).
     *
     * @param substring The substring to validate, should not include the first two,
     *                  or the last digit
     * @param lastDigit The last digit of the full PIN
     * @return Returns true if the substring can be validated against the last digit
     */
    private boolean substringIsValidPIN(String substring, int lastDigit) {
        int checksum = 0;
        for (int i = 0; i < substring.toCharArray().length; i++) {
            int num = getIntFromStringIndex(substring, i);
            int multiplier = (i + 1) % 2 + 1; // alternates between 2 and 1
            checksum += sumOfDigits(num * multiplier);
        }

        return ((10 - checksum % 10) % 10 == lastDigit);
    }

    /**
     * Checks if string only contains characters that are digits.
     *
     * @param str The string to validate
     * @return Returns true if string contains only digits
     */
    private boolean containsOnlylDigits(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Retrieves the character at a given index as an integer.
     *
     * Assumes that str consists only of the characters [0-9].
     *
     * @param str   The string to retreieve the integer from
     * @param index The index from where to retrieve the integer
     * @return Returns the character at the given index as an integer
     */
    private int getIntFromStringIndex(String str, int index) {
        return Character.getNumericValue(str.charAt(index));
    }

    /**
     * Retrieves the sum of the digits in a given number.
     *
     * Assumes that num is positive.
     *
     * @param num The number whose digits to retrieve the sum from
     * @return The sum of each digit in num
     */
    private int sumOfDigits(int num) {
        int sum = 0;
        for (char n : String.valueOf(num).toCharArray()) {
            sum += Character.getNumericValue(n);
        }
        return sum;
    }

    @Override
    public String toString() {
        return "Personal identification number check";
    }

}
