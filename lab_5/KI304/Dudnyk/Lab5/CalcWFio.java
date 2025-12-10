package KI304.Dudnyk.Lab5;

import java.io.*;

/**
 * Клас <code>CalcWFio</code> реалізує методи обчислення виразу
 * через клас Equations та збереження/зчитування результатів
 * у текстовому і двійковому форматах.
 * 
 * @author Dudnyk
 * @version 1.0
 */
public class CalcWFio {
    private double result; // збережений результат
    private Equations eq = new Equations();

    /**
     * Виконує обчислення виразу через клас Equations.
     * 
     * @param x кут у градусах
     * @throws CalcException якщо обчислення неможливе
     */
    public void calculate(int x) throws CalcException {
        result = eq.calculate(x);
    }

    public double getResult() {
        return result;
    }

    /**
     * Запис результату в текстовий файл.
     */
    public void writeResTxt(String fName) throws FileNotFoundException {
        PrintWriter f = new PrintWriter(fName);
        f.print(Double.toString(result));
        f.close();
    }

    /**
     * Зчитування результату з текстового файлу.
     */
    public void readResTxt(String fName) {
        try {
            File f = new File(fName);
            if (f.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(f));
                result = Double.parseDouble(reader.readLine().trim());
                reader.close();
            } else
                throw new FileNotFoundException("File " + fName + " not found");
        } catch (IOException ex) {
            System.out.println("Error reading text file: " + ex.getMessage());
        }
    }

    /**
     * Запис результату у двійковий файл.
     */
    public void writeResBin(String fName) throws IOException {
        DataOutputStream f = new DataOutputStream(new FileOutputStream(fName));
        f.writeDouble(result);
        f.close();
    }

    /**
     * Зчитування результату з двійкового файлу.
     */
    public void readResBin(String fName) throws IOException {
        DataInputStream f = new DataInputStream(new FileInputStream(fName));
        result = f.readDouble();
        f.close();
    }
}