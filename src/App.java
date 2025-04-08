import java.util.Arrays;

public class App {
    public static void main(String[] args) throws Exception {

        network net = new network(new int[] {32, 32, 10}, new ReLU(), new sigmoid(), new mse(), 0.01);
        
    }
}
