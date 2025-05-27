/* Find the second highest salary from employees.

Expected Output Columns:
------------------------
+-----------------------+
| second_highest_salary |
+-----------------------+

*/
USE test;
select sal as second_highest_salary from emp order by sal desc limit 2,1;