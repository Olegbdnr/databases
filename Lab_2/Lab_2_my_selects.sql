-- Вивести усі території і їхні номери телефону, які знаходяться у Львові
select teritory.id, phone, location.city from teritory
join location on teritory.location_id = location.id
where location.city = 'Lviv';
 
-- Вивести модель робота та камери які йому належать
select complete_set.name, camera.name as camera from complete_set
join camera on complete_set_id = complete_set.id;

-- Для кожної комплектації роботів вивести скільки метрів може подолати
-- робот (Час_роботи * середню швидкість), та посортувати за найбільшим
select name, (battery_reserve * average_speed) as length from complete_set
order by length desc;


-- Вивести загальну довжину шляхів для кожної території за спаданням
select teritory_id, sum(length) as total_length from route
group by teritory_id
order by total_length desc;

-- Вивести виробників роботів та моделі які вони виробляють
select manufacturer.name as manufacturer_name, complete_set.name as complete_set_name from manufacturer
join complete_set on manufacturer_id =  manufacturer.id;

-- Вивести назви комплектацій роботів у яких влаштована камера із можливістю повороту
select complete_set.name, camera.rotation_angle from complete_set
join camera on complete_set_id = complete_set.id
where camera.rotation_angle > 0;

-- Вивести список територій та імена працівників які там працюють
select teritory.id as teritory_id, employee.name, employee.surname from teritory
join employee on teritory_id = teritory.id;




