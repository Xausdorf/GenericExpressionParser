package expression;

public class Variable<T> implements GenericExpression<T> {
    private final String value;

    public Variable(String value) {
        if (value == null || !value.equals("x") &&
                !value.equals("y") && !value.equals("z")) {
            throw new IllegalArgumentException("variable is not 'x', 'y' or 'z'");
        } else {
            this.value = value;
        }
    }

    @Override
    public T evaluate(T x, T y, T z) {
        return switch (value) {
            case "x" -> x;
            case "y" -> y;
            default -> z; // guaranteed that value is "z"
        };
    }
}
