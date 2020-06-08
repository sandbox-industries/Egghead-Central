# A-Restricted partitions

l = []

for i in range(51):
    l.append(0)

l[0] = 1

print(l)

for i in range(1, 5):
    for j in range(51-i):
        l[i+j] = l[i+j] + l[j]

print(l)
print(l[50])

#
#
# ++++++++++++++++++++++++++
#
#

# A-Restricted compositions
line = []

for i in range(51):
    line.append(0)

line[0] = 1

print(line)

for i in range(51):
    for j in range(1, 5):
        if i - j >= 0:
            line[i] = line[i] + line[i - j]

print(line)
print(line[50])
