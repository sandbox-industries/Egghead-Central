class Hand:
    def __init__(self):
        # container for the cards in the hand
        self._hand = []
        # pointer to the highest card in the hand
        self._high_card = None
        # calculated value of the hand
        self._hand_value = -1
        # the value of the largest pair in the hand
        self._pair_value = -1

    # public function to add a card to the hand. This will only occur if there are
    # fewer than 5 cards in the hand. Once 5 cards have been added to the hand, this
    # will fire off a function to calculate the value of the hand
    def add_card(self, card):
        if len(self._hand) < 5:
            self._hand.append(card)
            self._hand.sort(key=lambda x: x.get_value(), reverse=True)
            self._high_card = self._hand[0]
            if len(self._hand) == 5:
                self._calculate_hand_value()
            return True
        return False

    # public function to retrieve the card container for the hand
    def get_hand_cards(self):
        return self._hand

    # public function to set the card container for the hand to a passed in container of cards
    def set_hand_cards(self, hand):
        try:
            self._hand = hand
            return True
        except:
            return False

    # public function to retrieve a single card from the hand given the position of that card in the hand container
    def get_card(self, pos):
        return self._hand[pos]

    # public function to set a single card to a position in the hand container
    def set_card(self, pos, card):
        try:
            self._hand[pos] = card
            return True
        except:
            return False

    # public function to return the highest value card object in the hand
    def get_high_card(self):
        return self._high_card

    # public function to return the value of the hand
    def get_hand_value(self):
        return self._hand_value

    # public function to get the value of the highest pair in the hand
    # -1 is an unset pair, meaning there are no pairs in this hand...
    def get_pair_value(self):
        return self._pair_value

    # internal function to calculate the value of a hand as given by the following list:
    # 0 : High Card
    # 1 : One Pair
    # 2 : Two Pair
    # 3 : Three of a Kind
    # 4 : Straight
    # 5 : Flush
    # 6 : Full House
    # 7 : Four of a Kind
    # 8 : Straight Flush
    # 9 : Royal Flush
    # Starts at Royal Flush and runs to the end of the types of hands.
    def _calculate_hand_value(self):
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

    # internal function to check if the hand is a royal flush
    # All of the cards have to be of a specific value and of the same suit
    def _is_royal(self):
        set_v = [14, 13, 12, 11, 10]
        suit = self._hand[0].get_suit()
        for i, card in enumerate(self._hand):
            if card.get_value() != set_v[i] or card.get_suit() != suit:
                return False
        return True

    # internal function to check if a hand is a straight flush
    #  the hand must be both a straight and a flush
    def _is_straight_flush(self):
        if self._is_straight() and self._is_flush():
            return True
        return False

    # internal function to check if a hand has four of a kind
    #  this will count all of the instances of the cards and check to see if
    #  four of a single value have been found. A pair value will be set if
    #  a four of a kind pair exists in the hand. This is used to determine tie
    #  breakers.
    def _is_four_of_a_kind(self):
        uniques = {}
        for card in self._hand:
            val = card.get_value()
            if val not in uniques.keys():
                uniques[val] = 1
            else:
                uniques[val] += 1
        if 4 not in uniques.values():
            return False
        for key, v in uniques.items():
            if v == 4:
                self._pair_value = key
        if self._pair_value == -1:
            return False
        return True

    # internal function to determine if the hand has a full house
    # the hand must be both a three of a kind as well as a single pair
    def _is_full_house(self):
        if self._is_three_of_a_kind() and self._is_single_pair():
            return True
        return False

    # internal function to determine if the hand has a flush
    # all of the cards in the hand must have the same suit. Just iterate
    #  over the entire hand and return if there is a mismatch
    def _is_flush(self):
        suit = self._hand[0].get_suit()
        for card in self._hand:
            if card.get_suit() != suit:
                return False
        return True

    # internal function to determine if the hand has a straight
    # all of the cards in the hand must be consecutive. Just iterate
    #  over the entire hand and return if there is a mismatch
    def _is_straight(self):
        for i in range(4):
            if self._hand[i].get_value() - 1 != self._hand[i + 1].get_value():
                return False
        return True

    # internal function to determine if the hand has a three of a kind
    # This function is similar to four of a kind, but with different internal conditions
    def _is_three_of_a_kind(self):
        uniques = {}
        for card in self._hand:
            val = card.get_value()
            if val not in uniques.keys():
                uniques[val] = 1
            else:
                uniques[val] += 1
        if 3 not in uniques.values():
            return False
        for key, v in uniques.items():
            if v == 3:
                self._pair_value = key
        if self._pair_value == -1:
            return False
        return True

    # internal function to determine if the hand has two individual pairs
    # this function is similar to four of a kind and three of a kind, but it is
    #  not called from anywhere and is mutually exclusive. Due to the math of the
    #  cards in the hand, this will only return true if there are three sets of
    #  cards.
    def _is_two_pairs(self):
        uniques = {}
        for card in self._hand:
            val = card.get_value()
            if val not in uniques.keys():
                uniques[val] = 1
            else:
                uniques[val] += 1
        if len(uniques.keys()) > 3 or 2 not in uniques.values():
            return False
        for key, v in uniques.items():
            if v == 2:
                if key > self._pair_value:
                    self._pair_value = key
        if self._pair_value == -1:
            return False
        return True

    # internal function to determine if the hand has a single pair.
    # because of the other functions and where this is called, this does not
    # need to check for anything outside of every card being unique.
    def _is_single_pair(self):
        uniques = {}
        for card in self._hand:
            val = card.get_value()
            if val not in uniques.keys():
                uniques[val] = 1
            else:
                uniques[val] += 1
        if len(uniques.keys()) == 5 or 2 not in uniques.values():
            return False
        for key, v in uniques.items():
            if v == 2 and self._pair_value == -1:
                self._pair_value = key
        if self._pair_value == -1:
            return False
        return True

