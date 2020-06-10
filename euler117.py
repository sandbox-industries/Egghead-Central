# A-Restricted partitions
# This will count all of the UNIQUE permutations of shapes.
#  4 = 1+2+1 = 1+1+2 = 2+1+1 but only one is counted as unique

# initialize the array to hold the values
l = [1]
for i in range(50):
    l.append(0)

# Partition sizes
for i in range(1, 5):
    # run through array, stopping before overrunning the total length
    for j in range(len(l)-i):
        l[i+j] = l[i+j] + l[j]

print(l)
print(l[50], "\n")

#
#
# ++++++++++++++++++++++++++
#
#

# A-Restricted compositions
# This will could ALL of the possible permutations of shapes.
#  4 = 1+2+1 = 1+1+2 = 2+1+1 and all three are counted

# initialize the array to hold the values
line = [1]
for i in range(50):
    line.append(0)

# length of array
for i in range(len(line)):
    # size of partitions
    for j in range(1, 5):
        # long partitions don't fit when the total length is too short
        if i - j >= 0:
            line[i] = line[i] + line[i - j]

print(line)
print(line[50])
