
public class App {
    public static void main(String[] args) throws Exception {

        // Load MNIST data
        MNISTLoaderArrays.loadCSV("raw_data/train.csv", 100);
        double[][] features = MNISTLoaderArrays.features;
        int[] labels = MNISTLoaderArrays.labels;
        // Print the first sample
        System.out.println("First sample features: " + java.util.Arrays.toString(features[0]));
        System.out.println("First sample label: " + labels[0]);



        network net = new network(new int[] {features[0].length, 32, 10}, // sizes for input, hidden, output
         new ReLU(), // activation function for input and hidden layers
         new sigmoid(), // activation function for output layer
         new MSE(), // loss function
         0.01); // learning rate

        net.train_one_epoch(features, features, 100); // Train on the first 100 samples
        
    }
}
