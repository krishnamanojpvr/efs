/* Find employees who earn more than the highest-paid employee in department 10.

Expected Output Columns:
------------------------
+-------+---------+--------+
| ename | sal     | deptno |
+-------+---------+--------+

*/
USE test;
select ename,sal,deptno from emp where sal>(select max(sal) from emp where deptno=10  )