package ValidityChecker;

import java.util.ArrayList;
import java.util.List;

public class ValidityChecker {
    List<ValidityCheck> checks;

    public ValidityChecker(List<ValidityCheck> checks) {
        this.checks = new ArrayList<ValidityCheck>(checks);
    }

    public ValidityChecker(ValidityCheck check) {
        this(List.of(check));
    }

    public boolean validate(Object data) {
        boolean result = true;
        for (ValidityCheck check : checks) {
            if (!check.validate(data)) {
                Logger.getInstance().log(String.format("Validity check '%s' for data '%s' failed ", check, data));
                result = false;
            }
        }
        return result;
    }

    public boolean addCheck(ValidityCheck check) {
        return checks.add(check);
    }

    public static void main(String[] args) {
        System.out.println("ValidityChecker");
    }
}
