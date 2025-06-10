'''

Problem: 
Write Python code to identify if given two strings are Anagrams 
(strings that contain same set of alphabets)

Sample Input: 
-------------
listen silent

Sample Output: 
--------------
true

'''

from collections import Counter

s1,s2 = input().split()


map1 = Counter(s1)
map2 = Counter(s2)

print("true" if map1==map2 else "false")