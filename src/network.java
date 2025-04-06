public class network {

    layer[] layers;
    int numLayers;
    activation activation;

    public network(int[] layerSizes, activation activation) {
        
        // Initialize the network with the given layer sizes and activation function
        this.activation = activation;
               
        this.numLayers = layerSizes.length;
        this.layers = new layer[numLayers];

        for (int i = 0; i < numLayers; i++) {
            if (i == 0) {
                // Input layer
                layers[i] = new layer(layerSizes[i], layerSizes[i], layerSizes[i + 1]);
            } else if (i == numLayers - 1) {
                // Output layer
                layers[i] = new layer(layerSizes[i], layerSizes[i - 1], layerSizes[i]);
            } else {
                // Hidden layers
                layers[i] = new layer(layerSizes[i], layerSizes[i - 1], layerSizes[i + 1]);
            }
        }
    }

    public double[] getoutput(double[] intputs){
        
        //initialize the outputs
        double[] outputs = null;

        // Loop through each layer and get the outputs
        for (int i = 0 ; i < numLayers; i++){
            if (i==0){ // Input layer - takes inputs directly
                outputs = layers[i].getOutputs(intputs);
            }
            else {
                outputs = layers[i].getOutputs(outputs);
            }
        }



        return outputs;
    }



    public void train(double[] input, double[] target){


        //feed forward
        double[][]  layers_inputs = new double[numLayers+1][];
        double[] outputs = input;

        for (int i = 0 ; i < numLayers; i++){
                outputs = layers[i].getOutputs(outputs);
                layers_inputs[i+1] = outputs;
            }
        

        //calculate the error
        double[] error = new double[layers[numLayers-1].getNumNeurons()];
        for (int i = 0; i <layers[numLayers-1].numNeurons; i++){
            error[i] = target[i] - layers_inputs[numLayers+1][i]   ;
        }






        //backpropagation





    }
}
