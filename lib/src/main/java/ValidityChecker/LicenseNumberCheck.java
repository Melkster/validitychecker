package ValidityChecker;

public class LicenseNumberCheck implements ValidityCheck {
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

    public String getName() {
        return "License number";
    }
}
