package ValidityChecker;

import java.util.List;

/**
 * Main file for illustrating {@link ValidityChecker}'s behaviour
 */
public class Main {
    public static void main(String[] args) {
        ValidityChecker nnChecker = new ValidityChecker(new NotNullCheck());
        System.out.println(nnChecker.validate(null)); // false
        System.out.println(nnChecker.validate("foo")); // true
        System.out.println();

        ValidityChecker pinChecker = new ValidityChecker(new PINCheck());
        System.out.println(pinChecker.validate("19780202-2389")); // true
        System.out.println(pinChecker.validate(198204112380L)); // true
        System.out.println(pinChecker.validate("1234-5678")); // false
        System.out.println();

        ValidityChecker licenseChecker = new ValidityChecker(List.of(new NotNullCheck(), new LicenseNumberCheck()));
        System.out.println(licenseChecker.validate("ABC-123")); // true
        System.out.println(licenseChecker.validate("ABC123")); // true
        System.out.println(licenseChecker.validate("A123BC")); // false
        System.out.println(licenseChecker.validate(null)); // false
    }

}
