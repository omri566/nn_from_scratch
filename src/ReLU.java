public class ReLU implements activation {

    // Constructor
    public ReLU() {
        // Initialize any necessary parameters for the ReLU activation function
    }


    // ReLU activation function implementation
    @Override
    public double activate(double x) {
        // Implement the activation function
        // For example, using the ReLU activation function
        if (x < 0) {
            return 0;
        } else {
            return x;
        }
    }
}