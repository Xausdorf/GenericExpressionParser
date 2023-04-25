package expression;

import expression.exceptions.EvaluateException;

public class DoubleEvaluator implements Evaluator<Double> {
    @Override
    public Double add(Double left, Double right) {
        return left + right;
    }

    @Override
    public Double subtract(Double left, Double right) {
        return left - right;
    }

    @Override
    public Double multiply(Double left, Double right) {
        return left * right;
    }

    @Override
    public Double divide(Double left, Double right) {
        return left / right;
    }

    @Override
    public Double negate(Double body) {
        return -body;
    }

    @Override
    public Double mod(Double left, Double right) {
        return left % right;
    }

    @Override
    public Double parse(String s) throws NumberFormatException {
        return Double.parseDouble(s);
    }
}
