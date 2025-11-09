# TODO — App exp1-s3
![Kotlin](https://img.shields.io/badge/kotlin-%237F52FF.svg?style=for-the-badge&logo=kotlin&logoColor=white)


### Regex
- [x] Valida los correos electrónicos ingresados para asegurarte de que siguen el formato
estándar (nombre@dominio.com). Si el formato es incorrecto, muestra un mensaje de
error y solicita al usuario que lo reingrese.
- [ ] Crea una función que procese números de teléfono y los formatee en un estilo uniforme
(+XX (XXX) XXX-XXXX).

### Ranges
- [ ] Implementa un rango de fechas para verificar si un pedido está dentro de un periodo de
promociones. Si está en el rango, aplica un descuento.
- [x] Evalúa si un número ingresado (por ejemplo, cantidad de productos) cae dentro de un
rango permitido (1-100), mostrando un mensaje en caso contrario.

### Anotaciones
- [ ] Agrega una anotación personalizada @Promocionable a los medicamentos
que califican para promociones especiales.
- [ ] Utiliza esta anotación para identificar dinámicamente cuáles productos son
elegibles durante el procesamiento de pedidos.

### Reflection:
- [ ] Implementa una funcionalidad que liste todos los métodos y propiedades de
una clase (Pedido, Medicamento o Cliente) utilizando reflection. Esto permitirá
analizar dinámicamente los atributos de las entidades.

###  Operator Overloading
-[ ] Sobrecarga el operador + en la clase Pedido para combinar dos pedidos en uno solo,
sumando los productos y recalculando el total.
-[ ] Sobrecarga el operador == en la clase Medicamento para comparar si dos objetos
representan el mismo medicamento basado en su nombre y dosificación.

### Declaraciones de Desestructuración
-[ ] Implementa la funcionalidad para desestructurar un objeto Cliente y obtener
directamente su nombre, correo electrónico y número de teléfono.
-[ ] Permite la desestructuración de un objeto Pedido para extraer el cliente, los productos
y el total del pedido en variables individuales.

### Evaluaciones de Igualdad de Objetos
-[ ] Sobrescribe los métodos equals() y hashCode() en las clases Cliente y Medicamento
para que dos objetos sean considerados iguales si sus atributos clave coinciden (por
ejemplo, nombre y correo en Cliente o nombre y dosificación en Medicamento).
-[ ] Utiliza estas evaluaciones en el sistema para evitar duplicados al registrar clientes o
medicamentos.

### Resumen y Ejecución
-[ ] Genera un archivo de resumen en la consola que incluya:
-[ ] Pedidos combinados mediante la sobrecarga del operador +.
-[ ] Información de un cliente desestructurado (nombre, correo electrónico, teléfono).
-[ ] Productos identificados como promocionables mediante anotaciones.
-[ ] Validación de igualdad de objetos (clientes y medicamentos).
-[ ] Ejecuta el programa principal (Main.kt) para demostrar cómo todas las funcionalidades
trabajan en conjunto de manera eficiente.

### Presentación y entrega
-[ ] Muestra este resumen de forma ordenada y clara para que sirva como confirmación para el
cliente y la veterinaria.
-[ ] Como entrega debes comprimir tu proyecto Kotlin en formato RAR o ZIP y subirlo en
plataforma.