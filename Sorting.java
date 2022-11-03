import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Scanner;

public class Sorting {

    private final int[] array;
    private final long[] times;

    public Sorting(int[] array) {
        this.array = array;
        this.times = new long[3];
    }

    private int[] bubble_sort() {
        Instant start = Instant.now();
        for (int i = 0; i < this.array.length; i++) {
            for (int j = 0; j < this.array.length; j++) {
                if (this.array[i] < this.array[j]) {
                    int temp = this.array[i];
                    this.array[i] = this.array[j];
                    this.array[j] = temp;
                }
            }
        }
        Instant end = Instant.now();
        this.times[0] = Duration.between(start, end).toSeconds();
        return this.array;
    }

    private int[] insertion_sort() {
        Instant currentTime = Instant.now();
        for (int i = 1; i < this.array.length; i++) {
            int key = this.array[i];
            int j = i - 1;
            while (j >= 0 && this.array[j] > key) {
                this.array[j + 1] = this.array[j];
                j--;
            }
            this.array[j + 1] = key;
        }
        Instant endTime = Instant.now();
        this.times[1] = Duration.between(currentTime, endTime).toSeconds();
        return this.array;
    }

    private int[] merge_sort(int[] array) {
        Instant currentTime = Instant.now();
        if (array.length > 1) {
            int[] left = new int[array.length / 2];
            System.arraycopy(array, 0, left, 0, array.length / 2);
            int[] right = new int[array.length - array.length / 2];
            System.arraycopy(array, array.length / 2, right, 0, array.length - array.length / 2);
            merge_sort(left);
            merge_sort(right);
            int i = 0, j = 0, k = 0;
            while (i < left.length && j < right.length) {
                if (left[i] < right[j]) {
                    array[k] = left[i];
                    i++;
                } else {
                    array[k] = right[j];
                    j++;
                }
                k++;
            }
            while (i < left.length) {
                array[k] = left[i];
                i++;
                k++;
            }
            while (j < right.length) {
                array[k] = right[j];
                j++;
                k++;
            }
        }
        Instant endTime = Instant.now();
        this.times[2] = Duration.between(currentTime, endTime).toSeconds();
        return array;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the size of the array: ");
        int input = scan.nextInt();
        Sorting sort = new Sorting(new int[input]);
        sort.bubble_sort();
        sort.insertion_sort();
        sort.merge_sort(sort.array);
        System.out.print(Arrays.toString(sort.times));
    }
}

