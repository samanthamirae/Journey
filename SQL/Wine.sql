create schema session16;

select * from wine;

#Q1
select (max(price))-(min(price))
from wine;

#Q2
select count(*)
from wine
where points > (select points
from wine
order by price desc
limit 1);

#we did did the subquery first (below) then just copy and paste in the query above as subquery
select points
from wine
order by price desc
limit 1;

#Q3
select count(*)
from wine
where country="US" and points > (select avg(points)
from wine
where country="France");

select avg(points)
from wine
where country="France";

#Q4
#this way not as recommended as step 3
select (avg(points))-(select avg(points) from wine where country="France") 
from wine
where country="Italy";

#step 1: get the average points for italian wines
select avg(points) as italypoints
from wine
where country="Italy";

#step 2: get the average points for french wine
select avg(points) as francepoints
from wine
where country="France";

#step 3: get the difference between them
select italypoints-francepoints
from (select avg(points) as italypoints
	from wine
	where country="Italy") as sub1, 
	(select avg(points) as francepoints
	from wine
	where country="France") as sub2;


#Q5
select country, province, variety, points
from wine
where description like "%chocolate%"
order by price desc
limit 1;

#Q6
select wine_id, country, variety, price
from wine
where points=(select max(points)
from wine);

#Q7
select count(*) as frenchchardonnay
from wine
where country="France" and variety like "%chardonnay%";

select count(*) as allfrench
from wine
where country="France";

#step 3: put it together 
select (sub1.frenchchardonnay/sub2.allfrench)
from (select count(*) as frenchchardonnay
	from wine
	where country="France" and variety like "%chardonnay%") as sub1,
(select count(*) as allfrench
	from wine
	where country="France") as sub2;
    
  
