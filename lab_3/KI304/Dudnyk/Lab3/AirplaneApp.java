package KI304.Dudnyk.Lab3;

import static java.lang.System.out;
import java.io.*;
import java.util.Scanner;

/**
 * Airplane Application class demonstrates Airplane class abilities
 */
public class AirplaneApp {
    /**
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);

        // --- Введення параметрів літака ---
        out.print("Введіть кількість місць у літаку: ");
        int seats = sc.nextInt();

        out.print("Введіть розмах крила (м): ");
        double span = sc.nextDouble();

        Airplane plane = new Airplane(seats, span);

        // --- Запуск двигуна ---
        plane.startEngine();
        out.println(plane.getStatus());

        // --- Зліт ---
        plane.takeOff();
        out.println(plane.getStatus());

        // --- Зміна курсу ---
        out.print("Введіть новий курс (в градусах): ");
        int heading = sc.nextInt();
        plane.changeHeading(heading);

        // --- Набір висоти ---
        out.print("Введіть висоту польоту (м): ");
        int altitude = sc.nextInt();
        plane.setAltitude(altitude);

        out.println(plane.getStatus());

        // --- Посадка ---
        plane.land();
        out.println(plane.getStatus());

        // --- Закриття файлу ---
        plane.dispose();

        sc.close();
    }
}
