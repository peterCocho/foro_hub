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
+---------+      +-----------+      +----------+
|  users  |<-----|  topics   |<-----| responses|
+---------+      +-----------+      +----------+
| id      |      | id        |      | id       |
| email   |      | title     |      | message  |
| name    |      | message   |      | topic_id |
| password|      | status    |      | user_id  |
+---------+      | user_id   |      +----------+
                 | course_id |
                 +-----------+
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
| DELETE | `/topics/{id}`          | Eliminar un tópico                          | ✅            |
| PUT    | `/topics/{id}/spam`     | Marcar tópico como SPAM (solo ADMIN)        | ✅ (ADMIN)     |
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

> _Espacio reservado para imágenes de requests y respuestas en Insomnia/Postman_

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

