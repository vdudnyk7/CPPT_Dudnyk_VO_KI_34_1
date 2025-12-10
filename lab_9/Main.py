from Bomber import Bomber

if __name__ == "__main__":
    print("=== Програма: Бомбардувальник ===")

    # Введення даних користувачем
    model = input("Введіть модель літака: ")
    speed = int(input("Введіть швидкість літака (км/год): "))
    bombs = int(input("Введіть кількість бомб на борту: "))

    # Створюємо об’єкт бомбардувальника
    bomber = Bomber(model, speed, bombs=bombs)

    print(f"\nСтворено бомбардувальник {model} зі швидкістю {speed} км/год і {bombs} бомбами.")

    # Запуск двигуна, зліт, політ
    bomber.startEngine()
    bomber.takeOff()
    distance = int(input("\nВведіть відстань польоту (км): "))
    bomber.fly(distance)

    # Скидання бомб
    print("\n--- Скидання бомб ---")
    to_drop = int(input("Скільки бомб скинути: "))
    for i in range(to_drop):
        bomber.dropBomb()

    # Поповнення боєкомплекту
    print("\n--- Поповнення боєкомплекту ---")
    to_reload = int(input("Скільки бомб дозавантажити: "))
    bomber.reloadBombs(to_reload)

    # Посадка
    bomber.land()
    bomber.stopEngine()

    print("\nПоліт завершено. Програма завершила роботу.")