public class QuickTest {
    public static void main(String[] args) throws Exception {
        // Small smoke test: train briefly and verify loss decreases
        MNISTLoaderArrays.loadCSV("raw_data/train.csv", 500);
        double[][] features = MNISTLoaderArrays.features;
        int[] labels = MNISTLoaderArrays.labels;
        double[][] one_hot = Util.one_hot(labels, 10);

        network net = new network(new int[] {features[0].length, 64, 10}, new ReLU(), new softmax(), new CrossEntropy(), 0.05);
        double loss1 = net.train_one_epoch(features, one_hot, 100);
        double loss2 = net.train_one_epoch(features, one_hot, 100);

        if (loss2 >= loss1) {
            System.err.println("Test failed: loss did not decrease: " + loss1 + " -> " + loss2);
            System.exit(1);
        } else {
            System.out.println("Test passed: loss decreased: " + loss1 + " -> " + loss2);
            System.exit(0);
        }
    }
}
