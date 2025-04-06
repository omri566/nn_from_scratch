public class neuron {
    
    //each neuron has the weights of the neurons from the previous layer
    double bias;
    double[] weights;
    double output;
    activation activation;

    public neuron(int predecessors) {

        // Initialize the bias and weights with random values
        // The number of weights is equal to the number of predecessors

        this.bias = Math.random();
        this.weights = new double[predecessors];
        for (int i = 0; i < predecessors; i++) {
            this.weights[i] = Math.random();
        }
        this.activation = new sigmoid();
    }

    private double compute(double[] inputs) {

        output = 0;
        for(int i = 0 ; i < inputs.length; i++){
            output += inputs[i] * weights[i];
        }
        output += bias;
        output = activation.activate(output);
        return output;
    }
    
    public double getOutput(double[] inputs) {
        compute(inputs);
        return output;
    }


}
