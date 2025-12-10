from Airplane import Airplane

class Bomber(Airplane):
    """Похідний клас — Бомбардувальник."""

    def __init__(self, model="B-2 Spirit", speed=900, altitude=0, bombs=10):
        super().__init__(model, speed, altitude)
        self.__bombs = bombs

    def dropBomb(self):
        """Скидання однієї бомби."""
        if self.__bombs > 0:
            self.__bombs -= 1
            print(f"Бомбу скинуто! Залишилось {self.__bombs}.")
        else:
            print("Бомби відсутні!")

    def reloadBombs(self, count):
        """Поповнення боєкомплекту."""
        self.__bombs += count
        print(f"Бомби поповнено. Тепер у наявності {self.__bombs}.")