package ValidityChecker;

public class PINCheck implements ValidityCheck {
    private int PINLength = 12;

    public synchronized boolean validate(Long data) {
        return validate(String.valueOf(data));
    }

    /**
     * Assumes that PINs consist of 12 digits
     **/
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

    private boolean substringIsValidPIN(String substring, int lastDigit) {
        int checksum = 0;
        for (int i = 0; i < substring.toCharArray().length; i++) {
            int num = getIntFromStringIndex(substring, i);
            int multiplier = (i + 1) % 2 + 1; // alternates between 2 and 1
            checksum += sumOfDigits(num * multiplier);
        }

        return ((10 - checksum % 10) % 10 == lastDigit);
    }

    private boolean containsOnlylDigits(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    private int getIntFromStringIndex(String str, int index) {
        return Character.getNumericValue(str.charAt(index));
    }

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
