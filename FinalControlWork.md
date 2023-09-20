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



     mkdir HumanFriends
    mv /home/pavel/Kontr_zd/HumanFriends /home/pavel/HumanFriends/HumanFriends
     cd /home/pavel/HumanFriends/
     cat HumanFriends 

3 Подключить дополнительный репозиторий MySQL.  Установить любой пакет
из этого репозитория.




 


