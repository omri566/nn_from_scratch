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
    public double derivative(double sigmoidOutput) {
        // sigmoidOutput is assumed to be σ(x), not the raw x
        return sigmoidOutput * (1 - sigmoidOutput);
    }
    public double[] derivative(double[] sigmoidOutputs) {
        // Calculate the derivative for each element in the array
        double[] derivatives = new double[sigmoidOutputs.length];
        for (int i = 0; i < sigmoidOutputs.length; i++) {
            derivatives[i] = derivative(sigmoidOutputs[i]);
        }
        return derivatives;
    }
}
