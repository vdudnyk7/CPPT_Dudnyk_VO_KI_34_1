package KI304.Dudnyk.Lab4;

import java.util.Scanner;
import java.io.*;
import static java.lang.System.out;

/**
 * Class <code>EquationsApp</code> Implements driver for Equations class
 * 
 * @author Student Name
 * @version 1.0
 */
public class EquationsApp {
    /**
     * Main method - entry point of the application
     * 
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        try {
            out.print("Enter file name: ");
            Scanner in = new Scanner(System.in);
            String fName = in.nextLine();
            PrintWriter fout = new PrintWriter(new File(fName));

            try {
                try {
                    Equations eq = new Equations();
                    out.print("Enter X (in degrees): ");
                    int x = in.nextInt();
                    double result = eq.calculate(x);
                    out.println("Result: " + result);
                    fout.println("For x = " + x + " degrees, result = " + result);
                    out.println("Result successfully written to file: " + fName);
                } finally {
                    // This block will execute under any circumstances
                    fout.flush();
                    fout.close();
                }
            } catch (CalcException ex) {
                // Block catches expression calculation errors
                out.println("Calculation error: " + ex.getMessage());
            }
        } catch (FileNotFoundException ex) {
            // Block catches file operation errors even if they occurred in finally block
            out.println("Exception reason: Perhaps wrong file path or file cannot be created");
        } catch (Exception ex) {
            // Block catches all other exceptions
            out.println("Unexpected error: " + ex.getMessage());
        }
    }
}
