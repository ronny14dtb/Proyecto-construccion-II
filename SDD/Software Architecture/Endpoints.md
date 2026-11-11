Guía Definitiva del Marketplace (Arquitectura Hexagonal - Híbrido MySQL & MongoDB)

¡Bienvenido a la guía oficial del proyecto! Este documento detalla el paso a paso para levantar el entorno de desarrollo y probar todos los flujos del marketplace (Compradores, Vendedores, Productos, Inventario, Carrito, Órdenes, Envíos, Devoluciones y Reportes).

🚀 1. Paso a Paso para Levantar el Entorno

Asegúrate de tener instalados Java 21, Maven, MySQL (o XAMPP/Docker ejecutándose en el puerto 3307 o 3306 según tu configuración) y MongoDB activo localmente.

Clonar o abrir el proyecto en tu entorno (por ejemplo, en los equipos de la universidad).

Verificar las credenciales de base de datos en tu archivo src/main/resources/application.properties:

spring.datasource.url=jdbc:mysql://localhost:3307/nexusmarket_db?serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update

spring.data.mongodb.uri=mongodb://localhost:27017/nexusmarket_db


Compilar y arrancar la aplicación desde la terminal raíz del proyecto ejecutando:

En Windows:

mvnw.cmd spring-boot:run


En Mac / Linux / Git Bash:

./mvnw spring-boot:run


Espera a que la consola muestre Started TiendaApplication y que Hibernate inicialice las tablas. ¡El servidor estará corriendo en http://localhost:8080!

📦 2. Guía de Endpoints y Cuerpos JSON (Thunder Client / Postman)

A continuación se presenta el orden lógico recomendado para realizar las pruebas de punta a punta.

Módulo de Vendedores (Sellers)

1. Registrar un Vendedor

Método: POST

URL: http://localhost:8080/api/sellers/register

JSON Body:

{
  "storeName": "TecnoTienda Medellín",
  "email": "contacto@tecnotienda.com"
}


Guarda el sellerId que te devuelva la respuesta (ej. seller-5b878df4).

Módulo de Productos e Inventario (Products & Inventory)

2. Crear un Producto (Amarrado al Vendedor)

Método: POST

URL: http://localhost:8080/api/products

JSON Body:

{
  "sellerId": "seller-5b878df4",
  "name": "Teclado Mecánico RGB",
  "price": 150000.0,
  "stock": 25
}


Guarda el productId que te devuelva la respuesta (ej. prod-456).

3. Consultar Productos por Vendedor

Método: GET

URL: http://localhost:8080/api/products/seller/seller-5b878df4

4. Registrar o Actualizar Stock en Bodega

Método: POST

URL: http://localhost:8080/api/inventory/update

JSON Body:

{
  "productId": "prod-456",
  "sellerId": "seller-5b878df4",
  "quantity": 50,
  "warehouseLocation": "Bodega Central Medellín"
}


Módulo de Usuarios y Carrito (Users & Cart)

5. Registrar Comprador

Método: POST

URL: http://localhost:8080/api/users/register

JSON Body:

{
  "username": "ronny",
  "email": "ronny@mail.com",
  "password": "123"
}


6. Iniciar Sesión (Generar Token JWT)

Método: POST

URL: http://localhost:8080/api/users/login

JSON Body:

{
  "email": "ronny@mail.com",
  "password": "123"
}


7. Agregar Productos al Carrito (Soporta Múltiples Vendedores)

Método: POST

URL: http://localhost:8080/api/carts/add

JSON Body:

{
  "buyerId": "user-123",
  "items": [
    {
      "productId": "prod-456",
      "sellerId": "seller-5b878df4",
      "quantity": 2
    },
    {
      "productId": "prod-001",
      "sellerId": "vendedor-A",
      "quantity": 1
    }
  ]
}


Módulo Transaccional (Orders, Shipments & Returns)

8. Crear Orden (Checkout)

Método: POST

URL: http://localhost:8080/api/orders

JSON Body:

{
  "buyerId": "user-123",
  "orderId": "ord-001",
  "status": "CARRITO"
}


9. Registrar Envío

Método: POST

URL: http://localhost:8080/api/shipments

JSON Body:

{
  "orderId": "ord-001",
  "carrier": "Servientrega",
  "trackingNumber": "TRK-987654321",
  "status": "EN_CAMINO"
}


10. Registrar Devolución / Reembolso

Método: POST

URL: http://localhost:8080/api/returns

JSON Body:

{
  "orderId": "ord-001",
  "reason": "Producto defectuoso"
}


Módulo de Reportes Consolidados (MongoDB)

11. Consultar Reporte del Comprador

Método: GET

URL: http://localhost:8080/api/reports/buyer/user-123

Consolida en MongoDB de forma estricta los carritos, órdenes, envíos y devoluciones asociados al comprador.