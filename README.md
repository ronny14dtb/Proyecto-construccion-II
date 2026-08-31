# Proyecto-construccion-II


1. DESCRIPCIÓN GENERAL
El presente entregable define la implementación del Modelo de Dominio (Domain Core) para la plataforma
NexusMarket, estructurado bajo el paradigma de Domain-Driven Design (DDD) y la Arquitectura Hexagonal (Ports
& Adapters). El objetivo central es desacoplar completamente la lógica de negocio de la infraestructura
tecnológica, bases de datos y marcos de trabajo externos.


2. ESTRUCTURA DE PAQUETES (`APPLICATION/DOMAIN`) La cual está basada en lo que había dejado sobre el proyecto Banco DDD por lo que se implementó esta estructura en este trabajo de tienda con diferencias en las clases

src/main/java/application/domain/
├── enums/
│ ├── UserRole.java
│ ├── UserStatus.java
│ ├── BuyerStatus.java
│ ├── ProductType.java
│ ├── ProductStatus.java
│ ├── InventoryMovementType.java
│ └── OrderStatus.java
│
├── exceptions/
│ ├── UserNotFoundException.java
│ ├── InsufficientInventoryException.java
│ ├── InvalidOrderStateException.java
│ └── UnauthorizedOperationException.java
│
├── models/
│ ├── User.java
│ ├── Buyer.java
│ ├── Seller.java
│ ├── Warehouse.java
│ ├── Product.java
│ ├── ProductVariant.java
│ ├── Inventory.java
│ ├── InventoryMovement.java
│ ├── ShoppingCart.java
│ ├── CartItem.java
│ ├── Order.java
│ ├── OrderItem.java
│ ├── Invoice.java
│ ├── Shipment.java
│ └── ReturnRequest.java
│
├── ports/
│ └── out/
│ ├── UserRepositoryPort.java
│ ├── BuyerRepositoryPort.java
│ ├── SellerRepositoryPort.java
│ ├── WarehouseRepositoryPort.java
│ ├── ProductRepositoryPort.java

│ ├── InventoryRepositoryPort.java
│ ├── OrderRepositoryPort.java
│ ├── InvoiceRepositoryPort.java
│ └── ShipmentRepositoryPort.java
│
├── services/
│ ├── user/
│ │ └── UserService.java
│ ├── buyer/
│ │ └── BuyerService.java
│ ├── seller/
│ │ └── SellerService.java
│ ├── warehouse/
│ │ └── WarehouseService.java
│ ├── catalog/
│ │ └── ProductCatalogService.java
│ ├── inventory/
│ │ └── InventoryService.java
│ ├── order/
│ │ └── OrderService.java
│ ├── invoice/
│ │ └── InvoiceService.java
│ ├── shipment/
│ │ └── ShipmentService.java
│ └── refund/
│ └── RefundService.java
│
└── valueobjects/
├── Address.java
├── Money.java
├── Email.java
└── Quantity.java

3. JUSTIFICACIÓN DEL DISEÑO Y REGLAS DE NEGOCIO
A. Separación de Contextos: User vs. Buyer / Seller
Se aisló la identidad global (User) de las funciones comerciales específicas (Buyer, Seller). User
encapsula las credenciales y roles administrativos generales (Administrador, Operador, Supervisor), mientras
que Buyer gestiona aspectos específicos como el estado comercial y las direcciones de despacho. Esto
evita la contaminación de datos en perfiles que no operan como compradores.

B. Organización Modular de Servicios por Subpaquetes (`services/`)
Siguiendo las directrices del proyecto de referencia, los servicios de dominio no se ubicaron de forma plana,
sino en subcarpetas orientadas a contextos delimitados (order, inventory, catalog, etc.). Esto garantiza
alta cohesión y bajo acoplamiento entre los flujos operativos.

C. Control de Inventario no Negativo
En Inventory.java y InventoryService.java se implementa la regla estricta de validación que
impide existencias negativas bajo cualquier circunstancia. Al intentar reservar o despachar stock insuficiente,
el sistema arroja la excepción explícita InsufficientInventoryException.

D. Inmutabilidad y Ciclo de Vida del Pedido
El modelo Order.java restringe estrictamente la modificación de pedidos cuya transición haya alcanzado el
estado FINALIZADO. Las transiciones de estado siguen una máquina de estados determinista (CARRITO →
PENDIENTE_PAGO → PAGADO → DESPACHADO → ENTREGADO → FINALIZADO).

E. Encapsulamiento mediante Value Objects
Conceptos como montos monetarios (Money), direcciones (Address) y correos electrónicos (Email) fueron
modelados como objetos de valor inmutables, garantizando validación sintáctica y comportamiento
consistente en todo el sistema.