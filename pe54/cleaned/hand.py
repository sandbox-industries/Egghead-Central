class Hand:
    def __init__(self):
        """
        Initialize the hand object with its internal variables.
        """
        self._hand = []
        self._high_card = None
        self._hand_value = -1
        self._pair_value = -1
        self._set_h = set()
        self._set_s = set()
        self._uniques = {}

    def add_card(self, card) -> bool:
        """
        Public function to add a given card to the hand. This will only occur if there
        are fewer than 5 cards in the hand. Once 5 cards have been added to the hand,
        this will fire off a function to calculate the value of the hand

        :param card: Object, reference to the card object to add to this hand
        :return: bool, if the card was successfully added to the hand
        """
        if len(self._hand) < 5:
            self._hand.append(card)
            self._hand.sort(key=lambda x: x.get_value(), reverse=True)
            self._high_card = self._hand[0]
            self._set_h.add(card.get_value())
            self._set_s.add(card.get_suit())
            if len(self._hand) == 5:
                self._calculate_hand_value()
            return True
        return False

    def get_high_card(self) -> object:
        """
        Public function to return the highest value card object in the hand

        :return: object, the object reference to the highest value card in the hand
        """
        return self._high_card

    def get_hand_value(self) -> int:
        """
        Public function to return the value of the hand

        :return: int, the hand value as previously calculated by the object
        """
        return self._hand_value

    def get_pair_value(self) -> int:
        """
        Public function to get the value of the highest pair in the hand
        -1 is an unset pair, meaning there are no pairs in this hand.

        :return: int, the pair value as previously calculated by the object
        """
        return self._pair_value

    def _calculate_hand_value(self):
        """
        Internal function to calculate the value of a hand given by the following list:
        9 : Royal Flush
        8 : Straight Flush
        7 : Four-of-a-kind
        6 : Full House
        5 : Flush
        4 : Straight
        3 : Three-of-a-kind
        2 : Two Pair
        1 : One Pair
        0 : High Card

        If the hand is of a higher value, lower values are not checked. This ensures
        that hand values are correctly assigned and not overwritten with a lower value
        """
        self._calc_uniques()
        val = 0
        if self._is_royal():
            val = 9
        elif self._is_straight_flush():
            val = 8
        elif self._is_four_of_a_kind():
            val = 7
        elif self._is_full_house():
            val = 6
        elif self._is_flush():
            val = 5
        elif self._is_straight():
            val = 4
        elif self._is_three_of_a_kind():
            val = 3
        elif self._is_two_pairs():
            val = 2
        elif self._is_single_pair():
            val = 1
        self._hand_value = val

    def _calc_uniques(self):
        """
        Creates a dictionary of unique values and the count of those values
        within the hand.
        """
        for card in self._hand:
            val = card.get_value()
            if val not in self._uniques.keys():
                self._uniques[val] = 1
            else:
                self._uniques[val] += 1

    def _is_royal(self) -> bool:
        """
        Internal function to check if the hand has a royal flush where all
        of the cards have to be of a specific value and of the same suit

        :return: bool, if there is a royal flush in the hand
        """
        set_r = {14, 13, 12, 11, 10}
        if self._set_h == set_r and len(self._set_s) == 1:
            return True
        return False

    def _is_straight_flush(self) -> bool:
        """
        Internal function to check if the hand has a straight flush
        This function calls both straight and flush and both must return true

        :return: bool, if there is a straight flush in the hand
        """
        if self._is_straight() and self._is_flush():
            return True
        return False

    def _is_four_of_a_kind(self) -> bool:
        """
        Internal function to check if a hand has four-of-a-kind.
        Pair value is used in tie breakers to determine who has the largest
        value of cards in the pair.

        :return: bool, if there is a four-of-a-kind in the hand
        """
        if 4 not in self._uniques.values():
            return False
        for key, v in self._uniques.items():
            if v == 4:
                self._pair_value = key
        return True

    def _is_full_house(self) -> bool:
        """
        Internal function to determine if the hand has a full house where
        the hand has both three of a kind as well as a single pair.

        :return: bool, if there is a full house in the hand
        """
        if self._is_three_of_a_kind() and self._is_single_pair():
            return True
        return False

    def _is_flush(self) -> bool:
        """
        Internal function to determine if the hand has a flush, where all
        of the cards in the hand have the same suit. Just iterate over the
        entire hand and return if there is a mismatch

        :return: bool, if there is a flush in the hand
        """
        if len(self._set_s) > 1:
            return False
        return True

    def _is_straight(self) -> bool:
        """
        Internal function to determine if the hand has a straight.
        All of the cards in the hand must be consecutive. Just iterate
        over the entire hand and return false if there is a mismatch

        :return: bool, if there is a straight in the hand
        """
        prev = None
        for c in self._hand:
            if prev is not None and prev.get_value() - c.get_value() != 1:
                return False
            prev = c
        return True

    def _is_three_of_a_kind(self) -> bool:
        """
        Internal function to determine if the hand has a three-of-a-kind match
        This function is similar to four-of-a-kind.
        Pair value is used in tie breakers to determine who has the largest
        value of cards in the pair.

        :return: bool, if there is a three-of-a-kind in the hand
        """
        if 3 not in self._uniques.values():
            return False
        for key, v in self._uniques.items():
            if v == 3:
                self._pair_value = key
        return True

    def _is_two_pairs(self) -> bool:
        """
        Internal function to determine if the hand has two individual pairs.
        This function is not not called in conjunction with anything else.
        Due to the math of the cards in the hand, this will only return true if
        there are three cards within the set of cards.
        Pair value is used in tie breakers to determine who has the largest
        value of cards in the pair.

        :return: bool, if there are two pairs within the hand
        """
        if len(self._set_h) != 3:
            return False
        for key, v in self._uniques.items():
            if v == 2:
                if key > self._pair_value:
                    self._pair_value = key
        return True

    def _is_single_pair(self) -> bool:
        """
        Internal function to determine if the hand has a single pair. Because
        of the other functions and where this is called from, this does not
        need to check for anything outside of every card being unique.
        Pair value is used in tie breakers to determine who has the largest
        value of cards in the pair.

        :return: bool, if there is a single pair within the hand
        """
        if 2 not in self._uniques.values():
            return False
        for key, v in self._uniques.items():
            if v == 2 and self._pair_value == -1:
                self._pair_value = key
        if self._pair_value == -1:
            return False
        return True
