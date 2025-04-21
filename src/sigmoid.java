public class sigmoid implements activation {
    
    // Constructor
    public sigmoid() {
        // Initialize any necessary parameters for the sigmoid activation function
    }
    
    
    @Override
    public double[] activate(double[] x) {
        double[] result = new double[x.length];
        for (int i = 0; i < x.length; i++) {
            result[i] = 1.0 / (1.0 + Math.exp(-x[i]));
        }
        return result;
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

