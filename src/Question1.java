import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Question1 {
    static String findSubString(String s){
        // Handle edge cases: null or empty string
        if (s == null || s.isEmpty()) {
            return "";
        }

        //store characters in the current window
        Set<Character> charSet = new HashSet<>();

        int start = 0; // Start of the current window
        int end = 0;   // End of the current window

        // Variables to store the maximum length and the actual longest substring
        int maxLength = 0;
        String longestSubstring = "";

        // Iterate through the string with the 'end' pointer
        while (end < s.length()) {
            char currentChar = s.charAt(end);

            // If the current character is not in the set, it's unique within the current window.
            if (!charSet.contains(currentChar)) {
                charSet.add(currentChar);
                int currentLength = end - start + 1;
                // If the current window is longer than the maximum found so far, update maxLength and longestSubstring
                if (currentLength > maxLength) {
                    maxLength = currentLength;
                    longestSubstring = s.substring(start, end + 1);
                }
                // Move the end pointer to expand the window
                end++;
            } else {
                // If the current character is already in the set, it's a duplicate.
                // We need to shift the window from the 'start' until the duplicate is removed.
                charSet.remove(s.charAt(start));
                start++; // Move the start pointer
            }
        }

        return longestSubstring;
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input a string:");
        String originalString = sc.nextLine();
        if (originalString.length() == 0){
            System.out.println("Please input a non-empty string.");
            sc.close();
            return;
        }
        System.out.println("Original string: " + originalString);
        String subString = findSubString((originalString));
        System.out.println("subString: " + subString);

        sc.close();
    }
}
