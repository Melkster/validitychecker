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
            if (substring.length() != PINLength) {
                return false;
            }
            for (char c : substring.toCharArray()) {
                if (!Character.isDigit(c)) {
                    return false;
                }
            }
            int lastNum = getIntFromStringIndex(substring, PINLength - 1);
            substring = substring.substring(2, PINLength - 1);
            int sum = 0;
            for (int i = 0; i < substring.toCharArray().length; i++) {
                int num = getIntFromStringIndex(substring, i);
                int multiplier = (i + 1) % 2 + 1; // alternates between 2 and 1

                sum += sumOfDigits(num * multiplier);
            }

            if ((10 - sum % 10) % 10 == lastNum) {
                return true;
            }
        } else if (data instanceof Long) {
            return validate(String.valueOf(data));
        }
        return false;
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
