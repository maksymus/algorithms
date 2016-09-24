package org.saurabhschool;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class Stacks {
    public static void main(String[] args) {
        System.out.println(new EvaluatePostfix().evaluate("1 0 / "));
    }
}

/**
 * Evaluate postfix
 */
class EvaluatePostfix {
    public double evaluate(String postfix) {
        Deque<BigDecimal> stack = new LinkedList<>();
        String[] tokens = postfix.split(" ");
        
        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            if (isNumeric(token)) {
                stack.push(BigDecimal.valueOf(Double.valueOf(token)));
            } else {
                BigDecimal right = stack.pop();
                BigDecimal left = stack.pop();
                switch (token) {
                case "+":
                    stack.push(left.add(right));
                    break;
                case "-":
                    stack.push(left.subtract(right));
                    break;
                case "*":
                    stack.push(left.multiply(right));
                    break;
                case "/":
                    stack.push(left.divide(right));
                    break;
                default:
                    throw new RuntimeException("wrong postfix");
                }
            }
        }
        
        return stack.pop().doubleValue();
    } 
    
    private boolean isNumeric(String s) {  
        return s.matches("[-+]?\\d*\\.?\\d+");  
    } 
} 

/**
 *  Infix to postfix.
 */
class InfixToPostfixSimple {
    
    public String infixToPostfix(String infix) {
        StringBuilder postfix = new StringBuilder();
        Deque<Character> stack = new LinkedList<>();
        
        for (int i = 0; i < infix.length(); i++) {
            char ch = infix.charAt(i);
            
            if (Character.isWhitespace(ch))
                continue;
            
            if (Character.isDigit(ch)) {
                postfix.append(ch + " ");
            } else if (isOperator(ch)) {
                Character top = stack.peek();
                if (top != null && compare(top, ch) > 0) {
                    postfix.append(stack.pop() + " ");
                }
                    
                stack.push(ch);
            }
        }
        
        while (!stack.isEmpty()) {
            postfix.append(stack.pop() + " ");
        }
        
        return postfix.toString();
    } 
    
    private boolean isOperator(char ch) {
        return Arrays.asList('+', '-', '*', '/').contains(ch);
    }
    
    private int compare(char op1, char op2) {
        if (op1 == op2)
            return 0;
        
        if (op1 == '*' || op1 == '/')
            return 1;
                    
        if (op2 == '+' || op2 == '-')
            return -1;
        
        return 0;
    }
}
