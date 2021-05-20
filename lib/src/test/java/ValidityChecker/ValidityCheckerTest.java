package ValidityChecker;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

public class ValidityCheckerTest {
    @Test
    public void testLogger() {
        Logger logger = Logger.getInstance();
        logger.log("foo");
    }

    @Test
    public void testAddCheck() {
        NotNullCheck nnCheck = new NotNullCheck();
        ValidityChecker vc = new ValidityChecker(nnCheck);
        assertEquals(1, vc.checks.size());
        assertTrue(vc.checks.contains(nnCheck));

        var lnCheck = new LicenseNumberCheck();
        assertTrue(vc.addCheck(lnCheck));
        assertEquals(2, vc.checks.size());
        assertTrue(vc.checks.contains(nnCheck));
        assertTrue(vc.checks.contains(lnCheck));
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
        assertFalse(vc.validate("ABCDEF"));

        assertTrue(vc.validate("ABC123"));
        assertTrue(vc.validate("FGA 837"));
        assertTrue(vc.validate(" UUW 135  "));
        assertTrue(vc.validate("ABC-123"));
        assertTrue(vc.validate("FGA-837"));
        assertTrue(vc.validate(" UUW-135  "));
    }

    @Test
    public void testPINCheck() {
        ValidityChecker vc = new ValidityChecker(new PINCheck());
        assertFalse(vc.validate("12345"));
        assertFalse(vc.validate(""));

        assertTrue(vc.validate("19780202-2389"));
        assertTrue(vc.validate("197802022389"));
        assertTrue(vc.validate("19780202 2389"));
        assertTrue(vc.validate(197802022389L));
    }

    @Test
    public void testNotNullAndLicenseNumberCheck() {
        ValidityChecker vc = new ValidityChecker(List.of(new NotNullCheck(), new LicenseNumberCheck()));
        assertFalse(vc.validate("foo"));
        assertFalse(vc.validate(""));
        assertFalse(vc.validate(null));

        assertTrue(vc.validate("ABC123"));
    }

    @Test
    public void testNotNullAndPINChecker() {
        ValidityChecker vc = new ValidityChecker(List.of(new NotNullCheck(), new PINCheck()));
        assertFalse(vc.validate("foo"));
        assertFalse(vc.validate(""));
        assertFalse(vc.validate(null));

        assertTrue(vc.validate("19780202-2389"));
    }
}
