'''

Write a python program that reads two timestamps (yyyy-MM-dd HH:mm:ss format) and
display the time elapsed between them in minutes and seconds.

Input: 
------
2025-06-04 10:30:00
2025-06-04 11:15:40

Output: 
-------
Elapsed: 45 minutes 40 seconds

'''

from datetime import datetime

d1 = input()
d2 = input()

d1 = datetime.strptime(d1,"%Y-%m-%d %H:%M:%S")
d2 = datetime.strptime(d2,"%Y-%m-%d %H:%M:%S")
d = d2-d1
d = int(d.total_seconds())

print(f"Elapsed: {abs(d//60)} minutes {abs(d%60)} seconds")

