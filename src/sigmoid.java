public class sigmoid implements activation {
    
    // Constructor
    public sigmoid() {
        // Initialize any necessary parameters for the sigmoid activation function
    }
    
    
    @Override
    public double activate(double x) {
        // Implement the activation function
        // For example, using the sigmoid activation function
        return 1 / (1 + Math.exp(-x));
    }
    
}
