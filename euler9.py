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

# stop the timer
print(time.time_ns() - start)
# print the found values
print("a:", a, "  b:", b, "  c:", c, "  sum:", a + b + c, "  project euler answer:", a * b * c)
