public class none_activation implements activation {
    
    // Constructor
    public none_activation() {
        // Initialize any necessary parameters for the none activation function
    }
    
    
    @Override
    public double[] activate(double[] x) {
        // Implement the activation function
        // For example, using the none activation function
        return x;
    }
    public double derivative(double x) {
        return 1.0;
    }
    public double[] derivative(double[] x) {
        // Calculate the derivative for each element in the array
        double[] derivatives = new double[x.length];
        for (int i = 0; i < x.length; i++) {
            derivatives[i] = derivative(x[i]);
        }
        return derivatives;
    }
    
}
