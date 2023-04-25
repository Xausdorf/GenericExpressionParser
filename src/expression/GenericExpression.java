package expression;

import expression.exceptions.EvaluateException;

public interface GenericExpression<T> {
    T evaluate(T x, T y, T z) throws EvaluateException;

    default int getPriority() {
        return Integer.MAX_VALUE;
    }
}
