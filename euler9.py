# All numbers are either prime or composites of prime numbers. The same can be said about
# Pythagorean triples: All Pythagorean triples are base sets or a scaled base set. This
# program answers Project Euler problem 9 (https://projecteuler.net/problem=9) with this
# principle in mind. 
#
# First, the program will calculate a Pythagorean triple base set
# Then, it will determine if this base set has a sum that is a multiple of the target value
# Finally, it will calculate the scale necessary to reach the scaled set of the base set
#
# For target = 1000, this will complete in 4 iterations of the loop.

import time

# start a time in nanoseconds
start = time.time_ns()

# number that we are attempting to find the pythagorean triples that sum to
target = 1000

# variable for the base number to calculate the pythagorean triple off of.
x = 1
flag = True
# base pythagorean triple values
a = 0
b = 0
c = 0

while flag:
    # increment calculator base
    x += 1
    # calculate the base pythagorean triple values
    # Equation based on Plato's formula for finding Pythagorean triples:
    #  (2m)^2 + (m^2 - 1)^2 = (m^2 + 1)^2
    a = (2 * x)
    b = (x ** 2 - 1)
    c = (x ** 2 + 1)

    # if the base set added together is a multiple of our target, we have found our match
    #   This is something Daul pointed out to me. Full credit goes there...
    if target % (a + b + c) == 0:
        mod = int(target / (a + b + c))
        a = a * mod
        b = b * mod
        c = c * mod
        break
        
    # break from infinite loop when no possible solution can be found
    if c == target:
        print("Cannot find a solution...")
        a = -1
        b = -1
        c = -1
        break

# stop the timer
print(time.time_ns() - start)
# print the found values
print("a:", a, "  b:", b, "  c:", c, "  sum:", a + b + c, "  project euler answer:", a * b * c)
