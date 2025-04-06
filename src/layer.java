public class layer {
    
    neuron[] neurons;
    int numNeurons;
    int numPredecessors;
    int numOutputs;
    double[] outputs;

    public layer(int numNeurons, int numinputs, int numOutputs){
        this.numNeurons = numNeurons;
        this.numPredecessors = numinputs;
        this.numOutputs = numOutputs;
        this.neurons = new neuron[numNeurons];
        this.outputs = new double[numNeurons];
        
        // Initialize the neurons in the layer
        for (int i = 0; i < numNeurons; i++) {
            neurons[i] = new neuron(numPredecessors);
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
