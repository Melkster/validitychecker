package ValidityChecker;

public class NotNullCheck implements ValidityCheck {
    public synchronized boolean validate(Object data) {
        return data != null;
    }

    @Override
    public String toString() {
        return "Not null check";
    }
}
