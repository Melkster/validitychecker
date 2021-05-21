package melkster.validitychecker;

import java.util.ArrayList;
import java.util.List;

import melkster.validitycheck.ValidityCheck;

/**
 * A validity checker that uses a given amount of {@link ValidityCheck}s to
 * validate a given input object.
 */
public class ValidityChecker {
    /**
     * The list of validity checks
     */
    List<ValidityCheck> checks;

    public ValidityChecker(List<ValidityCheck> checks) {
        this.checks = new ArrayList<ValidityCheck>(checks);
    }

    public ValidityChecker(ValidityCheck check) {
        this(List.of(check));
    }

    /**
     * Validates the input data with each {@link ValidityCheck}.
     *
     * Any failed validity check is logged using {@link Logger}.
     *
     * @param data The data to validate
     * @return Returns true if data passed the validation of each
     *         {@link ValidityCheck}, otherwise false
     */
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

    /**
     * Adds the validity check to the validity checker.
     *
     * @param check The validity check to add
     * @return Returns true if validity check was successfully added, otherwise
     *         false
     */
    public boolean addCheck(ValidityCheck check) {
        return checks.add(check);
    }

    /**
     * Counts the number of validity checks in the validity checker.
     *
     * @return Returns the number of validity checks
     */
    public int countChecks() {
        return checks.size();
    }
}
