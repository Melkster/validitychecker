package ValidityChecker;

public class NotNullCheck implements ValidityCheck {
    public synchronized boolean validate(Object data) {
        return data != null;
    }

    public String getName() {
        return "Not null";
    }
}
