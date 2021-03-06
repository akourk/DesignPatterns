// In computer programming, the interpreter pattern is a design pattern
// that specifies how to evaluate sentences in a language. The basic idea
// is to have a class for each symbol (terminal or nonterminal) in a
// specialized computer language. The syntax tree of a sentence in
// the language is an instance of the composite pattern and is used
// to evaluate (interpret) the sentence for a client. See also
// Composite pattern.

import java.util.Map;
import java.util.Stack;
import java.util.HashMap;


interface Expression {
    public int interpret(final Map<String, Expression> variables);
}

class Number implements Expression {
    private int number;
    public Number(final int number) {
        this.number = number;
    }
    public int interpret(final Map<String, Expression> variables) {
        return number;
    }
}

class Plus implements Expression {
    Expression leftOperand;
    Expression rightOperand;
    public Plus(final Expression left, final Expression right) {
        leftOperand = left;
        rightOperand = right;
    }

    public int interpret(final Map<String, Expression> variables) {
        return leftOperand.interpret(variables) + rightOperand.interpret(variables);
    }
}

class Minus implements Expression {
    Expression leftOperand;
    Expression rightOperand;
    public Minus(final Expression left, final Expression right) {
        leftOperand = left;
        rightOperand = right;
    }

    public int interpret(final Map<String, Expression> variables) {
        return leftOperand.interpret(variables) - rightOperand.interpret(variables);
    }
}

class Variable implements Expression {
    private String name;
    public Variable(final String name) {
        this.name = name;
    }
    public int interpret(final Map<String, Expression> variables) {
        if (null == variables.get(name)) return 0; // Either return new Number(0).
        return variables.get(name).interpret(variables);
    }
}

// While the interpreter pattern does not address parsing, a parser is provided
// for completeness

class Evaluator implements Expression {
    private Expression syntaxTree;

    public Evaluator(final String expression) {
        final Stack<Expression> expressionStack = new Stack<Expression>();
        for (final String token : expression.split(" ")) {
            if (token.equals("+")) {
                final Expression subExpression = new Plus(expressionStack.pop(), expressionStack.pop());
                expressionStack.push(subExpression);
            } else if (token.equals("-")) {
                // it's necessary to remove first the right operand from the stack

                final Expression right = expressionStack.pop();
                // ... and then the left one
                final Expression left = expressionStack.pop();
                final Expression subExpression = new Minus(left, right);
                expressionStack.push(subExpression);
            } else {
                expressionStack.push(new Variable(token));
            }
        }
        syntaxTree = expressionStack.pop();
    }

    public int interpret(final Map<String, Expression> context) {
        return syntaxTree.interpret(context);
    }
}


// Finally evaluating the expression "w x z - +" with w = 5, x = 10, and z = 42

// InterpreterExample
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        final String expression = "w x z - +";
        final Evaluator sentence = new Evaluator(expression);
        final Map<String, Expression> variables = new HashMap<String, Expression>();
        variables.put("w", new Number(5));
        variables.put("x", new Number(10));
        variables.put("z", new Number(42));
        final int result = sentence.interpret(variables);
        System.out.println(result);
    }
}