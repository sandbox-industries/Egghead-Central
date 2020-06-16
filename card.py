class Card:
    def __init__(self, value=None, suit=None):
        try:
            if 1 < int(value) < 15:
                self._value = value
            else:
                self._value = None
        except:
            self._value = None

        self._suit = suit

    def get_value(self):
        return self._value

    def get_suit(self):
        return self._suit

    def get_properties(self):
        return self._value, self._suit

    def set_value(self, value):
        try:
            if 1 < int(value) < 15:
                self._value = value
                return True
            else:
                self._value = None
        except:
            self._value = None

        return False

    def set_suit(self, suit):
        self._suit = suit
        return True

    # This will compare card values. There is no need to check
    #  the suit while comparing card values.
    # -1 : smaller than compare_card
    #  0 : same as compare_card
    #  1 : greater than compare_card
    def compare_value(self, compare_card):
        if self._value > compare_card.get_value():
            return 1

        if self._value == compare_card.get_value():
            return 0

        return -1

    # True  : suit is same
    # False : suit is not the same
    def suit_is_same(self, compare_card):
        return self._suit == compare_card.get_suit()

    # def __str__(self):
    #     return str(self._value) + " " + self._suit

