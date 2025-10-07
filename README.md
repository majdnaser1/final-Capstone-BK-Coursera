# Smart Clinic Management System (Backend)

Spring Boot backend implementing key components required by the assignment:
- Entities: Doctor, Patient, Appointment, Prescription
- Repositories: with specified query methods
- Services: AppointmentService, DoctorService, TokenService (JWT)
- Controllers: DoctorController, AppointmentController, PrescriptionController
- Dockerfile (multi-stage) and GitHub Actions workflow
- Schema design + stored procedures + curl examples

## Run
- Configure MySQL in `src/main/resources/application.properties`
- Start: `mvn spring-boot:run`

## Endpoints
- `GET /api/doctors/availability?doctorId=&date=YYYY-MM-DD` (Bearer token required)
- `POST /api/appointments/book?doctorId=&patientId=&when=YYYY-MM-DDTHH:mm:ss`
- `POST /api/prescriptions` (JSON body)

## Notes
- Provide `JWT_SECRET` env var in production.
- MySQL must be reachable (default user root, empty password).
