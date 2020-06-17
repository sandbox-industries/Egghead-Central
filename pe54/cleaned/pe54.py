import time
from hand import Hand
from card import Card

# map the card string values to their integer representations
card_map = {'2': 2,  '3': 3,  '4': 4,  '5': 5,  '6': 6,  '7': 7, '8': 8, '9': 9,
            'T': 10, 'J': 11, 'Q': 12, 'K': 13, 'A': 14, }

p1_wins = 0

with open('pe54.txt', 'r') as file:
    dat = file.readlines()

start = time.time()

for line in dat:
    p1 = Hand()
    p2 = Hand()

    cards = line.split(" ")

    # run through the cards for player 1
    for x in cards[:5]:
        p1.add_card(Card(card_map[x[0]], x[1]))

    # run through the cards for player 2
    for x in cards[5:]:
        p2.add_card(Card(card_map[x[0]], x[1]))

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

stop = time.time()
print("duration (sec):", stop-start)
print(p1_wins)

