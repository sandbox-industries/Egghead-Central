from hand import Hand
from card import Card

# map the card string values to their integer representations
card_map = {'2': 2,  '3': 3,  '4': 4,  '5': 5,  '6': 6,  '7': 7, '8': 8, '9': 9,
            'T': 10, 'J': 11, 'Q': 12, 'K': 13, 'A': 14, }

# container for the number of times that player 1 wins
p1_wins = 0

# read the text file containing pairs of hands
with open('pe54.txt', 'r') as file:
    dat = file.readlines()

# run through each pair of hands (each line contains one pair of hands)
for line in dat:
    # define the containers for the hands of player 1 and player 2
    p1 = Hand()
    p2 = Hand()

    # split the cards by the spaces between them
    cards = line.split(" ")

    # run through the cards for player 1
    for i in range(5):
        val = card_map[cards[i][0]]
        suit = cards[i][1]
        card = Card(val, suit)
        p1.add_card(card)

    # run through the cards for player 2
    for i in range(5, 10):
        val = card_map[cards[i][0]]
        suit = cards[i][1]
        card = Card(val, suit)
        p2.add_card(card)

    # Determine who won the hand based on the calculated values.
    # highest hand value wins, a tie proceeds to compare the pair values (if there is a pair)
    #  or will compare the value of the highest card in the hand.
    if p1.get_hand_value() > p2.get_hand_value():
        p1_wins += 1
    elif p1.get_hand_value() < p2.get_hand_value():
        pass
    else:
        if p1.get_hand_value() in [7, 3, 2, 1]:
            if p1.get_pair_value() > p2.get_pair_value():
                p1_wins += 1
        else:
            if p1.get_high_card().get_value() > p2.get_high_card().get_value():
                p1_wins += 1

# Project Euler only cares about how many games player 1 wins in the given file
print(p1_wins)

