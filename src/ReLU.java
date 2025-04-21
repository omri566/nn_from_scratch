public class ReLU implements activation {

    // Constructor
    public ReLU() {
        // Initialize any necessary parameters for the ReLU activation function
    }


    // ReLU activation function implementation
    private double activate(double x) {
        // Implement the activation xwfunction
        // For example, using the ReLU activation function
        return Math.max(0, x);
    }
    public double[] activate(double[] x) {
        // Implement the activation function
        // For example, using the ReLU activation function
        double[] activated = new double[x.length];
        for (int i = 0; i < x.length; i++) {
            activated[i] = activate(x[i]);
        }
        return activated;
        
    }

    public double derivative(double x) {
        if (x > 0) {
            return 1.0;
        } else {
            return 0.0;
        }
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