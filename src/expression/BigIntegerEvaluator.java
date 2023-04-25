package expression;

import expression.exceptions.EvaluateException;

import java.math.BigInteger;

public class BigIntegerEvaluator implements Evaluator<BigInteger> {

    @Override
    public BigInteger add(BigInteger left, BigInteger right) {
        return left.add(right);
    }

    @Override
    public BigInteger subtract(BigInteger left, BigInteger right) {
        return left.subtract(right);
    }

    @Override
    public BigInteger multiply(BigInteger left, BigInteger right) {
        return left.multiply(right);
    }

    @Override
    public BigInteger divide(BigInteger left, BigInteger right) throws EvaluateException {
        if (right.equals(BigInteger.ZERO)) {
            throw new EvaluateException("Division by zero");
        }
        return left.divide(right);
    }

    @Override
    public BigInteger negate(BigInteger body) {
        return body.negate();
    }

    @Override
    public BigInteger mod(BigInteger left, BigInteger right) throws EvaluateException {
        if (right.compareTo(BigInteger.ZERO) <= 0) {
            throw new EvaluateException("Division by zero");
        }
        return left.mod(right);
    }

    @Override
    public BigInteger parse(String s) throws NumberFormatException {
        return new BigInteger(s);
    }
}
