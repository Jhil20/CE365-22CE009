
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;


public class LexicalAnalyzer {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder program =  new StringBuilder();

        //Program input
        while (true) {
            String line = sc.nextLine();
            if(line.equals("END"))
            {
                break;
            }
            program.append(line).append("\n");
        }

        System.out.println(program);

        //adding Tokens
        String[] cKeywords = {
            "auto", "break", "case", "char", "const", "continue", "default", "do", "double",
            "else", "enum", "extern", "float", "for", "goto", "if", "inline", "int", "long",
            "register", "restrict", "return", "short", "signed", "sizeof", "static", "struct",
            "switch", "typedef", "union", "unsigned", "void", "volatile", "while", "_Alignas",
            "_Alignof", "_Atomic", "_Bool", "_Complex", "_Generic", "_Imaginary", "_Noreturn",
            "_Static_assert", "_Thread_local"
        };
        String[] cOperators = {
            "+", "-", "*", "/", "%", "++", "--", "=", "+=", "-=", "*=", "/=", "%=", "==", "!=",
            ">", "<", ">=", "<=", "&&", "||", "!", "&", "|", "^", "~", "<<", ">>", "->", "."
        };
        String[] cPunctuations = {
            ";", ",", ".", ":", "?", "(", ")", "{", "}", "[", "]", "/*", "*/", "//"
        };

        // Map to store keywords with "keyword" as type
        Map<String, String> tokens = new LinkedHashMap<>();

        // Add each keyword to the map
        for (String keyword : cKeywords) {
            tokens.put(keyword, "keyword");
        }

        for (String operator : cOperators) {
            tokens.put(operator, "operator");
        }
        for (String punctuation : cPunctuations) {
            tokens.put(punctuation, "punctuation");
        }

        //classify the tokens according to code
        int i=0;
       
        while(i < program.length())
        {
            StringBuilder word = new StringBuilder();
            if(program.charAt(i) != ' ')
            {
                word.append(program.charAt(i));
                i++;
            }
            i++;
            System.out.print(word);
        }
    }
}
