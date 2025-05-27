/* Find employees who belong to departments located in 'Dallas'.

Expected Output Columns:
------------------------
+-------+--------+
| ename | deptno |
+-------+--------+

*/
USE test;
select e.ename,e.deptno from emp e join dept d using (deptno) where d.location = 'DALLAS';