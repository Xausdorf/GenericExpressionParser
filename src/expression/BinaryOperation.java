package expression;

import expression.exceptions.EvaluateException;

import java.util.Objects;

public abstract class BinaryOperation<T extends Comparable<T>> implements GenericExpression<T> {
    private final GenericExpression<T> left;
    private final GenericExpression<T> right;
    protected final Evaluator<T> evaluator;

    protected BinaryOperation(GenericExpression<T> left, GenericExpression<T> right, Evaluator<T> evaluator) {
        this.left = left;
        this.right = right;
        this.evaluator = evaluator;
    }

    @Override
    public T evaluate(T x, T y, T z) throws EvaluateException {
        return realEvaluate(left.evaluate(x, y, z), right.evaluate(x, y, z));
    }

    protected abstract T realEvaluate(T left, T right) throws EvaluateException;
}
