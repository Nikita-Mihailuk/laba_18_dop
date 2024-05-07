import java.util.Random;
import java.util.Scanner;

interface Expression{
    boolean isEqual (int a, int b);
}

public class Main {
    public static void heapSort(int[] arr, Expression func) {
        int n = arr.length;

        // Создание дерева
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i, func);
        }
        // Извлечение элементов дерева
        for (int i = n - 1; i >= 0; i--) {
            // Замена корня дерева последним элементом
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            // Создание дерева
            heapify(arr, i, 0, func);
        }
    }
    private static void heapify(int[] arr, int n, int i, Expression func) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        // Если левый дочерний элемент больше корня
        if (l < n && func.isEqual(arr[l], arr[largest]))
            largest = l;

        // Если правый дочерний элемент больше, чем самый крупный на данный момент
        if (r < n && func.isEqual(arr[r], arr[largest]))
            largest = r;

        // Если самый большой элемент не является корнем
        if (largest!= i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;


            heapify(arr, n, largest, func);
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int[] arr = new int[100];
        Random random = new Random();
        for (int i = 0; i < arr.length; i++){
            arr[i] = random.nextInt(500);
        }
        System.out.println("Исходный массив: ");
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();

        System.out.print("Введите способ сортировки(> - по убыванию; < - по возрастанию): ");
        String oper = in.next();

        if(oper.equals("<")){
            Expression func = (a,b) -> a > b;
            heapSort(arr, func);
        } else if (oper.equals(">")) {
            Expression func = (a,b) -> a < b;
            heapSort(arr, func);
        } else System.out.println("Неправильно введён способ сортировки");

        System.out.println("Отсортированный массив: ");
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
}