# Premium Full-Stack Portfolio

Welcome to my premium, fully responsive personal portfolio website. This project is a decoupled full-stack application designed to showcase my experience, skills, and featured projects with a modern, dynamic UI and a robust, secure backend.

## 🚀 Architecture & Tech Stack

This project follows a monorepo structure containing both the frontend client and the backend API.

### Frontend (`/frontend`)
- **Framework:** Angular 19+
- **Styling:** Tailwind CSS (Custom themes, Glassmorphism, CSS Animations)
- **Features:** Fully responsive, animated tooltips, dark-mode styling, dynamic project grids, direct resume download.

### Backend (`/backend`)
- **Framework:** Java Spring Boot 3
- **Database:** H2 In-Memory Database (via Spring Data JPA)
- **Features:** 
  - RESTful API endpoints.
  - SMTP Integration (`JavaMailSender`) to send contact form submissions directly to a designated email.
  - Exception handling for robust input validation.
  - Configured for simple deployment via Render or Railway.

---

## 📂 Project Structure

```text
portfolio-website/
├── frontend/                 # Angular application
│   ├── src/
│   │   ├── app/              # Components (Hero, About, Projects, Contact, etc.)
│   │   ├── environments/     # Local and Production API configurations
│   │   └── styles.css        # Global Tailwind CSS definitions
│   └── package.json
├── backend/                  # Spring Boot application
│   ├── src/main/java/.../    # Controllers, Services, Models, and Repositories
│   ├── src/main/resources/   # application.properties
│   ├── .env                  # (Git-ignored) SMTP secrets
│   └── pom.xml
└── README.md                 # You are here
```

---

## 🛠️ Local Development Setup

### 1. Running the Backend (Spring Boot)

1. Navigate to the backend directory:
   ```bash
   cd backend
   ```
2. Create your `.env` file (copy from `.env.example` if available) and add your Gmail App Password:
   ```properties
   EMAIL_ID=your_email@gmail.com
   APP_PASSWORD=your_16_char_app_password
   ```
3. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```
   *The backend will start on `http://localhost:8080`.*

### 2. Running the Frontend (Angular)

1. Open a new terminal and navigate to the frontend directory:
   ```bash
   cd frontend
   ```
2. Install dependencies:
   ```bash
   npm install
   ```
3. Start the development server:
   ```bash
   npm run start
   ```
   *The frontend will start on `http://localhost:4200`.*

---

## 🌍 Deployment Guide

This repository is configured to be deployed as two separate services:

1. **Frontend (Vercel):** Connect this repository to Vercel and set the Root Directory to `frontend`. Vercel will automatically build and deploy the Angular application.
2. **Backend (Render/Railway):** Connect this repository to Render as a Web Service. Set the Root Directory to `backend`. Make sure to inject your `EMAIL_ID` and `APP_PASSWORD` as Environment Variables in the hosting dashboard.

*Note: Once the backend is deployed, update `frontend/src/environments/environment.prod.ts` with your live backend API URL.*

---

*Designed and engineered by Deep Patel.*
