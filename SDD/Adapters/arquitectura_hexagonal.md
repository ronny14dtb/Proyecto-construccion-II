# Guía de Arquitectura Hexagonal y Estructura de Paquetes

Este documento explica de manera detallada cómo está estructurado el proyecto bajo los principios de **Arquitectura Hexagonal (Ports & Adapters)**. Aquí se detalla la responsabilidad de cada capa, qué clases contienen y cómo interactúan entre sí.

---

## 🏛️ Visión General de la Arquitectura Hexagonal

La arquitectura hexagonal aísla el núcleo de la aplicación (el **Domain**) del mundo exterior (bases de datos, frameworks web, seguridad, etc.). Esto permite que la lógica de negocio sea completamente independiente de la tecnología.

El proyecto se divide en tres grandes mundos:
1. **Inbound / Driving (Entrada):** Controladores REST (`adapters/in/web`) que reciben las peticiones HTTP del usuario o cliente.
2. **Domain (Núcleo):** Modelos de negocio puros, puertos (interfaces) y servicios con las reglas del negocio (`domain`).
3. **Outbound / Driven (Salida):** Adaptadores que conectan la aplicación con bases de datos relacionales (MySQL/JPA) y NoSQL (MongoDB) (`adapters/out/persistence`).

---

## 📂 Descripción Detallada por Paquete y Carpeta

### 1. `application.adapters.in.web` (Adaptadores de Entrada / Controladores)
* **¿Qué es y a dónde va?** Es la capa que entra en contacto directo con las peticiones HTTP (Thunder Client, Postman, Frontend).
* **¿Qué lleva?** Controladores REST (`@RestController`), DTOs (Data Transfer Objects) para recibir y enviar JSONs limpios.
* **¿De qué se encargan?** 
  * Recibir las peticiones HTTP (GET, POST, etc.).
  * Validar o mapear los DTOs de entrada.
  * Llamar al servicio de dominio correspondiente.
  * Retornar la respuesta HTTP adecuada (`ResponseEntity`).

---

### 2. `application.adapters.out.persistence` (Adaptadores de Salida / Persistencia)
* **¿Qué es y a dónde va?** Es la capa que maneja la comunicación con los sistemas externos de almacenamiento (MySQL y MongoDB).
* **¿Qué subcarpetas lleva y de qué se encargan?**
  * **`entities`**: Contienen las clases anotadas con `@Entity` (JPA para MySQL) y `@Document` (para MongoDB). Representan la estructura física en la base de datos.
  * **`mappers`**: Clases encargadas de traducir / transformar los objetos de la base de datos (Entidades) a objetos puros del negocio (Modelos de Dominio) y viceversa.
  * **`repositories`**: Las interfaces de Spring Data JPA y MongoRepository que ejecutan las consultas directas contra las bases de datos.
* **Adaptadores Principales (`MySQL...Adapter`, `MongoDB...Adapter`):** Implementan los puertos de salida (`ports/out`) utilizando los repositorios anteriores, aislando a Hibernate o Mongo de la lógica pura.

---

### 3. `application.domain` (El Núcleo de Negocio)
* **¿Qué es y a dónde va?** Es el corazón de la aplicación. **No conoce** a Spring Data, ni a Hibernate, ni a las peticiones HTTP. Contiene las reglas puras del negocio.
* **¿Qué subcarpetas lleva y de qué se encargan?**
  * **`models`**: Clases de dominio puras (ej. `Product`, `Seller`, `Order`, `Inventory`). Contienen los atributos y comportamientos lógicos del negocio sin anotaciones de bases de datos.
  * **`ports/out`**: Interfaces (contratos) que definen qué operaciones de guardado o consulta necesita el dominio (ej. `ProductRepositoryPort`). El dominio le dice *"qué"* necesita, y los adaptadores de persistencia resuelven *"cómo"* hacerlo.
  * **`services`**: Los Casos de Uso (`Use Cases`). Contienen la lógica secuencial y las reglas de negocio (ej. cómo descontar inventario, cómo asociar un producto a un vendedor, cómo consolidar un reporte).

---

### 4. `application.Infrastructure` (Infraestructura y Configuración Transversal)
* **¿Qué es y a dónde va?** Aloja los componentes técnicos transversales que apoyan a toda la aplicación pero que no forman parte de la regla de negocio pura ni de los controladores.
* **¿Qué subcarpetas lleva y de qué se encargan?**
  * **`security`**: Contiene la configuración de Spring Security (`SecurityConfig`), los filtros de autenticación JWT (`JwtAuthenticationFilter`) y la gestión de tokens. Permite proteger las rutas o dejarlas públicas (`permitAll()`).
  * **`persistence`**: Configuraciones avanzadas de beans o adaptadores híbridos si se requieren conectar múltiples fuentes de datos.

---

## 🔄 ¿Cómo fluye una petición en este sistema? (Ejemplo: Crear un Producto)

1. **Entrada:** El cliente hace un `POST /api/products` con un JSON.
2. **Adaptador In (Web):** El `ProductController` recibe el JSON mapeado en un `ProductRequestDto`.
3. **Servicio de Dominio:** El controlador llama a `CreateProductService` pasándole los datos limpios.
4. **Dominio:** El servicio instancia un objeto puro de tipo `Product` y aplica la lógica de negocio.
5. **Puerto de Salida:** El servicio invoca a la interfaz `ProductRepositoryPort.save(product)`.
6. **Adaptador Out (Persistencia):** `MySQLProductRepositoryAdapter` recibe el modelo, el `ProductPersistenceMapper` lo convierte en un `ProductEntity`, y el `ProductJpaRepository` lo guarda en MySQL.
7. **Retorno:** La respuesta sube de regreso transformada y llega al cliente como un JSON `200 OK`.