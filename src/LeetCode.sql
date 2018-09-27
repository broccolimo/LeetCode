#175 Combine Two Tables(左外连接)
select 
	p.FirstName, p.LastName, a.City, a.State 
from 
	Person p left join Address a 
on 
	p.PersonId = a.PersonId;
 
 
#176 Second Highest Salary(第二高)
select 
	max(Salary) as SecondHighestSalary 
from 
	Employee 
where 
	Salary <> (select max(Salary) from Employee);


#177 Nth Highest Salary(第n高)(DECLARE/SET加分号 只有SQL语句不加分号)
CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
DECLARE M INT;
SET M = N-1;
  RETURN (
      # Write your MySQL query statement below.
      SELECT DISTINCT Salary FROM Employee ORDER BY Salary DESC LIMIT M, 1
  );
END


#178 Rank Scores
SELECT 
	Score, 
	(SELECT 
		COUNT(DISTINCT Score) 
	FROM 
		Scores 
	WHERE 
		Score >= s.Score) Rank 
FROM 
	Scores s 
ORDER BY Score DESC;


#180 Consecutive Numbers(多表查询的结果是笛卡儿积)
select 
	distinct l1.Num ConsecutiveNums 
from 
	logs l1, logs l2, logs l3 
where 
	l1.Id = l2.Id-1 and l1.Id = l3.Id-2 and l1.Num = l2.Num and l1.Num = l3.Num;
	

#181 Employees Earning More Than Their Managers
#虽然只有一个表，但仍然是多表查询
#假如表中有4条记录，这样连接一共会产生4*4=16条记录
select 
	e1.Name Employee 
from 
	Employee e1, Employee e2
where 
	e1.Salary > e2.Salary and e1.ManagerId = e2.Id;

#如果用外连接可以省去一些无用的连接
#runtime确实减少了
#针对此题的实例，多表查询的记录数相较于上边少了16-4=12条
select 
	e1.Name Employee 
from 
	Employee e1 left join Employee e2 on e1.ManagerId = e2.Id 
where 
	e1.Salary > e2.Salary;


#182 Duplicate Emails
#此题最佳解法并不是用多表查询，但多表查询也能做出来
select
    distinct p1.Email
from
    Person p1 left join Person p2 on p1.Email = p2.Email
where
    p1.Id <> p2.Id;
    
#最佳解法是使用group by + having
select Email from Person group by Email having count(Email) > 1;


#183 Customers Who Never Order
select 
    Name Customers
from
    Customers
where
    Id not in (select CustomerId from Orders);
    
    
#184 Department Highest Salary
select 
	d.Name as Department, 
	e.Name as Employee, 
	e.Salary 
from 
	Employee e, 
	Department d,
	(select DepartmentId, max(Salary) as Salary from Employee group by DepartmentId) t
where 
	e.Salary = t.Salary and
	e.DepartmentId = t.DepartmentId and
	t.DepartmentId = d.Id
	

#185 Department Top Three Salaries
#1.使用内连接 否则会出现null的情况
#2.必须加distinct 因为不是按人数排的 而是按薪水排的
select 
	d.Name as Department, e.Name as Employee, e.Salary 
from 
	Department d join Employee e on d.Id = e.DepartmentId 
where 
	(select count(distinct Salary) from Employee where Salary > e.Salary and DepartmentId = e.DepartmentId)  < 3 
order 
	by d.Id, e.Salary desc;
	
#196 Delete Duplicate Emails
#You can't specify target table for update in FROM clause
#不能一边查一遍删 
#Every derived table must have its own alias
#派生表必须有别名
#如要解决此题情况 要用中间表承接值 即把语句放入from里边再查一次
#注意列名要相同 如果不相同 要as一下
delete from Person where Id not in (select Id from (select min(Id) as Id from Person group by Email) temp);


#197 Rising Temperature
select w1.Id from Weather w1, Weather w2 where datediff(w1.Date, w2.Date) = 1 and w1.Temperature > w2.Temperature;

#262 Trips and Users
select 
	t.Request_at as Day, 
	round(sum(t.Status like 'cancelled_by_%')/count(*), 2) as "Cancellation Rate" 
from 
	Trips t inner join Users u on 
		t.Client_Id = u.Users_Id and 
		u.Banned = 'No' and 
		t.Request_at between "2013-10-01" and "2013-10-03" 
group by t.Request_at;

#620 Not Boring Movies
select *from cinema where id % 2 = 1 and description <> "boring" order by rating desc;

#626 Exchange Seats
#case的用法
#when的用法
#派生表必须有表名
select 
	(case 
		when mod(id, 2) != 0 and id != counts then id + 1
		when mod(id, 2) != 0 and id = counts then id
		else id - 1
	end) as id, 
	student 
from 
	seat, (select count(*) as counts from seat) as _seat 
order by id;
 





