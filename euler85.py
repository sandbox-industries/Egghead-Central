# No. This is not efficient.
# No. I don't care. 
# Yes. I am leaving it as it is. 
# The answer is right.

row = 1
col = 2
count = 0

# arbitrary end condition
while count < 1990000:
    # initialize values each iteration
    row += 1
    if row > 100:
        col += 1
        row = 2
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

print(count)
print(row, col, row * col)
