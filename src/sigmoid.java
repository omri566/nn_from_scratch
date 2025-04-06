public class sigmoid implements activation {
    @Override
    public double activate(double x) {
        // Implement the activation function
        // For example, using the sigmoid activation function
        return 1 / (1 + Math.exp(-x));
    }
    
}
