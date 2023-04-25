package expression;

import expression.exceptions.EvaluateException;

public class IntegerEvaluator implements Evaluator<Integer> {
    @Override
    public Integer add(Integer left, Integer right) throws EvaluateException {
        if (left >= 0) {
            if (right > 0) {
                if (left > Integer.MAX_VALUE - right) {
                    throw new EvaluateException("Integer overflow");
                }
            }
        } else {
            if (right < 0) {
                if (left < Integer.MIN_VALUE - right) {
                    throw new EvaluateException("Integer overflow");
                }
            }
        }
        return left + right;
    }

    @Override
    public Integer subtract(Integer left, Integer right) throws EvaluateException {
        if (left >= 0) {
            if (right < 0) {
                if (left > Integer.MAX_VALUE + right) {
                    throw new EvaluateException("Integer overflow");
                }
            }
        } else {
            if (right > 0) {
                if (left < Integer.MIN_VALUE + right) {
                    throw new EvaluateException("Integer overflow");
                }
            }
        }
        return left - right;
    }

    @Override
    public Integer multiply(Integer left, Integer right) throws EvaluateException {
        if (left > 0) {
            if (right > 0) {
                if (left > Integer.MAX_VALUE / right) {
                    throw new EvaluateException("Integer overflow");
                }
            } else if (right < 0) {
                if (right < Integer.MIN_VALUE / left) {
                    throw new EvaluateException("Integer overflow");
                }
            }
        } else if (left < 0) {
            if (right > 0) {
                if (left < Integer.MIN_VALUE / right) {
                    throw new EvaluateException("Integer overflow");
                }
            } else if (right < 0) {
                if (left < Integer.MAX_VALUE / right) {
                    throw new EvaluateException("Integer overflow");
                }
            }
        }
        return left * right;
    }

    @Override
    public Integer divide(Integer left, Integer right) throws EvaluateException {
        if (right == 0) {
            throw new EvaluateException("Division by zero");
        }
        if (left == Integer.MIN_VALUE && right == -1) {
            throw new EvaluateException("Integer overflow");
        }
        return left / right;
    }

    @Override
    public Integer negate(Integer body) throws EvaluateException {
        if (body == Integer.MIN_VALUE) {
            throw new EvaluateException("Integer overflow");
        }
        return -body;
    }

    @Override
    public Integer mod(Integer left, Integer right) throws EvaluateException {
        if (right == 0) {
            throw new EvaluateException("Division by zero");
        }
        return left % right;
    }

    @Override
    public Integer parse(String s) throws NumberFormatException {
        return Integer.parseInt(s);
    }
}
