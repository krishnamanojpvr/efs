'''
Write a python program to read a comma-separated values file and print its 
contents in table format, replacing commas with tabs or spaces.

Input File: 
------
file.csv

Output:
-------
name age
John 20
Jane 25

Explanation:
-------------
File contains:- 

name,age
John,20
Jane,25

'''

import csv
filename = input() 
with open(filename) as f:
    file = csv.reader(f);
    for row in file:
        print(" ".join(row))


