'''

Write a Python code to return the length of longest sub-string without repeating 
characters

Sample Input: 
-------------
abcabcbb

Sample Output: 
--------------
3

'''
word = input()
c = 0;
set = set()
l=0
for r in range(len(word)):
    if(word[r] in set):
        if(word[l] in set):
            set.remove(word[l])
        l+=1
    set.add(word[r])
    c = max(c,len(set))
print(c)
        