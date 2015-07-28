#!/usr/bin/env python

import csv

print "Hello World!"

total = 0
n = 0
with open('test.csv', 'rb') as f:
    rdr = csv.reader(f)
    for row in rdr:
        total += int(row[0])
        n += 1

print "total : %d" % total
print "average : %f" % (total * 1.0 / n)
print ""

# position  0  1  2  3  4  5
faceFreq = [0, 0, 0, 0, 0, 0]
with open('test.csv', 'rb') as f:
    rdr = csv.reader(f)
    for row in rdr:
        face = int(row[0])
        faceFreq[face - 1] += 1

for i in range(6):
    print "%d : %d" % (i + 1, faceFreq[i])
