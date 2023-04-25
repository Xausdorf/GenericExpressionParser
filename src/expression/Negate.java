package expression;

import expression.exceptions.EvaluateException;

public class Negate<T extends Comparable<T>> extends UnaryOperation<T> {
    public Negate(GenericExpression<T> body, Evaluator<T> evaluator) {
        super(body, evaluator);
    }

    @Override
    protected T realEvaluate(T body) throws EvaluateException {
        return evaluator.negate(body);
    }
}
