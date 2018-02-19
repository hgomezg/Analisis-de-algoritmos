
import java.util.LinkedList;
import java.util.Stack;

/**
 *
 * @author hugo
 */
public class ConjuntoPotencia {

    static void printSubsets(int set[]) {
        int n = set.length;

        // Run a loop for printing all 2^n
        // subsets one by obe
        for (int i = 0; i < (1 << n); i++) {
            System.out.print("{ ");
            int suma = 0;
            // Print current subset
            for (int j = 0; j < n; j++) // (1<<j) is a number with jth bit 1
            // so when we 'and' them with the
            // subset number we get which numbers
            // are present in the subset and which
            // are not
            {
                if ((i & (1 << j)) > 0) {
                    suma = suma + set[j];
                    System.out.print(set[j] + " ");
                }
            }

            System.out.println(suma+" }");
            System.out.println("i="+(i+1));
        }
        
    }

    // Driver code
    public static void main(String[] args) {
        int set[] = {1, 2, 3};
        printSubsets(set);
    }

}
