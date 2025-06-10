'''

Write a program that finds the longest substring that reads the same forwards 
and backwards.

Input: 
------
babad

Output: 
-------
bab or aba

'''

def palindrome(x):
    return x==x[::-1]
a = ""
c = 0
x = input()
for i in range(len(x)):
    for j in range(1,len(x)+1):
        if(palindrome(x[i:j]) and len(x[i:j])>c):
            a = x[i:j]
            c = len(x[i:j])
print(a)