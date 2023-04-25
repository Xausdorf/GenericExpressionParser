package expression;

import expression.exceptions.EvaluateException;

public class Abs<T extends Comparable<T>> extends UnaryOperation<T> {
    public Abs(GenericExpression<T> body, Evaluator<T> evaluator) {
        super(body, evaluator);
    }

    @Override
    protected T realEvaluate(T body) throws EvaluateException {
        return evaluator.abs(body);
    }
}
