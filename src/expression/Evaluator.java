package expression;

import expression.exceptions.EvaluateException;

public interface Evaluator<T extends Comparable<T>> {
    T add(T left, T right) throws EvaluateException;

    T subtract(T left, T right) throws EvaluateException;

    T multiply(T left, T right) throws EvaluateException;

    T divide(T left, T right) throws EvaluateException;

    T negate(T body) throws EvaluateException;

    default T abs(T body) throws EvaluateException {
        if (body.compareTo(parse("0")) >= 0) {
            return body;
        } else {
            return negate(body);
        }
    }

    default T square(T body) throws EvaluateException {
        return multiply(body, body);
    }

    T mod(T left, T right) throws EvaluateException;

    T parse(String s) throws NumberFormatException;
}
