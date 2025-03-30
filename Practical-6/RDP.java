import java.util.Scanner;

public class RDP {
    static String s;  // Input string
    static int i = 0; // Global index tracker
    static boolean flag = false;  // Validation flag

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the string to validate: ");
        s = scanner.next();
        scanner.close();

        i = 0;
        flag = false;
        
        S();  // Start parsing from S

        if (i == s.length() && !flag) {  // Ensure entire string is parsed
            System.out.println("Valid string");
        } else {
            System.out.println("Invalid string");
        }
    }

    // Recursive Descent Parser functions
    static void S() {
        if (i < s.length() && s.charAt(i) == 'a') {  // Case: S → a
            i++;
        } 
        else if (i < s.length() && s.charAt(i) == '(') {  // Case: S → ( L )
            i++;  // Move past '('
            L();
            if (i < s.length() && s.charAt(i) == ')') {
                i++;  // Move past ')'
            } else {
                flag = true;  // Invalid string
            }
        } 
        else {
            flag = true;  // Invalid string
        }
    }

    static void L() {
        S();  // First call S
        Ld(); // Then call L'
    }

    static void Ld() {
        if (i < s.length() && s.charAt(i) == ',') {  // Case: L' → , S L'
            i++;  // Move past ','
            S();
            Ld();  // Recursively check for more elements
        }
        // Case: L' → ε (do nothing, base case)
    }
}