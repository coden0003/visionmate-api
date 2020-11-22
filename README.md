# Visionmate task API


## Server

- Server starts at port `8081`
- Change `application.properties` to modify


## Databases

I personally prefer to use H2 in-memory database for such a short projects, but MySQL is also configured:

- Change `application.properties` or even `pom.xml` to set H2 or MySQL database. For H2 it works instantly.
- You can access H2 console on `http://localhost:8081/h2-console`
- H2 database is droped and created from scrath on every restart of the application
- H2 credentials are: user `ca` and empty password


## FlyWay

I added FlyWay to the project, in order to insert some initial data to the DB on start:

- Migration scripts are in `db.migration` package
- Change `application.properties` to turn off this functionality


## Swagger2

I added also Swagger2, so the API is minimally documented and there is also no need for Postman.

So, all end-points have self-explanatory examples

- API documentation is on `http://localhost:8081/v2/api-docs`
- API can be tested from `http://localhost:8081/swagger-ui.html`, and there are examples there too.
- Change `se.visionmate.api.v1.swagger.Swagger2Config` to turn off this functionality


## Unit tests

There are some minimal unit-tests that mainly check Services funcionality

- Test are in standard location `src.test.java`




