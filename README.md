# Hotel Price Follower

## Table of Contents
- [💡 Overview](#-overview)
- [✨ Features](#-features)
- [👩‍💻 Technologies Used](#-technologies-used)
- [📦 Getting Started](#-getting-started)
- [🛠️ Installation](#-installation)
- [🔗 API Endpoints](#-api-endpoints)
- [🔧 Future Improvements](#-future-improvements)

## 💡 Overview
Hotel Price Follower is a full-stack application built for tracking hotel room prices with automated scraping, historical data storage, and price monitoring. 

⚠️ Frontend is still under construction.
Planned design:
![](/resources/designplan.png)

## ✨ Features
This application provides the following functionality:
- Browse hotels and view their available room types
- View room price data filtered by:
  - Departure date
  - Holiday duration
  - Number of adults and children
- Authenticated users can:
  - Add new hotels
  - Create new scrape profiles with customizable tracking settings
  - Add scrape tasks for selected departure dates
- Automated price scraping using scheduled background jobs
- Automatic storage and comparison of room prices over time

## 👩‍💻 Technologies Used
### Backend
- Java 25
- Spring Boot
- Spring Security + JWT
- Spring Data JPA / Hibernate
- PostgreSQL
- Docker
- Selenium
- Jsoup

### 🚧 Frontend (Under Construction)
- TypeScript
- React
- Vite
- npm
- TailwindCSS


## 📦 Getting Started
To get a local copy of this project up and running, follow these steps.

### 🚀 Prerequisites
- JDK 25 or higher
- Maven
- Docker
- Node.js v24 or higher
- npm

## 🛠️ Installation
**Clone the repository**
   ```bash
   git clone https://github.com/Roko122/hotel-price-follower.git
   cd hotel-price-follower
   ```

**Backend**
1. Navigate to `backend` folder
   ```bash
   cd backend
   ```
   
2. Create a `.env` file in the root of `backend` folder with following variables
   ```properties
   SERVER_PORT=8080
   POSTGRES_USER=postgres
   POSTGRES_PASSWORD=password
   POSTGRES_DB=hotelprices
   POSTGRES_PORT=5432
   JWT_SECRET=your_jwt_secret_key_here_should_be_long_and_secure_and_base64_encoded
   JWT_EXPIRATION=86400
   CORS_ALLOWED_ORIGIN=http://localhost:5173
   ```

3. Start the PostgreSQL database with Docker
   ```bash
   docker compose up
   ```
   
4. Start the backend
   ```bash
   ./mvnw spring-boot:run
   ```
<br></br>
**Frontend**
1. Navigate to `frontend` folder
   ```bash
   cd frontend
   ```

2. Install dependencies
   ```bash
   npm install
   ```
   
3. Start the frontend
   ```bash
   npm start
   ```

## 🔗 API Endpoints
### Hotels
   ```
   GET /api/v1/hotels   - List all hotels
   POST /api/v1/hotels  - Add a new hotel (authenticated users)
   ```

### Prices
   ```bash
   GET /api/v1/hotels/{hotelId}/profiles/{profileId}/rooms/{roomId}/prices          - List all prices for specified room
   GET /api/v1/hotels/{hotelId}/profiles/{profileId}/rooms/{roomId}/prices/summary  - Price summary for specified room
   ```

### Scrape Profiles
   ```bash
   GET /api/v1/hotels/{hotelId}/profiles  - List scrape profiles of specified hotel
   POST /api/v1/hotels/{hotelId}/profiles - Add a new scrape profile for specified hotel (authenticated users)
   ```

### Scrape Tasks
   ```bash
   POST /api/v1/hotels/{hotelId}/profiles/{profileId}/tasks   - Add a new scrape task (authenticated users)
   ```

### Login
   ```bash
   POST /api/v1/auth/login  - Allows users to login
   ```

## 🔧 Future Improvements
- Implement refresh tokens
- Add rate limiting to prevent abuse of API endpoints
- Implement email notifications when prices drop

## 📜 License
Distributed under the MIT License. See [License](/LICENSE) for more information.