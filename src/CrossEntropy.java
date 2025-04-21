public class CrossEntropy implements loss_func {

    private static final double EPSILON = 1e-15;

    private double safe(double value) {
        return Math.max(Math.min(value, 1 - EPSILON), EPSILON);
    }

    @Override
    public double[] compute_loss(double[] target, double[] output) {
        double[] losses = new double[target.length];
        for (int i = 0; i < target.length; i++) {
            double y_hat = safe(output[i]);
            losses[i] = -target[i] * Math.log(y_hat);
        }
        return losses;
    }

    @Override
    public double[] compute_loss_derivative(double[] target, double[] output) {
        double[] gradient = new double[target.length];
        for (int i = 0; i < target.length; i++) {
            gradient[i] = output[i] - target[i];
        }
        return gradient;
    }

    @Override
    public double compute_loss_derivative(double target, double output) {
        // Not typically used in multi-class, but can support it
        return output - target;
    }
}
