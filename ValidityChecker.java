import java.util.Arrays;

public class ValidityChecker {
    public Iterable<ValidityCheck> checks;

    public ValidityChecker(Iterable<ValidityCheck> checks) {
        this.checks = checks;
    }

    // TODO: add logger
    public boolean validate(Object data) {
        for (ValidityCheck check : checks) {
            if (!check.validate(data)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        var vc = new ValidityChecker(Arrays.asList(new NotNull()));
    }
}
