# FreshCart (Spring Boot E-commerce)

FreshCart is a full-stack e-commerce web app built as an internship project to demonstrate real-world skills in Java, Spring Boot, and UI development. It includes a modern storefront, cart UX, and basic admin management.

## Highlights
- Product browsing with categories
- Product details page
- Cart with quantity controls and per-item subtotal
- Order summary with VAT and shipping breakdown
- Discount code UI (demo)
- Modern, responsive UI
- Admin pages for products, categories, and users

## Tech Stack
- Java 17
- Spring Boot 3
- Spring Security
- Spring Data JPA (Hibernate)
- Thymeleaf
- MySQL 8

## Setup
1. Create a MySQL database named `ecommerce`.
2. Update DB credentials in `src/main/resources/application.properties` if needed.
3. Run the app:

```bash
./mvnw spring-boot:run
```

App runs at `http://localhost:8080`.

## Admin
Create an admin account at:
- `http://localhost:8080/admin/registration`

## File Uploads
Product images are saved in:
- `uploads/productImages`

This folder is ignored by Git.

## Project Structure
- `src/main/java` — controllers, services, models
- `src/main/resources/templates` — Thymeleaf views
- `src/main/resources/static` — CSS/JS/images

## Notes
- Cart is stored in memory for simplicity.
- Discount code UI is demo-only (no backend validation yet).

---
If you want seed data, payment integration, or a REST API, those can be added later.
