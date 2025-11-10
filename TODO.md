- [ ] Paso 1: Regex y Ranges
  - [x] Regex
    - [x] Validar los correos electrónicos para que sigan el formato estándar (nombre@dominio.com); si es incorrecto, mostrar un mensaje y pedir reingreso.
    - [x] Crear una función que procese números de teléfono y los formatee en un estilo uniforme (+XX (XXX) XXX-XXXX).
  - [x] Ranges
    - [x] Implementar un rango de fechas para verificar si un pedido está dentro de un periodo de promociones; si está en el rango, aplicar un descuento.
    - [x] Evaluar si un número ingresado (por ejemplo, cantidad de productos) cae dentro de un rango permitido, mostrando un mensaje en caso contrario.

- [x] Paso 2: Anotaciones y Reflection
  - [x] Anotaciones
    - [x] Agregar una anotación personalizada @Promocionable a los medicamentos que califican para promociones especiales.
    - [X] Utilizar esta anotación para identificar dinámicamente cuáles productos son elegibles durante el procesamiento de pedidos.
  - [x] Reflection
    - [x] Implementar una funcionalidad que liste todos los métodos y propiedades de una clase (Pedido, Medicamento o Cliente) utilizando reflection, para analizar dinámicamente sus atributos.

- [ ] Paso 3: Operator Overloading
  - [x] Sobrecargar el operador + en la clase Pedido para combinar dos pedidos en uno solo, sumando los productos y recalculando el total.
  - [ ] Sobrecargar el operador == en la clase Medicamento para comparar si dos objetos representan el mismo medicamento basado en su nombre y dosificación.

- [x] Paso 4: Declaraciones de Desestructuración
  - [x] Implementar la desestructuración de un objeto Cliente para obtener directamente su nombre, correo electrónico y número de teléfono.
  - [x] Permitir la desestructuración de un objeto Pedido para extraer el cliente, los productos y el total del pedido en variables individuales.
- 
- [x] **Paso 5: Evaluaciones de Igualdad de Objetos**
  - [x] Sobrescribir equals() y hashCode() en las clases Cliente y Medicamento para que dos objetos sean considerados iguales si sus atributos clave coinciden (por ejemplo, nombre y correo en Cliente; nombre y dosificación en Medicamento).
  - [x] Usar estas evaluaciones en el sistema para evitar duplicados al registrar clientes o medicamentos.

