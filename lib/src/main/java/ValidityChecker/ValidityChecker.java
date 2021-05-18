package ValidityChecker;

public class ValidityChecker {
    public Iterable<ValidityCheck> checks;

    public ValidityChecker(Iterable<ValidityCheck> checks) {
        this.checks = checks;
    }

    public boolean validate(Object data) {
        boolean result = true;
        for (ValidityCheck check : checks) {
            if (!check.validate(data)) {
                Logger.getInstance()
                        .log(String.format("Validity check '%s' for data '%s' failed ", check.getName(), data));
                result = false;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("ValidityChecker");
    }
}
