# Write a python program to find the nth prime number. 
# The value of n should be input by the user.

# Sample Input:
# ---------------
# 5

# Sample Output:
# -----------------
# 11

import math
n = int(input())
def prime(n):
    if n<2:
        return False
    for i in range(2,int(math.sqrt(n))+1):
        if(n%i==0):
            return False
    return True

count = 0
i = 1
while(count<n):
    if(prime(i)):
        count+=1
    i+=1
print(i-1)
