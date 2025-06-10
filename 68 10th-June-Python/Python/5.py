'''
Problem: Write a program to count each word's first index and total occurrences 
in a sentence.

Sample Input: 
apple banana apple orange banana apple

Sample Output:
apple -> first index: 0, count: 3
banana -> first index: 6, count: 2
orange -> first index: 19, count: 1

'''

line = input().split()
d = {}
i = 0
for word in line:
    if word in d:
        d[word][0] = d[word][0] + 1
    else:
        d[word] = [1,i]
    i+=len(word)+1
for i,j in d.items():
    print(f"{i} -> first index: {j[1]}, count: {j[0]}")

