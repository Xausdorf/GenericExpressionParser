package expression;

import expression.exceptions.EvaluateException;

public class Add<T extends Comparable<T>> extends BinaryOperation<T> {
    public Add(GenericExpression<T> left, GenericExpression<T> right, Evaluator<T> evaluator) {
        super(left, right, evaluator);
    }

    @Override
    protected T realEvaluate(T left, T right) throws EvaluateException {
        return evaluator.add(left, right);
    }

    @Override
    public int getPriority() {
        return 1000;
    }
}
