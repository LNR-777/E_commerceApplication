# FreshCart Healthy Snacks

FreshCart is a Spring Boot e-commerce app focused on healthy snacks and beverages. It was built as an internship project to demonstrate full-stack fundamentals: clean MVC structure, MySQL persistence, Thymeleaf UI, and practical UX improvements like a modern cart and checkout validation.

## Highlights
- Healthy snacks catalog with categories
- Product details page with images
- Cart with quantity controls and per-item subtotal
- Order summary with VAT and shipping breakdown
- Checkout form with server-side validation
- Admin pages for products, categories, and users
- Demo seed data for quick setup

## Tech Stack
- Java 17
- Spring Boot 3
- Spring Data JPA (Hibernate)
- Spring Security
- Thymeleaf
- MySQL 8

## Getting Started
1. Create a MySQL database named `ecommerce`.
2. Update credentials in `src/main/resources/application.properties` if needed.
3. Run the app:

```bash
./mvnw spring-boot:run
```

App runs at `http://localhost:8080`.

## Demo Seed Data
Sample categories and products load automatically on startup from `src/main/resources/data.sql`.
If you want to disable it, set:

```
spring.sql.init.mode=never
```

## Admin
Create an admin account at:
`http://localhost:8080/admin/registration`

## Images
Product images can be stored as:
- Local files under `uploads/productImages`
- Static assets under `src/main/resources/static/productImages`
- External image URLs

The app serves `/productImages/**` from both the uploads folder and the bundled static images.

## Project Structure
- `src/main/java` — controllers, services, models
- `src/main/resources/templates` — Thymeleaf views
- `src/main/resources/static` — CSS/JS/images

## Notes
- Cart is stored in memory for simplicity in this demo.
- Checkout uses server-side validation and shows field errors in the UI.

