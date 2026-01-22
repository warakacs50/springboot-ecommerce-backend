# Spring Boot E-Commerce Backend (Cart System)

Backend-focused e-commerce system built with Spring Boot to practice real REST API design,
entity relationships, and frontend–backend integration.

## Why a minimal frontend?
The frontend is intentionally minimal (plain HTML + JavaScript) and acts like a lightweight client
to test and visualize backend endpoints — similar to a custom Postman/Swagger UI.
This project’s focus is backend behavior, not UI polish.

## Features
- Users with roles: CUSTOMER / SELLER / ADMIN
- Seller can add products
- Product listing
- Cart system:
  - Add item to cart
  - Remove item
  - Clear cart
  - View cart

## Tech Stack
- Java, Spring Boot
- Spring Data JPA / Hibernate
- MySQL (or H2)
- HTML + Vanilla JS (testing client)

## API Endpoints (current)
### Products
- `GET /product`
- `POST /product?sellerId={sellerId}`

### Cart
- `POST /api/cart/add?userId={userId}&productId={productId}&quantity={quantity}`
- `DELETE /api/cart/remove?userId={userId}&productId={productId}`
- `DELETE /api/cart/clear?userId={userId}`
- `GET /api/cart?userId={userId}`

## How to Run
1. Run the Spring Boot application
2. Open http://localhost:8080/index.html
3. Use the UI to create users, add products, and test cart operations


## Demo


## Future Improvements
- Reduce product stock on purchase
- Authentication & authorization (JWT)
- Checkout + order history
