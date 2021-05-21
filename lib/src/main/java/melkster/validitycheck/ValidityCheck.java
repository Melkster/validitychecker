package melkster.validitycheck;

/**
 * Validity check interface.
 */
public interface ValidityCheck {
    /**
     * Validates the input data.
     *
     * @param data The data to validate
     * @return Returns true if input data passed the validation, otherwise false.
     */
    public boolean validate(Object data);

    public String toString();
}
