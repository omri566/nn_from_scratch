import java.util.Arrays;

public class App {
    public static void main(String[] args) throws Exception {

        network net = new network(new int[] {32, 32, 10}, // sizes for input, hidden, output
         new ReLU(), // activation function for input and hidden layers
         new sigmoid(), // activation function for output layer
         new MSE(), // loss function
         0.01); // learning rate
        
    }
}
