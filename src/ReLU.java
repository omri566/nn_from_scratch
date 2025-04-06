public class ReLU implements activation {

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