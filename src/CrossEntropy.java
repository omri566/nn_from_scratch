public class CrossEntropy implements loss_func {

    private static final double EPSILON = 1e-15;

    // Ensure output is in [0, 1], clamp to avoid log/div zero
    private double safeOutput(double value) {
        if (value < 0.0 || value > 1.0) {
            throw new IllegalArgumentException(
                "Output value must be in the range [0, 1]. Got: " + value
            );
        }
        return Math.max(Math.min(value, 1 - EPSILON), EPSILON);
    }

    // Ensure target is binary (0 or 1)
    private void validateTarget(double value) {
        if (value != 0.0 && value != 1.0) {
            throw new IllegalArgumentException(
                "Target value must be 0 or 1. Got: " + value
            );
        }
    }

    @Override
    public double[] compute_loss(double[] target, double[] output) {
        double[] losses = new double[target.length];
        for (int i = 0; i < target.length; i++) {
            validateTarget(target[i]);
            double y_hat = safeOutput(output[i]);
            losses[i] = -target[i] * Math.log(y_hat) - (1 - target[i]) * Math.log(1 - y_hat);
        }
        return losses;
    }

    @Override
    public double compute_loss_derivative(double target, double output) {
        validateTarget(target);
        double y_hat = safeOutput(output);
        return (y_hat - target) / (y_hat * (1 - y_hat));
    }

    @Override
    public double[] compute_loss_derivative(double[] target, double[] output) {
        double[] derivatives = new double[target.length];
        for (int i = 0; i < target.length; i++) {
            derivatives[i] = compute_loss_derivative(target[i], output[i]);
        }
        return derivatives;
    }
}
