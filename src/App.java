public class App {
    public static void main(String[] args) throws Exception {

        // Create a neuron with 3 predecessors
        neuron n = new neuron(3);
        
        // Create an array of inputs
        double[] inputs = {0.5, 0.2, 0.8};
        
        // Get the output of the neuron
        double output = n.getOutput(inputs);
        
        // Print the output
        System.out.println("Output: " + output);



    }
}
