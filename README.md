# 🏆 Foro Hub

¡Bienvenido a **Foro Hub**!  
Un sistema de foros moderno y seguro desarrollado con **Spring Boot** que permite a los usuarios crear, consultar, actualizar y gestionar tópicos, respuestas y perfiles de usuario, todo protegido mediante autenticación JWT.

---

## 🧾 Descripción

**Foro Hub** es una API RESTful para la gestión de foros académicos o profesionales. Permite a los usuarios autenticados interactuar con tópicos y respuestas, mientras que los administradores pueden moderar el contenido y gestionar el estado de los tópicos (por ejemplo, marcarlos como SPAM).

---

## 🚀 Tecnologías Utilizadas

- Spring Boot 3
- JWT (JSON Web Token)
- JPA / Hibernate
- MySQL
- Maven
- Lombok
- JUnit 5
- MockMvc
- Insomnia

---

## 🛠️ Instrucciones para Ejecutar Localmente

1. **Clona el repositorio**
   ```bash
   git clone https://github.com/tuusuario/foro_hub.git
   cd foro_hub/backend
   ```

2. **Configura la base de datos**
   - Asegúrate de tener MySQL corriendo.
   - Crea la base de datos `foro_hub` o deja que Spring Boot la cree automáticamente.

3. **Configura las variables de entorno**
   - Edita `src/main/resources/application.properties` según tus credenciales de MySQL.
   - La clave JWT está definida como:
     ```
     api.security.token=claveSuperSegura1234567890ABCDEF123456
     ```

4. **Compila y ejecuta el proyecto**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

5. **Accede a la API**
   - Por defecto en: `http://localhost:8080`

---

## 🔐 Sistema de Autenticación JWT

- El login se realiza vía `POST /auth/login` enviando email y contraseña.
- Si las credenciales son correctas, se retorna un JWT.
- El JWT debe enviarse en el header `Authorization: Bearer <token>` para acceder a los endpoints protegidos.
- Los roles (`ROLE_USER`, `ROLE_ADMIN`) determinan los permisos sobre los recursos.

---

## 🗃️ Diagrama de la Base de Datos

```
            +--------------------+
            |  user_profiles     |
            +--------------------+
            | user_id (PK, FK)   |
            | profile            |
            +--------------------+
                    ^
                    |
+---------+         |
|  users  |         |
+---------+         |
| id      |---------|
| name    |         |
| email   |         |   
| password|         |
+---------+         |-----------------------------------------
     |                              |                         |
+----------------+      +----------------------+      +----------------+
|   courses      |<-----|  topics              |<-----|  responses     |
+----------------+      +----------------------+      +----------------+
| id             |      | id                   |      | id             |
| name           |      | title                |      | message        |
| category       |      | message              |      | topic_id       |
+----------------+      | creation_date        |      | creation_date  |
                        | status               |      | author_id      |
                        | author_id (FK)       |      | is_solution    |
                        | course_id (FK)       |      +----------------+
                        | solution_response_id |
                        +----------------------+

```

---

## 📡 Documentación de Endpoints Principales

| Método | Endpoint                | Descripción                                 | Autenticación |
|--------|-------------------------|---------------------------------------------|---------------|
| POST   | `/auth/login`           | Login y obtención de JWT                    | ❌            |
| POST   | `/users/register`       | Registro de usuario                         | ❌            |
| GET    | `/topics`               | Listar tópicos                              | ✅            |
| GET    | `/topics/{id}`          | Detalle de un tópico                        | ✅            |
| POST   | `/topics`               | Crear un nuevo tópico                       | ✅            |
| PUT    | `/topics/{id}`          | Actualizar un tópico                        | ✅            |
| DELETE | `/topics/{id}`          | Eliminar un tópico                          | ✅ (ADMIN)    |
| PUT    | `/topics/{id}/spam`     | Marcar tópico como SPAM (solo ADMIN)        | ✅ (ADMIN)    |
| POST   | `/responses`            | Crear respuesta a un tópico                 | ✅            |
| PUT    | `/responses/{id}`       | Editar respuesta                            | ✅            |

> **Nota:** Todos los endpoints protegidos requieren el header `Authorization: Bearer <token>`

---

## 🧪 Pruebas

### Pruebas Unitarias y de Integración

- Se utilizan **JUnit 5** y **MockMvc** para pruebas automáticas de controladores y servicios.
- Ejemplo de prueba con MockMvc:
  ```java
  mockMvc.perform(post("/auth/login")
      .contentType(MediaType.APPLICATION_JSON)
      .content("{\"email\":\"admin@foro.com\",\"password\":\"admin123\"}"))
      .andExpect(status().isOk());
  ```

### Pruebas Manuales con Postman/Insomnia

- Importa la colección de endpoints.
- Realiza login y usa el token JWT en las siguientes peticiones.
- Verifica respuestas 401/403 para endpoints protegidos.

---

## 📸 Capturas de Pantalla

<img width="781" height="297" alt="registrar_usuarios" src="https://github.com/user-attachments/assets/7b53edce-b1dd-440a-9d02-3ea7ffb77836" />

<img width="884" height="322" alt="iniciar_sesion" src="https://github.com/user-attachments/assets/ba12aa31-57bb-4a8b-8d1b-294634b5b6e1" />

<img width="866" height="360" alt="2025-08-10_17h10_31" src="https://github.com/user-attachments/assets/45164ebd-b530-46db-991a-4e3e5611d36b" />

<img width="896" height="336" alt="listar_topicos" src="https://github.com/user-attachments/assets/1f913a46-6e4e-4cbe-9324-8e1a72caf795" />

<img width="862" height="381" alt="topicos_paginacion" src="https://github.com/user-attachments/assets/d21e9ca7-273e-4839-ad7a-549555b8dde9" />

<img width="755" height="357" alt="solicitar_1_topico" src="https://github.com/user-attachments/assets/9f2eefd3-ffab-459f-8919-7ff68eb683f7" />

<img width="759" height="366" alt="actualizar_topico" src="https://github.com/user-attachments/assets/398440cd-0f81-4aab-b4f5-aba430a46375" />

<img width="760" height="312" alt="eliminar_topico" src="https://github.com/user-attachments/assets/0576a9e1-00c1-4f46-ad98-9ac4f9b05fa2" />

<img width="757" height="426" alt="solucionar_topico" src="https://github.com/user-attachments/assets/fc2e8b09-4cfe-4bef-9451-669457078bb1" />

<img width="750" height="290" alt="marcar_spam" src="https://github.com/user-attachments/assets/f5f99874-dab1-470a-a3d8-814d2978bfc6" />

<img width="779" height="425" alt="registrar_respuestas" src="https://github.com/user-attachments/assets/0f2d3f7f-ab74-4e0e-9eb5-0d9a542351ba" />








---

## 📂 Estructura del Proyecto

```
backend/
├── src/
│   ├── main/
│   │   ├── java/com/aluracursos/backend/
│   │   │   ├── controller/        # Controladores REST
│   │   │   ├── domain/            # Entidades JPA y enums
│   │   │   ├── infra/             # Configuración, seguridad, utilidades
│   │   │   ├── service/           # Lógica de negocio
│   │   └── resources/
│   │       └── application.properties
│   └── test/                      # Pruebas unitarias e integración
```

---

## 🧠 Buenas Prácticas Aplicadas

- **Separación de capas:** Controladores, servicios y repositorios bien definidos.
- **Validación:** Uso de `@Valid` y DTOs para entrada de datos.
- **Seguridad:** JWT, roles y permisos bien gestionados.
- **Manejo de errores:** Excepciones personalizadas y respuestas claras.
- **Documentación:** Código comentado y endpoints documentados.

---

## 📌 Notas y Mejoras Futuras

- Implementar paginación y filtros avanzados en la consulta de tópicos.
- Añadir Swagger/OpenAPI para documentación interactiva.
- Mejorar la gestión de perfiles y permisos.
- Soporte para notificaciones y suscripciones a tópicos.
- Internacionalización y soporte multi-idioma.

---

## 🎯 ¡Gracias por visitar Foro Hub!

¿Tienes sugerencias o quieres contribuir?  
¡Abre un issue o un pull request!  
⭐️ _¡No olvides darle estrella al repositorio si te resultó útil!_

---


