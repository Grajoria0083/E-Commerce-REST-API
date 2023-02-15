# E-Commerce-Rest-API
This is Rest-API for online shoping, This web service is created by Spring-Boot. All the functionalities of an online shopping app such as login/signup, view products, add products to cart, remove products from cart, order and payment are avalible. All the data is connect with database.
# Tech Stack
   Java
. Hibernate
. Maven
. Spring
. Spring Boot
. Spring Data JPA
. MySQL
. Postman
. Swagger UI

# ER Diagram![184973861-3a0d1ef2-f9b6-44fd-9ddc-978127509434](https://user-images.githubusercontent.com/101567803/219017268-6a1b28bc-bdc7-4092-ab05-df5b0c6d4440.jpg)


# How to run
Before running the API server, you should update the database config inside the application.properties file in side spring project.
Create database ecomdb;
Update the port number, username and password according your database configuration.

    server.port=8789

    spring.datasource.url=jdbc:mysql://localhost:3306/ecomdb;
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.datasource.username=root
    spring.datasource.password=root
   
# Swagger Documentation
[Swagger UI API Documentation E-commerce web service.pdf](https://github.com/Grajoria0083/E-Commerce-API/files/10553814/screencapture-localhost-8789-swagger-ui-2023-02-01-10_59_44.pdf)
