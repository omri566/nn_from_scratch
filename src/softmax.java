import java.util.Arrays;
public class softmax implements activation {

    public softmax() {
        // No initialization needed
    }

    public double[] activate(double[] input) {
        double max = Arrays.stream(input).max().orElse(0.0);
        double sum = 0.0;
        double[] exp = new double[input.length];

        for (int i = 0; i < input.length; i++) {
            exp[i] = Math.exp(input[i] - max); // for numerical stability
            sum += exp[i];
        }

        for (int i = 0; i < input.length; i++) {
            exp[i] /= sum;
        }

        return exp;
    }

    // Not used for softmax + cross-entropy simplification
    @Override
    public double[] derivative(double[] input) {
        // optional: implement only if you're not using softmax + cross-entropy combo
        return new double[input.length]; // placeholder
    }

    

    @Override
    public double derivative(double x) {
        throw new UnsupportedOperationException("Softmax derivative requires full vector.");
    }
}
