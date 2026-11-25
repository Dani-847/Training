# 🏨 API de Hoteles - Prototipo de Turismo

API REST desarrollada con **Spring Boot** para gestionar información de hoteles a nivel mundial. Utiliza **MongoDB Atlas** como base de datos y ofrece endpoints de lectura públicos y operaciones de escritura protegidas con **Spring Security**.

---

## 🛠 Tecnologías

- Java 17
- Spring Boot 4.0.2
- Spring Security (Basic Authentication)
- Spring Data MongoDB
- MongoDB Atlas
- Lombok

---

## 🔐 Autenticación

| Operación | Autenticación |
|-----------|---------------|
| GET (lectura) | ❌ No |
| POST, PUT, DELETE | ✅ Sí (Basic Auth) |

---

## 📚 Endpoints

### Base URL: `http://localhost:8080/api/v1/hotels`

---

## 📖 LECTURA (Públicos)

### 1. Listar todos los hoteles (id y nombre)
```http
GET /api/v1/hotels
```
**Respuesta:**
```json
[
  { "id": "698da3b0cb5331a6b8da2c6b", "hotelName": "Hotel Green" },
  { "id": "698da3b0cb5331a6b8da2c6c", "hotelName": "Grand Palace" }
]
```

---

### 2. Obtener hotel completo por ID
```http
GET /api/v1/hotels/{id}
```
**Ejemplo:** `GET /api/v1/hotels/698da3b0cb5331a6b8da2c6b`

---

### 3. Obtener hotel por código
```http
GET /api/v1/hotels/code/{hotelCode}
```
**Ejemplo:** `GET /api/v1/hotels/code/1003301`

---

### 4. Buscar hoteles por nombre
```http
GET /api/v1/hotels/search/{name}
```
**Ejemplo:** `GET /api/v1/hotels/search/Hilton`

**Respuesta:**
```json
[
  { "id": "...", "hotelName": "Hilton Garden Inn" },
  { "id": "...", "hotelName": "Hilton Paris" }
]
```

---

### 5. Listar hoteles ordenados por rating
```http
GET /api/v1/hotels/rating
```
**Respuesta:**
```json
[
  { "hotelName": "Luxury Resort", "hotelRating": "FiveStar" },
  { "hotelName": "Grand Hotel", "hotelRating": "FourStar" }
]
```

---

### 6. Listar hoteles por rating específico
```http
GET /api/v1/hotels/rating/{rating}
```
Valores: `FiveStar`, `FourStar`, `ThreeStar`, `TwoStar`, `OneStar`

**Ejemplo:** `GET /api/v1/hotels/rating/FiveStar`

**Respuesta:**
```json
[
  { "id": "...", "hotelName": "Ritz Carlton", "hotelRating": "FiveStar" }
]
```

---

### 7. Listar hoteles por país
```http
GET /api/v1/hotels/country/{countryCode}
```
**Ejemplo:** `GET /api/v1/hotels/country/ES`

**Respuesta:**
```json
[
  { "id": "...", "hotelName": "Hotel Madrid", "cityName": "Madrid" },
  { "id": "...", "hotelName": "Hotel Barcelona", "cityName": "Barcelona" }
]
```

---

### 8. Listar hoteles por ciudad
```http
GET /api/v1/hotels/city/{cityName}
```
**Ejemplo:** `GET /api/v1/hotels/city/Paris`

**Respuesta:**
```json
[
  { "id": "...", "hotelName": "Le Grand Paris", "address": "123 Champs-Élysées" }
]
```

---

### 9. Listar hoteles con atracciones
```http
GET /api/v1/hotels/attractions
```
**Respuesta:**
```json
[
  { "id": "...", "hotelName": "Beach Resort", "attractions": "Beach, Museum, Park" }
]
```

---

### 10. Listar hoteles por facilidad
```http
GET /api/v1/hotels/facilities/{facility}
```
**Ejemplo:** `GET /api/v1/hotels/facilities/WiFi`

**Respuesta:**
```json
[
  { "id": "...", "hotelName": "Tech Hotel", "hotelFacilities": "WiFi Pool Spa" }
]
```

---

### 11. Estadísticas generales
```http
GET /api/v1/hotels/stats
```
**Respuesta:**
```json
{
  "totalHotels": 1000000,
  "fiveStarHotels": 50000,
  "fourStarHotels": 150000,
  "threeStarHotels": 300000
}
```

---

## ✏️ ESCRITURA (Requieren Autenticación)

### 12. Crear hotel
```http
POST /api/v1/hotels
Authorization: Basic base64(user:password)
Content-Type: application/json
```
**Body:**
```json
{
  "hotelCode": 9999999,
  "hotelName": "Mi Nuevo Hotel",
  "hotelRating": "FourStar",
  "countryCode": "ES",
  "countryName": "Spain",
  "cityName": "Madrid",
  "address": "Calle Gran Vía 123"
}
```

---

### 13. Actualizar hotel
```http
PUT /api/v1/hotels/{id}
Authorization: Basic base64(user:password)
Content-Type: application/json
```
**Body:**
```json
{
  "hotelName": "Hotel Actualizado",
  "hotelRating": "FiveStar"
}
```

---

### 14. Eliminar hotel
```http
DELETE /api/v1/hotels/{id}
Authorization: Basic base64(user:password)
```

---

## ❌ Manejo de Errores

```json
{
  "error": "Hotel not found",
  "message": "Hotel no encontrado con ID: xxx",
  "errorCode": 404
}
```

| Código | Descripción |
|--------|-------------|
| 400 | Bad Request |
| 401 | Unauthorized |
| 404 | Not Found |
| 409 | Conflict (duplicado) |
| 500 | Internal Server Error |

---

## 🚀 Ejecución

```bash
./mvnw spring-boot:run
```

---

## 👤 Autor

Daniel Rodriguez Karcz - Reto 4 AD CESUR

