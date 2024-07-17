import java.util.Scanner;

public class CheckPalindrome {

    public static boolean ispalindrome(String s) {
        String Str = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        int st = 0;
        int end = Str.length() - 1;

        while (st < end) {
            if (Str.charAt(st) != Str.charAt(end)) {
                return false;
            }
            st++;
            end--;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a string to check if it is a palindrome:");
        String input = scanner.nextLine();

        boolean res = ispalindrome(input);

        if (res) {
            System.out.println("The string is a palindrome.");
        } else {
            System.out.println("The string is not a palindrome.");
        }
        scanner.close();
    }
}

