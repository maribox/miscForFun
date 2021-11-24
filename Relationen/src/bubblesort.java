import java.util.Arrays;

public class bubblesort {
    public static void main(String...args) {
        int[] numbers = {3, 5, 623, 323, 534, 23};

        for (int i = 0; i < numbers.length; i++) {
            for(int j = 0; j < numbers.length; j++) {
                if (numbers[i] < numbers[j]) {
                    int tmp = numbers[j];
                    numbers[j] = numbers[i];
                    numbers[i] = tmp;
                }
            }
        }
        System.out.println(Arrays.toString(numbers));

    }
}
