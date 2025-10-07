# curl command examples

# 1) Generate a token (example assumes you expose an auth endpoint later)
# echo TOKEN=$(curl -s http://localhost:8080/api/auth?email=doctor@example.com)

# 2) Get doctor availability
curl -H "Authorization: Bearer $TOKEN" "http://localhost:8080/api/doctors/availability?doctorId=1&date=2025-01-15"

# 3) Book appointment
curl -X POST -H "Authorization: Bearer $TOKEN" "http://localhost:8080/api/appointments/book?doctorId=1&patientId=1&when=2025-01-15T09:30:00"

# 4) Create prescription
curl -X POST -H "Content-Type: application/json" -H "Authorization: Bearer $TOKEN" -d '{"doctor":{"id":1},"patient":{"id":1},"medication":"Ibuprofen 400mg","notes":"After meals"}' http://localhost:8080/api/prescriptions
