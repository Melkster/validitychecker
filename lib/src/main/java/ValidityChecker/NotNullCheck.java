package ValidityChecker;

/**
 * {@link ValidityCheck} for checking if data is not null.
 */
public class NotNullCheck implements ValidityCheck {
    /**
     * Checks if the data is not null.
     *
     * @param data The data to validate
     * @return Returns true if data is not null, otherwise false
     */
    public synchronized boolean validate(Object data) {
        return data != null;
    }

    @Override
    public String toString() {
        return "Not null check";
    }
}
