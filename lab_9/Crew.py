class Crew:
    """Клас, що описує екіпаж літака."""

    def __init__(self, members=2):
        self.__members = members

    def getCrewSize(self):
        """Повертає кількість членів екіпажу."""
        return self.__members

    def setCrewSize(self, members):
        """Встановлює кількість членів екіпажу."""
        self.__members = members