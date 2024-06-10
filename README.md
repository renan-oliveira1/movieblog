# Movie Blog

This is the documentation for the Movie Blog API. The Api is a RESTful API that provides access to data of movies and categories, just admins users can create, update, delete a movie or category, normal users can just do get operations .

## About the application

The application was developed with Spring Boot, Spring Security, JWT, H2(database), Java and a flexible architecture based on SOLID principles. With this software, you can:

 - Register a user;
 - Log in to the user account;
 - Admins Users:
     - Can register, edit, delete and visualize movies
     - Can register, edit, delete and visualize categories
 - Normal Users:
     - Visualize movies
     - Visualize categories

## Base URL

 - http://localhost:8080

# API Endpoints


<details>
<summary>Register User</summary>

Register a user to the database and receive a token.

<b>URL:</b> baseUrl + /auth/register </br>
<b>METHOD:</b> POST </br>
<b>BODY REQUEST:</b>
```json
{
	"username": "renan",
	"email": "renan@gmail.com",
	"password": "renan",
	"phone": "3375-1623",
	"authority": "ADMIN"
}
```
<b>BODY SUCCES RESPONSE:</b>
```json
{
	"email": "renan@gmail.com",
	"token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJtb3ZpZS1ibG9nIiwic3ViIjoiMjY0MmQ1ZjgtNGM0Mi00MWI2LWEwZjQtNDEzNmRlNzdjZjRlIiwiZXhwIjoxNzE2OTIyMDc0fQ.iqP0ov7DnaitdMSKEc-gzD_V9Tvho0KRNiXJjmAIRK4"
}
```
</details>

<details>
<summary>Login with User</summary>

Login and receive a token.

<b>URL:</b> baseUrl + /auth/login </br>
<b>METHOD:</b> POST </br>
<b>BODY REQUEST:</b>
```json
{
	"email": "renan@gmail.com",
	"password": "renan"
}
```
<b>BODY SUCCES RESPONSE:</b>
```json
{
	"email": "renan@gmail.com",
	"token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJtb3ZpZS1ibG9nIiwic3ViIjoiMjY0MmQ1ZjgtNGM0Mi00MWI2LWEwZjQtNDEzNmRlNzdjZjRlIiwiZXhwIjoxNzE2OTIyMDc0fQ.iqP0ov7DnaitdMSKEc-gzD_V9Tvho0KRNiXJjmAIRK4"
}
```
</details>

<details>
<summary>Create Category (ADMIN)</summary>

Register a new category.

<b>URL:</b> baseUrl + /categories </br>
<b>METHOD:</b> POST </br>
<b>BODY REQUEST:</b>
```json
{
	"name": "Horror",
	"description": "Some category description"
}
```
<b>BODY SUCCES RESPONSE:</b>
```json
{
	"id": 1,
	"name": "Horror",
	"description": "Some category description"
}
```
</details>
<details>
<summary>Get Categories (ADMIN, USER)</summary>

Get categories.

<b>URL:</b> baseUrl + /categories </br>
<b>METHOD:</b> GET </br>
<b>BODY REQUEST:</b>

<b>BODY SUCCES RESPONSE:</b>
```json
[
	{
		"id": 1,
		"name": "Horror",
		"description": "Some category description"
	}
]
```
</details>

<details>
<summary>Get Category (ADMIN, USER)</summary>

Get categories.

<b>URL:</b> baseUrl + /categories/idCategory </br>
<b>METHOD:</b> GET </br>
<b>BODY REQUEST:</b>

<b>BODY SUCCES RESPONSE:</b>
```json
[
	{
		"id": 1,
		"name": "Horror",
		"description": "Some category description"
	}
]
```
</details>

<details>
<summary>Create Category (ADMIN)</summary>

Create a new category.

<b>URL:</b> baseUrl + /categories </br>
<b>METHOD:</b> POST </br>
<b>BODY REQUEST:</b>

<b>BODY REQUEST:</b>
```json
{
	"name": "Horror",
	"description": "Some category description"
}
```
</details>

<details>
<summary>Get Movies (ADMIN, USER)</summary>

Get movies.

<b>URL:</b> baseUrl + /movies </br>
<b>METHOD:</b> GET </br>
<b>BODY REQUEST:</b>

<b>BODY RESPONSE:</b>
```json
[
	{
		"id": 1,
		"name": "Horror",
		"description": "Some category description"
	}
]
```
</details>

<details>
<summary>Get Movie (ADMIN, USER)</summary>

Get a moview.

<b>URL:</b> baseUrl + /movies/idMovie </br>
<b>METHOD:</b> GET </br>
<b>BODY REQUEST:</b>

<b>BODY REQUEST:</b>
```json
{
	"id": 1,
	"name": "Horror",
	"description": "Some category description"
}
```
</details>

<details>
<summary>Create Movie (ADMIN)</summary>

Create movie.

<b>URL:</b> baseUrl + /movies </br>
<b>METHOD:</b> POST </br>
<b>BODY REQUEST:</b>

<b>BODY REQUEST:</b>
```json
{
	"title": "Stranger Things",
	"description": "Some movie description",
	"premiere": "2023-02-02",
	"categories": [
		{"id": 1}
	]
}
```
</details>
