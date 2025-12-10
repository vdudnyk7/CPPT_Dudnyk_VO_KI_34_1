package KI304.Dudnyk.Lab4;

/**
 * Class <code>CalcException</code> уточнює стандартний ArithmeticException.
 * Використовується для передачі повідомлення про конкретну причину помилки.
 * 
 * @author Dudnyk
 * @version 1.1
 */
class CalcException extends ArithmeticException {
    public CalcException() {
    }

    public CalcException(String cause) {
        super(cause);
    }
}

/**
 * Class <code>Equations</code> реалізує метод обчислення виразу y =
 * tg(x)/sin(2x)
 * із перевірками на недопустимі значення аргументу.
 * 
 * @author Dudnyk
 * @version 1.1
 */
public class Equations {
    /**
     * Метод обчислює вираз y = tg(x) / sin(2x)
     * 
     * @param x Кут у градусах
     * @return Результат обчислення виразу
     * @throws CalcException якщо x призводить до недопустимих значень
     */
    public double calculate(int x) throws CalcException {
        double y, rad;
        rad = x * Math.PI / 180.0; // переводимо у радіани

        try {
            double sin2x = Math.sin(2 * rad);
            double tanx = Math.tan(rad);

            y = tanx / sin2x;

            // Перевірка на невизначеність або розрив
            if (Double.isNaN(y) || Double.isInfinite(y))
                throw new ArithmeticException();

            // Перевірка окремих випадків (зона розриву)
            if (Math.abs(Math.cos(rad)) < 1e-10)
                throw new ArithmeticException();
            if (Math.abs(sin2x) < 1e-10)
                throw new ArithmeticException();
        } catch (ArithmeticException ex) {
            // Аналізуємо, де саме сталася помилка
            if (Math.abs(Math.cos(rad)) < 1e-10)
                throw new CalcException("Exception reason: Illegal value of X for tangent calculation (cos(x) ≈ 0)");
            else if (Math.abs(Math.sin(2 * rad)) < 1e-10)
                throw new CalcException("Exception reason: Illegal value of X for sine calculation (sin(2x) ≈ 0)");
            else
                throw new CalcException("Unknown reason of the exception during expression calculation");
        }

        return y;
    }
}