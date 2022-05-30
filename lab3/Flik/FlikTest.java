import static org.junit.Assert.*;

import org.junit.Test;

public class FlikTest {
    @Test
    public void testSameNumber() {
        assertTrue(Flik.isSameNumber(128, 128));
    }
}
