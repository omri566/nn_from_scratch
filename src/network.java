public class network {

    layer[] layers;
    int numLayers;
    activation activation;
    loss_func loss_func;
    double learning_rate = 0.01;
    activation outputActivation;

    public network(int[] layerSizes, activation activation,activation outputActivation,loss_func loss_func, double learning_rate) {
        // Initialize the network with the given layer sizes, activation function, and loss function
        
        this.activation = activation;
        this.outputActivation = outputActivation;
        this.loss_func = loss_func;
        this.numLayers = layerSizes.length;
        this.layers = new layer[numLayers];
        this.learning_rate = learning_rate;
        
        //check that there are at least 2 layers, other wise the layer initialization will fail
        if (numLayers < 2) {
            throw new IllegalArgumentException("Network must have at least 2 layers.");
        }
        
        // Ensure all layer sizes are greater than 0
        for (int index = 0; index < layerSizes.length; index++) {
            int size = layerSizes[index];
            if (size <= 0) {
                throw new IllegalArgumentException("Invalid layer size at index " + index + ": expected > 0, but found " + size);
            }
        }

        if (learning_rate <= 0 || Double.isNaN(learning_rate) || Double.isInfinite(learning_rate)) {
            throw new IllegalArgumentException("Learning rate must be a positive finite number. Got: " + learning_rate);
        }

        for (int i = 0; i < numLayers; i++) {
            if (i == 0) {
                // Input layer - fixed initialization
                layers[i] = new layer(layerSizes[i], layerSizes[i], layerSizes[i + 1], activation);
            } else if (i == numLayers - 1) {
                // Output layer
                layers[i] = new layer(layerSizes[i], layerSizes[i - 1], layerSizes[i], outputActivation);
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
        for (int i = 0; i < numLayers; i++) { // Initialize deltas to avoid runtime errors due to uninitialized arrays
            deltas[i] = new double[layers[i].getNumNeurons()];
        }
        // Calculate the error
        double[] loss_vector = loss_func.compute_loss(target, layers[numLayers-1].getOutputs());
        double loss = Util.vector_sum(loss_vector);
        // Calculate deltas for output layer
        double[] error_vector = Util.multiply(loss_func.compute_loss_derivative(layers[numLayers-1].getOutputs(), target),
        layers[numLayers-1].getActivationDerivative());
        deltas[numLayers - 1] = error_vector;//indentify the last layer for backpropagation


        //calculate deltas for hidden layers
        for (int i = numLayers - 2; i >=0; i--){
            for(int j = 0; j < layers[i].getNumNeurons(); j++){
                double weightedSum = 0;
                // Calculate the weighted sum of deltas from the next layer
                for(int k = 0; k< layers[i+1].getNumNeurons(); k++){
                    weightedSum += deltas[i+1][k]*layers[i+1].getWeights()[k][j];
                }
                deltas[i][j] = weightedSum * layers[i].getActivationDerivative(j);

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

    public double train_one_batch(double[][] inputs, double[][] targets) {
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
        return batch_loss / inputs.length;
       
    }

    public double train_one_epoch(double[][] inputs, double[][] targets, int batch_size) {
        if (inputs.length != targets.length) {
            throw new IllegalArgumentException("Input and target arrays must have the same length.");
        }

        // Divide the dataset into batches randomly
        int num_batches = inputs.length / batch_size;

        // Create an array of indices and shuffle them
        int[] indices = new int[inputs.length];
        for (int i = 0; i < indices.length; i++) {
            indices[i] = i;
        }
        // Shuffle the indices
        java.util.Random rnd = new java.util.Random();
        for (int i = indices.length - 1; i > 0; i--) {
            int j = rnd.nextInt(i + 1);
            int temp = indices[i];
            indices[i] = indices[j];
            indices[j] = temp;
        }

        double total_epoch_loss = 0;
        double epoch_loss = 0;

        // Process each batch
        for (int batch = 0; batch < num_batches; batch++) {
            // Create the batch
            double[][] batch_inputs = new double[batch_size][];
            double[][] batch_targets = new double[batch_size][];
            for (int i = 0; i < batch_size; i++) {
                int idx = indices[batch * batch_size + i];
                batch_inputs[i] = inputs[idx];
                batch_targets[i] = targets[idx];
            }

            // Train on the batch
            total_epoch_loss += train_one_batch(batch_inputs, batch_targets);
            //NOTE: The loss is averaged over the batch size
            //if we want to have batches in different sizes we'd need to avg the epoch by samples
        }
        epoch_loss = total_epoch_loss / num_batches;
        System.out.println("Epoch loss: " + epoch_loss);
        return epoch_loss;
        }
    }

