# 📚 Library Management System

A simple Java console app using **JDBC + MySQL** to manage a book library.

## ✅ Features

- Add books (title & author)  
- View all books  
- Delete books by ID  

## 🛠️ Setup

1. **MySQL Table**:

```sql
CREATE DATABASE library;
USE library;
CREATE TABLE books (
  id INT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(255),
  author VARCHAR(255)
);
```

2. **Update DB config** in code:

```java
static final String PASS = "your_mysql_password";
```

---

## ▶️ Run

```bash
javac LibraryManagementSystem.java
java LibraryManagementSystem
```

---

## 📋 Menu

```
1. Add Book
2. View Books
3. Delete Book
4. Exit
```
