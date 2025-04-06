public class layer {
    
    neuron[] neurons;
    int numNeurons;
    int inputSize;
    int outputSize;
    double[] outputs;
    activation activation;

    public layer(int numNeurons, int numinputs, int numOutputs, activation activation) {
        
        // Initialize the layer with the number of neurons, input size, and output size
        this.numNeurons = numNeurons;
        this.inputSize = numinputs;
        this.outputSize = numOutputs;
        this.neurons = new neuron[numNeurons];
        this.outputs = new double[numNeurons];
        this.activation = activation;

        // Initialize the neurons in the layer
        for (int i = 0; i < numNeurons; i++) {
            neurons[i] = new neuron(inputSize, activation);
        }
    }

    private double[] compute(double[] inputs) {
        for (int i = 0; i < numNeurons; i++) {
            outputs[i] = neurons[i].getOutput(inputs);
        }
        return outputs;
    }

    public double[] getOutputs(double[] inputs) {
        compute(inputs);
        return outputs;
    }
    
    public int getNumNeurons() {
        return numNeurons;
    }
}
