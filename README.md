# RegisterLogInWebApp
Web application which allows register and log in to a SQL database.

## Technologies:

* Spring Core
* Spring Boot
* Spring MVC
* Spring Data JPA and Hibernate
* PostgreSQL
* HTML/CSS

## HOW TO USE
Create database with name **weblogin**, then in open **application.properties**
file in project(path: RegisterLogInWebApp\webapp\src\main\resources\application.properties).
Change **spring.jpa.hibernate.ddl-auto=update**(5th line) to **spring.jpa.hibernate.ddl-auto=create**.
Launch application with terminal(git bash) from RegisterLogInWebApp\webapp with command: `./mvnw spring-boot:run`<br />
After that, change **spring.jpa.hibernate.ddl-auto=create**(the same line in the same file) to **spring.jpa.hibernate.ddl-auto=update**.
Relaunch app. That's all! 
Open browser with following path: http://localhost:8080/home or http://localhost:8080. And enjoy it!!!

