# Write python code for a method which takes a String (a sentence) as input 
# and returns a Map. The Map key is String (word in the String) and 
# value is frequency of the word in the given sentence.
# (In the words ignore any special characters)

# Sample Input:
# -------------
# Java is fun, and Ja#va@ is powerful 

# Sample Output:
# --------------
# java: 2
# is: 2
# fun: 1
# and: 1
# powerful: 1

word = input()
str = ""
for i in word:
    if i==" " or i.isalpha():
        str+=i
str = str.split()
d = {}
for i in str:
    d[i.lower()] = d.get(i.lower(),0)+1
for i,j in d.items():
    print(f"{i}: {j}")