package ValidityChecker;

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
        System.out.println("ValidityChecker");
    }
}
