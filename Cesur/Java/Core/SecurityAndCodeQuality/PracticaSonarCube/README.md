# 📝 TodoList - Práctica SonarQube

> Aplicación de lista de tareas por terminal en Java, diseñada como demostración educativa de las funcionalidades de **SonarQube**.

## 📋 Descripción

Este proyecto es una aplicación de consola que permite gestionar tareas (añadir, listar, completar, eliminar, buscar y ver resumen). Su objetivo principal es servir como ejemplo para demostrar cómo **SonarQube** detecta problemas de calidad de código.

El código contiene **defectos intencionales** marcados con comentarios que SonarQube detectará como:
- 🐛 **Bugs**
- 🧹 **Code Smells**
- 🔒 **Vulnerabilities / Security Hotspots**
- 📄 **Duplications**
- 📊 **Coverage gaps**

---

## 🏗️ Estructura del Proyecto

```
PracticaSonarCube/
├── pom.xml                                    # Configuración Maven + JaCoCo + SonarQube
├── README.md                                  # Este archivo
├── src/
│   ├── main/java/org/drk/
│   │   ├── Main.java                          # Aplicación principal (menú terminal)
│   │   ├── model/
│   │   │   └── Task.java                      # Modelo de tarea
│   │   └── service/
│   │       └── TodoService.java               # Servicio CRUD de tareas
│   └── test/java/org/drk/
│       ├── model/
│       │   └── TaskTest.java                  # Tests del modelo
│       └── service/
│           └── TodoServiceTest.java           # Tests del servicio
```

---

## ⚙️ Requisitos

- **Java 17** o superior
- **Maven 3.8+**
- **Docker** (para ejecutar SonarQube)

---

## 🚀 Cómo Ejecutar la Aplicación

```bash
# Compilar
mvn clean compile

# Ejecutar
mvn exec:java -Dexec.mainClass="org.drk.Main"

# Ejecutar tests
mvn clean test

# Generar reporte de cobertura
mvn clean verify
```

---

## 🔍 Cómo Usar SonarQube

### Paso 1: Levantar SonarQube con Docker

```bash
docker run -d --name sonarqube -p 9000:9000 sonarqube:lts-community
```

Espera ~1-2 minutos a que arranque completamente. Accede a **http://localhost:9000**

### Paso 2: Configurar SonarQube

1. Inicia sesión con `admin` / `admin` (te pedirá cambiar la contraseña)
2. Crea un nuevo proyecto:
   - **Project key**: `PracticaSonarCube`
   - **Display name**: `Practica SonarQube - TodoList`
3. Selecciona **"Locally"** como método de análisis
4. Genera un **token** de autenticación (guárdalo)
5. Selecciona **Maven** como build tool

### Paso 3: Ejecutar el Análisis

```bash
# Compilar, ejecutar tests, generar cobertura y enviar a SonarQube
mvn clean verify sonar:sonar \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.token=TU_TOKEN_AQUI
```

> **Windows (PowerShell):**
> ```powershell
> mvn clean verify sonar:sonar `
>   -D"sonar.host.url=http://localhost:9000" `
>   -D"sonar.token=TU_TOKEN_AQUI"
> ```

### Paso 4: Ver los Resultados

Accede a **http://localhost:9000/dashboard?id=PracticaSonarCube** para ver:

- **Overview**: Resumen general de calidad
- **Issues**: Bugs, Code Smells, Vulnerabilities
- **Measures**: Métricas detalladas
- **Code**: Código fuente con anotaciones de problemas
- **Activity**: Historial de análisis

---

## 🐛 Defectos Intencionales para SonarQube

### Tabla de Defectos

| # | Tipo | Archivo | Descripción | Regla SonarQube |
|---|------|---------|-------------|-----------------|
| 1 | 🐛 Bug | `Task.java` | `equals()` sin `hashCode()` | S1206 |
| 2 | 🐛 Bug | `Main.java` | Comparación de String con `==` en vez de `.equals()` | S4973 |
| 3 | 🐛 Bug | `TodoService.java` | No se valida `null` en `addTask()` | S2259 |
| 4 | 🐛 Bug | `TodoService.java` | `getTitle().toUpperCase()` puede lanzar NPE | S2259 |
| 5 | 🧹 Code Smell | `Main.java` | Bloque `catch` vacío (traga excepciones) | S108 |
| 6 | 🧹 Code Smell | `Main.java` | Scanner nunca se cierra (resource leak) | S2095 |
| 7 | 🧹 Code Smell | `Task.java` | Concatenación de Strings en vez de StringBuilder | S1643 |
| 8 | 🧹 Code Smell | `Task.java` | Campo `public` debería ser `private` | S1104 |
| 9 | 🧹 Code Smell | `Task.java` | Complejidad cognitiva alta en `isValid()` | S3776 |
| 10 | 🧹 Code Smell | `TodoService.java` | Método privado sin usar (`unusedPrivateMethod`) | S1144 |
| 11 | 🧹 Code Smell | `TodoService.java` | Switch sin `default` en `getSummary()` | S131 |
| 12 | 🔒 Security Hotspot | `Task.java` | Contraseña hardcodeada (`password = "admin123"`) | S2068 |
| 13 | 🔒 Security Hotspot | `TodoService.java` | Credenciales hardcodeadas (`DB_PASSWORD`, `DB_USER`) | S2068 |
| 14 | 🔒 Security Hotspot | `TodoService.java` | Credenciales impresas en consola | S2068 |
| 15 | 📄 Duplication | `Task.java` | `getInfo()` es duplicado exacto de `toString()` | Duplications |
| 16 | 📄 Duplication | `TodoService.java` | `findByTitle()` y `findByDescription()` casi idénticos | Duplications |
| 17 | 📊 Coverage | Tests | Métodos sin testear (getInfo, getPassword, getAllTitles) | Coverage < 80% |

---

## 📊 Lo que SonarQube Mostrará

### 1. Quality Gate
SonarQube evaluará si el proyecto pasa el "Quality Gate" (umbral de calidad). Con los defectos intencionales, probablemente **NO pasará** ❌.

### 2. Métricas Principales
- **Reliability (Fiabilidad)**: Rating basado en bugs encontrados (A-E)
- **Security (Seguridad)**: Rating basado en vulnerabilidades (A-E)
- **Maintainability (Mantenibilidad)**: Rating basado en code smells y deuda técnica
- **Coverage (Cobertura)**: Porcentaje de código cubierto por tests
- **Duplications (Duplicaciones)**: Porcentaje de código duplicado

### 3. Issues (Problemas)
Cada issue tiene:
- **Tipo**: Bug, Vulnerability, Code Smell, Security Hotspot
- **Severidad**: Blocker, Critical, Major, Minor, Info
- **Esfuerzo estimado**: Tiempo estimado para corregirlo
- **Regla**: Identificador de la regla violada (ej: `java:S1206`)

### 4. Security Hotspots
Los security hotspots requieren **revisión manual**. SonarQube los marca como:
- **To Review**: Pendiente de revisar
- **Acknowledged**: Revisado, riesgo aceptado
- **Fixed**: Corregido
- **Safe**: Revisado, no es un problema real

### 5. Deuda Técnica
SonarQube calcula el tiempo estimado para corregir todos los problemas. Esto ayuda a priorizar qué arreglar primero.

---

## 🛠️ Cómo Corregir los Defectos

Cada defecto en el código tiene un comentario `VERSIÓN CORREGIDA` con la solución. Ejemplos:

### Bug: `equals()` sin `hashCode()`
```java
// ANTES (Bug):
@Override
public boolean equals(Object obj) { ... }
// Sin hashCode()

// DESPUÉS (Corregido):
@Override
public int hashCode() {
    return Objects.hash(id);
}
```

### Bug: Comparación de String con `==`
```java
// ANTES (Bug):
if (title == "") { ... }

// DESPUÉS (Corregido):
if (title.equals("") || title.isBlank()) { ... }
```

### Code Smell: Resource leak
```java
// ANTES (Smell):
Scanner scanner = new Scanner(System.in);
// ...sin cerrar

// DESPUÉS (Corregido):
try (Scanner scanner = new Scanner(System.in)) {
    // ...uso del scanner
}
```

---

## 📖 Características de SonarQube Demostradas

| Característica | Descripción |
|---------------|-------------|
| **Static Analysis** | Análisis estático del código sin ejecutarlo |
| **Bug Detection** | Detecta errores reales en tiempo de compilación |
| **Code Smells** | Identifica prácticas de código que dificultan el mantenimiento |
| **Vulnerabilities** | Encuentra problemas de seguridad (OWASP, CWE) |
| **Security Hotspots** | Marca código que requiere revisión de seguridad manual |
| **Code Coverage** | Muestra qué código está cubierto por tests (requiere JaCoCo) |
| **Code Duplication** | Detecta bloques de código repetidos |
| **Technical Debt** | Estima el tiempo necesario para corregir todos los problemas |
| **Quality Gate** | Define umbrales mínimos de calidad (pasa/no pasa) |
| **Quality Profiles** | Conjuntos de reglas configurables por lenguaje |
| **Multi-language** | Soporta Java, JS, Python, C#, Go, y muchos más |
| **CI/CD Integration** | Se integra con Jenkins, GitHub Actions, GitLab CI, etc. |
| **Historical Tracking** | Guarda historial de análisis para ver evolución |

---

## 📚 Comandos Útiles

```bash
# Solo compilar
mvn clean compile

# Ejecutar tests
mvn clean test

# Compilar + tests + cobertura JaCoCo
mvn clean verify

# Análisis completo con SonarQube
mvn clean verify sonar:sonar -Dsonar.token=TU_TOKEN

# Ver reporte de cobertura local (JaCoCo)
# Abrir: target/site/jacoco/index.html

# Parar SonarQube Docker
docker stop sonarqube

# Reiniciar SonarQube Docker
docker start sonarqube

# Ver logs de SonarQube
docker logs -f sonarqube
```

---

## 👤 Autor

**DRK** - Práctica de Ciberseguridad - CESUR

---

## 📄 Licencia

Proyecto educativo - Uso libre para fines de aprendizaje.

