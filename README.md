# RMI-SERVER
A distributed tenant billing and payment management system that allows property managers to handle rent tracking, tenant records, and payment summaries. It features a JavaFX GUI, secure login, CRUD operations, and a real-time dashboard, all powered by Java RMI for remote communication.
Here’s a professional and complete **GitHub `README.md`** you can use for your Tenant Billing & Payment System project. It includes a project description, features, setup instructions, screenshots, and contribution/license notes.


markdown
# Tenant Billing & Payment Management System

A distributed system that allows property managers to manage tenants, track rent payments, and view real-time financial summaries. The system is built using Java RMI for remote communication, PostgreSQL for backend storage, and JavaFX for the user interface.

---

## 🧠 Overview

This project demonstrates a full client-server architecture using:

- **Java RMI** for remote method invocation across the network
- **JavaFX** for the frontend (GUI) client application
- **PostgreSQL** as the backend database
- **Java OOP principles** and layered architecture for separation of concerns

---

## 🚀 Features

- Secure login with visible demo credentials
- Full tenant CRUD (Create, Read, Update, Delete)
- Real-time dashboard with summaries:
  - Total tenants
  - Total rent collected
  - Unpaid balances
- Settings page with user role and password form
- 50 demo tenants with realistic Kenyan names
- RMI registry setup with port handling

---

## 🏗️ Architecture

- `SharedLibrary` (contains shared interfaces and models)
- `BillingServer` (RMI server + database logic)
- `BillingClient` (JavaFX GUI client)

---

## 💻 Technologies Used

- Java 17+
- JavaFX SDK 17/21
- PostgreSQL
- Java RMI
- NetBeans IDE (for development/build)



🛠️ Setup Instructions
 
🗄️  Database Setup (PostgreSQL)

sql
CREATE DATABASE tenant_db;

\c tenant_db

CREATE TABLE tenants (
    id INT PRIMARY KEY,
    name VARCHAR(100),
    house_number VARCHAR(20),
    rent_amount NUMERIC,
    paid_amount NUMERIC,
    payment_date VARCHAR(20)
);

Load the 50 demo tenants
See `/database/demo_data.sql` for full insert script


 ⚙️ 3. Run the Server

bash
cd BillingServer/dist
java -jar BillingServer.jar


🧑‍💻 4. Run the Client

Ensure JavaFX is set up and run:

bash
cd BillingClient/dist
java --module-path "C:\javafx-sdk-21\lib" --add-modules javafx.controls,javafx.fxml -cp "BillingClient.jar;SharedLibrary.jar" client.LoginScreen


 🔐 Login Credentials


Username: admin
Password: 1234




## 🖼️ Screenshots

| Login Screen               | Dashboard View                 | CRUD Manager                 | Settings Page                 |
| -------------------------- | ------------------------------ | ---------------------------- | ----------------------------- |
| ![](screenshots/login.png) | ![](screenshots/dashboard.png) | ![](screenshots/tenants.png) | ![](screenshots/settings.png) |

---

 📁 Folder Structure


├── BillingClient
├── BillingServer
├── SharedLibrary
├── database
│   └── demo_data.sql
└── README.md




 🤝 Contribution

Pull requests and issue reports are welcome. Please ensure code follows standard Java conventions and is properly tested before submission.



📄 License

This project is for academic use only under the MIT License.




