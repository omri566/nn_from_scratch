public interface loss_func {
    
    public double[] compute_loss(double[] target, double[] output);
    public double[] compute_loss_derivative(double[] target, double[] output);
    public double compute_loss_derivative(double target, double output);
}
