import edu.princeton.cs.algs4.Queue;

import java.util.LinkedList;

/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 *
 */
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {
        if (asciis.length == 0 || asciis.length == 1) {
            return asciis;
        }

        int maxLength = 0;
        for (String s : asciis) {
            maxLength = Math.max(maxLength, s.length());
        }

        String[] sorted = new String[asciis.length];
        System.arraycopy(asciis, 0, sorted, 0, asciis.length);
        for (int index = maxLength - 1; index >= 0; index--) {
            sorted = sortHelperLSD(sorted, index);
        }

        return sorted;
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     * @param asciis Input array of Strings
     * @param index The position to sort the Strings on.
     */
    private static String[] sortHelperLSD(String[] asciis, int index) {
        // Optional LSD helper method for required LSD radix sort
        int[] counts = new int[256];
        for (String s : asciis) {
            if (s.length() <= index) {
                counts[0]++;
            } else {
                counts[s.charAt(index)]++;
            }
        }

        int[] starts = new int[256];
        int pos = 0;
        for (int i = 0; i < starts.length; i++) {
            starts[i] = pos;
            pos += counts[i];
        }

        String[] sorted = new String[asciis.length];
        for (String s : asciis) {
            int place;
            if (s.length() <= index) {
                place = starts[0];
                starts[0]++;
            } else {
                place = starts[s.charAt(index)];
                starts[s.charAt(index)]++;
            }
            sorted[place] = s;
        }

        return sorted;
    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start int for where to start sorting in this method (includes String at start)
     * @param end int for where to end sorting in this method (does not include String at end)
     * @param index the index of the character the method is currently sorting on
     *
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }

    public static void main(String[] args) {
        String[] strings = new String[]{"add", "cab", "do", "fad", "fee",
                "bad", "bee", "fed", "bed", "an", "ace", "beef", "bees"};
        strings = sort(strings);
        for (String s : strings) {
            System.out.println(s);
        }
    }
}
