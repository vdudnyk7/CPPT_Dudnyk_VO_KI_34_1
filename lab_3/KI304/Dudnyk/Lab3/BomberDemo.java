package KI304.Dudnyk.Lab3;

import java.io.*;
import java.util.Scanner;

/**
 * Демонстраційний клас для тестування функціоналу бомбардувальника
 */
public class BomberDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Просте введення параметрів
            System.out.print("Введіть кількість місць: ");
            int seats = scanner.nextInt();

            System.out.print("Введіть розмах крила: ");
            double span = scanner.nextDouble();

            System.out.print("Введіть місткість бомб: ");
            int capacity = scanner.nextInt();

            // Створення бомбардувальника
            Bomber bomber = new Bomber(seats, span, capacity);

            // Просте меню
            int choice;
            do {
                System.out.println("\n1 - Статус, 2 - Завантажити бомби, 3 - Виконати завдання, 0 - Вихід");
                System.out.print("Вибір: ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("Статус: " + bomber.getStatus());
                        break;
                    case 2:
                        System.out.print("Скільки бомб завантажити: ");
                        int bombs = scanner.nextInt();
                        bomber.loadBombs(bombs);
                        break;
                    case 3:
                        System.out.print("Курс до цілі: ");
                        int heading = scanner.nextInt();
                        System.out.print("Висота атаки: ");
                        int altitude = scanner.nextInt();
                        System.out.print("Кількість бомб: ");
                        int bombsToDrop = scanner.nextInt();
                        bomber.executeCombatMission(heading, altitude, bombsToDrop);
                        break;
                    case 0:
                        System.out.println("Вихід...");
                        break;
                    default:
                        System.out.println("Невірний вибір");
                }
            } while (choice != 0);

            bomber.dispose();

        } catch (FileNotFoundException e) {
            System.err.println("Помилка: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
