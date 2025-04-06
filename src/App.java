import java.util.Arrays;

public class App {
    public static void main(String[] args) throws Exception {

        // Create a neuron with 3 predecessors
        activation sigmoid = new sigmoid();
        neuron n = new neuron(3, sigmoid);
        
        // Create an array of inputs
        double[] inputs = {0.5, 0.2, 0.8};
        
        // Get the output of the neuron
        double output = n.getOutput(inputs);
        
        // Print the output
        System.out.println("Output: " + output);

        layer l = new layer(3, 3, 3, sigmoid);
        double[] layerInputs = {0.5, 0.2, 0.8};
        double[] layerOutputs = l.getOutputs(layerInputs);
        System.out.println("Layer Outputs: "+ Arrays.toString(layerOutputs));

        // Create a network with 3 layers
        int[] layerSizes = {3, 3, 3};
        network net = new network(layerSizes, sigmoid);
        double[] networkInputs = {0.5, 0.2, 0.8};
        double[] networkOutputs = net.getoutput(networkInputs);
        System.out.println("Network Outputs: "+ Arrays.toString(networkOutputs));


    }
}
