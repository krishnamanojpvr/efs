'''

Write a python program, for given a birthdate in yyyy-MM-dd format, calculate 
the person’s current age in years, months, and days.

Input:
------
1990-05-25

Output:
-------
Age: 35 years, 0 months, 16 days

'''

from datetime import date
from dateutil.relativedelta import relativedelta

x = list(input().split("-"))
for i in range(len(x)):
    x[i] = int(x[i])
d1 = date(x[0],x[1],x[2])
d2 = date(2025,6,10)
diff = relativedelta(d2,d1)
print(f"Age: {diff.years} years, {diff.months} months, {diff.days} days")