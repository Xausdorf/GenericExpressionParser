package expression;

import expression.exceptions.EvaluateException;

public class UncheckedIntegerEvaluator implements Evaluator<Integer> {
    @Override
    public Integer add(Integer left, Integer right) {
        return left + right;
    }

    @Override
    public Integer subtract(Integer left, Integer right) {
        return left - right;
    }

    @Override
    public Integer multiply(Integer left, Integer right) {
        return left * right;
    }

    @Override
    public Integer divide(Integer left, Integer right) throws EvaluateException {
        if (right == 0) {
            throw new EvaluateException("Division by zero");
        }
        return left / right;
    }

    @Override
    public Integer negate(Integer body) {
        return -body;
    }

    @Override
    public Integer mod(Integer left, Integer right) throws EvaluateException {
        if (right == 0) {
            throw new EvaluateException("Division by zero");
        }
        return left % right;
    }

    @Override
    public Integer parse(String s) throws NumberFormatException {
        return Integer.parseInt(s);
    }
}
