package expression;

import expression.exceptions.EvaluateException;

public class Divide<T extends Comparable<T>> extends BinaryOperation<T> {
    public Divide(GenericExpression<T> left, GenericExpression<T> right, Evaluator<T> evaluator) {
        super(left, right, evaluator);
    }

    @Override
    protected T realEvaluate(T left, T right) throws EvaluateException {
        return evaluator.divide(left, right);
    }

    @Override
    public int getPriority() {
        return 2000;
    }
}
