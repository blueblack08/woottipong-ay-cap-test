# woottipong-ay-cap-test

Requiremt.
1. Eclipse JavaEE IDE
2. PostgreSQL
3. Postman

Installation.
1. In PostgreSQL, create database name aycaptest.
2. Clone this project and Import Existing Maven Projects to your Eclipse IDE.
3. Open file application.properties from src/main/resources for change your connection to database and you can change secret key at grokonez.app.jwtSecret property.
4. Run AppAYCapTest.java (from com/woottipong/aycaptest) as Java Application once, if no error program will create tables automaticly.
5. Copy sql code from src/main/resources/member_type.sql and run in PostgreSQL.

Using Demo in Postman.
1. try to use POST http://localhost:8080/api/auth/signup add body as code below
```
{
	"name": "gold1",
	"username": "gold1",
	"memberType": "gold1",
	"password": "123456",
	"phone": "0000000001",
	"address": "001",
	"salary": 30000
}
```
2. try to Sign in by use POST 
