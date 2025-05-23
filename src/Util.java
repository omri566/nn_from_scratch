public class Util {
    

    public static final double EPSILON = 1e-12;


    public static double[] dotProduct(double[] vector , double[][] matrix) {
        
        if (matrix[0].length != vector.length) {
            throw new IllegalArgumentException("Matrix columns must match vector size.");
        }
        double[] result = new double[matrix.length];
        for(int i = 0; i < matrix.length; i++) {
            result[i] = 0;
            for (int j = 0; j < matrix[i].length; j++) {
                result[i] += matrix[i][j] * vector[j];
            }
        }
        
        return result;
    }

    public static double[] add(double[] vector1, double[] vector2) {
        if (vector1.length != vector2.length) {
            throw new IllegalArgumentException("Vectors must be of the same size.");
        }
        double[] result = new double[vector1.length];
        for (int i = 0; i < vector1.length; i++) {
            result[i] = vector1[i] + vector2[i];
        }
        return result;
    }

    public static double[] subtract(double[] vector1, double[] vector2) {
        if (vector1.length != vector2.length) {
            throw new IllegalArgumentException("Vectors must be of the same size.");
        }
        double[] result = new double[vector1.length];
        for (int i = 0; i < vector1.length; i++) {
            result[i] = vector1[i] - vector2[i];
        }
        return result;
    }
    public static double[] multiply(double[] vector1, double[] vector2) {
        if (vector1.length != vector2.length) {
            throw new IllegalArgumentException("Vectors must be of the same size.");
            
        }
        double[] result = new double[vector1.length];
        for (int i = 0; i < vector1.length; i++) {
            result[i] = vector1[i] * vector2[i];
        }
        return result;
    }

    public static double[] activate(activation activation, double[] vector) {
        double[] result = new double[vector.length];
        result = activation.activate(vector);

        return result;
    }
    public static double vector_mean(double[] vector) {
        double sum = 0;
        for (int i=0; i < vector.length; i++) {
            sum += vector[i];
        }
        return sum / vector.length;
    }
    public static double vector_sum(double[] vector) {
        double sum = 0;
        for (int i=0; i < vector.length; i++) {
            sum += vector[i];
        }
        return sum;
    }
    
    public static double[][] one_hot(int[] labels, int num_classes) {
        double[][] one_hot = new double[labels.length][num_classes];
        for (int i = 0; i < labels.length; i++) {
            one_hot[i][labels[i]] = 1.0;
        }
        return one_hot;
    }


}
