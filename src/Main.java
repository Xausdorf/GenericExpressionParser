import expression.generic.GenericTabulator;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("The number of arguments should be two");
            return;
        }
        Object[][][] result;
        try {
            result = (new GenericTabulator()).tabulate(args[0].substring(1),
                    args[1], -2, 2, -2, 2, -2, 2);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    System.out.println((i - 2) + ", " + (j - 2) + ", " + (k - 2)
                            + ": " + result[i][j][k]);
                }
            }
        }
    }
}
