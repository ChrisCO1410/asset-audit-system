# Enterprise Asset & Compliance Audit Platform

An enterprise-grade, full-stack compliance and asset tracking dashboard engineered to manage hardware inventory lifecycle, audit logs, and regulatory compliance schedules. Built with a modern microservice-ready architecture using **Java Spring Boot**, **React**, **PostgreSQL**, and **Docker**.

---

## 🛠 Key Architecture & Features

- **Role-Based Access Control & Security:** Stateless authentication driven by Spring Security and JWT tokens.
- **Data Persistence & Audit Readiness:** Relational tracking using PostgreSQL with JPA/Hibernate for asset schedules and calibration metrics.
- **Modern Responsive Dashboard:** Dynamic React frontend styled with Tailwind CSS, supporting token handling and live state sync.
- **Containerized Deployment:** Fully containerized multi-service orchestration via Docker and Docker Compose.

---

## 🧰 Tech Stack

| Domain | Technology |
| :--- | :--- |
| **Backend Framework** | Java 21, Spring Boot, Spring Security |
| **Database** | PostgreSQL, Hibernate ORM |
| **Frontend Framework** | React (Vite), JavaScript, Tailwind CSS |
| **DevOps & Containerization** | Docker, Docker Compose |
| **Build Tools** | Maven, npm |

---

## 🚀 Quickstart Guide

### Prerequisites
- [Docker Desktop](https://www.docker.com/) installed and running.

### Local Startup with Docker Compose

1. Clone the repository:
   ```bash
   git clone [https://github.com/your-username/asset-audit-system.git](https://github.com/your-username/asset-audit-system.git)
   cd asset-audit-system