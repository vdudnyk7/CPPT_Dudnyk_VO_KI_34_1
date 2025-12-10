class Engine:
    """Клас для опису двигуна літака."""

    def __init__(self, power=1000):
        self.__power = power  # потужність двигуна, к.с.
        self.__running = False

    def start(self):
        """Запуск двигуна."""
        self.__running = True
        print("Двигун запущено.")

    def stop(self):
        """Зупинка двигуна."""
        self.__running = False
        print("Двигун зупинено.")

    def isRunning(self):
        """Перевірка, чи працює двигун."""
        return self.__running

    def getPower(self):
        """Отримати потужність двигуна."""
        return self.__power