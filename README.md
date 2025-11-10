# Desarrollo Aplicaciones Móviles — Veterinaria (EXP1-S3)
![Kotlin](https://img.shields.io/badge/kotlin-%237F52FF.svg?style=for-the-badge&logo=kotlin&logoColor=white)
![Gradle](https://img.shields.io/badge/gradle-%237F52FF.svg?style=for-the-badge&logo=gradle&logoColor=white)
#### Aplicación de consola en Kotlin que simula el flujo de una veterinaria: registro de tutor y mascota, creación de consultas, cálculo de próximas vacunas y dosis recomendada, y visualización de veterinarios disponibles según el día de la semana.

## ⭐ Características
### 2 flujos de interacción: 
### Primer Flujo: Consulta veterinaria
- Registro de datos de tutor y cliente: nombre, teléfono y correo con validación de formato.
- Registro de datos de la mascota: nombre, especie, edad y peso, con validaciones numéricas.
- Recomendaciones:
    - Próxima vacuna calculada en función de la edad.
    - Dosis recomendada (ml) calculada a partir de edad y peso.
- Gestión de consultas:
    - Selección del tipo de consulta: Peluquería, Control u Otro.
    - Selección de horario con verificación de horas ocupadas (10:00, 11:30, 15:00).
    - Identificador de consulta incremental durante la ejecución.
    - Listado de todas las consultas registradas.
- Disponibilidad de veterinarios por día:
    - Filtrado automático según el día actual.
- Menú simple de navegación: crear nueva consulta, ver todas, salir.

### Segundo Flujo: Venta de Fármacos
- Registro de datos del cliente: nombre, teléfono y correo con validación de formato.
- Elección de fármaco
- Verificicación de descuento según tipo de fármaco y anotación `@Promocionable`
- Uso de `reflexion`, `destructuring` y `overloading` de operadores 


## 📐 Estructura del proyecto
```
/ (raíz)
├ src/
├── main/
├─── kotlin/
├────── Main.kt                  # Punto de entrada; ejecuta Veterinaria.aplicacion()
├────── Veterinaria              # Lógica principal del flujo y utilidades  
├────── model/
         ├─ Cliente
         ├─ Medicamento
         ├─ Promocionable
         ├─ Pedido
         ├─ Usuario              
         ├─ Veterinario            
         ├─ Tutor                  
         ├─ Mascota              
         └─ Consulta
├────── utils/
         └─ VeterinariaUtils                        
└─ README.md
└─ TODO.md
```

## ✏️ Requisitos
- JDK 21.
- IntelliJ IDEA
- Gradle

## ⚙️ Cómo ejecutar
1. Abrir el proyecto en IntelliJ IDEA.
2. Asegurarte de usar JDK 21+ en Project SDK.
3. Ejecutar `Main.kt` (configuración "Run" en `src/Main.kt`).

## 🧪 Flujo de ejemplo
1. Se inicia la aplicación
2. Ingresa los datos del tutor y de la mascota
3. Selecciona el tipo de consulta: `1` Peluquería, `2` Control, `3` Otro.
4. Ingresa la hora en formato `HH:MM`. Si coincide con una hora ocupada, se solicitará otra.
5. Con los datos ingresados, se recomndará la próxima fecha de vacunación y la dosis recomendada.
6. Se imprimirá un resumen de la consulta creada.
7. Menú de continuación:
    - `[1]` Crear una nueva consulta
    - `[2]` Ver todas las consultas
    - `[3]` Salir

## 🔬 Detalles técnicos
- Validación de email: expresión regular simple `^.+@.+\..+$`.
- Horarios ocupados de ejemplo: `10:00`, `11:30`, `15:00`.
- Turnos de veterinarios de ejemplo:
    - Gabriel Chavez → lunes, miércoles
    - Humberto Velez → martes, jueves
    - Victor Delgado → viernes, sábado

