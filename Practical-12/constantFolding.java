import java.util.*;

public class constantFolding {
    static boolean isNumber(String s) {
        return !s.isEmpty() && s.chars().allMatch(Character::isDigit);
    }

    static int precedence(char op) {
        if (op == '+' || op == '-') return 1;
        if (op == '*' || op == '/') return 2;
        return 0;
    }

    static double applyOp(double a, double b, char op) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': return a / b;
            default: return 0;
        }
    }

    static List<String> infixToPostfix(String expr) {
        List<String> output = new ArrayList<>();
        Stack<Character> opStack = new Stack<>();
        StringBuilder num = new StringBuilder();
        
        for (char ch : expr.toCharArray()) {
            if (Character.isDigit(ch)) {
                num.append(ch);
            } else {
                if (num.length() > 0) {
                    output.add(num.toString());
                    num.setLength(0);
                }
                if (ch == '(') {
                    opStack.push(ch);
                } else if (ch == ')') {
                    while (!opStack.isEmpty() && opStack.peek() != '(') {
                        output.add(String.valueOf(opStack.pop()));
                    }
                    opStack.pop();
                } else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                    while (!opStack.isEmpty() && precedence(opStack.peek()) >= precedence(ch)) {
                        output.add(String.valueOf(opStack.pop()));
                    }
                    opStack.push(ch);
                } else if (Character.isLetter(ch)) {
                    output.add(String.valueOf(ch));
                }
            }
        }
        if (num.length() > 0) output.add(num.toString());
        while (!opStack.isEmpty()) {
            output.add(String.valueOf(opStack.pop()));
        }
        return output;
    }

    static String optimizeExpression(List<String> postfix) {
        Stack<String> evalStack = new Stack<>();
        
        for (String token : postfix) {
            if (isNumber(token)) {
                evalStack.push(token);
            } else if (Character.isLetter(token.charAt(0))) {
                evalStack.push(token);
            } else {
                String op2 = evalStack.pop();
                String op1 = evalStack.pop();
                
                if (isNumber(op1) && isNumber(op2)) {
                    double val = applyOp(Double.parseDouble(op1), Double.parseDouble(op2), token.charAt(0));
                    evalStack.push(String.valueOf(val));
                } else {
                    evalStack.push(op1 + " " + token + " " + op2);
                }
            }
        }
        return evalStack.peek();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an arithmetic expression: ");
        String expr = scanner.nextLine();
        
        List<String> postfix = infixToPostfix(expr);
        String optimized = optimizeExpression(postfix);
        
        System.out.println("Optimized Expression: " + optimized);
        scanner.close();
    }
} 
