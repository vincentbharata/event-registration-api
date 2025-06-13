# event-registration-api

## 🧩 Soal 1 – Event Registration System

### 📁 Repository: `event-registration-api`

### 🎯 Product Requirement:

Aplikasi untuk manajemen event dan pendaftaran peserta.

### 📡 Endpoint (Minimal 8):

| Method | Endpoint                    | Description                        |
| ------ | --------------------------- | ---------------------------------- |
| POST   | `/events`                   | Buat event baru                    |
| GET    | `/events`                   | List semua event (search & paging) |
| GET    | `/events/{id}`              | Detail event                       |
| PUT    | `/events/{id}`              | Update event                       |
| DELETE | `/events/{id}`              | Hapus event                        |
| POST   | `/participants`             | Daftar peserta ke event            |
| GET    | `/events/{id}/participants` | List peserta event                 |
| DELETE | `/participants/{id}`        | Hapus peserta                      |
| GET    | `/stats/events`             | Jumlah peserta per event           |

### 🧪 Validasi:

- `@NotBlank` → title, name, location
- `@Email` → email peserta
- `@Pattern` → phone number
- Custom: `start_date < end_date`

---

