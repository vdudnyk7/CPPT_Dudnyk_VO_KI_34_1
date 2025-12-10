package KI304.Dudnyk.Lab5;

import java.io.*;
import java.util.*;

/**
 * Клас <code>FioApp</code> тестує роботу класу CalcWFio,
 * який виконує обчислення виразу та запис/читання результатів у файли.
 * 
 * @author Dudnyk
 * @version 1.0
 */
public class FioApp {
    public static void main(String[] args) throws IOException {
        CalcWFio obj = new CalcWFio();
        Scanner s = new Scanner(System.in);

        System.out.print("Enter angle X in degrees: ");
        int x = s.nextInt();

        try {
            obj.calculate(x);
            System.out.println("Result is: " + obj.getResult());

            // Запис результатів у текстовий і двійковий файли
            obj.writeResTxt("textRes.txt");
            obj.writeResBin("binRes.bin");

            // Читання результату з двійкового файлу
            obj.readResBin("binRes.bin");
            System.out.println("Binary file read result: " + obj.getResult());

            // Читання результату з текстового файлу
            obj.readResTxt("textRes.txt");
            System.out.println("Text file read result: " + obj.getResult());

        } catch (CalcException ex) {
            System.out.println(ex.getMessage());
        }
        s.close();
    }
}
