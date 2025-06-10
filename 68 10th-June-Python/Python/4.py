# Write a python program to convert a decimal number to binary using both
# 1. Recursive method
# 2. Iterative method

# Implement the methods in Solution class and return the binary number.

# Sample Input:
# ---------------
# 10

# Sample Output:
# ------------------
# Binary (Recursive): 1010
# Binary (Iterative): 1010

def recur(n):
    if n == 0:
        return ""
    return recur(n//2)+str(n % 2)


def iter(n):
    x = ""
    while (n > 0):
        x = str(n % 2) + x
        n = n//2
    return x


n = int(input())
print(f"Binary (Recursive): {recur(n)}")
print(f"Binary (Iterative): {recur(n)}")
