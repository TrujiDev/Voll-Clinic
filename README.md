# VollClinic - REST API for Clinic Management

VollClinic es una API RESTful diseñada para gestionar los datos de los médicos de una clínica. Permite registrar, actualizar, listar y eliminar médicos, además de gestionar sus especialidades y direcciones. Es un sistema escalable y fácil de usar que proporciona una solución completa para la gestión de información clínica.

## Características

- **Registro de médicos**: Permite agregar médicos con su nombre, email, teléfono, documento de identidad, especialidad y dirección.
- **Actualización de médicos**: Puedes actualizar la información de los médicos, como su nombre, especialidad o dirección.
- **Listar médicos**: Visualiza todos los médicos activos de la clínica con paginación.
- **Desactivación de médicos**: Los médicos pueden ser desactivados en lugar de ser eliminados permanentemente, lo que garantiza la integridad de los datos.

## Tecnologías

- **Java 21**
- **Spring Boot 3.3.5**
- **Spring Data JPA**
- **MySQL**
- **Flyway** para migraciones de base de datos
- **Hibernate**
- **Lombok** para simplificar el código
- **Spring Boot DevTools** para desarrollo en caliente

## Instalación

Sigue estos pasos para configurar y ejecutar el proyecto en tu máquina local:

### Requisitos previos

Asegúrate de tener instalados los siguientes programas:

- **Java 21**: Puedes descargarlo desde [aquí](https://www.oracle.com/java/technologies/javase-jdk21-downloads.html).
- **Maven**: Si no lo tienes, instala Maven siguiendo las instrucciones de su [sitio oficial](https://maven.apache.org/install.html).
- **MySQL**: Asegúrate de tener MySQL instalado y funcionando en tu máquina. Crea una base de datos llamada `voll_med`.

### Pasos para la instalación

1. Clona el repositorio:
    ```bash
    git clone https://github.com/TrujiDev/VollClinic.git
    ```

2. Navega al directorio del proyecto:
    ```bash
    cd VollClinic
    ```

3. Configura tu base de datos en el archivo `src/main/resources/application.yml`:

    ```yaml
    spring:
      datasource:
        driver-class-name: com.mysql.cj.jdbc.Driver
        url: jdbc:mysql://localhost:3306/voll_med
        username:
        password:

      jpa:
        show-sql: true
        properties:
          hibernate:
            format_sql: true
    ```

4. Ejecuta las migraciones de base de datos utilizando Flyway:
    ```bash
    ./mvnw clean install
    ```

5. Ejecuta el proyecto:
    ```bash
    ./mvnw spring-boot:run
    ```

El servicio estará disponible en `http://localhost:8080`.

## Uso de la API

Aquí te dejamos algunos ejemplos de cómo interactuar con la API:

### Registrar un nuevo médico

- **Endpoint**: `POST /doctors`
- **Body**:
    ```json
    {
      "name": "Dr. Juan Pérez",
      "email": "juan@vollclinic.com",
      "phone": "1234567890",
      "document": "123456789",
      "specialty": "CARDIOLOGY",
      "address": {
        "street": "Av. La 80",
        "district": "Centro",
        "city": "Armenia",
        "number": "80-12",
        "complement": "Oficina 203"
      }
    }
    ```

### Listar médicos activos

- **Endpoint**: `GET /doctors`
- **Parámetros**:
    - `page`: Número de página para la paginación (por defecto es 0).
    - `size`: Número de elementos por página (por defecto es 10).

**Respuesta de ejemplo**:
```json
[
  {
    "id": 1,
    "name": "Dr. Juan Pérez",
    "specialty": "Cardiology",
    "document": "123456789",
    "email": "juan@vollclinic.com"
  }
]
```

### Actualizar un médico

- **Endpoint**: `PUT /doctors`
- **Body**:
    ```json
    {
      "id": 1,
      "name": "Dr. Juan Pérez Actualizado",
      "document": "987654321"
    }
    ```

### Desactivar un médico

- **Endpoint**: `DELETE /doctors/{id}`
- Este endpoint desactivará un médico de manera lógica, marcándolo como inactivo en la base de datos.

## Contribuir

¡Contribuir es fácil! Si deseas colaborar en el proyecto, sigue estos pasos:

1. Haz un fork de este repositorio.
2. Crea una rama para tu funcionalidad o corrección de bug.
3. Realiza tus cambios y asegúrate de que el código esté bien probado.
4. Abre un pull request con una descripción detallada de tus cambios.

## Agradecimientos

Este proyecto no habría sido posible sin el apoyo y los recursos proporcionados por:

- Gracias a **Spring Boot** por proporcionar un marco robusto y fácil de usar.
- Gracias a **Flyway** por hacer que la gestión de migraciones de bases de datos sea sencilla y eficiente.
- Gracias a los cursos que tomé en la plataforma **Alura**, pude adquirir los conocimientos necesarios para desarrollar este proyecto de manera estructurada y eficiente.
- Gracias al programa ONE, tuve la oportunidad de acceder a valiosos recursos y conocimientos sobre desarrollo de software.
- Agradezco profundamente a **Diego Rojas** por su excelente instrucción durante el curso que me permitió llevar este proyecto a cabo. Su enfoque claro y práctico fue crucial para mi aprendizaje.
- A todos los contribuidores de código abierto que hacen que proyectos como este sean posibles.
