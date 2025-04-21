import java.util.Arrays;
public class App {
    public static void main(String[] args) throws Exception {

        // Load MNIST data
        MNISTLoaderArrays.loadCSV("nn_from_scratch/raw_data/train.csv", 10000);
        double[][] features = MNISTLoaderArrays.features;
        int[] labels = MNISTLoaderArrays.labels;
        double[][] one_hot_labels = Util.one_hot(labels, 10);



        System.out.println();
        network net = new network(new int[] {features[0].length, 128,64, 10}, // sizes for input, hidden, output
         new ReLU(), // activation function for input and hidden layers
         new softmax(), // activation function for output layer
         new CrossEntropy(), // loss function
         0.05); // learning rate
        int numEpochs = 10;
        int batchSize = 256;
        for (int epoch = 0; epoch < numEpochs; epoch++) {
            System.out.println("Epoch " + (epoch + 1) + " of " + numEpochs);
            // Train the network on the entire dataset
            net.train_one_epoch(features, one_hot_labels, 32);
        }


        //net.train_one_epoch(features, one_hot_labels, 100); // Train on the first 100 samples
        
    }
}
