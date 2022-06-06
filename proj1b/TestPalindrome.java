import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        String emptyWord = "";
        String singleChar = "A";
        String pd1 = "aSdFdSa";
        String pd2 = "aSdFFdSa";
        String npd1 = "aSdFDSa";
        String npd2 = "aSdFfdSa";

        assertTrue(palindrome.isPalindrome(emptyWord));
        assertTrue(palindrome.isPalindrome(singleChar));
        assertTrue(palindrome.isPalindrome(pd1));
        assertTrue(palindrome.isPalindrome(pd2));
        assertFalse(palindrome.isPalindrome(npd1));
        assertFalse(palindrome.isPalindrome(npd2));
    }

    @Test
    public void testIsPalindrome_OffByOne() {
        OffByOne obo = new OffByOne();
        assertTrue(palindrome.isPalindrome("flake", obo));
    }
}
