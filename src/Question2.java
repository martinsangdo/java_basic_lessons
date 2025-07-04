
public class Question2 {
    static int[] getTotal(int[][] matrix){
        int colNum = matrix[0].length;
        int[] results = new int[2];
        results[0] = 0;
        results[1] = 0;
        //get total 1
        for (int i=0; i<colNum; i++){
            results[0] += matrix[i][i];
        }
        //get total 2
        for (int i=0; i<colNum; i++){
            results[1] += matrix[colNum - i - 1][i];
        }

        return results;
    }

    public static void main(String[] args) throws Exception {
        int[][] matrix = { {1,4,6},{8,5,2},{1,9,9} };
        int[] results = getTotal(matrix);
        System.out.println("Results: [" + results[0] + "," + results[1] + "]");
    }
}
