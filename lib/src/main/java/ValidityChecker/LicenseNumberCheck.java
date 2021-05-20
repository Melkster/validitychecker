package ValidityChecker;

/**
 * {@link ValidityCheck} for checking if data a standard Swedish car license
 * number.
 */
public class LicenseNumberCheck implements ValidityCheck {
    /**
     * Checks if the data is a standard Swedish car license number.
     *
     * @param data The data to validate
     * @return Returns true if data is a license number, otherwise false
     */
    public synchronized boolean validate(Object data) {
        if (!(data instanceof String)) {
            return false;
        }

        String dataString = (String) data;
        dataString = dataString.replaceAll("\\s", "").replaceAll("-", "");
        if (dataString.length() != 6) {
            return false;
        }

        for (Character c : dataString.substring(0, 2).toCharArray()) {
            if (!Character.isLetter(c)) {
                return false;
            }
        }
        for (Character c : dataString.substring(3, 5).toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "License number check";
    }
}
