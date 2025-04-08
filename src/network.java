public class network {

    layer[] layers;
    int numLayers;
    activation activation;
    loss_func loss_func;
    double learning_rate = 0.01;

    public network(int[] layerSizes, activation activation,activation outpuActivation,loss_func loss_func, double learning_rate) {
        // Initialize the network with the given layer sizes, activation function, and loss function
        
        this.activation = activation;
        this.loss_func = loss_func;
        this.numLayers = layerSizes.length;
        this.layers = new layer[numLayers];

        for (int i = 0; i < numLayers; i++) {
            if (i == 0) {
                // Input layer - fixed initialization
                layers[i] = new layer(layerSizes[i], layerSizes[i], layerSizes[i + 1], activation);
            } else if (i == numLayers - 1) {
                // Output layer
                layers[i] = new layer(layerSizes[i], layerSizes[i - 1], layerSizes[i], outpuActivation);
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

    public double update_nudges(double[] input, double[] target){
        
        // Check if the input and target sizes match the network's input and output sizes
        if (input.length != layers[0].getNumNeurons()) {
            throw new IllegalArgumentException("Input size does not match the network's input size.");
        }
        if (target.length != layers[numLayers - 1].getNumNeurons()) {
            throw new IllegalArgumentException("Target size does not match the network's output size.");
        }
        // Feed forward
        double[][] layers_inputs = new double[numLayers + 1][];
        layers_inputs[0] = input; // Fixed initialization of input layer
        double[] outputs = input;
        for(int i = 0 ; i < numLayers; i++) { // Fixed loop condition
            outputs = layers[i].getOutputs(outputs);
            layers_inputs[i + 1] = outputs;
        }

        double[][] deltas = new double[numLayers][];

        // Calculate the error
        double[] loss_vector = loss_func.compute_loss(target, layers[numLayers].getOutputs());
        double loss = Util.vector_mean(loss_vector);
        // Calculate deltas for output layer
        double[] error_vector = Util.multiply(loss_func.compute_loss_derivative(layers[numLayers].getOutputs(), target),
        activation.derivative(layers[numLayers].getPreActivations()));
        


        //calculate deltas for hidden layers
        for (int i = numLayers - 2; i >=0; i--){
            for(int j = 0; j < layers[i].getNumNeurons(); j++){
                double weightedSum = 0;
                // Calculate the weighted sum of deltas from the next layer
                for(int k = 0; k< layers[i+1].getNumNeurons(); k++){
                    weightedSum += deltas[i+1][k]*layers[i+1].getWeights()[k][j];
                }
                deltas[i][j] = weightedSum * activation.derivative(layers[i].getPreActivations()[j]);

            }
        }


        //update weights and biases nudges
        for (int i = 0; i <= numLayers-1; i++) {
            layers[i].addBiasesNudge(deltas[i]);
            for(int j = 0; j < layers[i].getNumNeurons(); j++){
                for(int k = 0; k < layers[i].getWeights()[j].length; k++){
                    layers[i].getWeightUpdates()[j][k] += deltas[i][j] * layers_inputs[i][k];
                }
            }


        }
        return loss;
    }

    public void train_one_batch(double[][] inputs, double[][] targets) {
        if (inputs.length != targets.length) {
            throw new IllegalArgumentException("Input and target arrays must have the same length.");
        
        }
        double batch_loss = 0;
        for(int i = 0; i < inputs.length; i++) {
           batch_loss += update_nudges(inputs[i], targets[i]);
       }
        for(int i = 0; i < numLayers; i++) {
            layers[i].updateParams(learning_rate, inputs.length);
        }
       
    }

    public void train_one_epoch(double[][] inputs, double[][] targets, int batch_size) {
        if (inputs.length != targets.length) {
            throw new IllegalArgumentException("Input and target arrays must have the same length.");
        }
        //divide the dataset into batches randomly
        int num_batches = inputs.length / batch_size;
        // Randomly shuffle the dataset
        //for batch in batches run train_one_batch
        //print the loss of the mean of all batches losses

        // run over the entire dataset in batches
        

    }









    
  
}
