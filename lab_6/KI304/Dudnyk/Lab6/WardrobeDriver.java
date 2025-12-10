package KI304.Dudnyk.Lab6;

import java.util.*;

/**
 * Клас WardrobeDriver демонструє роботу з параметризованим класом Wardrobe.
 * Реалізація для парного варіанту (пошук мінімального елемента).
 * 
 * @author
 * @version 1.0
 */
public class WardrobeDriver {
    public static void main(String[] args) {
        // Створюємо шафу, яка зберігає об’єкти типу Item
        Wardrobe<? super Item> wardrobe = new Wardrobe<Item>();

        // Додаємо різні предмети
        wardrobe.addItem(new Book("Java for Beginners", "A. Smith", 350));
        wardrobe.addItem(new Clothes("T-Shirt", "Cotton", 200));
        wardrobe.addItem(new Book("Algorithms", "T. Cormen", 500));
        wardrobe.addItem(new Clothes("Jacket", "Leather", 100));

        // Знаходимо найменший елемент (за розміром)
        Item minItem = wardrobe.findMin();
        System.out.println("\nНайменший елемент у шафі:");
        if (minItem != null)
            minItem.print();

        // Видаляємо елемент за індексом
        wardrobe.removeItem(1);

        // Виводимо усі залишені елементи
        System.out.println("\nПісля видалення:");
        wardrobe.showAll();
    }
}

/**
 * Параметризований клас Wardrobe - контейнер для зберігання об’єктів типу Item
 * 
 * @param <T> тип об'єктів, які реалізують інтерфейс Item
 */
class Wardrobe<T extends Item> {
    private ArrayList<T> items;

    /**
     * Конструктор за замовчуванням
     */
    public Wardrobe() {
        items = new ArrayList<T>();
    }

    /**
     * Метод додає новий елемент у шафу
     * 
     * @param item об’єкт, який додається
     */
    public void addItem(T item) {
        items.add(item);
        System.out.print("Додано елемент: ");
        item.print();
    }

    /**
     * Метод видаляє елемент за індексом
     * 
     * @param index індекс елемента для видалення
     */
    public void removeItem(int index) {
        if (index >= 0 && index < items.size()) {
            System.out.println("Видалено елемент:");
            items.get(index).print();
            items.remove(index);
        } else {
            System.out.println("Помилка: неправильний індекс!");
        }
    }

    /**
     * Метод шукає елемент з найменшим розміром
     * 
     * @return елемент з мінімальним розміром
     */
    public T findMin() {
        if (!items.isEmpty()) {
            T min = items.get(0);
            for (int i = 1; i < items.size(); i++) {
                if (items.get(i).compareTo(min) < 0)
                    min = items.get(i);
            }
            return min;
        }
        return null;
    }

    /**
     * Виводить усі елементи шафи
     */
    public void showAll() {
        for (T item : items) {
            item.print();
        }
    }
}

/**
 * Інтерфейс Item - базовий для всіх елементів, які зберігаються в шафі
 */
interface Item extends Comparable<Item> {
    /**
     * Повертає розмір або вагу предмета
     * 
     * @return значення розміру
     */
    public int getSize();

    /**
     * Виводить інформацію про предмет
     */
    public void print();
}

/**
 * Клас Book - реалізує інтерфейс Item
 */
class Book implements Item {
    private String title;
    private String author;
    private int pages;

    /**
     * Конструктор класу Book
     * 
     * @param title  назва книги
     * @param author автор
     * @param pages  кількість сторінок
     */
    public Book(String title, String author, int pages) {
        this.title = title;
        this.author = author;
        this.pages = pages;
    }

    public int getSize() {
        return pages;
    }

    public int compareTo(Item other) {
        return Integer.compare(this.getSize(), other.getSize());
    }

    public void print() {
        System.out.println("Книга: \"" + title + "\", Автор: " + author + ", Сторінок: " + pages);
    }
}

/**
 * Клас Clothes - реалізує інтерфейс Item
 */
class Clothes implements Item {
    private String name;
    private String material;
    private int weight;

    /**
     * Конструктор класу Clothes
     * 
     * @param name     назва одягу
     * @param material матеріал
     * @param weight   вага (в грамах)
     */
    public Clothes(String name, String material, int weight) {
        this.name = name;
        this.material = material;
        this.weight = weight;
    }

    public int getSize() {
        return weight;
    }

    public int compareTo(Item other) {
        return Integer.compare(this.getSize(), other.getSize());
    }

    public void print() {
        System.out.println("Одяг: " + name + ", Матеріал: " + material + ", Вага: " + weight + " г");
    }
}
