/* Find employees who earn more than the average salary of all employees.

Expected Output Columns:
------------------------
+-------+---------+
| ename | sal     |
+-------+---------+

*/
USE test;
select ename ,sal from emp where sal>(select avg(sal) from emp);