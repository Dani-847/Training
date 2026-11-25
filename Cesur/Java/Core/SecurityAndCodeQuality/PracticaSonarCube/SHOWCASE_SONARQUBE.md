# 🚀 Guía Práctica — SonarQube con TodoList

¡Bienvenido! En esta guía vamos a ver paso a paso cómo funciona **SonarQube** usando una aplicación de lista de tareas (TodoList) hecha en Java. El código tiene defectos puestos a propósito para que SonarQube los detecte y podamos ver todas sus funcionalidades en acción.

Vamos allá 👇

---

## 1. Arrancamos SonarQube con Docker

Lo primero que necesitamos es tener SonarQube corriendo. Para eso usamos Docker, que nos lo pone muy fácil:

```bash
docker run -d --name sonarqube -p 9000:9000 sonarqube:lts-community
```

Esto descarga la imagen y arranca SonarQube en segundo plano. Tarda **1-2 minutos** en estar listo. Para comprobar que ya está, abrimos el navegador y vamos a:

👉 **http://localhost:9000**

Nos pide credenciales. Ponemos:
- **Usuario:** `admin`
- **Contraseña:** `admin`

Nos pedirá cambiar la contraseña, ponemos la que queramos y listo, ya estamos dentro.

> 💡 Si la página no carga todavía, podemos ver cómo va con: `docker logs sonarqube --tail 10`

---

## 2. Creamos el proyecto en SonarQube

Ahora necesitamos decirle a SonarQube que vamos a analizar nuestro proyecto. Para eso:

1. Hacemos clic en **"Create Project"** → **"Manually"**
2. Rellenamos:
   - **Project display name:** `Practica SonarQube - TodoList`
   - **Project key:** `PracticaSonarCube`
3. Le damos a **"Set Up"**
4. Elegimos **"Locally"** (porque lo vamos a analizar desde nuestra máquina)
5. Generamos un **token**:
   - Le ponemos un nombre (por ejemplo `mi-token`)
   - Tipo: **User Token**
   - Le damos a **Generate** y **copiamos el token** que nos da
6. Seleccionamos **Maven** como herramienta de build

> ⚠️ **Importante:** Abrimos el archivo `pom.xml` del proyecto y pegamos el token en la línea `<sonar.login>TU_TOKEN_AQUÍ</sonar.login>`

---

## 3. Echamos un vistazo al proyecto

Antes de analizar nada, veamos qué tenemos. El proyecto tiene esta estructura:

```
PracticaSonarCube/
├── pom.xml                          ← Configuración de Maven, JaCoCo y SonarQube
├── README.md                        ← Documentación del proyecto
├── SHOWCASE_SONARQUBE.md            ← Esta guía que estás leyendo
├── src/
│   ├── main/java/org/drk/
│   │   ├── Main.java                ← La app de terminal (menú interactivo)
│   │   ├── model/
│   │   │   └── Task.java            ← El modelo de tarea (con defectos a propósito)
│   │   └── service/
│   │       └── TodoService.java     ← El servicio CRUD (con defectos a propósito)
│   └── test/java/org/drk/
│       ├── model/
│       │   └── TaskTest.java        ← 12 tests del modelo
│       └── service/
│           └── TodoServiceTest.java ← 20 tests del servicio
```

Si abrimos cualquier archivo del código fuente, veremos que cada defecto intencional está marcado con un comentario `// SONARQUBE: ...` y justo debajo tiene una `VERSIÓN CORREGIDA` comentada. Así se ve claramente qué es a propósito y cómo se arreglaría.

---

## 4. Compilamos el proyecto

Abrimos una terminal en la carpeta del proyecto y lo compilamos:

```bash
mvn clean compile
```

Si todo va bien, veremos `BUILD SUCCESS`. El proyecto compila sin errores usando Java 17.

---

## 5. Ejecutamos los tests

Como hemos desarrollado tests unitarios con **JUnit 5**, vamos a ejecutarlos para comprobar que todo funciona:

```bash
mvn clean test
```

Aquí vemos que se ejecutan **32 tests** (12 del modelo + 20 del servicio) y todos pasan: **0 fallos, 0 errores**.

Los tests cubren cosas como: crear tareas, buscar por ID, eliminar, completar, buscar por título, edge cases con null, etc. Usamos anotaciones como `@Test`, `@DisplayName` y `@BeforeEach`.

**Un detalle importante:** hemos dejado algunos métodos **sin testear a propósito** (como `getInfo()`, `getPassword()`, `getAllTitles()`). Esto es para que luego SonarQube nos diga que la cobertura no es del 100%.

---

## 6. Generamos el reporte de cobertura con JaCoCo

Para que SonarQube sepa qué partes del código están cubiertas por tests, usamos **JaCoCo**. Como ya lo tenemos configurado en el `pom.xml`, solo tenemos que ejecutar:

```bash
mvn clean verify
```

Esto compila, ejecuta los tests y genera el reporte de cobertura. Para verlo en local, abrimos:

```bash
start target\site\jacoco\index.html
```

Aquí podemos ver visualmente:
- Las barras **verdes** → código que sí ejecuta los tests
- Las barras **rojas** → código que NO ejecuta ningún test
- `Main.java` aparece con 0% de cobertura (es la interfaz de terminal, no se puede testear fácilmente)
- Los métodos que dejamos sin testear aparecen en rojo

---

## 7. Lanzamos el análisis de SonarQube

¡Ahora viene lo bueno! Este comando hace todo de golpe: compila, ejecuta tests, genera cobertura y envía los resultados a SonarQube:

```bash
mvn clean verify sonar:sonar
```

En la terminal vemos cómo trabaja paso a paso:
- `Sensor JavaSensor` → analiza nuestro código Java
- `Sensor JaCoCo XML Report Importer` → importa los datos de cobertura
- `Sensor TextAndSecretsSensor` → busca credenciales y secretos en el código
- `CPD Executor` → detecta código duplicado (copy-paste)
- Al final nos dice `ANALYSIS SUCCESSFUL` y nos da la URL del dashboard

---

## 8. Vemos el Dashboard principal

Abrimos el navegador y vamos a:

👉 **http://localhost:9000/dashboard?id=PracticaSonarCube**

Aquí es donde SonarQube nos muestra el resumen de todo lo que ha encontrado. Vemos de un vistazo:

- **Quality Gate** → si el proyecto pasa o no el umbral de calidad (con nuestros defectos seguramente salga **Failed** ❌)
- **Bugs** → errores reales en el código
- **Code Smells** → malas prácticas que dificultan el mantenimiento
- **Vulnerabilities** → problemas de seguridad
- **Security Hotspots** → código que necesita revisión de seguridad
- **Coverage** → qué porcentaje del código cubren los tests
- **Duplications** → qué porcentaje de código está repetido
- **Ratings** → letras de la A (👍) a la E (👎) para fiabilidad, seguridad y mantenibilidad

---

## 9. Exploramos los Issues — Bugs

Hacemos clic en la pestaña **"Issues"** o vamos a:

👉 **http://localhost:9000/project/issues?id=PracticaSonarCube**

Para ver los bugs, filtramos por **Type = Bug**. Deberían aparecer cosas como:

| Bug | Archivo | ¿Qué pasa? |
|-----|---------|-----------|
| `equals()` sin `hashCode()` | Task.java | Sobreescribimos equals pero no hashCode, y eso viola el contrato de Java |
| Comparación de String con `==` | Main.java | Usamos `title == ""` cuando debería ser `title.equals("")` |

Si hacemos **clic en un bug**, vemos algo muy útil:
- La **línea exacta** del código donde está el problema
- La **severidad** (Critical, Major, Minor...)
- El **tiempo estimado** para arreglarlo (ej: "5 min")
- La **regla** que hemos violado (ej: `java:S1206`)
- Si hacemos clic en la regla → nos explica con detalle qué está mal y cómo corregirlo con ejemplos

---

## 10. Exploramos los Issues — Code Smells

Ahora cambiamos el filtro a **Type = Code Smell**. Aquí aparecen las malas prácticas que hemos puesto a propósito:

| Code Smell | Archivo | ¿Qué pasa? |
|-----------|---------|-----------|
| Bloque catch vacío | Main.java | Capturamos una excepción pero no hacemos nada con ella (se "traga" el error) |
| Método privado sin usar | TodoService.java | `unusedPrivateMethod()` existe pero nadie lo llama — es código muerto |
| Switch sin default | TodoService.java | El switch de `getSummary()` no tiene un caso default para valores inesperados |
| Complejidad cognitiva alta | Task.java | `isValid()` tiene tantos if-else que es difícil de leer y mantener |
| Campo público | Task.java | `public int id` debería ser private y accederse con un getter |

También podemos filtrar por **Severity** (Blocker, Critical, Major, Minor, Info) para ver cómo SonarQube prioriza qué arreglar primero.

---

## 11. Exploramos los Security Hotspots

Hacemos clic en la pestaña **"Security Hotspots"** o vamos a:

👉 **http://localhost:9000/security_hotspots?id=PracticaSonarCube**

Aquí SonarQube nos señala código que podría ser un riesgo de seguridad. En nuestro proyecto ha detectado las **credenciales hardcodeadas** que pusimos a propósito:

| Hotspot | Archivo | ¿Qué ha encontrado? |
|---------|---------|---------------------|
| Password en texto plano | Task.java | `password = "admin123"` directamente en el código |
| Credenciales de BD | TodoService.java | `DB_PASSWORD = "root1234"` y `DB_USER = "admin"` |
| Credenciales impresas | TodoService.java | Un `System.out.println` que imprime el usuario y la contraseña por consola |

Si hacemos clic en un hotspot, vemos:
- A qué categoría de seguridad pertenece (**OWASP**, **CWE**)
- El código afectado resaltado
- Una explicación de por qué es peligroso
- Opciones de revisión: podemos marcarlo como **To Review**, **Safe**, **Fixed** o **Acknowledged**

---

## 12. Exploramos las Measures (Métricas)

Hacemos clic en **"Measures"** o vamos a:

👉 **http://localhost:9000/component_measures?id=PracticaSonarCube**

Aquí SonarQube nos da métricas detalladas de todo. Vamos navegando por cada categoría:

| Categoría | Qué vemos |
|-----------|-----------|
| **Reliability** | Cuántos bugs hay y qué rating tiene (A-E) |
| **Security** | Cuántas vulnerabilidades y su rating |
| **Maintainability** | Cuántos code smells hay + la **deuda técnica** (el tiempo que tardaríamos en arreglar todo) |
| **Coverage** | El porcentaje de cobertura total. Si hacemos clic, vemos el detalle **por archivo** |
| **Duplications** | El porcentaje de código duplicado y **qué bloques** están repetidos |
| **Size** | Líneas de código, número de archivos, clases y funciones |
| **Complexity** | Complejidad ciclomática y cognitiva de cada archivo |

Lo más interesante es hacer clic en **Coverage** → ahí se abre una vista donde vemos archivo por archivo cuánto está cubierto por tests.

---

## 13. Vemos el código anotado por SonarQube

Hacemos clic en la pestaña **"Code"** o vamos a:

👉 **http://localhost:9000/code?id=PracticaSonarCube**

Aquí navegamos por los archivos del proyecto y vemos algo muy visual:

1. Entramos en `src/main/java/org/drk/model/` → abrimos **Task.java**
2. A la izquierda de cada línea vemos **barras de colores**:
   - 🟢 **Verde** = esta línea la ejecuta algún test
   - 🔴 **Roja** = esta línea NO la ejecuta ningún test
3. También vemos **iconos** en las líneas que tienen bugs o smells
4. Repetimos con **TodoService.java** y **Main.java** para ver las diferencias de cobertura

---

## 14. Vemos las duplicaciones de código

Dentro de **Measures → Duplications**, vemos que SonarQube ha detectado nuestro código copiado:

- En **Task.java**: el método `getInfo()` es un **duplicado exacto** de `toString()`. Los pusimos iguales a propósito y SonarQube los marca.
- En **TodoService.java**: los métodos `findByTitle()` y `findByDescription()` son casi idénticos (solo cambia el campo que consultan). Es el típico copy-paste que SonarQube detecta.

Si hacemos clic en los bloques duplicados, podemos ver la comparación lado a lado.

---

## 15. Corregimos un defecto y re-analizamos

Para demostrar que SonarQube refleja los cambios en tiempo real, vamos a corregir un bug y ver cómo mejora.

### Abrimos `Task.java` y buscamos el método `equals()` (línea ~67)

Justo debajo hay un bloque comentado con la versión corregida. Lo descomentamos para añadir el `hashCode()` que falta:

```java
// Descomentamos esto:
@Override
public int hashCode() {
    return Objects.hash(id);
}
```

### Ahora re-ejecutamos el análisis:

```bash
mvn clean verify sonar:sonar
```

### Volvemos al Dashboard de SonarQube y vemos que:

- El **número de bugs ha bajado** (uno menos)
- El issue de `equals() sin hashCode()` **ya no aparece** en la lista
- En la pestaña **Activity** se ve un nuevo punto en la gráfica con la mejora

Esto demuestra cómo SonarQube permite hacer seguimiento de la evolución de la calidad del código a lo largo del tiempo.

---

## 16. Vemos el historial en Activity

Hacemos clic en la pestaña **"Activity"** o vamos a:

👉 **http://localhost:9000/project/activity?id=PracticaSonarCube**

Aquí vemos:
- Cada análisis que hemos hecho, con su fecha y hora
- Si hicimos el paso 15 (la corrección), se ven **al menos 2 análisis** y la mejora entre ambos
- Las gráficas muestran cómo evolucionan los bugs, la cobertura, los code smells, etc.

Esto es muy útil en proyectos reales para ver si la calidad mejora o empeora con cada sprint.

---

## 17. (Bonus) Probamos la aplicación

Para ver que la TodoList funciona como app de terminal:

```bash
mvn exec:java -Dexec.mainClass="org.drk.Main"
```

Se abre un menú donde podemos:
1. ➕ Añadir tareas
2. 📋 Listar tareas
3. ✅ Completar tareas
4. 🗑️ Eliminar tareas
5. 🔍 Buscar tareas
6. 📊 Ver resumen estadístico
7. 🚪 Salir

---

## 18. (Bonus) Consultamos la API REST de SonarQube

SonarQube tiene una API REST completa. Podemos probar estas URLs directamente en el navegador (estando logueados):

```
# Métricas del proyecto
http://localhost:9000/api/measures/component?component=PracticaSonarCube&metricKeys=bugs,code_smells,coverage,security_hotspots,vulnerabilities

# Ver solo los bugs
http://localhost:9000/api/issues/search?componentKeys=PracticaSonarCube&types=BUG

# Estado del Quality Gate
http://localhost:9000/api/qualitygates/project_status?projectKey=PracticaSonarCube

# Security Hotspots
http://localhost:9000/api/hotspots/search?projectKey=PracticaSonarCube
```

---

## 📊 Tabla resumen — Todos los defectos intencionales

Todos están marcados en el código con `// SONARQUBE: ...` y tienen una versión corregida comentada:

| # | Tipo | Archivo | Defecto |
|---|------|---------|---------|
| 1 | 🐛 Bug | Task.java | `equals()` sin `hashCode()` |
| 2 | 🐛 Bug | Main.java | Comparación de String con `==` en vez de `.equals()` |
| 3 | 🐛 Bug | TodoService.java | `addTask(null)` no valida null |
| 4 | 🐛 Bug | TodoService.java | `getTitle().toUpperCase()` puede lanzar NullPointerException |
| 5 | 🧹 Smell | Main.java | Bloque catch vacío que traga excepciones |
| 6 | 🧹 Smell | Main.java | Scanner nunca se cierra (resource leak) |
| 7 | 🧹 Smell | Task.java | Campo `public id` debería ser private |
| 8 | 🧹 Smell | Task.java | Complejidad cognitiva alta en `isValid()` |
| 9 | 🧹 Smell | TodoService.java | Método privado `unusedPrivateMethod()` nunca usado |
| 10 | 🧹 Smell | TodoService.java | Switch sin `default` en `getSummary()` |
| 11 | 🔒 Hotspot | Task.java | Contraseña hardcodeada `"admin123"` |
| 12 | 🔒 Hotspot | TodoService.java | Credenciales BD hardcodeadas `"root1234"` |
| 13 | 🔒 Hotspot | TodoService.java | Credenciales impresas por consola |
| 14 | 📄 Duplicación | Task.java | `getInfo()` es duplicado exacto de `toString()` |
| 15 | 📄 Duplicación | TodoService.java | `findByTitle()` ≈ `findByDescription()` (copy-paste) |
| 16 | 📊 Cobertura | Tests | Métodos sin testear a propósito (cobertura < 100%) |

---

## 🏁 Resumen — Qué características de SonarQube hemos visto

| # | Característica | Dónde la vemos |
|---|---------------|----------------|
| 1 | Análisis estático de código | Paso 7 — el análisis completo |
| 2 | Detección de Bugs | Paso 9 — filtro por bugs |
| 3 | Detección de Code Smells | Paso 10 — filtro por smells |
| 4 | Security Hotspots | Paso 11 — credenciales hardcodeadas |
| 5 | Cobertura de tests con JaCoCo | Pasos 6, 12 y 13 |
| 6 | Detección de duplicaciones | Paso 14 — código copy-paste |
| 7 | Quality Gate (Passed/Failed) | Paso 8 — dashboard principal |
| 8 | Métricas y ratings (A-E) | Paso 12 — measures |
| 9 | Deuda técnica | Paso 12 — maintainability |
| 10 | Código fuente anotado | Paso 13 — vista de código |
| 11 | Corrección y re-análisis | Paso 15 — arreglar un bug y ver la mejora |
| 12 | Historial y evolución | Paso 16 — activity |
| 13 | Integración con Maven | `pom.xml` — plugins configurados |
| 14 | Integración con Docker | Paso 1 — arrancar SonarQube |
| 15 | API REST | Paso 18 — consultas por URL |
| 16 | Tests unitarios con JUnit 5 | Paso 5 — 32 tests |
