# Итоговая контрольная работа

## Решение

1 Используя команду cat в терминале операционной системы Linux, создать два файла:Домашние животные (заполнив файл собаками, кошками,
хомяками) и Вьючные животными (заполнив файл лошадьми, верблюдами и ослами).<br>
Затем объединить их. Просмотреть содержимое созданного файла. Переименовать файл, дав ему новое имя (Друзья человека).

* Создаём для  итоговой контольной работы директорию Kontr_zd и заходим в неё.
Потом создаём два файла: packanimals.txt(собаками, кошками,
хомяками), homeanimal.txt (лошадьми, верблюдами и ослами) 
Объединяем два файла в homeanimal.txt просматриваем и переименоваем в HumanFriends и также просматриваем результат.

      mkdir Kontr_zd
      cd Kontr_zd/
      cat > packanimals.txt
      cat > homeanimal.txt 
      cat packanimals.txt >> homeanimal.txt 
      cat  homeanimal.txt
      mv homeanimal.txt HumanFriends
      cat HumanFriends 


2 Создать директорию, переместить файл туда.
* Создаём новую директорию с именем HumanFriends.
  Перемещаем файл HumanFriends.txt в директорию HumanFriends и просматриваем полученный результат

     mkdir HumanFriends
     mv /home/pavel/Kontr_zd/HumanFriends /home/pavel/HumanFriends/HumanFriends
     cd /home/pavel/HumanFriends/
     cat HumanFriends 

3 Подключить дополнительный репозиторий MySQL.  Установить любой пакет из этого репозитория.

4 Установить и удалить deb-пакет с помощью dpkg.

5 Выложить историю команд в терминале ubuntu.

6  Нарисовать диаграмму, в которой есть класс родительский класс, домашние животные и вьючные животные, в составы которых в случае домашних животных войдут классы: собаки, кошки, хомяки, а в класс вьючные животные войдут: Лошади, верблюды и ослы.

7  В подключенном MySQL репозитории создать базу данных “Друзья человека”.
* Создаём базу данных и заходим в неё

        mysql> CREATE DATABASE `Друзья человека`;
        mysql> use `друзья человека`;


8  Создать таблицы с иерархией из диаграммы в БД. 
* Создаём родительскую таблицу Animal. 

        mysql> create table Animal (
         -> id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(40));

* Создание таблицы "Домашние животные" с внешним ключом, наследника таблицы "Pet":

        mysql> create table Pet (
        -> id INT PRIMARY KEY AUTO_INCREMENT, class VARCHAR(40),
         -> animal_id INT,
         -> FOREIGN KEY (animal_id) REFERENCES animal(id));
    

   


mysql> create table PackAnimal (
    -> id INT PRIMARY KEY AUTO_INCREMENT, class VARCHAR(40),
    -> animal_id INT,
    -> FOREIGN KEY (animal_id) REFERENCES animal(id));
Query OK, 0 rows affected (0.15 sec)

 


