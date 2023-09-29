# Итоговая контрольная работа, часть вторая

## MySQL

6 Нарисовать диаграмму, в которой есть класс родительский класс, домашние животные и вьючные животные, в составы которых в случае домашних животных войдут классы: собаки, кошки, хомяки, а в класс вьючные животные войдут: Лошади, верблюды и ослы.

![Диаграмма классов](pictures/diagramma.png "Диаграмма классов") 

7  В подключенном MySQL репозитории создать базу данных “Друзья человека”.

 Создаём базу данных и заходим в неё

        mysql> CREATE DATABASE `Друзья человека`;
        mysql> use `друзья человека`;


8  Создать таблицы с иерархией из диаграммы в БД.

 Создаём родительскую таблицу __Animal__. 

        mysql> create table Animal (
         -> id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(40));

Создание таблицы "Домашние животные"( __Pet__ )с внешним ключом, наследующий от таблицы Animal.

        mysql> create table Pet (
        -> id INT PRIMARY KEY AUTO_INCREMENT, class VARCHAR(40),
         -> animal_id INT,
         -> FOREIGN KEY (animal_id) REFERENCES animal(id));
    
Создание таблицы "Вьючные животные"( __PackAnimal__ ) с внешним ключом, наследующий от таблицы Animal.

        mysql> create table PackAnimal (
        -> id INT PRIMARY KEY AUTO_INCREMENT, class VARCHAR(40),
        -> animal_id INT,
        -> FOREIGN KEY (animal_id) REFERENCES animal(id));

 Создание таблиц "Собаки", "Кошки", "Хомяки" с внешним ключом, наследующий от таблицы "Домашние животные":

__Кошки__

        mysql> create table cat (
         -> id INT PRIMARY KEY AUTO_INCREMENT, pet_id INT,
         -> name VARCHAR(40),
          -> command VARCHAR(40),
         -> date_of_birth DATE,
         -> FOREIGN KEY (pet_id) REFERENCES pet(id));

__Собаки__ 

        mysql> create table dog (
         -> id INT PRIMARY KEY AUTO_INCREMENT, pet_id INT,
         -> name VARCHAR(40),
         -> command VARCHAR(40),
         -> date_of_birth DATE,
         -> FOREIGN KEY (pet_id) REFERENCES pet(id));

__Хомяки__

    mysql> create table hamster (
    -> id INT PRIMARY KEY AUTO_INCREMENT, pet_id INT,
    -> name VARCHAR(40),
    -> command VARCHAR(40),
    -> date_of_birth DATE,
    -> FOREIGN KEY (pet_id) REFERENCES pet(id));

Создание таблиц "Лошади", "Верблюды", "Ослы" с внешним ключом, наследующий от таблицы "Вьючные животные":

__Лошади__

    mysql> create table horse (
    -> id INT PRIMARY KEY AUTO_INCREMENT, packanimal_id INT,
    -> name VARCHAR(40),
    -> command VARCHAR(40),
    -> date_of_birth DATE,
    -> FOREIGN KEY (packanimal_id) REFERENCES packanimal(id));

__Верблюды__

    mysql> create table camel (
    -> id INT PRIMARY KEY AUTO_INCREMENT, packanimal_id INT,
    -> name VARCHAR(40),
    -> command VARCHAR(40),
    -> date_of_birth DATE,
    -> FOREIGN KEY (packanimal_id) REFERENCES packanimal(id));  

__Ослы__ 

       mysql> create table donkey (
    -> id INT PRIMARY KEY AUTO_INCREMENT, packanimal_id INT,
    -> name VARCHAR(40),
    -> command VARCHAR(40),
    -> date_of_birth DATE,
    -> FOREIGN KEY (packanimal_id) REFERENCES packanimal(id)); 

Проверяем созданную БД:

        mysql> SHOW DATABASES;
        mysql> SHOW TABLE;

![Просмотр созданных таблиц и даты базы](pictures/mysql_table.png "Просмотр созданных таблиц и даты базы")

9 Заполнить низкоуровневые таблицы именами(животных), командами, которые они выполняют, и датами рождения Прежде, чем заполнять низкоуровнневые таблицы, заполним родительские: "Домашние животные" и "Вьючные животные"

 "Домашние животные"      __Pet__

    mysql> INSERT INTO pet(class, animal_id)
    -> values ("Собаки", 1),
    -> ("Кошки", 1),
    ->  ("Хомяки", 1);

"Вьючные животные"     __PackAnimal__

    mysql> insert into  packanimal(class, animal_id)
    -> values ("Лошади", 2),
    -> ("Верблюды", 2),
    -> ("Ослы", 2);

Заполним низкоуровнивые таблицы данными:

Таблица "__Собаки__":

    mysql> INSERT INTO dog (pet_id, name, command,  date_of_birth)
    -> SELECT p.id, n.name, c.command, DATE_ADD('2008-01-01', INTERVAL FLOOR(RAND() * 10000) DAY)
    -> FROM pet AS p
    -> CROSS JOIN (
    -> SELECT "Лео" AS name UNION ALL
    -> SELECT "Шарик" UNION ALL
    -> SELECT "Вуд"  UNION ALL
    -> SELECT "Венера"  UNION ALL
    -> SELECT "Лунна"  UNION ALL
    -> SELECT "Ричард" )
    -> AS n
    -> CROSS JOIN (
    -> SELECT 'Ко мне' AS command UNION ALL
    -> SELECT 'Апорт'  UNION ALL
    -> SELECT 'Рядом'  UNION ALL
    -> SELECT 'Голос'  UNION ALL
    -> SELECT 'Сидеть'  UNION ALL
    -> SELECT 'Вперёд')
    -> AS c
    -> WHERE p.class = 'Собаки';

Таблица "__Кошки__":

    mysql> INSERT INTO cat (pet_id, name, command, date_of_birth)
    -> SELECT p.id, n.name, c.command, DATE_ADD('2005-01-01', INTERVAL FLOOR(RAND() * 10000) DAY)
    -> FROM pet AS p
    -> CROSS JOIN (
    -> SELECT "Мурка" AS name UNION ALL
    -> SELECT "Бакс" UNION ALL
    -> SELECT "Вася"  UNION ALL
    -> SELECT "Барсик"  UNION ALL
    -> SELECT "Марс" )
    -> AS n
    -> CROSS JOIN (
    -> SELECT 'Кис-кис-кис' AS command UNION ALL
    -> SELECT 'Брысь'  UNION ALL
    -> SELECT 'Волчок'  UNION ALL
    -> SELECT 'Прыгай' )
    -> AS c
    -> WHERE p.class = 'Кошки';

Таблица "__Хомяки__":

    mysql> INSERT INTO hamster (pet_id, name, command,  date_of_birth)
    -> SELECT p.id, n.name, c.command, DATE_ADD('2013-01-01', INTERVAL FLOOR(RAND() * 10000) DAY)
    -> FROM pet AS p
    -> CROSS JOIN (
    -> SELECT "Хоря" AS name UNION ALL
    -> SELECT "Шустрик"  UNION ALL
    -> SELECT "Непоседа"  UNION ALL
    -> SELECT "Рики"  UNION ALL
    -> SELECT "Хвостик"  UNION ALL
    -> SELECT "Нэлли"  )
    -> AS n
    -> CROSS JOIN (
    -> SELECT 'Прыжок' AS command UNION ALL
    -> SELECT "Кувырок"  UNION ALL
    -> SELECT "Стоять"  UNION ALL
    -> SELECT "Нельзя" )
    -> AS c
    -> WHERE p.class = 'Хомяки';

Таблица "__Лошади__":

     mysql> INSERT INTO horse (packanimal_id, name, command,  date_of_birth)
    -> SELECT pa.id, n.name, c.command, DATE_ADD('2000-01-01', INTERVAL FLOOR(RAND() * 10000) DAY)
    -> FROM packanimal AS pa
    -> CROSS JOIN (
    -> SELECT "Дымка" AS name UNION ALL
    -> SELECT "Граф"  UNION ALL
    -> SELECT "Зорька"  UNION ALL
    -> SELECT "Ворон"  UNION ALL
    -> SELECT "Ласточка" )
    -> AS n
    -> CROSS JOIN (
    -> SELECT 'Стой' AS command UNION ALL
    -> SELECT "Вперёд"  UNION ALL
    -> SELECT "Хоп"  UNION ALL
    -> SELECT "Шагом"  UNION ALL
    -> SELECT "Но, пошла!" )
    -> AS c
    -> WHERE pa.class = 'Лошади';

Таблица "__Верблюды__":

    mysql>INSERT INTO camel (packanimal_id, name, command,  date_of_birth)
    -> SELECT pa.id, n.name, c.command, DATE_ADD('2000-01-01', INTERVAL ( SELECT(FLOOR(RAND() * DATEDIFF('2023-01-01','2000-01-01')))) DAY)
    -> FROM packanimal AS pa
    -> CROSS JOIN (
    -> SELECT "Агата" AS name UNION ALL
    -> SELECT "Чайна"  UNION ALL
    -> SELECT "Марта"  UNION ALL
    -> SELECT "Ида" )
    -> AS n
    -> CROSS JOIN (
    -> SELECT 'Стой' AS command UNION ALL
    -> SELECT "Вперёд"  UNION ALL
    -> SELECT "За мной"  UNION ALL
    -> SELECT "Лежать")
    -> AS c
    -> WHERE pa.class = 'Верблюды';

Таблица "__Ослы__":

    mysql> INSERT INTO donkey (packanimal_id, name, command,  date_of_birth)
    -> SELECT pa.id, n.name, c.command, DATE_ADD('2008-01-01', INTERVAL ( SELECT(FLOOR(RAND() * DATEDIFF('2023-01-01','2008-01-01')))) DAY)
    -> FROM packanimal AS pa
    -> CROSS JOIN (
    -> SELECT "Вольт" AS name UNION ALL
    -> SELECT "Дюк"  UNION ALL
    -> SELECT "Мэтью"  UNION ALL
    -> SELECT "Шкипер"  UNION ALL
    -> SELECT "Скуби" )
    -> AS n
    -> CROSS JOIN (
    -> SELECT 'Стой' AS command UNION ALL
    -> SELECT "Вперёд"  UNION ALL
    -> SELECT "За мной"  UNION ALL
    -> SELECT "Лежать")
    -> AS c
    -> WHERE pa.class = 'Ослы';

Проверяем заполнение таблиц.

![Просмотр заполненых таблиц ](pictures/mysql_tables.png "Просмотр заполненых таблиц")

10. Удалив из таблицы верблюдов, т.к. верблюдов решили перевезти в другой питомник на зимовку. Объединить таблицы лошади, и ослы в одну таблицу.

        mysql> DELETE FROM camel; 

        mysql> CREATE TABLE horse_donkey AS
        -> SELECT * FROM horse
        -> UNION ALL
        -> SELECT * FROM donkey;

![Просмотр таблицы лошади_ослы  ](pictures/camel.png "Просмотр таблицы лошади_ослы")

11. Создать новую таблицу “молодые животные” в которую попадут все животные старше 1 года, но младше 3 лет и в отдельном столбце с точностью до месяца подсчитать возраст животных в новой таблице

        mysql> CREATE TABLE young_animal AS
        -> SELECT id, name, command,  date_of_birth,
        -> TIMESTAMPDIFF(YEAR, date_of_birth, CURDATE()) AS age_years,
        -> TIMESTAMPDIFF(MONTH, date_of_birth, CURDATE()) AS age_months,
        -> "Cобаки" AS class
        -> FROM dog
        -> WHERE  date_of_birth > DATE_SUB(CURDATE(), INTERVAL 3 YEAR)
        -> AND date_of_birth < DATE_SUB(CURDATE(), INTERVAL 1 YEAR)
        -> UNION ALL
        -> SELECT id, name, command,  date_of_birth,
        -> TIMESTAMPDIFF(YEAR, date_of_birth, CURDATE()) AS age_years,
        -> TIMESTAMPDIFF(MONTH, date_of_birth, CURDATE()) AS age_months,
        -> "Кошки" AS class
        -> FROM cat
        -> WHERE  date_of_birth > DATE_SUB(CURDATE(), INTERVAL 3 YEAR)
        -> AND date_of_birth < DATE_SUB(CURDATE(), INTERVAL 1 YEAR)
        -> UNION ALL
        -> SELECT id, name, command,  date_of_birth,
        -> TIMESTAMPDIFF(YEAR, date_of_birth, CURDATE()) AS age_years,
        -> TIMESTAMPDIFF(MONTH, date_of_birth, CURDATE()) AS age_months,
        -> "Хомяки" AS class
        -> FROM hamster
        -> WHERE  date_of_birth > DATE_SUB(CURDATE(), INTERVAL 2 YEAR)
        -> AND date_of_birth < DATE_SUB(CURDATE(), INTERVAL 1 YEAR)
        -> UNION ALL
        -> SELECT id, name, command,  date_of_birth,
        -> TIMESTAMPDIFF(YEAR, date_of_birth, CURDATE()) AS age_years,
        -> TIMESTAMPDIFF(MONTH, date_of_birth, CURDATE()) AS age_months,
        -> "Лошади" AS class
        -> FROM horse
        -> WHERE  date_of_birth > DATE_SUB(CURDATE(), INTERVAL 3 YEAR)
        -> AND date_of_birth < DATE_SUB(CURDATE(), INTERVAL 1 YEAR)
        -> UNION ALL
        -> SELECT id, name, command,  date_of_birth,
        -> TIMESTAMPDIFF(YEAR, date_of_birth, CURDATE()) AS age_years,
        -> TIMESTAMPDIFF(MONTH, date_of_birth, CURDATE()) AS age_months,
        -> "Верблюды" AS class
        -> FROM camel
        -> WHERE  date_of_birth > DATE_SUB(CURDATE(), INTERVAL 3 YEAR)
        -> AND date_of_birth < DATE_SUB(CURDATE(), INTERVAL 1 YEAR)
        -> UNION ALL
        -> SELECT id, name, command,  date_of_birth,
        -> TIMESTAMPDIFF(YEAR, date_of_birth, CURDATE()) AS age_years,
        -> TIMESTAMPDIFF(MONTH, date_of_birth, CURDATE()) AS age_months,
        -> "Ослы" AS class
        -> FROM donkey
        -> WHERE  date_of_birth > DATE_SUB(CURDATE(), INTERVAL 3 YEAR)
        -> AND date_of_birth < DATE_SUB(CURDATE(), INTERVAL 1 YEAR);
    
![Просмотр таблицы young_animals  ](pictures/young_animals.png "Просмотр таблицы young_animals")

12. Объединить все таблицы в одну, при этом сохраняя поля, указывающие на прошлую принадлежность к старым таблицам.

        mysql> CREATE TABLE joint_animals AS
        -> SELECT "Cобаки" AS class, id, name, command, date_of_birth
        -> FROM dog
        -> UNION
        -> SELECT "Кошки" as class, id, name, command,  date_of_birth
        -> FROM cat
        -> UNION
        -> SELECT "Хомяки" as class, id, name, command,  date_of_birth
        -> FROM hamster
        -> UNION
        -> SELECT "Лошади" as class, id, name, command,  date_of_birth
        -> FROM horse
        -> UNION
        -> SELECT "Ослы" as class, id, name, command,  date_of_birth
        -> FROM donkey
        -> UNION
        -> SELECT "Верблюды" as class, id, name, command,  date_of_birth
        -> FROM camel;

![Просмотр  объединёной таблицы](pictures/joint_animals.png "Просмотр объединёной таблицы")

