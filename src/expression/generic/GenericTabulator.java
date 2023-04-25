package expression.generic;

import expression.*;
import expression.exceptions.EvaluateException;
import expression.parser.RealExpressionParser;

import java.util.Map;

public class GenericTabulator implements Tabulator {
    private static final Map<String, Evaluator<?>> EVALUATOR_BY_MODE = Map.of(
            "i", new IntegerEvaluator(),
            "d", new DoubleEvaluator(),
            "bi", new BigIntegerEvaluator(),
            "u", new UncheckedIntegerEvaluator(),
            "p", new ModuloEvaluator(),
            "s", new ShortEvaluator()
    );

    @Override
    public Object[][][] tabulate(String mode, String expression,
                                 int x1, int x2, int y1, int y2, int z1, int z2)
            throws IllegalArgumentException {
        Evaluator<?> evaluator;
        if (EVALUATOR_BY_MODE.containsKey(mode)) {
            evaluator = EVALUATOR_BY_MODE.get(mode);
        } else {
            throw new IllegalArgumentException("Invalid mode");
        }
        return tabulateImpl(evaluator, expression, x1, x2, y1, y2, z1, z2);
    }

    private <T extends Comparable<T>> Object[][][] tabulateImpl(
            Evaluator<T> evaluator, String expression, int x1, int x2,
            int y1, int y2, int z1, int z2) {
        RealExpressionParser<T> parser = new RealExpressionParser<>(expression, evaluator);
        GenericExpression<T> parsedExpression = parser.parse();
        Object[][][] result = new Object[x2 - x1 + 1][y2 - y1 + 1][z2 - z1 + 1];
        for (int x = x1; x <= x2; x++) {
            for (int y = y1; y <= y2; y++) {
                for (int z = z1; z <= z2; z++) {
                    try {
                        result[x - x1][y - y1][z - z1] = parsedExpression.evaluate(
                                evaluator.parse(Integer.toString(x)),
                                evaluator.parse(Integer.toString(y)),
                                evaluator.parse(Integer.toString(z))
                        );
                    } catch (EvaluateException e) {
                        result[x - x1][y - y1][z - z1] = null;
                    }
                }
            }
        }
        return result;
    }
}
