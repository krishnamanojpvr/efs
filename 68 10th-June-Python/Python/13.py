'''

Write a python program that reads a sentence and counts how many times each word 
appears. Display only the words that occur more than once.

Input: 
------
this is a test this test is easy

Output:
-------
this -> 2
is -> 2
test -> 2

'''

from collections import Counter

sentance = Counter(input().split())

for i,j in sentance.items():
    if(j>1):
        print(f"{i} -> {j}")