/* Find the department name where ‘JONES’ works.

Expected Output Columns:
------------------------
+----------+
| dname    |
+----------+

*/
USE test;
select dname from dept join emp using(deptno) where ename='JONES';