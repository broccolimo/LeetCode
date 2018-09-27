#175 Combine Two Tables(��������)
select 
	p.FirstName, p.LastName, a.City, a.State 
from 
	Person p left join Address a 
on 
	p.PersonId = a.PersonId;
 
 
#176 Second Highest Salary(�ڶ���)
select 
	max(Salary) as SecondHighestSalary 
from 
	Employee 
where 
	Salary <> (select max(Salary) from Employee);


#177 Nth Highest Salary(��n��)(DECLARE/SET�ӷֺ� ֻ��SQL��䲻�ӷֺ�)
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


#180 Consecutive Numbers(����ѯ�Ľ���ǵѿ�����)
select 
	distinct l1.Num ConsecutiveNums 
from 
	logs l1, logs l2, logs l3 
where 
	l1.Id = l2.Id-1 and l1.Id = l3.Id-2 and l1.Num = l2.Num and l1.Num = l3.Num;
	

#181 Employees Earning More Than Their Managers
#��Ȼֻ��һ��������Ȼ�Ƕ���ѯ
#���������4����¼����������һ�������4*4=16����¼
select 
	e1.Name Employee 
from 
	Employee e1, Employee e2
where 
	e1.Salary > e2.Salary and e1.ManagerId = e2.Id;

#����������ӿ���ʡȥһЩ���õ�����
#runtimeȷʵ������
#��Դ����ʵ��������ѯ�ļ�¼��������ϱ�����16-4=12��
select 
	e1.Name Employee 
from 
	Employee e1 left join Employee e2 on e1.ManagerId = e2.Id 
where 
	e1.Salary > e2.Salary;


#182 Duplicate Emails
#������ѽⷨ�������ö���ѯ��������ѯҲ��������
select
    distinct p1.Email
from
    Person p1 left join Person p2 on p1.Email = p2.Email
where
    p1.Id <> p2.Id;
    
#��ѽⷨ��ʹ��group by + having
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
#1.ʹ�������� ��������null�����
#2.�����distinct ��Ϊ���ǰ������ŵ� ���ǰ�нˮ�ŵ�
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
#����һ�߲�һ��ɾ 
#Every derived table must have its own alias
#����������б���
#��Ҫ���������� Ҫ���м��н�ֵ ����������from����ٲ�һ��
#ע������Ҫ��ͬ �������ͬ Ҫasһ��
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
#case���÷�
#when���÷�
#����������б���
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
 





