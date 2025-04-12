import java.util.Scanner;

public class ArrayElementSearch {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt(); 
        int X = scanner.nextInt(); 
        
        int[] A = new int[N]; 
        for (int i = 0; i < N; i++) {
            A[i] = scanner.nextInt();
        }
        
     
        boolean found = false;
        for (int i = 0; i < N; i++) {
            if (A[i] == X) {
                found = true;
                break;
            }
        }

       
        if (found) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }

        scanner.close();
    }
}
