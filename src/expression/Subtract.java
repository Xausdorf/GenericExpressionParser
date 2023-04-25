package expression;

import expression.exceptions.EvaluateException;

public class Subtract<T extends Comparable<T>> extends BinaryOperation<T> {
    public Subtract(GenericExpression<T> left, GenericExpression<T> right, Evaluator<T> evaluator) {
        super(left, right, evaluator);
    }

    @Override
    protected T realEvaluate(T left, T right) throws EvaluateException {
        return evaluator.subtract(left, right);
    }

    @Override
    public int getPriority() {
        return 1000;
    }
}
