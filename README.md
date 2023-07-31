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
