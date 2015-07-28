#!/usr/bin/env python3
import csv
import sys
import scipy.stats

if len(sys.argv) == 1:
    exit()

freqs = [0 for x in range(6)]
with open(sys.argv[1]) as f:
    r = csv.reader(f)
    for row in r:
        face = int(row[0])
        freqs[face - 1] += 1

c, p = scipy.stats.chisquare(freqs)
print("P-value: %f" % p)
