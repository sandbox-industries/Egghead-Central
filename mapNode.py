import sys
import math

# zero indexed height/width
MAX_HEIGHT = 79
MAX_WIDTH = 79


class Node:
    def __init__(self, row=-1, column=-1, value=-1):
        # where the node is located in the map
        self._row = row
        self._column = column

        # the value of the node
        self._value = value

        # calculate the distance from the root of the graph
        self._distance = self._calcDistance()

        # pointers
        self._left = None
        self._right = None
        self._up = None
        self._down = None

        # default the path sum to a very big number
        self._pathSum = sys.maxsize

    def _calcDistance(self):
        return math.sqrt(abs(MAX_WIDTH - self._row)**2 + abs(MAX_HEIGHT - self._column)**2)

    def __str__(self):
        return "Node Value: " + str(self._value) + "\nCumulative Sum: " + str(self._pathSum)

    # setters

    def set_row(self, row):
        self._row = row

    def set_column(self, column):
        self._column = column

    def set_value(self, val):
        self._value = val

    def set_pathSum(self, val):
        self._pathSum = val

    def set_up(self, node):
        self._up = node

    def set_down(self, node):
        self._down = node

    def set_left(self, node):
        self._left = node

    def set_right(self, node):
        self._right = node

    # getters

    def get_value(self):
        return self._value

    def get_pathSum(self):
        return self._pathSum

    def get_left(self):
        return self._left

    def get_right(self):
        return self._right

    def get_up(self):
        return self._up

    def get_down(self):
        return self._down

    def get_distance(self):
        return self._distance

    def get_column(self):
        return self._column

    def get_row(self):
        return self._row
