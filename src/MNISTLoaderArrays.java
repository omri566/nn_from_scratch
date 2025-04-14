import java.io.*;

public class MNISTLoaderArrays {
    public static double[][] features;  // [numSamples][784]
    public static int[] labels;         // [numSamples]

    public static void loadCSV(String filePath, int numSamples) throws IOException {
        features = new double[numSamples][784];
        labels = new int[numSamples];

        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;
        int rowIndex = 0;
        boolean firstLine = true;

        while ((line = br.readLine()) != null && rowIndex < numSamples) {
            if (firstLine) {
                firstLine = false; // Skip header
                continue;
            }

            String[] values = line.split(",");
            labels[rowIndex] = Integer.parseInt(values[0]);

            for (int i = 1; i < values.length; i++) {
                features[rowIndex][i - 1] = Double.parseDouble(values[i]) / 255.0; // Normalize pixel
            }

            rowIndex++;
        }
        br.close();
    }}