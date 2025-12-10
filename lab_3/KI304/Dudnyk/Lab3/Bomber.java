package KI304.Dudnyk.Lab3;

import java.io.*;
import KI304.Dudnyk.Lab3.Airplane;

/**
 * Інтерфейс <code>BombingCapable</code> визначає функціонал для літаків,
 * здатних нести та скидати бомби.
 */
interface BombingCapable {
    /**
     * Завантажити бомби на літак
     * 
     * @param count кількість бомб для завантаження
     */
    void loadBombs(int count);

    /**
     * Скинути бомби
     * 
     * @param count кількість бомб для скидання
     * @return true якщо бомби успішно скинуті, false якщо ні
     */
    boolean dropBombs(int count);

    /**
     * Отримати кількість бомб, що залишилися
     * 
     * @return кількість бомб на борту
     */
    int getBombCount();
}

/**
 * Клас <code>Bomber</code> реалізує бомбардувальник, який розширює базовий клас
 * Airplane
 * та реалізує інтерфейс BombingCapable.
 * 
 * @author Dudnyk
 * @version 1.0
 * @see Airplane
 * @see BombingCapable
 */
public class Bomber extends Airplane implements BombingCapable {
    private int bombCount;
    private int maxBombCapacity;
    private boolean bombingSystemActive;

    /**
     * Конструктор за замовчуванням
     * 
     * @throws FileNotFoundException якщо файл логу не може бути створений
     */
    public Bomber() throws FileNotFoundException {
        super();
        this.bombCount = 0;
        this.maxBombCapacity = 10;
        this.bombingSystemActive = false;
    }

    /**
     * Конструктор з параметрами
     * 
     * @param seats           кількість місць у кабіні
     * @param span            розмах крила
     * @param maxBombCapacity максимальна місткість бомб
     * @throws FileNotFoundException якщо файл логу не може бути створений
     */
    public Bomber(int seats, double span, int maxBombCapacity) throws FileNotFoundException {
        super(seats, span);
        this.bombCount = 0;
        this.maxBombCapacity = maxBombCapacity;
        this.bombingSystemActive = false;
    }

    /**
     * Завантажити бомби на літак
     * 
     * @param count кількість бомб для завантаження
     */
    @Override
    public void loadBombs(int count) {
        if (count <= 0) {
            System.out.println("Кількість бомб має бути більше 0");
            return;
        }

        if (bombCount + count <= maxBombCapacity) {
            bombCount += count;
            System.out.println("Завантажено " + count + " бомб. Загальна кількість: " + bombCount);
        } else {
            int actualLoad = maxBombCapacity - bombCount;
            bombCount = maxBombCapacity;
            System.out.println("Завантажено " + actualLoad + " бомб. Досягнуто максимальної місткості: " + bombCount);
        }
    }

    /**
     * Скинути бомби
     * 
     * @param count кількість бомб для скидання
     * @return true якщо бомби успішно скинуті, false якщо ні
     */
    @Override
    public boolean dropBombs(int count) {
        if (!bombingSystemActive) {
            System.out.println("Система бомбометання не активована!");
            return false;
        }

        if (count <= 0) {
            System.out.println("Кількість бомб має бути більше 0");
            return false;
        }

        if (bombCount >= count) {
            bombCount -= count;
            System.out.println("Скинуто " + count + " бомб. Залишилось: " + bombCount);
            return true;
        } else {
            System.out.println("Недостатньо бомб для скидання. На борту: " + bombCount);
            return false;
        }
    }

    /**
     * Отримати кількість бомб, що залишилися
     * 
     * @return кількість бомб на борту
     */
    @Override
    public int getBombCount() {
        return bombCount;
    }

    /**
     * Активувати систему бомбометання
     */
    public void activateBombingSystem() {
        bombingSystemActive = true;
        System.out.println("Система бомбометання активована");
    }

    /**
     * Деактивувати систему бомбометання
     */
    public void deactivateBombingSystem() {
        bombingSystemActive = false;
        System.out.println("Система бомбометання деактивована");
    }

    /**
     * Перевірити чи активна система бомбометання
     * 
     * @return true якщо система активна, false якщо ні
     */
    public boolean isBombingSystemActive() {
        return bombingSystemActive;
    }

    /**
     * Отримати максимальну місткість бомб
     * 
     * @return максимальна кількість бомб
     */
    public int getMaxBombCapacity() {
        return maxBombCapacity;
    }

    /**
     * Виконати бойове завдання - зліт, політ до цілі, скидання бомб, повернення
     * 
     * @param targetHeading  курс до цілі
     * @param targetAltitude висота для атаки
     * @param bombsToDrop    кількість бомб для скидання
     */
    public void executeCombatMission(int targetHeading, int targetAltitude, int bombsToDrop) {
        System.out.println("=== ПОЧАТОК БОЙОВОГО ЗАВДАННЯ ===");

        // Зліт
        startEngine();
        takeOff();

        // Політ до цілі
        setAltitude(targetAltitude);
        changeHeading(targetHeading);

        // Підготовка до атаки
        activateBombingSystem();

        // Атака
        boolean success = dropBombs(bombsToDrop);
        if (success) {
            System.out.println("Атака успішна! Ціль уражена.");
        } else {
            System.out.println("Атака не вдалася.");
        }

        // Повернення на базу
        deactivateBombingSystem();
        changeHeading(180); // Повернення на базу
        land();

        System.out.println("=== ЗАВЕРШЕННЯ БОЙОВОГО ЗАВДАННЯ ===");
    }

    /**
     * Отримати статус бомбардувальника (розширена версія)
     * 
     * @return рядок з інформацією про стан бомбардувальника
     */
    @Override
    public String getStatus() {
        String baseStatus = super.getStatus();
        String bomberStatus = ", bombs=" + bombCount +
                "/" + maxBombCapacity +
                ", bombingSystem=" + (bombingSystemActive ? "active" : "inactive");
        return baseStatus + bomberStatus;
    }
}
