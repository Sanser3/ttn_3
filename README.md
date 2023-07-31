# описание
Мой проект Ttn 

# что использовал
JDK 8+
Intellij Idea
Spring (Spring Boot, Spring MVC, Spring Security)
Hibernate
Thymeleaf
MySQL

# трудности
Spring Security
Thymeleaf

# архитектура запросов
/api/customers
|Запрос | Доп.адресс |Требование        |          CustomerRequest:
|-------|------------|------------------|          String name
|Get    |            |                  |          String address
|Post   |            | CustomerRequest  |          String route
|Get    | /route     | CustomerRequest  |          String accessKey
|Get    | /find      | CustomerRequest  |
|Put    |            | CustomerRequest  |
|Delete |            | CustomerRequest  |



/api/orders
|Запрос | Доп.адресс |Требование        |          OrderRequest:
|-------|------------|------------------|          List<Integer> orders
|Get    |            |                  |          
|Post   |            | OrderRequest     |          



/api/ttns
|Запрос  | Доп.адресс |Требование      |          TtnRequest:
|--------|------------|----------------|          String name
|Get     |            |                |          String address
|Post    |            | TtnRequest     |          String route
|Get     | /date      | TtnRequest     |          String accessKey
|Get     | /route     | TtnRequest     |          LocalDate date
|Get     | /allby     | TtnRequest     |          List<Integer> orders
|Put     | /update    | TtnRequest     |          


Вертикальные линии обозначают столбцы.

| Таблицы       | Это                | Круто |
| ------------- |:------------------:| -----:|
| столбец 3     | выровнен вправо    | $1600 |
| столбец 2     | выровнен по центру |   $12 |
| зебра-строки  | прикольные         |    $1 |

Внешние вертикальные линии (|) не обязательны и нужны только, чтобы сам код Markdown выглядел красиво. Тот же код можно записать так:

Markdown | не такой | красивый
--- | --- | ---
*Но выводится* | `так же` | **клево**
1 | 2 | 3