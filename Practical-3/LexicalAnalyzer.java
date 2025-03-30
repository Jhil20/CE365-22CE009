import java.util.*;
import java.util.regex.*;

public class LexicalAnalyzer {
    private static final int MAX_TOKEN_LEN = 100;
    private static final int MAX_SYMBOLS = 100;
    
    private static final Set<String> keywords = new HashSet<>(Arrays.asList(
        "int", "char", "return", "if", "while", "for", "else", "void"
    ));

    private static final List<String> symbolTable = new ArrayList<>();
    
    public static boolean isKeyword(String word) {
        return keywords.contains(word);
    }

    public static boolean isFunctionName(String token, char nextChar) {
        return nextChar == '(';
    }

    public static void addToSymbolTable(String name) {
        if (!symbolTable.contains(name)) {
            symbolTable.add(name);
        }
    }

    public static void tokenize(String code) {
        StringBuilder token = new StringBuilder();
        int index = 0, lineNumber = 1;
        
        char[] chars = code.toCharArray();
        
        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];

            if (ch == '\n') {
                lineNumber++;
            }

            if (Character.isLetter(ch)) {
                token.setLength(0);
                while (i < chars.length && (Character.isLetterOrDigit(chars[i]) || chars[i] == '_')) {
                    token.append(chars[i++]);
                }
                i--;

                String word = token.toString();
                if (isKeyword(word)) {
                    System.out.println("Keyword: " + word);
                } else if (!isFunctionName(word, i + 1 < chars.length ? chars[i + 1] : ' ')) {
                    System.out.println("Identifier: " + word);
                    addToSymbolTable(word);
                } else {
                    System.out.println("Function: " + word);
                }
            }

            else if (Character.isDigit(ch)) {
                token.setLength(0);
                while (i < chars.length && Character.isDigit(chars[i])) {
                    token.append(chars[i++]);
                }

                if (i < chars.length && Character.isLetter(chars[i])) {
                    System.out.println("LEXICAL ERROR (Line " + lineNumber + "): " + token + chars[i] + " is an invalid lexeme");
                    while (i < chars.length && Character.isLetterOrDigit(chars[i])) i++;
                } else {
                    System.out.println("Constant: " + token);
                }
                i--;
            }

            else if (ch == '\'') {
                token.setLength(0);
                token.append(ch);
                if (i + 1 < chars.length && Character.isLetterOrDigit(chars[i + 1])) {
                    token.append(chars[++i]);
                    if (i + 1 < chars.length && chars[i + 1] == '\'') {
                        token.append(chars[++i]);
                        System.out.println("Constant: " + token);
                    } else {
                        System.out.println("LEXICAL ERROR (Line " + lineNumber + "): Invalid character constant");
                        while (i < chars.length && chars[i] != '\'') i++;
                    }
                } else {
                    System.out.println("LEXICAL ERROR (Line " + lineNumber + "): Invalid character constant");
                }
            }

            else if (ch == '"') {
                token.setLength(0);
                token.append(ch);
                i++;
                while (i < chars.length && chars[i] != '"') {
                    token.append(chars[i++]);
                }
                if (i < chars.length) {
                    token.append('"');
                    System.out.println("String: " + token);
                }
            }

            else if (ch == '/' && i + 1 < chars.length && chars[i + 1] == '*') {
                i += 2;
                while (i + 1 < chars.length && !(chars[i] == '*' && chars[i + 1] == '/')) {
                    if (chars[i] == '\n') lineNumber++;
                    i++;
                }
                i++;
            }

            else if (ch == '/' && i + 1 < chars.length && chars[i + 1] == '/') {
                while (i < chars.length && chars[i] != '\n') i++;
                lineNumber++;
            }

            else if ("=+-*/%".indexOf(ch) != -1) {
                System.out.println("Operator: " + ch);
            }

            else if (Character.toString(ch).matches("\\p{Punct}") && ch != '/' && ch != '*' && ch != '+' && ch != '-' && ch != '=') {
                System.out.println("Punctuation: " + ch);
            }

            else if (!Character.isWhitespace(ch)) {
                System.out.println("LEXICAL ERROR (Line " + lineNumber + "): Unrecognized character '" + ch + "'");
            }
        }
    }

    public static void printSymbolTable() {
        System.out.println("\nSYMBOL TABLE ENTRIES");
        for (int i = 0; i < symbolTable.size(); i++) {
            System.out.println((i + 1) + ") " + symbolTable.get(i));
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder code = new StringBuilder();

        System.out.println("Enter the code (end input with Ctrl+D on Linux/Mac or Ctrl+Z on Windows):");

        while (scanner.hasNextLine()) {
            code.append(scanner.nextLine()).append("\n");
        }
        scanner.close();

        tokenize(code.toString());
        printSymbolTable();
    }
}
