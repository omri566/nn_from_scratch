
public class layer {
    
    //fields
    double[][] weights;
    double[][] weightUpdates;
    double[] biases;
    double[] biasUpdates;
    int numNeurons;
    int inputSize;
    int outputSize;
    double[] postActivations;
    activation activation;
    double[] preActivations; // used in backpropagation
    double learning_rate;

    public layer(int numNeurons, int numinputs, int numOutputs, activation activation) {
        
        // Initialize the layer with the number of neurons, input size, and output size
        this.numNeurons = numNeurons;
        this.inputSize = numinputs;
        this.outputSize = numOutputs;
        this.postActivations = new double[numNeurons];
        this.preActivations = new double[numNeurons];
        this.activation = activation;
        
        // Initialize the matrices in the layer
        this.weights = new double[numNeurons][numinputs];
        this.weightUpdates = new double[numNeurons][numinputs];
        for (int i = 0; i < numNeurons; i++) {
            for (int j = 0; j < numinputs; j++) {
                weights[i][j] = (Math.random() - 0.5) * 2.0 * Math.sqrt(2.0 / numinputs);
                this.weightUpdates[i][j] = 0; // Initialize weight updates to zero
            }
        }
        // Initialize the biases and bias updates
        this.biases = new double[numNeurons];
        this.biasUpdates = new double[numNeurons];
        for (int i = 0; i < numNeurons; i++) {
            this.biases[i] = Math.random(); // Initialize biases randomly
            this.biasUpdates[i] = 0; // Initialize bias updates to zero
        }
    }

    private double[] compute(double[] inputs) {
        double[] output = new double[numNeurons];
        output = Util.dotProduct(inputs, weights);
        output = Util.add(output, biases);
        preActivations = output;
        // Apply the activation function
        output = Util.activate(activation, output);
        postActivations = output;
        return output;
    }
    

    public double[] getOutputs(double[] inputs) {
        compute(inputs);
        return postActivations;
    }
    public double[] getOutputs() {
        return postActivations;
    }
    public double[] getPreActivations() {
        return preActivations;
    }
    
    public int getNumNeurons() {
        return numNeurons;
    }
    public double[][] getWeights() {
        return weights;
    }   
    public double[][] getWeightUpdates() {
        return weightUpdates;
    }
    public double[] getActivationDerivative() {
        return activation.derivative(preActivations);
    }
    public double getActivationDerivative(int j){
        return activation.derivative(preActivations[j]);
    }
    public void addBiasesNudge(double[] nudge) {
        if (nudge.length != biases.length) {
            throw new IllegalArgumentException("Nudge size must match the number of neurons.");
        }
        for (int i = 0; i < biases.length; i++) {
            biases[i] += nudge[i];
        }
    }
    public void updateParams(double leanrning_rate, int batch_size) {
        for (int i = 0; i < numNeurons; i++) {
            for (int j = 0; j < inputSize; j++) {
                weights[i][j] -= (learning_rate * weightUpdates[i][j])/batch_size;
                weightUpdates[i][j] = 0;
            }
            biases[i] -= (learning_rate * biasUpdates[i])/batch_size;
            biasUpdates[i] = 0;
        }
    }
   




}
