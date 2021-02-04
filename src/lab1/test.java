package lab1;

public class test {
    public static void main(String[] args) {
        int[][] matrix = new int[40][40];
        for (int j = 0; j < 40; j++) {
            matrix[0][j] = j;
        }
        for (int i = 1; i < 40; i++) {
            matrix[i][0] = i;
            for (int j = 1; j < 40; j++) {
                matrix[i][j] = 100;
            }
        }

        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
