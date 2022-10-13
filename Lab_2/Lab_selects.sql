-- 1. БД «Комп. фірма». Знайти виробників ПК. Вивести: maker, type.
-- Вихідні дані впорядкувати за спаданням за стовпцем maker.
select distinct maker, type from product
where type = "pc"
order by maker desc;

-- 2. БД «Аеропорт». З таблиці Pass_in_trip вивести дати, коли були зайняті
-- місця 'c' у будь-якому ряді.
select date from pass_in_trip
where place like '_c';

-- 3. БД «Аеропорт». Для пасажирів таблиці Passenger вивести дати, коли
-- вони користувалися послугами авіаліній.
select Passenger.name, Pass_in_trip.date
from Passenger
join Pass_in_trip
on Passenger.ID_psg = Pass_in_trip.ID_psg;

-- 4. БД «Кораблі». Вивести класи всіх кораблів України ('Ukraine'). Якщо
-- в БД немає класів кораблів України, тоді вивести класи для всіх
-- наявних у БД країн. Вивести: country, class

select class, country from classes
where not exists(select class from classes where class = "Ukraine") or class = "Ukraine";

-- 5. БД «Комп. фірма». Виведіть тих виробників ноутбуків, які не
-- випускають принтери.
select distinct maker from product 
where type='Laptop' and maker not in (select maker from product where type='printer');

-- 6. БД «Комп. фірма». Для таблиці Printer вивести всю інформацію з
-- коментарями в кожній комірці, наприклад, 'модель: 1276', 'ціна:
-- 400,00' і т.д.
select concat('code: ', code), 
	   concat('model: ', model),
       concat('color: ', color), 
	   concat('type: ', type), 
       concat('price: ', price) from printer;

-- 7. БД «Кораблі». Для кожної країни визначити рік, у якому було спущено на воду максимальна кількість 
-- її кораблів. У випадку, якщо виявиться декілька таких років, тоді взяти мінімальний із них. 
-- Вивести: country, кількість кораблів, рік.

SELECT country, MAX(x) as Quantity, MIN(launched) as Year
FROM (SELECT country, COUNT(*) x , launched 
FROM Ships, Classes WHERE classes.class = ships.class 
GROUP BY country, launched) r
WHERE launched = ANY(SELECT MIN(launched) 
FROM Ships, Classes WHERE ships.class = classes.class GROUP BY country, launched) 
GROUP BY country
Order by Quantity ASC;

-- 8. БД «Комп. фірма». Для таблиці Product отримати підсумковий набір
-- у вигляді таблиці зі стовпцями maker, pc, laptop та printer, у якій для
-- кожного виробника необхідно вказати кількість продукції, що ним ви-
-- пускається, тобто наявну загальну кількість продукції в таблицях, від-
-- повідно, PC, Laptop та Printer. (Підказка: використовувати підзапити в
-- якості обчислювальних стовпців)

select maker, 
	COUNT(pc_id) as pc, 
	COUNT(laptop_id) as laptop, 
    COUNT(printer_id) as printer from product 
	left join (select model as pc_id, model from pc) t1 using(model) 
    left join (select model as laptop_id, model from laptop) t2 using(model) 
    left join (select model as printer_id, model from printer) t3 using(model)
    group by maker
    order by maker;

-- 9. БД «Комп. фірма». Для таблиці Product отримати підсумковий набір
-- у вигляді таблиці зі стовпцями maker, laptop, у якій для кожного ви-
-- робника необхідно вказати, чи виробляє він ('yes'), чи ні ('no')
-- відповідний тип продукції. У першому випадку ('yes') додатково
-- вказати поруч у круглих дужках загальну кількість наявної (тобто, що
-- знаходиться в таблиці Laptop) продукції, наприклад, 'yes(2)'. (Підказка:
-- використовувати підзапити в якості обчислювальних стовпців та
-- оператор CASE)

select distinct product.maker,
case
when form.count is not null then concat('yes(', form.count, ')')
else 'no' end as laptop from Product
left join (select maker, count(*) as count from Laptop
join Product on laptop.model = product.model group by maker) as form on product.maker = form.maker
order by maker ASC;

-- 10. БД «Кораблі». Знайдіть класи, до яких входить лише один корабель
-- з усієї БД (врахувати також кораблі в таблиці Outcomes, яких немає 
-- в таблиці Ships). Вивести: class. (Підказка: використовувати оператор
-- UNION та операцію EXISTS

select case when count(distinct classes.class) = count(classes.class) 
then classes.class end as unique_ship from classes
union 
select case when count(distinct ships.class) = count(ships.class)
then ships.class end from ships;

select ships.name, outcomes.ship from Ships 
right JOIN outcomes on ships.name = outcomes.ship
where name is null;




