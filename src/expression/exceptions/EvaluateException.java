package expression.exceptions;

public class EvaluateException extends Exception {
    public EvaluateException(String message) {
        super("Evaluate error\n" + message + '\n');
    }
}
