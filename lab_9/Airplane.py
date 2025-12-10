from Engine import Engine
from Crew import Crew

class Airplane:
    """Базовий клас Літак."""

    def __init__(self, model="Generic Plane", speed=600, altitude=0):
        self.__model = model
        self.__speed = speed          # швидкість км/год
        self.__altitude = altitude    # висота польоту
        self.__engine = Engine()
        self.__crew = Crew()

    def startEngine(self):
        self.__engine.start()

    def stopEngine(self):
        self.__engine.stop()

    def takeOff(self):
        if self.__engine.isRunning():
            self.__altitude = 1000
            print(f"{self.__model} набрав висоту {self.__altitude} м.")
        else:
            print("Не можна злетіти: двигун не запущено!")

    def land(self):
        self.__altitude = 0
        print(f"{self.__model} здійснив посадку.")

    def fly(self, distance):
        """Політ на вказану відстань."""
        if self.__engine.isRunning() and self.__altitude > 0:
            print(f"{self.__model} пролітає {distance} км на висоті {self.__altitude} м.")
        else:
            print("Політ неможливий. Перевірте двигун або висоту!")

    def getModel(self):
        return self.__model

    def getCrewSize(self):
        return self.__crew.getCrewSize()