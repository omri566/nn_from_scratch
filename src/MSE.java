public class MSE {
    // Constructor
    public MSE() {
        // Initialize any necessary parameters for the MSE loss function
    }

    // Compute the Mean Squared Error (MSE) loss
    public double compute_loss(double[] target, double[] output) {
        double sum = 0.0;
        for (int i = 0; i < target.length; i++) {
            double diff = target[i] - output[i];
            sum += diff * diff;
        }
        return sum / target.length;
    }

    // Compute the derivative of the MSE loss with respect to the output
    public double[] compute_loss_derivative(double[] target, double[] output) {
        double[] derivative = new double[target.length];
        for (int i = 0; i < target.length; i++) {
            derivative[i] = 2 * (output[i] - target[i]) / target.length;
        }
        return derivative;
    }

    // Compute the derivative of the MSE loss with respect to a single output
    public double compute_loss_derivative(double target, double output) {
        return 2 * (output - target);
    }
}
