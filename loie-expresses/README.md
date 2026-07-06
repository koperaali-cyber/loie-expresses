# LOIE EXPRESSES — Transport Services Management System

A complete, enterprise-style transport company platform built with **Spring Boot 3.3 + Java 21**.
It has two parts: a public marketing website and a secured admin dashboard (CMS) that lets
staff manage the entire site — content, services, fleet, gallery, news, bookings, quotes,
loan applications, consultations and messages — without touching code.

---

## Tech stack

| Layer     | Technology                                   |
|-----------|----------------------------------------------|
| Backend   | Spring Boot 3.3, Java 21, Spring MVC         |
| Security  | Spring Security (form login, roles, reset)   |
| Data      | Spring Data JPA / Hibernate                  |
| Database  | PostgreSQL                                   |
| Frontend  | Thymeleaf, Bootstrap 5, Font Awesome 6       |
| Charts    | Chart.js                                     |
| Build     | Maven                                        |

---

## Setup

The application uses **PostgreSQL**. You need it installed and running before the first launch.

### Step 1 — Create the database
```sql
CREATE DATABASE loie_db;
```
(Use pgAdmin, `psql`, or any client. The default owner `postgres` is fine.)

### Step 2 — Set your credentials
Open `src/main/resources/application.properties` and set your PostgreSQL username and
password, or leave the defaults (`postgres` / `postgres`) and override via environment
variables if you prefer:
```
DB_HOST=localhost  DB_PORT=5432  DB_NAME=loie_db
DB_USER=postgres   DB_PASSWORD=your_password
```

### Step 3 — Run

**IntelliJ IDEA**
1. **File ▸ Open** and select the `loie-expresses` folder.
2. Let IntelliJ import the Maven project and download dependencies.
3. Ensure **Project SDK = Java 21** (File ▸ Project Structure ▸ Project).
4. Run **`LoieExpressesApplication`** (a run configuration is already included), or
   right-click the class ▸ Run.
5. Open **http://localhost:8080**

**Command line**
```bash
./mvnw spring-boot:run        # macOS / Linux
mvnw.cmd spring-boot:run      # Windows
```

On first run, Hibernate creates all tables automatically (`ddl-auto=update`) and the app
seeds a default admin account, website settings and sample services.

---

## Default login

| Field    | Value      |
|----------|------------|
| Username | `admin`    |
| Password | `admin123` |
| Role     | SUPER_ADMIN|

Admin dashboard: **http://localhost:8080/admin/dashboard**
Public site: **http://localhost:8080/**

> Change the default password from **Users** in the dashboard immediately after first login.

---

## Features

### Public website
Home, About, Services (+ detail pages), Fleet, Gallery (lightbox), News (+ articles),
Contact (with map), and a **Get a Quote** page with four tabbed forms: quote, booking,
Bajaj/Bodaboda financing, and consultation.

### Admin dashboard
- **Dashboard** — statistics cards, a bookings-by-status doughnut chart, recent activity.
- **Website Settings** — edit hero, about, footer, contact, social links, Google Map, SEO,
  and upload the logo. The whole public site reads from here.
- **Services / Fleet / Gallery / News** — full create, edit, delete, image upload;
  news has publish/unpublish.
- **Bookings / Quotes / Loans / Consultations** — view requests and update status
  (Pending, Approved, Rejected, In Progress, Completed); loans support admin notes.
- **Messages** — inbox with reply (mailto), mark-read and delete.
- **Users** — Super Admin, Admin, Editor accounts (Users page is Super-Admin only).
- **Auth** — login, logout, forgot-password / reset-password flow (if no SMTP is
  configured the reset token is shown on screen; set the `MAIL_*` values in
  `application.properties` to email it instead).

---

## Project structure

```
loie-expresses/
├── pom.xml
├── mvnw / mvnw.cmd                 # Maven wrapper
├── src/main/java/com/loieexpresses/
│   ├── LoieExpressesApplication.java
│   ├── config/        # SecurityConfig, DataInitializer
│   ├── security/      # CustomUserDetailsService
│   ├── controller/    # public + auth + form controllers
│   │   └── admin/     # all admin dashboard controllers
│   ├── entity/        # JPA entities + enums
│   ├── repository/    # Spring Data repositories
│   ├── service/       # SettingsService, StatsService
│   └── util/          # FileStorageService
└── src/main/resources/
    ├── application.properties       # PostgreSQL + app config
    ├── static/css                   # style.css, admin.css
    └── templates/
        ├── fragments/  # layout + admin-layout
        ├── public/     # public pages
        └── admin/      # dashboard pages
```

---

## Roles & access control

| Role         | Access                                             |
|--------------|----------------------------------------------------|
| SUPER_ADMIN  | Everything, including User Management               |
| ADMIN        | All dashboard sections except User Management       |
| EDITOR       | All dashboard sections except User Management       |

Rules live in `config/SecurityConfig.java` — adjust `requestMatchers` to fine-tune.

---

## Uploaded files

Images are stored in the local `uploads/` folder (created automatically) and served at
`/uploads/**`. For production, point `app.upload.dir` to a persistent volume or swap
`FileStorageService` for cloud storage (S3, etc.).

---

## Building a deployable JAR

```bash
./mvnw clean package
java -jar target/loie-expresses-1.0.0.jar
```
Provide database credentials via environment variables when running on a server:
```bash
DB_HOST=... DB_NAME=loie_db DB_USER=... DB_PASSWORD=... \
  java -jar target/loie-expresses-1.0.0.jar
```

The app is a self-contained executable JAR (embedded Tomcat) — deploy it on any server
with Java 21, or containerise it with a simple `FROM eclipse-temurin:21-jre` Dockerfile.

---

## Customising the look

All brand colours are CSS variables at the top of `static/css/style.css`
(`--navy`, `--green`, `--gold`, …) and `static/css/admin.css`. Change them once to re-skin
the whole site.
