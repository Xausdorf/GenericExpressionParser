package expression;

import expression.exceptions.EvaluateException;

public class Multiply<T extends Comparable<T>> extends BinaryOperation<T> {
    public Multiply(GenericExpression<T> left, GenericExpression<T> right, Evaluator<T> evaluator) {
        super(left, right, evaluator);
    }

    @Override
    protected T realEvaluate(T left, T right) throws EvaluateException {
        return evaluator.multiply(left, right);
    }

    @Override
    public int getPriority() {
        return 2000;
    }
}
