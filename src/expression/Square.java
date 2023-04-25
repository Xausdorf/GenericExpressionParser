package expression;

import expression.exceptions.EvaluateException;

public class Square<T extends Comparable<T>> extends UnaryOperation<T> {
    public Square(GenericExpression<T> body, Evaluator<T> evaluator) {
        super(body, evaluator);
    }

    @Override
    protected T realEvaluate(T body) throws EvaluateException {
        return evaluator.square(body);
    }
}
