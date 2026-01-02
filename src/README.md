# Inventory Management System

A backend-focused Inventory Management System built using Java and JDBC.

---

## Tech Stack
- Java 21
- JDBC
- MySQL
- IntelliJ IDEA
- Git & GitHub

---

## Features (JDBC Phase)
- Add product
- View all products
- Update stock (IN / OUT)
- Transaction-safe stock updates
- Service-layer based business logic

---

## Project Structure
- model → Entity classes(Product, StockTransaction)
- dao → Database logic(JDBC)
- service → Business logic & transaction management
- util → DB connection utility
- main → Application runner(console UI)

---


## How to Run
1. Create MySQL database
2. Create required tables (products, stock_transactions)
3. Configure DB credentials in `DBUtil.java`
4. Run `InventoryApp.java`

---

## Author
Ashutosh Gajankush
