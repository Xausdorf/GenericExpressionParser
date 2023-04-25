package expression;

import expression.exceptions.EvaluateException;

public class ShortEvaluator implements Evaluator<Short> {

    @Override
    public Short add(Short left, Short right) {
        return (short)(left + right);
    }

    @Override
    public Short subtract(Short left, Short right) {
        return (short)(left - right);
    }

    @Override
    public Short multiply(Short left, Short right) {
        return (short)(left * right);
    }

    @Override
    public Short divide(Short left, Short right) throws EvaluateException {
        if (right == 0) {
            throw new EvaluateException("Division by zero");
        }
        return (short)(left / right);
    }

    @Override
    public Short negate(Short body) {
        return (short)(-body);
    }

    @Override
    public Short mod(Short left, Short right) throws EvaluateException {
        if (right == 0) {
            throw new EvaluateException("Division by zero");
        }
        return (short)(left % right);
    }

    @Override
    public Short parse(String s) throws NumberFormatException {
        return (short)Integer.parseInt(s);
    }
}
