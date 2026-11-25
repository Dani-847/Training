# Training

A single, organised monorepo with the coursework and practice projects from my software development training.
It replaces a set of separate repositories and keeps their commit history.

| Institution | Programme | Main technologies | Index |
|---|---|---|---|
| **Cesur** | Higher Technician in Multiplatform Application Development (DAM), 2024–2026 | Java (Spring Boot, JavaFX, Swing, JDBC, Hibernate/JPA), Kotlin (Android), C# (Unity), Docker | [Cesur/README.md](Cesur/README.md) |
| **UEA** | Fundamentals of Programming | Modern C++ (C++23: ranges, views, `std::print`) | [UEA/README.md](UEA/README.md) |

## Repository layout

Every project lives at a predictable path:

```
<Institution>/<Language>/<Platform or framework>/<Subject>/<Project>
```

```
Training/
├── Cesur/
│   ├── Java/
│   │   ├── Core/            plain Java SE (no frameworks)
│   │   ├── Swing/
│   │   ├── JavaFX/
│   │   ├── JDBC/
│   │   ├── Hibernate_JPA/
│   │   ├── JasperReports/
│   │   └── SpringBoot/
│   ├── Kotlin/Android/
│   ├── CSharp_Pizzard/      single C# project: Pizzard, a Unity game
│   └── Docker/Compose/
└── UEA/
    └── Cpp/Core/FundamentalsOfProgramming/
```

- **Institution:** where the work was done. New institutions go at the top level.
- **Language** and **Platform or framework:** the main technology stack, so related projects sit together.
- **Subject:** the course module the project belongs to (for example `DataAccess`, `UserInterfaces`, `ServicesAndProcesses`).
- **Project:** the original project name, kept unchanged so it can be traced back.

## Conventions

- **Source first.** The repository contains source code, resources and project-specific configuration
  (`pom.xml`, Gradle build files, `application.properties`, `persistence.xml`, Unity `ProjectSettings/`, database connection
  definitions, and so on). Generic, regenerable files (IDE workspace files, Maven/Gradle wrappers, build output) are excluded.
  Run `mvn wrapper:wrapper` to regenerate the Maven wrapper. Android Studio and IntelliJ regenerate the rest when the project is opened.
- **No secrets.** Credentials found during the migration were removed from the files and from the git history.
  Each one is replaced by a `[CREDENTIAL REMOVED FOR SECURITY]` note that explains how the owner can provide it again,
  normally through an environment variable such as `MONGODB_URI`.
- **No third-party artwork or course material.** Copyrighted images (game covers, film posters) and the instructors' slide decks are not included.
  Code examples written by instructors are marked as such in the indexes.
- **History.** Projects that had their own repository keep their full history, already placed in their final folder.
  `git log -- <folder>` and `git blame` work as usual.

## Author

Dani-847: [github.com/Dani-847](https://github.com/Dani-847)
