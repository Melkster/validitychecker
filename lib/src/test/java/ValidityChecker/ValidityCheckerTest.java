package ValidityChecker;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;

public class ValidityCheckerTest {
    @Test
    public void testNotNull() {
        ValidityChecker vc = new ValidityChecker(Arrays.asList(new NotNull()));
        assertFalse(vc.validate(null));
        assertTrue(vc.validate("foo"));
        assertTrue(vc.validate(""));
        assertTrue(vc.validate(new Object()));
    }

    @Test
    public void testLogger() {
        Logger logger = Logger.getInstance();
        logger.log("bar");
    }
}
