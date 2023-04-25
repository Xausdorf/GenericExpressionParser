package expression;

import expression.exceptions.EvaluateException;

public abstract class UnaryOperation<T extends Comparable<T>> implements GenericExpression<T> {
    private final GenericExpression<T> body;
    protected final Evaluator<T> evaluator;

    protected UnaryOperation(GenericExpression<T> body, Evaluator<T> evaluator) {
        this.body = body;
        this.evaluator = evaluator;
    }

    @Override
    public T evaluate(T x, T y, T z) throws EvaluateException {
        return realEvaluate(body.evaluate(x, y, z));
    }

    protected abstract T realEvaluate(T body) throws EvaluateException;
}
