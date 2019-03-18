# woottipong-ay-cap-test

## Requiremt.
1. Java JDK 1.8 
2. Eclipse JavaEE IDE
3. PostgreSQL
4. Postman

## Installation.
1. In PostgreSQL, create database name aycaptest.
2. Clone this project and Import Existing Maven Projects to your Eclipse IDE.
3. Open file application.properties from src/main/resources for change your connection to database and you can change secret key at grokonez.app.jwtSecret property.
4. Run AppAYCapTest.java (from com/woottipong/aycaptest) as Java Application once, if no error program will create tables automaticly.
5. Copy sql code from src/main/resources/member_type.sql and run in PostgreSQL.

## Using Demo in Postman.

1. Try to Sign-Up use POST http://localhost:8080/api/auth/signup add body as raw json as code below

Eg: Body raw JSON
```
{
	"name": "gold1",
	"username": "gold1",
	"password": "123456",
	"phone": "0000000001",
	"address": "001",
	"salary": 30000
}
```


2. Try to Sign-In by use POST http://localhost:8080/api/auth/signin add body as code below. 

Eg: Body raw JSON
```
{
	"username": "gold1",
	"password": "123456"
}
```
Ex: Result:
```
{
    "tokenType": "Bearer",
    "accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJnb2xkMSIsImlhdCI6MTU1Mjg4OTI3NSwiZXhwIjoxNTUyOTc1Njc1fQ.5hcnCxCfMd0KL-pw6ei10_i8sfSofSpbW7SLGlMdrk-FYkWLlF1Wqq8gQSaBhw1_UrJ8_nrXYmTwSi6fjmOiRQ"
}
```


3. Try to access resources with authorization by add HTTP Header's key "Authorization" and value "Bearer \<accessToken\>"

Ex:
```
Authorization: "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJnb2xkMSIsImlhdCI6MTU1Mjg4OTI3NSwiZXhwIjoxNTUyOTc1Njc1fQ.5hcnCxCfMd0KL-pw6ei10_i8sfSofSpbW7SLGlMdrk-FYkWLlF1Wqq8gQSaBhw1_UrJ8_nrXYmTwSi6fjmOiRQ"
```

Resources: GET URL

http://localhost:8080/api/silver/member

http://localhost:8080/api/gold/member

http://localhost:8080/api/platinum/member





