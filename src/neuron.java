public class neuron {
    
    //each neuron has the weights of the neurons from the previous layer
    double bias;
    double[] weights;
    double output;
    double Z; //the linear combination of the inputs and weights
    activation activation;

    public neuron(int input_size,activation activation) {

        // Initialize the bias and weights with random values
        // The number of weights is equal to the number of predecessors

        this.bias = Math.random();
        this.weights = new double[input_size];
        for (int i = 0; i < input_size; i++) {
            this.weights[i] = Math.random();
        }
        this.activation = activation;
    }

    private double compute(double[] inputs) {

        output = 0;
        for(int i = 0 ; i < inputs.length; i++){
            Z += inputs[i] * weights[i];
        }
        Z += bias;
        output = activation.activate(output);
        return output;
    }
    
    public double getOutput(double[] inputs) {
        compute(inputs);
        return output;
    }

    public double get_Z() {
        return Z;
    }

    public void update_bias(double nudge) {
        this.bias -= nudge;
    }
    public void update_weights(double nudge, int index) {
        this.weights[index] -= nudge;
    }


}
