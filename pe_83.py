import sys

from mapNode import Node


FILE_PATH = "matrix.txt"

head = Node()
tail = Node()
prev_nodes = []


def processFile():
    """
    Processes the data file line-by-line and passes the line data to the map builder

    :return: None
    """
    print("process file")
    with open(FILE_PATH, 'r') as f:
        for idx, line in enumerate(f.readlines()):
            buildMap(line.split(","), idx)


def buildMap(line_values, line):
    """
    Builds the map data structure from the lines passed in by the file processor

    :param line_values: the values of the line split along the commas
    :param line: the line number that the program is on
    :return: None
    """
    # reference the global head and prev_nodes variables instead of making a local variable
    global head, prev_nodes
    print("build map line:", str(line), str(len(line_values)))
    curr_nodes = []
    for i, value in enumerate(line_values):
        curr = Node(line, i, int(value))
        # first line
        if not len(prev_nodes):
            # first element
            if not len(curr_nodes):
                curr.set_pathSum(curr.get_value())
                head = curr
            # all others on the first line
            else:
                curr.set_left(curr_nodes[i - 1])
                curr_nodes[i - 1].set_right(curr)
        # not the first line
        else:
            curr.set_up(prev_nodes[i])
            prev_nodes[i].set_down(curr)
            # excludes the first element in a row, which doesn't have an element to the left
            if i > 0:
                # set the left reference
                curr.set_left(curr_nodes[i - 1])
                curr_nodes[i - 1].set_right(curr)
        # add the current element to a reference of the elements on the current line
        curr_nodes.append(curr)
    # set the reference to the elements of the current line to the previous line and return to the file handler
    prev_nodes = curr_nodes


def sort_support(e: Node):
    return e.get_distance() + e.get_value()


def findPath(step: Node):
    """
    Will recursively find the path with the lowest tottal value from the initial passed node to the very last node

    :param step: the current element from which the algorithm is looking forward
    :return: None
    """
    # reference the global tail variable instead of making a local variable
    global tail

    # we have reached the end of the table, so return so recursion can continue
    if step is tail:
        return

    # container for all the viable direction options
    opts = []

    # containers for the different direction options
    # we do not know where we came from, so we include all directions for now
    up: Node = step.get_up()
    right: Node = step.get_right()
    down: Node = step.get_down()
    left: Node = step.get_left()

    # only add the up direction if it exists and we have not gone there before with a shorter path
    if up is not None and up.get_pathSum() > step.get_pathSum() + up.get_value():
        opts.append(step.get_up())

    # only add the right direction if it exists and we have not gone there before with a shorter path
    if right is not None and right.get_pathSum() > step.get_pathSum() + right.get_value():
        opts.append(step.get_right())

    # only add the down direction if it exists and we have not gone there before with a shorter path
    if down is not None and down.get_pathSum() > step.get_pathSum() + down.get_value():
        opts.append(step.get_down())

    # only add the left direction if it exists and we have not gone there before with a shorter path
    if left is not None and left.get_pathSum() > step.get_pathSum() + left.get_value():
        opts.append(step.get_left())

    # sort the list with the help of a supporting function
    opts.sort(key=sort_support)

    for opt in opts:
        # set the next path sum
        opt.set_pathSum(opt.get_value() + step.get_pathSum())
        # recursively call the next option
        findPath(opt)


if __name__ == "__main__":
    print("main")
    processFile()
    tail = prev_nodes[len(prev_nodes) - 1]
    sys.setrecursionlimit(5000)
    findPath(head)
    print(tail.get_pathSum())
