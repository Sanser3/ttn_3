# описание
Мой проект Ttn

Реализована возможность создания новых - заказчика, заказа и Ttn на основании заказчика и добавления их в базу данных
Реализована возможность удалять, обновлять и искать заказчика в базе данных
Реализована возможность обновления заказа в определённой Ttn, поиска Ttn по дате, маршруту и по заказчику

# что использовал
JDK 8+,
Intellij Idea,
Spring (Spring Boot, Spring MVC, Spring Security),
Hibernate,
Thymeleaf,
MySQL

# трудности
Spring Security,
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

| CustomerRequest: |
|:-----------------|
| String name      |
| String address   |
| String route     |
| String accessKey |

/api/orders
|Запрос | Доп.адресс |Требование        |          
|-------|------------|------------------|          
|Get    |            |                  |          
|Post   |            | OrderRequest     |          

| OrderRequest:        |
|:---------------------|
| List(Integer) orders |

/api/ttns
|Запрос  | Доп.адресс |Требование      |          
|--------|------------|----------------|         
|Get     |            |                |          
|Post    |            | TtnRequest     |          
|Get     | /date      | TtnRequest     |          
|Get     | /route     | TtnRequest     |          
|Get     | /allby     | TtnRequest     |          
|Put     | /update    | TtnRequest     |          

| TtnRequest:          |
|:---------------------|
| String name          |
| String address       |
| String route         |
| String accessKey     |
| LocalDate date       |
| List(Integer) orders |

