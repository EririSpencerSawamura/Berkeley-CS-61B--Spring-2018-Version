import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    /** A randomized test for the StudentArrayDeque.
     * @source StudentArrayDequeLauncher.java */
    @Test
    public void testArrayDeque() {
        StudentArrayDeque<Integer> testArray = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> solArray = new ArrayDequeSolution<>();
        String message = "";
        for (int i = 0; i < 32; i++) {
            double numberBetweenZeroAndOne1 = StdRandom.uniform();

            if (numberBetweenZeroAndOne1 < 0.5) {
                testArray.addFirst(i);
                solArray.addFirst(i);
                message = message +"addFirst(" + (i) + ")\n";
            } else {
                testArray.addLast(i);
                solArray.addLast(i);
                message = message +"addLast(" + (i) + ")\n";
            }
        }

        Integer testRemoved = 0;
        Integer solRemoved = 0;
        for (int i = 0; i < 32; i++) {
            double numberBetweenZeroAndOne2 = StdRandom.uniform();
            if (numberBetweenZeroAndOne2 < 0.5) {
                testRemoved = testArray.removeFirst();
                solRemoved = solArray.removeFirst();
                message = message + "removeFirst()\n";
            } else {
                testRemoved = testArray.removeLast();
                solRemoved = solArray.removeLast();
                message = message + "removeLast()\n";
            }
            assertEquals(message, solRemoved, testRemoved);
        }
    }
}
