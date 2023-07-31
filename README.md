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
|Запрос | Доп.адресс |Требование        |          
|-------|------------|------------------|          
|Get    |            |                  |          
|Post   |            | CustomerRequest  |          
|Get    | /route     | CustomerRequest  |          
|Get    | /find      | CustomerRequest  |
|Put    |            | CustomerRequest  |
|Delete |            | CustomerRequest  |

CustomerRequest:
String name
String address
String route
String accessKey

/api/orders
|Запрос | Доп.адресс |Требование        |          
|-------|------------|------------------|          
|Get    |            |                  |          
|Post   |            | OrderRequest     |          

OrderRequest:
List<Integer> orders

/api/ttns
|Запрос  | Доп.адресс |Требование      |          
|--------|------------|----------------|         
|Get     |            |                |          
|Post    |            | TtnRequest     |          
|Get     | /date      | TtnRequest     |          
|Get     | /route     | TtnRequest     |          
|Get     | /allby     | TtnRequest     |          
|Put     | /update    | TtnRequest     |          

TtnRequest:
String name
String address
String route
String accessKey
LocalDate date
List<Integer> orders




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