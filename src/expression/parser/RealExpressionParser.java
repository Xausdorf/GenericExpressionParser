package expression.parser;

import expression.*;

import java.util.Map;

public class RealExpressionParser<T extends Comparable<T>> extends BaseParser {
    private final Evaluator<T> evaluator;

    private static final Map<String, Integer> PRIORITY_OF_OP = Map.of(
            "+", 1,
            "-", 1,
            "*", 2,
            "/", 2,
            "mod", 2
    ) ;
    
    public RealExpressionParser(String source, Evaluator<T> evaluator) {
        super(new StringSource(source));
        this.evaluator = evaluator;
    }

    public GenericExpression<T> parse() {
        final GenericExpression<T> result = parseExpression();
        if (eof()) {
            return result;
        }
        throw error("End of expression expected");
    }

    private GenericExpression<T> parseExpression() {
        skipWhitespace();
        GenericExpression<T> left = parseElement();
        skipWhitespace();
        if (isEndOfExpr()) {
            return left;
        }
        String operation = takeBinaryOperation();
        skipWhitespace();
        GenericExpression<T> right = parseElement();
        skipWhitespace();
        if (isEndOfExpr()) {
            return makeBinaryOperation(left, right, operation);
        }
        String secondOperation = takeBinaryOperation();
        if (PRIORITY_OF_OP.get(operation) >= PRIORITY_OF_OP.get(secondOperation)) {
            return makeBinaryOperation(makeBinaryOperation(left, right, operation),
                    parseExpression(), secondOperation);
        } else {
            return makeBinaryOperation(left, makeBinaryOperation(
                    right, parseExpression(), secondOperation
            ), operation);
        }

    }
    private GenericExpression<T> parseElement() {
        skipWhitespace();
        if (take('-')) {
            if (between('0', '9')) {
                return takeConst(true);
            } else {
                return new Negate<>(parseElement(), evaluator);
            }
        } else if (take('a')) {
            expect("bs");
            return new Abs<>(parseElement(), evaluator);
        } else if (take('s')) {
            expect("quare");
            return new Square<>(parseElement(), evaluator);
        } else if (take('(')) {
            return parseExpression();
        } else if (between('0', '9')) {
            return takeConst(false);
        } else if (take('x')) {
            return new Variable<>("x");
        } else if (take('y')) {
            return new Variable<>("y");
        } else if (take('z')) {
            return new Variable<>("z");
        } else {
            throw error("Invalid expression");
        }
    }

    private GenericExpression<T> makeBinaryOperation(GenericExpression<T> left,
                                                     GenericExpression<T> right,
                                                     String operation) {
        return switch(operation) {
            case "+" -> new Add<>(left, right, evaluator);
            case "-" -> new Subtract<>(left, right, evaluator);
            case "*" -> new Multiply<>(left, right, evaluator);
            case "/" -> new Divide<>(left, right, evaluator);
            default -> new Mod<>(left, right, evaluator); // guaranteed that op is mod
        };
    }

    private void takeDigits(final StringBuilder sb) {
        while (between('0', '9')) {
            sb.append(take());
        }
    }

    private Const<T> takeConst(boolean isNegate) {
        final StringBuilder sb = new StringBuilder();
        if (take('-') || isNegate) {
            sb.append('-');
        }
        if (take('0')) {
            sb.append('0');
        } else if (between('1', '9')) {
            takeDigits(sb);
        } else {
            throw error("Invalid number");
        }
        try {
            return new Const<>(evaluator.parse(sb.toString()));
        } catch (NumberFormatException e) {
            throw error("Invalid number");
        }
    }

    private String takeBinaryOperation() {
        if (take('+')) {
            return "+";
        } else if (take('-')) {
            return "-";
        } else if (take('*')) {
            return "*";
        } else if (take('/')) {
            return "/";
        } else if (take('m')) {
            expect("od");
            return "mod";
        } else {
            throw error("Invalid binary operation");
        }
    }

    private void skipWhitespace() {
        while (isWhitespace()) {
            take();
        }
    }

    private boolean isEndOfExpr() {
        return eof() || take(')');
    }
}
