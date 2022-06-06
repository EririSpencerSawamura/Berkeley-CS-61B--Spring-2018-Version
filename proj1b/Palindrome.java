public class Palindrome {
    /** Returns a Deque where the characters appear in the same order as in the String. */
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> dq = new LinkedListDeque<>();
        for (int i =0; i < word.length(); i++) {
            dq.addLast(word.charAt(i));
        }
        return dq;
    }

    /** Returns true if the given word is a palindrome, and false otherwise. */
    public boolean isPalindrome(String word) {
        if (word == null || word.length() <= 1) {
            return true;
        }
        Deque<Character> dq = wordToDeque(word);
        while (dq.size() > 1) {
            char first = dq.removeFirst();
            char last = dq.removeLast();
            if (first != last) {
                return false;
            }
        }
        return true;
    }

    /** Overloaded method of the above. Returns true if the given word is a palindrome with regard to
     *  the "Off-By-One" rule for identical characters, and false otherwise. */
    public boolean isPalindrome(String word, OffByOne obo) {
        if (word == null || word.length() <= 1) {
            return true;
        }
        Deque<Character> dq = wordToDeque(word);
        while (dq.size() > 1) {
            char first = dq.removeFirst();
            char last = dq.removeLast();
            if (!obo.equalChars(first, last)) {
                return false;
            }
        }
        return true;
    }
}
