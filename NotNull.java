/**
 * NotNull
 */
public class NotNull implements ValidityCheck {
    public boolean validate(Object data) {
        return data != null;
    }
}
