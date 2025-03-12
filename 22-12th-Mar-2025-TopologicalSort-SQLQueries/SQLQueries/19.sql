/* Perform Sum of salaries and commissions by dept 30 

Expected Output Columns:
+----------+------------+
| TotalPay | TotalBonus |
+----------+------------+

*/
USE test;
select sum(sal) as TotalPay , sum(comm) as TotalBonus from emp where deptno = 30;