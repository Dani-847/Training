# ============================================================
#  SHOWCASE SONARQUBE - GUÍA PASO A PASO
#  Proyecto: TodoList (PracticaSonarCube)
# ============================================================
#
# Este script documenta todos los pasos para hacer una
# demostración completa de SonarQube y sus características.
#
# REQUISITOS PREVIOS:
#   - Docker corriendo con SonarQube: docker run -d --name sonarqube -p 9000:9000 sonarqube:lts-community
#   - Java 17+ instalado
#   - Maven instalado
#   - Proyecto ya configurado en SonarQube (http://localhost:9000)
#
# ============================================================

# ============================================================
# PARTE 1: COMPILACIÓN Y TESTS
# ============================================================

# 1.1 - Compilar el proyecto
mvn clean compile

# 1.2 - Ejecutar los tests unitarios (32 tests)
mvn clean test

# 1.3 - Ejecutar tests + generar reporte de cobertura JaCoCo
mvn clean verify

# 1.4 - Ver reporte de cobertura local (abrir en navegador)
# Archivo: target/site/jacoco/index.html
start target\site\jacoco\index.html

# ============================================================
# PARTE 2: ANÁLISIS SONARQUBE
# ============================================================

# 2.1 - Ejecutar análisis completo (compile + test + coverage + sonar)
mvn clean verify sonar:sonar

# 2.2 - Ver resultados en el navegador
start http://localhost:9000/dashboard?id=PracticaSonarCube

# ============================================================
# PARTE 3: CONSULTAR RESULTADOS POR API (TERMINAL)
# ============================================================

# 3.1 - Resumen general del proyecto (métricas principales)
curl -u squ_2f17197291a3f31f4c2f4ae6252ae94374380deb: "http://localhost:9000/api/measures/component?component=PracticaSonarCube&metricKeys=bugs,vulnerabilities,code_smells,coverage,duplicated_lines_density,ncloc,security_hotspots,sqale_index"

# 3.2 - Listar todos los BUGS encontrados
curl -u squ_2f17197291a3f31f4c2f4ae6252ae94374380deb: "http://localhost:9000/api/issues/search?componentKeys=PracticaSonarCube&types=BUG"

# 3.3 - Listar todos los CODE SMELLS
curl -u squ_2f17197291a3f31f4c2f4ae6252ae94374380deb: "http://localhost:9000/api/issues/search?componentKeys=PracticaSonarCube&types=CODE_SMELL"

# 3.4 - Listar todas las VULNERABILITIES
curl -u squ_2f17197291a3f31f4c2f4ae6252ae94374380deb: "http://localhost:9000/api/issues/search?componentKeys=PracticaSonarCube&types=VULNERABILITY"

# 3.5 - Listar SECURITY HOTSPOTS
curl -u squ_2f17197291a3f31f4c2f4ae6252ae94374380deb: "http://localhost:9000/api/hotspots/search?projectKey=PracticaSonarCube"

# 3.6 - Ver estado del Quality Gate
curl -u squ_2f17197291a3f31f4c2f4ae6252ae94374380deb: "http://localhost:9000/api/qualitygates/project_status?projectKey=PracticaSonarCube"

# 3.7 - Ver duplicaciones de código
curl -u squ_2f17197291a3f31f4c2f4ae6252ae94374380deb: "http://localhost:9000/api/measures/component?component=PracticaSonarCube&metricKeys=duplicated_lines,duplicated_blocks,duplicated_files,duplicated_lines_density"

# 3.8 - Ver cobertura de tests por archivo
curl -u squ_2f17197291a3f31f4c2f4ae6252ae94374380deb: "http://localhost:9000/api/measures/component_tree?component=PracticaSonarCube&metricKeys=coverage,line_coverage,branch_coverage&qualifiers=FIL"

# 3.9 - Ver historial de análisis
curl -u squ_2f17197291a3f31f4c2f4ae6252ae94374380deb: "http://localhost:9000/api/project_analyses/search?project=PracticaSonarCube"

# 3.10 - Ver reglas activas del Quality Profile
curl -u squ_2f17197291a3f31f4c2f4ae6252ae94374380deb: "http://localhost:9000/api/rules/search?qprofile=java-sonar-way&languages=java&ps=10"

# ============================================================
# PARTE 4: NAVEGACIÓN WEB (QUÉ MOSTRAR EN http://localhost:9000)
# ============================================================
#
# 4.1 - DASHBOARD PRINCIPAL
#     → http://localhost:9000/dashboard?id=PracticaSonarCube
#     Mostrar: Ratings (A-E), métricas de bugs/smells/vulnerabilities,
#              cobertura, duplicaciones, Quality Gate (Passed/Failed)
#
# 4.2 - PESTAÑA "Issues"
#     → http://localhost:9000/project/issues?id=PracticaSonarCube
#     Mostrar: Lista de problemas, filtrar por tipo (Bug/Smell/Vulnerability),
#              por severidad (Blocker/Critical/Major/Minor/Info),
#              clicar en un issue para ver el código afectado
#
# 4.3 - PESTAÑA "Security Hotspots"
#     → http://localhost:9000/security_hotspots?id=PracticaSonarCube
#     Mostrar: Credenciales hardcodeadas detectadas (password, DB_PASSWORD),
#              opciones de revisión (Safe/Fixed/To Review)
#
# 4.4 - PESTAÑA "Measures"
#     → http://localhost:9000/component_measures?id=PracticaSonarCube
#     Mostrar: Reliability, Security, Maintainability, Coverage,
#              Duplications, Size, Complexity
#
# 4.5 - PESTAÑA "Code"
#     → http://localhost:9000/code?id=PracticaSonarCube
#     Mostrar: Navegar por archivos, ver anotaciones de SonarQube
#              directamente en el código fuente
#
# 4.6 - PESTAÑA "Activity"
#     → http://localhost:9000/project/activity?id=PracticaSonarCube
#     Mostrar: Historial de análisis, gráficas de evolución
#
# ============================================================
# PARTE 5: DEMOSTRAR CORRECCIÓN DE DEFECTOS
# ============================================================
#
# 5.1 - Mostrar el código con defectos en el IDE
# 5.2 - Corregir un defecto (ej: añadir hashCode() en Task.java)
# 5.3 - Re-ejecutar análisis:
#        mvn clean verify sonar:sonar
# 5.4 - Mostrar en SonarQube cómo el issue desaparece
# 5.5 - Mostrar en Activity cómo mejora la calidad con el tiempo
#
# ============================================================
# PARTE 6: EJECUTAR LA APLICACIÓN (DEMO FUNCIONAL)
# ============================================================

# 6.1 - Ejecutar la TodoList por terminal
mvn exec:java -Dexec.mainClass="org.drk.Main"

# ============================================================
# PARTE 7: DOCKER (GESTIÓN DE SONARQUBE)
# ============================================================

# 7.1 - Ver si SonarQube está corriendo
docker ps | findstr sonarqube

# 7.2 - Ver logs de SonarQube
docker logs sonarqube --tail 20

# 7.3 - Parar SonarQube
docker stop sonarqube

# 7.4 - Reiniciar SonarQube
docker start sonarqube

# 7.5 - Eliminar contenedor (reset completo)
docker rm -f sonarqube

