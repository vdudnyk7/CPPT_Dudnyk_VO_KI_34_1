# Модуль для обчислення виразу y = tg(x) / sin(2x)
# Кут x вводиться у градусах, але обчислення виконуються у радіанах
# Результати зберігаються у текстовому та двійковому файлах

import os
import struct
import sys
import math

# --- Функції для роботи з файлами ---

def writeResTxt(fName, result):
    """Запис результату у текстовий файл із максимальною точністю"""
    with open(fName, 'w') as f:
        # Використовуємо формат з 17 значущими цифрами (точність double)
        f.write(f"{result:.17g}")


def readResTxt(fName):
    """Зчитування результату з текстового файлу без втрати точності"""
    result = 0.0
    try:
        if os.path.exists(fName):
            with open(fName, 'r') as f:
                result = float(f.read().strip())
        else:
            raise FileNotFoundError(f"Файл {fName} не знайдено.")
    except FileNotFoundError as e:
        print(e)
    return result


def writeResBin(fName, result):
    """Запис результату у двійковий файл у форматі double (8 байт)"""
    with open(fName, 'wb') as f:
        # 'd' — формат double (8 байт)
        f.write(struct.pack('d', result))


def readResBin(fName):
    """Зчитування результату з двійкового файлу (double)"""
    result = 0.0
    try:
        if os.path.exists(fName):
            with open(fName, 'rb') as f:
                result = struct.unpack('d', f.read())[0]
        else:
            raise FileNotFoundError(f"Файл {fName} не знайдено.")
    except FileNotFoundError as e:
        print(e)
    return result


# --- Функція обчислення виразу ---

def calculate(x_deg):
    """
    Обчислення виразу y = tg(x) / sin(2x)
    :param x_deg: значення кута у градусах
    :return: результат y або None, якщо обчислення неможливе
    """
    try:
        # Перетворюємо градуси у радіани
        x = math.radians(x_deg)

        # Перевірка: чи не наближається тангенс до нескінченності (x = pi/2 + k*pi)
        # Використаємо похибку eps для уникнення помилки через плаваючу точність
        eps = 1e-10
        if abs(math.cos(x)) < eps:
            raise ValueError("Помилка: тангенс не визначений при цьому куті (cos(x)=0).")

        # Перевірка ділення на нуль у знаменнику
        denominator = math.sin(2 * x)
        if abs(denominator) < eps:
            raise ZeroDivisionError("Помилка: ділення на нуль у sin(2x)!")

        # Обчислення виразу
        y = math.tan(x) / denominator
        return y

    except (ZeroDivisionError, ValueError) as e:
        print(e)
        return None


# --- Основна частина програми ---

if __name__ == "__main__":
    try:
        # Введення кута користувачем у градусах
        data = float(input("Введіть значення кута x у градусах: "))

        # Обчислення результату
        result = calculate(data)
        if result is not None:
            print(f"Результат y = {result}")

            # Запис результатів у файли
            writeResTxt("textRes.txt", result)
            writeResBin("binRes.bin", result)

            # Зчитування з файлів і виведення результатів
            print("Результат із двійкового файлу:", readResBin("binRes.bin"))
            print("Результат із текстового файлу:", readResTxt("textRes.txt"))

    except ValueError:
        print("Помилка: введено некоректне число.")
        sys.exit(1)
