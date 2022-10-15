-- 1. БД «Комп. фірма». Знайти виробників ПК. Вивести: maker, type.
-- Вихідні дані впорядкувати за спаданням за стовпцем maker.
select distinct maker, type from product
where type = "pc"
order by maker desc;

-- 2. БД «Аеропорт». З таблиці Pass_in_trip вивести дати, коли були
-- зайняті місця в першому ряді.
select date from pass_in_trip
where place like '1_';

-- 3. БД «Аеропорт». Для пасажирів таблиці Passenger вивести дати, коли
-- вони користувалися послугами авіаліній.
select Passenger.name, Pass_in_trip.date
from Passenger
join Pass_in_trip
on Passenger.ID_psg = Pass_in_trip.ID_psg;

-- 4. БД «Комп. фірма». Знайти тих виробників ПК, усі моделі ПК яких є
-- в наявності в таблиці PC (використовувати вкладені підзапити та опе-
-- ратори IN, ALL, ANY). Вивести maker.
select distinct maker
from product
where type = 'pc' and model in (select model from pc);

-- 5. БД «Кораблі». Знайдіть класи кораблів, у яких хоча б один корабель
-- був затоплений у битвах. Вивести: class. (Назви класів кораблів визна-
-- чати за таблицею Ships, якщо його там немає, тоді порівнювати чи
-- його назва не співпадає з назвою класу, тобто він є головним)
select if(class is null, ship, class) as result_class 
	from (select * from outcomes where result = 'sunk') as sunk_outcome 
    left join ships on ships.name = sunk_outcome.ship;

-- 6. БД «Комп. фірма». Для таблиці Printer вивести всю інформацію з
-- коментарями в кожній комірці, наприклад, 'модель: 1276', 'ціна:
-- 400,00' і т.д.
select concat('code: ', code), 
	   concat('model: ', model),
       concat('color: ', color), 
	   concat('type: ', type), 
       concat('price: ', price) from printer;

-- 7. БД «Комп. фірма». Знайдіть виробників найдешевших принтерів.
-- Вивести: maker, price.
select maker, Printer.price from Product
join Printer on Product.model = Printer.model
order by price asc;

-- 8. БД «Комп. фірма». Знайдіть максимальну ціну ПК, що випускаються кожним виробником. Вивести: maker, максимальна ціна. (Підказка:
-- використовувати підзапити в якості обчислювальних стовпців)
select maker, MAX(PC.price) from Product
join PC on Product.model = PC.model
group by maker;

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
order by maker asc;

-- 10. БД «Кораблі». Знайдіть класи, у які входять лише один або два
-- кораблі із цілої БД (врахувати також кораблі в таблиці Outcomes, яких
-- немає в таблиці Ships). Вивести: class, кількість кораблів у класі.
-- (Підказка: використовувати оператор UNION та операцію EXISTS)
select case when count(distinct classes.class) = count(classes.class) 
then classes.class end as unique_ship from classes
union 
select case when count(distinct ships.class) = count(ships.class)
then ships.class end from ships;




