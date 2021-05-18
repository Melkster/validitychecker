package ValidityChecker;

public class NotNull implements ValidityCheck {
    public synchronized boolean validate(Object data) {
        return data != null;
    }

    public String getName() {
        return "Not null";
    }
}
