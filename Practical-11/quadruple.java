import java.util.*;

public class quadruple {
    static List<String[]> quadruples = new ArrayList<>(); 
    static int tempCount = 1;

    static String getTemp() {
        return "t" + tempCount++;
    }

    static int precedence(char op) {
        if (op == '+' || op == '-') return 1;
        if (op == '*' || op == '/') return 2;
        return 0;
    }

    static List<String> infixToPostfix(String expr) {
        Stack<Character> opStack = new Stack<>();
        List<String> output = new ArrayList<>();
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
                } else {
                    while (!opStack.isEmpty() && precedence(opStack.peek()) >= precedence(ch)) {
                        output.add(String.valueOf(opStack.pop()));
                    }
                    opStack.push(ch);
                }
            }
        }
        if (num.length() > 0) output.add(num.toString());
        while (!opStack.isEmpty()) {
            output.add(String.valueOf(opStack.pop()));
        }
        return output;
    }

    static String generateQuadruples(List<String> postfix) {
        Stack<String> evalStack = new Stack<>();
        
        for (String token : postfix) {
            if (Character.isDigit(token.charAt(0))) {
                evalStack.push(token);
            } else {
                String op2 = evalStack.pop();
                String op1 = evalStack.pop();
                String temp = getTemp();
                quadruples.add(new String[]{token, op1, op2, temp});
                evalStack.push(temp);
            }
        }
        return evalStack.peek();
    }

    static void printQuadruples() {
        System.out.println("Operator\tOperand1\tOperand2\tResult");
        for (String[] q : quadruples) {
            System.out.println(q[0] + "\t\t" + q[1] + "\t\t" + q[2] + "\t\t" + q[3]);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an arithmetic expression: ");
        String expr = scanner.nextLine();
        
        List<String> postfix = infixToPostfix(expr);
        generateQuadruples(postfix);
        printQuadruples();
        
        scanner.close();
    }
}