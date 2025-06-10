'''

Write a program that reads a sentence, count and display the total number of 
vowels and consonants.

Input: 
------
Hello World

Output:
-------
Vowels: 3, Consonants: 7

'''

from collections import Counter

d = Counter(input().lower())
v = 0
c = 0
for i,j in d.items():
    if i in ['a','e','i','o','u']:
        v+=j
    elif i!=" ":
        c+=j
print(f"Vowels: {v}, Consonants: {c}")