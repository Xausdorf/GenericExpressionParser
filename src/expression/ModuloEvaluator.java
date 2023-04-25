package expression;

import expression.exceptions.EvaluateException;

public class ModuloEvaluator implements Evaluator<Integer> {
    private final int MODULUS = 10079;

    @Override
    public Integer add(Integer left, Integer right) {
        return ((left + right) % MODULUS + MODULUS) % MODULUS;
    }

    @Override
    public Integer subtract(Integer left, Integer right) {
        return ((left - right) % MODULUS + MODULUS) % MODULUS;
    }

    @Override
    public Integer multiply(Integer left, Integer right) {
        return ((left * right) % MODULUS + MODULUS) % MODULUS;
    }

    @Override
    public Integer divide(Integer left, Integer right) throws EvaluateException {
        if (right == 0) {
            throw new EvaluateException("Division by zero");
        }
        return left * power(right, MODULUS - 2) % MODULUS;
    }

    private Integer power(Integer left, Integer right) {
        if (right == 0) {
            return 1;
        }
        if (right % 2 == 0) {
            return power(left * left % MODULUS, right / 2);
        } else {
            return left * power(left, right - 1) % MODULUS;
        }
    }

    @Override
    public Integer negate(Integer body) {
        return (MODULUS - body) % MODULUS;
    }

    @Override
    public Integer mod(Integer left, Integer right) throws EvaluateException {
        if (right == 0) {
            throw new EvaluateException("Division by zero");
        }
        return ((left % right) % MODULUS + MODULUS) % MODULUS;
    }

    @Override
    public Integer parse(String s) throws NumberFormatException {
        return (Integer.parseInt(s) % MODULUS + MODULUS) % MODULUS;
    }
}
