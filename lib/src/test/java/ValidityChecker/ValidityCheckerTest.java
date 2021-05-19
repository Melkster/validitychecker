package ValidityChecker;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;

public class ValidityCheckerTest {
    @Test
    public void testLogger() {
        Logger logger = Logger.getInstance();
        logger.log("bar");
    }

    @Test
    public void testNotNull() {
        ValidityChecker vc = new ValidityChecker(new NotNullCheck());
        assertFalse(vc.validate(null));
        assertTrue(vc.validate("foo"));
        assertTrue(vc.validate(""));
        assertTrue(vc.validate(new Object()));
    }

    @Test
    public void testLicenseNumberCheck() {
        ValidityChecker vc = new ValidityChecker(new LicenseNumberCheck());
        assertFalse(vc.validate(null));
        assertFalse(vc.validate("foo"));
        assertFalse(vc.validate(""));
        assertFalse(vc.validate(new Object()));
        assertFalse(vc.validate("123 987"));

        assertTrue(vc.validate("ABC123"));
        assertTrue(vc.validate("FGA 837"));
        assertTrue(vc.validate(" UUW 135  "));
        assertTrue(vc.validate("ABC-123"));
        assertTrue(vc.validate("FGA-837"));
        assertTrue(vc.validate(" UUW-135  "));
    }
}
