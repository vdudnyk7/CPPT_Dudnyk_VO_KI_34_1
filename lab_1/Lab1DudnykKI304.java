package lab_1;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Клас Lab1DudnykKI304 реалізує завдання до лабораторної роботи №1
 */

public class Lab1DudnykKI304 {
    /**
     * Статичний метод main є точкою входу в програму
     *
     * @param args
     * @throws FileNotFoundException
     *
     */
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        File dataFile = new File("MyFile.txt");
        PrintWriter fout = new PrintWriter(dataFile);

        // Ввід даних
        System.out.print("Введіть розмір матриці n: ");
        int n = in.nextInt();
        in.nextLine();
        System.out.print("Введіть символ-заповнювач: ");
        String filler = in.nextLine();

        if (filler.length() == 0) {
            System.out.print("Не введено символ-заповнювач");
            in.close();
            fout.close();
            return;
        }
        if (filler.length() > 1) {
            System.out.print("Введено забагато символів");
            in.close();
            fout.close();
            return;
        }

        // Зубчатий масив для збереження заштрихованих символів
        char[][] arr = new char[n][];
        // Символ-заповнювач
        char fillchar = filler.charAt(0);

        for (int i = 0; i < n; i++) {
            // рахуємо скільки елементів у цьому рядку буде заштриховано
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (isShaded(i, j, n))
                    count++;
            }

            // створюємо зубчатий підмасив
            arr[i] = new char[count];

            // заповнюємо його

            int idx = 0;
            for (int j = 0; j < n; j++) {
                if (isShaded(i, j, n)) {
                    arr[i][idx++] = fillchar;
                }
            }
        }

        // Вивід у вигляді повної матриці (з пробілами для порожніх місць)
        System.out.println("\nЗаштрихована матриця:");
        for (int i = 0; i < n; i++) {
            int idx = 0;
            for (int j = 0; j < n; j++) {
                if (isShaded(i, j, n)) {
                    System.out.print(arr[i][idx] + " ");
                    fout.print(arr[i][idx] + " ");
                    idx++;
                } else {
                    System.out.print("  "); // пробіл для порожнього
                    fout.print("  ");
                }
            }
            System.out.println();
            fout.println();
        }

        in.close();
        fout.close();
    }

    // Перевірка умови заштрихованості
    private static boolean isShaded(int i, int j, int n) {
        return (i >= j && i + j <= n - 1) || (i <= j && i + j >= n - 1);
    }
}
