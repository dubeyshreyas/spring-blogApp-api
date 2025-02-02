# Spring Boot Blog API

## Overview
This is a **Spring Boot REST API** project for managing a blog application. The API allows users to perform **CRUD operations** on users, posts, categories, and comments using **Spring Boot, Spring MVC, Spring Data JPA, Lombok, and PostgreSQL**. Users can interact with the API via **Postman** or a web browser.

## Features
- Create, update, delete, and fetch posts
- Fetch posts by user or category
- Paginate and sort posts
- Manage users, categories, and comments

## Technologies Used
- **Spring Boot** (REST API framework)
- **Spring MVC** (Request handling)
- **Spring Data JPA** (Database interaction)
- **Lombok** (Boilerplate code reduction)
- **PostgreSQL** (Relational database)
- **Maven** (Build tool)
- **Postman** (API testing)

## Installation & Setup
### 1. Clone the Repository
```sh
 git clone https://github.com/dubeyshreyas/spring-blogApp-api.git
 cd springboot-blog-api
```

### 2. Configure Database
Update the `application.properties` file with your **PostgreSQL** credentials:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/blog_app_apis
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

### 3. Build and Run the Application
```sh
 mvn clean install
 mvn spring-boot:run
```
The server will start on **http://localhost:8080/**.

## API Endpoints
### Post Management
#### 1. Create a Post
**POST** `/api/post/create-new`
- **Request Parameters:**
  - `userId`: Integer (User ID)
  - `catId`: Integer (Category ID)
  - `title`: String (Post title)
  - `content`: JSON (Post content)

#### 2. Get All Posts by User ID
**GET** `/api/post/get-all-user/{id}`

#### 3. Get All Posts by Category ID
**GET** `/api/post/get-all-cat/{id}`

#### 4. Get a Single Post by ID
**GET** `/api/post/{id}`

#### 5. Get All Posts with Pagination & Sorting
**GET** `/api/post/`
- **Query Parameters:**
  - `pageNum`: Integer (Page number, default: Constants.PAGE_NUMBER)
  - `pageSize`: Integer (Page size, default: Constants.PAGE_SIZE)
  - `sortBy`: String (Sort field, default: Constants.SORT_BY)
  - `dir`: String (Sort direction, default: Constants.SORT_DIR)

#### 6. Update a Post
**PUT** `/api/post/update-post/{id}`
- **Request Body (JSON):**
```json
{
  "title": "Updated Title",
  "content": "Updated post content"
}
```

#### 7. Delete a Post
**DELETE** `/api/post/delete-post/{id}`

## Testing with Postman
- Open Postman
- Import the API endpoints
- Send requests and verify responses

## License
This project is open-source and free to use under the [MIT License](LICENSE).

---
**Happy Coding! 🚀**

