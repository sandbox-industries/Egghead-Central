class Card:
    def __init__(self, value=None, suit=None):
        """
        Initialize the card object
        :param value: int or none, integer value of the card
        :param suit:  str or none, character value of the suit of the card
        """
        self._value = value
        self._suit = suit

    def get_value(self) -> int:
        """
        Public function to return the value of the card object
        :return: int, the value of the card object
        """
        return self._value

    def get_suit(self) -> str:
        """
        Public function to return the suit of the card
        :return: str, the character representing the suit of the card
        """
        return self._suit
