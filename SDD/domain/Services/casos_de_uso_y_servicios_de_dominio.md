# Guía de Casos de Uso, Servicios de Dominio y Enums

Este documento complementa la arquitectura del sistema y se enfoca en el **Núcleo de Dominio (`domain`)**, detallando cómo operan los Casos de Uso (`services`), las reglas de negocio, los estados (`enums`) y las excepciones que blindan el funcionamiento del marketplace.

## ⚙️ 1. Servicios de Dominio (Casos de Uso / Use Cases)

En la Arquitectura Hexagonal estricta que implementamos, los **Servicios** (ubicados en `application.domain.services`) representan los **Casos de Uso**. 

* **¿Cómo funcionan?** Cada servicio encapsula **una única acción de negocio** (Principio de Responsabilidad Única). Por ejemplo, un servicio para registrar vendedores, otro para actualizar inventario, otro para agregar al carrito, etc.
* **¿A dónde van?** Se sitúan dentro de la capa de dominio. Reciben modelos puros, aplican las validaciones o transformaciones necesarias y se comunican con los puertos de salida (`ports/out`) para persistir o consultar datos, **sin enterarse jamás de que existe Spring Boot, HTTP o bases de datos relacionales**.

### Servicios Clave en tu Proyecto:
1. **`RegisterSellerService` / `CreateProductService`**: Gestionan el nacimiento de entidades de negocio, asignándoles IDs únicos y asegurando que nazcan con relaciones correctas (`sellerId`, stock inicial).
2. **`ManageInventoryService`**: Controla la disponibilidad en bodega. Evalúa si un producto ya tiene inventario para actualizarlo o si debe crearlo desde cero, evitando duplicidades.
3. **`GenerateBuyerReportService` / `GenerateSellerReportService`**: Ejecutan la lógica de agregación y consolidación de datos cruzando MySQL (transaccional) y MongoDB (documentos de auditoría y reportes).

---

## 📋 2. Enums (Estados y Tipificaciones de Negocio)

Los **Enums** (`application.domain.enums`) definen el vocabulario controlado y los estados posibles por los que atraviesan las entidades del marketplace a lo largo de su ciclo de vida.

* **¿Para qué sirven?** Evitan el uso de "Magic Strings" (cadenas de texto sueltas como `"activo"` o `"pendiente"`) en el código, estandarizando los estados válidos tanto en el dominio como en las bases de datos.

### Enums Esenciales del Sistema:
* **`OrderStatus`**: Controla el ciclo de vida de una orden de compra:
  * `CARRITO`: Cuando los ítems apenas están agregados.
  * `PENDIENTE` / `PAGADA`: Cuando el cliente confirma el pago.
  * `EN_PROCESO`: Cuando el vendedor está preparando el despacho.
  * `COMPLETADA` / `CANCELADA`.
* **`ShipmentStatus`**: Define el estado logístico del envío:
  * `PREPARANDO`, `EN_CAMINO`, `ENTREGADO`, `DEVUELTO`.
* **`SellerStatus`**: Controla el estado del comercio:
  * `ACTIVE`, `SUSPENDED`, `PENDING_APPROVAL`.

---

## 🛡️ 3. Excepciones de Dominio (`exceptions`)

Las excepciones personalizadas dentro de `application.domain.exceptions` aseguran que cuando una regla de negocio se rompa (por ejemplo, intentar comprar un producto sin stock, o buscar un vendedor que no existe), el sistema lance un error semántico y controlado.

* **¿Cómo operan?** Viajan desde el servicio de dominio hacia arriba, siendo capturadas opcionalmente por controladores o manejadores globales de errores para devolver mensajes limpios al usuario (ej. `400 Bad Request` o `404 Not Found`).

---

## 🔄 Ejemplo Práctico: El Flujo Interno de un Caso de Uso

Tomemos como ejemplo el caso de uso **Actualizar Inventario (`ManageInventoryService`)**:

1. **Entrada:** El adaptador web recibe un `InventoryRequestDto` y llama al servicio de dominio pasándole los datos primitivos.
2. **Lógica de Dominio:** 
   * El servicio invoca al puerto `InventoryRepositoryPort.findByProductId(productId)`.
   * Si el inventario ya existe, ejecuta el método de negocio `inventory.updateStock(quantity)`.
   * Si no existe, instancia un nuevo objeto de dominio `Inventory` con un ID generado.
3. **Persistencia:** El servicio delega el guardado al puerto `InventoryRepositoryPort.save(inventory)`.
4. **Salida:** El modelo actualizado retorna limpio y sin acoplamientos tecnológicos al controlador para responderle al cliente.