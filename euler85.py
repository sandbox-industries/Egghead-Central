# No. This is not efficient.
# No. I don't care.
# Yes. I am leaving it as it is.
# The answer is right.

row = 10
col = 10
count = 0

target = 2000000

while True:
    col += 1
    # arbitrary value
    if col > 100:
        col = 10
        row += 1
    # arbitrary end condition
    if row > 100:
        break
    count = 0

    # determine the number of shapes per row/col based on initialized values
    # basically determines smaller "sub-grids" of the full grid that provides
    #  the number of the row x col shapes that fit
    for i in range(1, row + 1):
        r = 0
        for ii in range(1, row + 1):
            if ii + i <= row + 1:
                r += 1
        for j in range(1, col + 1):
            c = 0
            for jj in range(1, col + 1):
                if jj + j <= col + 1:
                    c += 1
            count += r*c

    # through testing, found the minimum possible score, used this as condition to catch first instance of it
    if target - count == 2:
        break

print(count)
print(row, col, row * col)
