# MySQL Schema Design — Smart Clinic Management System

## 🏥 Tables Overview
| Table | Description |
|--------|-------------|
| **doctor** | Stores doctor details like name, email, password, and specialty. |
| **patient** | Stores patient info such as name, email, phone, and password. |
| **appointment** | Connects doctors and patients for a specific date/time. |
| **prescription** | Contains medication details linked to doctor & patient. |
| **doctor_available_times** | Stores each doctor’s available time slots for appointments. |

---

## 🧩 Table Structures

### 1️⃣ doctor
| Column | Type | Constraints |
|---------|------|-------------|
| id | BIGINT | Primary Key |
| name | VARCHAR(100) | NOT NULL |
| email | VARCHAR(100) | UNIQUE, NOT NULL |
| password | VARCHAR(100) | NOT NULL |
| speciality | VARCHAR(100) | NOT NULL |

---

### 2️⃣ patient
| Column | Type | Constraints |
|---------|------|-------------|
| id | BIGINT | Primary Key |
| name | VARCHAR(100) | NOT NULL |
| email | VARCHAR(100) | UNIQUE, NOT NULL |
| phone | VARCHAR(20) | NOT NULL |
| password | VARCHAR(100) | NULL |

---

### 3️⃣ appointment
| Column | Type | Constraints |
|---------|------|-------------|
| id | BIGINT | Primary Key |
| doctor_id | BIGINT | FK → doctor(id) |
| patient_id | BIGINT | FK → patient(id) |
| appointment_time | DATETIME | NOT NULL |

---

### 4️⃣ prescription
| Column | Type | Constraints |
|---------|------|-------------|
| id | BIGINT | Primary Key |
| doctor_id | BIGINT | FK → doctor(id) |
| patient_id | BIGINT | FK → patient(id) |
| medication | VARCHAR(255) | NOT NULL |
| notes | TEXT | NULL |

---

### 5️⃣ doctor_available_times
| Column | Type | Constraints |
|---------|------|-------------|
| doctor_id | BIGINT | FK → doctor(id) |
| time_slot | TIME | NOT NULL |

---

## 🔗 Relationships (ER Diagram Summary)
- **Doctor ↔ Appointment:** One-to-Many  
- **Patient ↔ Appointment:** One-to-Many  
- **Doctor ↔ Prescription:** One-to-Many  
- **Patient ↔ Prescription:** One-to-Many  
- **Doctor ↔ Available Times:** One-to-Many

✅ *All foreign keys ensure data integrity.*
✅ *Indexed columns for faster search (doctor_id, appointment_time).*
