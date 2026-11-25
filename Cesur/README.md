# Cesur: Multiplatform Application Development (DAM)

Projects from the two-year *Técnico Superior en Desarrollo de Aplicaciones Multiplataforma* programme at Cesur (2024–2026).

## Subjects

| Folder name | Subject (original name) |
|---|---|
| `Programming` | Programming, 1st year (*Programación*). Also contains Development Environments and Digitalisation exercises. |
| `DataAccess` | Data Access (*Acceso a Datos*) |
| `UserInterfaces` | User Interface Development (*Desarrollo de Interfaces*) |
| `ServicesAndProcesses` | Services and Processes Programming (*Programación de Servicios y Procesos*): concurrency, threads, networking |
| `MultimediaAndMobileProgramming` | Multimedia and Mobile Device Programming (*Programación Multimedia y Dispositivos Móviles*) |
| `DataAccess_UserInterfaces_Challenges` | Joint projects ("retos") assessed in both Data Access and User Interface Development |
| `SecurityAndCodeQuality` | Secure coding and code quality: hashing and static analysis with SonarQube (Cybersecurity module) |
| `Misc` | Initial assessment and scratch projects |

✱ = keeps the full commit history of its original repository.

## Java

### Core: plain Java SE

| Project | Subject | Description |
|---|---|---|
| [EjerciciosJava2024](Java/Core/Programming/EjerciciosJava2024) ✱ | Programming | All first-year exercises, organised by unit, plus exams. Folders named `SolucionesProfesor` contain instructor reference solutions. |
| [EjercicioPracticoEvaluable_T1](Java/Core/DataAccess/EjercicioPracticoEvaluable_T1) ✱ | Data Access | Loading and filtering a film catalogue from CSV files |
| [RETO_TVDB](Java/Core/DataAccess/RETO_TVDB) ✱ | Data Access | REST client for TheTVDB API, using Gson |
| [DIteoria](Java/Core/UserInterfaces/DIteoria) | User Interfaces | Theory examples |
| [PSPdam2](Java/Core/ServicesAndProcesses/PSPdam2), [PSPdam2_29_9](Java/Core/ServicesAndProcesses/PSPdam2_29_9), [PSPdam2ej3](Java/Core/ServicesAndProcesses/PSPdam2ej3), [PSPdam2UD2CP5](Java/Core/ServicesAndProcesses/PSPdam2UD2CP5), [PAMdam2UD2CP4](Java/Core/ServicesAndProcesses/PAMdam2UD2CP4), [PSPUDP2CP08](Java/Core/ServicesAndProcesses/PSPUDP2CP08) | Services and Processes | Threads and synchronisation exercises (unit 2) |
| [Tema4Ejercicio1](Java/Core/ServicesAndProcesses/Tema4Ejercicio1), [Tema4Ejercicio2](Java/Core/ServicesAndProcesses/Tema4Ejercicio2) | Services and Processes | Unit 4 exercises |
| [PSPUD05CP01](Java/Core/ServicesAndProcesses/PSPUD05CP01), [PSPUD05CP03](Java/Core/ServicesAndProcesses/PSPUD05CP03) | Services and Processes | Unit 5 case studies |
| [PSPejemploExamen](Java/Core/ServicesAndProcesses/PSPejemploExamen), [PSP_Examen_28_11_2025](Java/Core/ServicesAndProcesses/PSP_Examen_28_11_2025) | Services and Processes | Mock exam (networking) and exam |
| [PSPHashPractica1](Java/Core/SecurityAndCodeQuality/PSPHashPractica1) | Security and Code Quality | Hashing with `java.security` |
| [PracticaSonarCube](Java/Core/SecurityAndCodeQuality/PracticaSonarCube) | Security and Code Quality | Console to-do app with deliberate defects (bugs, code smells, security hotspots), used to demonstrate SonarQube analysis |
| [EjEvIni2DAM](Java/Core/Misc/EjEvIni2DAM) ✱ | Misc | Second-year initial assessment: a console contact book with file persistence |
| [Test](Java/Core/Misc/Test), [t](Java/Core/Misc/t) | Misc | Scratch projects |

### Swing

| Project | Subject | Description |
|---|---|---|
| [EjercicioEvaluableDI](Java/Swing/UserInterfaces/EjercicioEvaluableDI) ✱ | User Interfaces | Contact manager |
| [IntroSwing](Java/Swing/UserInterfaces/IntroSwing) | User Interfaces | Introduction to Swing |
| [Juegoteca](Java/Swing/UserInterfaces/Juegoteca) | User Interfaces | Video game library backed by CSV files |
| [Reto1_DI_DA](Java/Swing/DataAccess_UserInterfaces_Challenges/Reto1_DI_DA) ✱ | Joint challenge 1 | Film catalogue with login, list, detail and create views, using CSV storage |

### JDBC

| Project | Subject | Description |
|---|---|---|
| [HolaMundoJdbc](Java/JDBC/DataAccess/HolaMundoJdbc), [EjemplosJdbc](Java/JDBC/DataAccess/EjemplosJdbc) | Data Access | First steps with JDBC and MySQL |
| [AD_EjercicioEV_T2](Java/JDBC/DataAccess/AD_EjercicioEV_T2) ✱ | Data Access | DAO pattern with MySQL (assessed exercise, unit 2) |

### Hibernate / JPA

| Project | Subject | Description |
|---|---|---|
| [HelloHibernate](Java/Hibernate_JPA/DataAccess/HelloHibernate), [HibernateFX](Java/Hibernate_JPA/DataAccess/HibernateFX) | Data Access | Hibernate basics |
| [HelloJPA](Java/Hibernate_JPA/DataAccess/HelloJPA), [HelloObjectDB](Java/Hibernate_JPA/DataAccess/HelloObjectDB) | Data Access | Jakarta Persistence with Hibernate and with ObjectDB |
| [AD_EjercicioEV_T3](Java/Hibernate_JPA/DataAccess/AD_EjercicioEV_T3) ✱ | Data Access | Repositories with Hibernate and MySQL (assessed exercise, unit 3) |

### JavaFX

| Project | Subject | Description |
|---|---|---|
| [HelloJavafxCode](Java/JavaFX/UserInterfaces/HelloJavafxCode) | User Interfaces | JavaFX built entirely in code |
| [DI_EjercicioEV_T3](Java/JavaFX/UserInterfaces/DI_EjercicioEV_T3) ✱ | User Interfaces | FXML-based application (assessed exercise, unit 3) |
| [DI_EjercicioRepaso_T3](Java/JavaFX/UserInterfaces/DI_EjercicioRepaso_T3) | User Interfaces | Unit 3 revision exercise |
| [HelloHibernateJavaFX](Java/JavaFX/DataAccess_UserInterfaces_Challenges/HelloHibernateJavaFX) | Joint | JavaFX on top of Hibernate |
| [GestorJuegosFX](Java/JavaFX/DataAccess_UserInterfaces_Challenges/GestorJuegosFX) | Joint | Game manager with login, DAO layer and cover images (covers not included, see its `covers/README.md`) |
| [Reto2_DI-AD_Hibernate](Java/JavaFX/DataAccess_UserInterfaces_Challenges/Reto2_DI-AD_Hibernate) ✱ | Joint challenge 2 | Desktop app to manage users, films and physical copies, built with JavaFX, Hibernate and MySQL (v1.0 to v1.2.7, after a usability heuristics review) |
| [Reto4_DI-AD_ObjectDB](Java/JavaFX/DataAccess_UserInterfaces_Challenges/Reto4_DI-AD_ObjectDB) ✱ | Joint challenge 4 | The same application migrated to ObjectDB/JPA (v2.0) |

### JasperReports

| Project | Subject | Description |
|---|---|---|
| [HelloJasper2026](Java/JasperReports/UserInterfaces/HelloJasper2026) | User Interfaces | Reports generated with JasperReports from a JavaFX application |

### Spring Boot

| Project | Subject | Description |
|---|---|---|
| [HelloWorldSpringBoot2026](Java/SpringBoot/DataAccess/HelloWorldSpringBoot2026) | Data Access | Spring Data JPA with MySQL |
| [HelloMongoDBSpringBoot2026](Java/SpringBoot/DataAccess/HelloMongoDBSpringBoot2026) | Data Access | Spring Data MongoDB |
| [BibliotecaDeJuegosSpringBoot](Java/SpringBoot/DataAccess/BibliotecaDeJuegosSpringBoot) ✱ | Data Access | Game library: JPA, Spring Data REST and Thymeleaf |
| [AD_Reto4_Turismo](Java/SpringBoot/DataAccess/AD_Reto4_Turismo) ✱ | Data Access | Tourism REST API on MongoDB Atlas, with a Dockerfile |
| [AD_Examen_18_03_26](Java/SpringBoot/DataAccess/AD_Examen_18_03_26) ✱ | Data Access | Exam: store REST API with MongoDB, DTOs, global exception handling and security |
| [DI_Reto6_Turismo](Java/SpringBoot/UserInterfaces/DI_Reto6_Turismo) ✱ | User Interfaces | Hotel management web app: Thymeleaf, Spring Security and MongoDB |
| [DI_Examen_18_03_26](Java/SpringBoot/UserInterfaces/DI_Examen_18_03_26) ✱ | User Interfaces | Exam: Thymeleaf front end for the store, with Spring Security |

Projects that use MongoDB Atlas read the connection string from the `MONGODB_URI` environment variable.

## Kotlin: Android, in `Kotlin/Android/MultimediaAndMobileProgramming`

| Project | Description |
|---|---|
| PrimerProyecto, Proyecto26_09_25, Pr29_9, Pr29_9_25, PR03_10 | First Android projects (September and October 2025) |
| PMDMdam2u6cp0, PMDMdam2u6cp1, PMDMdam2u6cp2 | Unit 6 case studies, three iterations |
| Examen21_11_2025 | Exam: hardware store catalogue (SQLite, DAO, editor screen) |
| EjemploRV25v5, EjemploRV25v5_1, EjemploDataStore | Instructor examples (package `com.jlara`): RecyclerView and DataStore |
| PMDMUD09CP01, PMDMUD09CP02, PMDMUD09CP03 | Unit 9: film catalogue with login (iterations) |
| PMDMUD12CP01 | Unit 12: Room database, user registration and Google Maps (API key not included) |
| PMDMUD13CP01 | Unit 13: TMDB client with Retrofit (API key not included) |
| MisTareasPro | Task manager with login, SQLite and DataStore preferences |
| AppProyectPM | RecyclerView lists (fruits and films) with detail screens |
| PhoneLauncher, Emu | Launcher and emulator experiments |

Film posters in these apps were replaced by a neutral placeholder with the same file name, so the apps still compile.

## C#: [CSharp_Pizzard](CSharp_Pizzard)

The only C# project: **Pizzard**, a game made with Unity 2022.3 for the cross-module project (*Proyecto Intermodular*), under the MIT licence.
It has its own README and planning documents. ✱

## Docker

| Project | Description |
|---|---|
| [Ollama-OpenWebUI](Docker/Compose/Utilities/Ollama-OpenWebUI) | `docker-compose.yml` that runs a local LLM (Ollama) with Open WebUI |
