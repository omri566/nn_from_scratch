public class network {

    layer[] layers;
    int numLayers;
    activation activation;
    loss_func loss_func;
    double learning_rate = 0.01;

    public network(int[] layerSizes, activation activation, loss_func loss_func, double learning_rate) {
        // Initialize the network with the given layer sizes, activation function, and loss function
        
        this.activation = activation;
               
        this.numLayers = layerSizes.length;
        this.layers = new layer[numLayers];

        for (int i = 0; i < numLayers; i++) {
            if (i == 0) {
                // Input layer - fixed initialization
                layers[i] = new layer(layerSizes[i], layerSizes[i], layerSizes[i + 1], activation);
            } else if (i == numLayers - 1) {
                // Output layer
                layers[i] = new layer(layerSizes[i], layerSizes[i - 1], layerSizes[i], activation);
            } else {
                // Hidden layers
                layers[i] = new layer(layerSizes[i], layerSizes[i - 1], layerSizes[i + 1], activation);
            }
        }
    }

    public double[] getoutput(double[] inputs){
        
        //initialize the outputs
        double[] outputs = null;

        // Loop through each layer and get the outputs
        for (int i = 0 ; i < numLayers; i++){
            if (i==0){ // Input layer - takes inputs directly
                outputs = layers[i].getOutputs(inputs);
            }
            else {
                outputs = layers[i].getOutputs(outputs);
            }
        }

        return outputs;
    }

    public void train(double[] input, double[] target) {
        // Feed forward
        double[][] layers_inputs = new double[numLayers + 1][];
        layers_inputs[0] = input; // Fixed initialization of input layer
        double[] outputs = input;

        for (int i = 0; i < numLayers; i++) { // Fixed loop condition
            outputs = layers[i].getOutputs(outputs);
            layers_inputs[i + 1] = outputs;
        }

        // Calculate the error
        double[] error_vector = new double[layers[numLayers - 1].getNumNeurons()];
        for (int i = 0; i < layers[numLayers - 1].numNeurons; i++) {
            error_vector[i] = loss_func.compute_loss_derivative(target[i], layers_inputs[numLayers][i]); // Clarified index
        }

        // Calculate deltas
        double[][] deltas = new double[numLayers][];
        for (int i = numLayers - 1; i >= 0; i--) {
            deltas[i] = new double[layers[i].getNumNeurons()];
            for (int j = 0; j < layers[i].getNumNeurons(); j++) {
                if (i == numLayers - 1) {
                    deltas[i][j] = error_vector[j] * activation.derivative(layers[i].neurons[j].get_Z());
                } else {
                    double weightedSum = 0;
                    for (int k = 0; k < layers[i + 1].getNumNeurons(); k++) {
                        weightedSum += deltas[i + 1][k] * layers[i + 1].neurons[k].weights[j];
                    }
                    deltas[i][j] = weightedSum * activation.derivative(layers[i].neurons[j].get_Z());
                }
            }
        }

        // Backpropagation
        for (int i = numLayers - 1; i >= 0; i--) {
            for (int j = 0; j < layers[i].getNumNeurons(); j++) {
                for (int k = 0; k < layers[i].neurons[j].weights.length; k++) {
                    layers[i].neurons[j].weights[k] -= learning_rate * deltas[i][j] * layers_inputs[i][k];
                }
                layers[i].neurons[j].update_bias(learning_rate * deltas[i][j]); 
            }
        }
    }
}
