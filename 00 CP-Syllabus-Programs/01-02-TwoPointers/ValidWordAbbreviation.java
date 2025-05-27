// Given a non-empty string s and an abbreviation abbr, 
// return whether the string matches with the given abbreviation.

// A string such as "word" contains only the following valid abbreviations:
// ["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]

// Notice that only the above abbreviations are valid abbreviations of the string "word". 
// Any other string is not a valid abbreviation of "word".

// Note: Assume s contains only lowercase letters and abbr contains only lowercase letters and digits.

// Input Format:
// -------------
// Line-1: A string, S contains only lowercase letters
// Line-2: A string, abbr contains lowercase letters and digits

// Output Format:
// --------------
// Line-1: A boolean value.
// Sample Input-1:
// ---------------
// internationalization
// i12iz4n

// Sample Output-1:
// ---------------
// true

// Sample Input-2:
// ---------------
// apple
// a2e

// Sample Output-2:
// ---------------
// false

// Time Complexity: O(n) where n=max(len(word),len(abbr))
// Auxiliary Space:  O(1).

import java.util.*;

public class ValidWordAbbreviation {
    public static boolean check(String word, String abb) {
        int l = 0;
        int r = 0;

        while (l < word.length() && r < abb.length()) {
            if (Character.isDigit(abb.charAt(r))) {
                if (abb.charAt(r) == '0')
                    return false;
                int num = 0;
                while (r < abb.length() && Character.isDigit(abb.charAt(r))) {
                    num = num * 10 + (abb.charAt(r) - '0');
                    r++;
                }
                l += num;
                if (l > word.length())
                    return false;
            } else {
                if (l >= word.length() || word.charAt(l) != abb.charAt(r)) {
                    return false;
                }
                l++;
                r++;
            }
        }
        return l == word.length() && r == abb.length();
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        String word = sc.next();
        String abb = sc.next();
        System.out.println(check(word, abb));
        sc.close();
    }
}