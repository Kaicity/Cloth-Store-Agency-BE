# Cloth Store Agency Services Back-end

This project was generated with Spring maven version Java JDK 17

## Config application.properties in module ct_start/src/resources

```
server.port=5555
spring.datasource.url=jdbc:mysql://localhost:3306/ct_agency
spring.datasource.username=root
spring.datasource.password=123456
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
spring.jpa.properties.hibernate.format_sql=true
spring.mvc.cors.allowed-origins=*

portAgency = http://localhost:5556
clientFE = http://localhost:4201
adminFE =  http://localhost:4202
```
 - Database name: ct_agency
 - username and password in your MySQL

 Project use port local
 - portWarehouse = http://localhost:5556
 - clientFE = http://localhost:4201
 - adminFE =  http://localhost:4202

Note: Application.properties different to deployment server
   
## Update and run maven project:
Run `mvn clean install`

## Run and start warehouse springboot project
Run application

## Deloyment Docker

This is an instructional clip: https://youtu.be/GE7tn2l9B1E 
For agencydb :

 - Build image for this : " docker build -t agencydb . "

 - Create Container from image : " docker run -p 3308:3306 --name agencydb agencydb "

For agencybe you open terminal in ct-start:

 - Build image for this : " docker build -t agencybe . "

 - Create Container from image : " docker run -p 5555:5555 --name agencybe agencybe "
## Image Demo
![agency](https://github.com/Kaicity/Kaicity/blob/main/assets/Screenshot%202024-05-20%20005525.png)

