import model.Cliente
import model.Consulta
import model.Mascota
import model.Medicamento
import model.Pedido
import model.Promocionable
import model.Tutor
import model.Veterinario
import java.time.LocalDate
import kotlin.reflect.full.declaredMemberFunctions
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.findAnnotations
import utils.dosisRecomendada
import utils.proximaVacuna
import utils.revisarVeterinariosDisponibles
import utils.formatearTelefono
import utils.validarInput

class Veterinaria {

  fun aplicacion(){
    var idConsulta = 0
    var menuPrincipal = 0
    var inputMenu = 1

    val listaConsutlas = mutableListOf<Consulta>()
    val listaPedidos = mutableListOf<Pedido>()
    val tutoresRegistrados = mutableSetOf<Tutor>()
    val clientesRegistrados = mutableSetOf<Cliente>()

    val veterinarios = listOf(
      Veterinario("Gabriel Chavez", listOf("lunes", "miércoles")),
      Veterinario("Humberto Velez", listOf("martes", "jueves")),
      Veterinario("Victor Delgado", listOf("viernes", "sábado"))
    )

    do {
      println("")
      println("---------------------------------------")
      println("----- BIENVENIDO A LA VETERINARIA -----")
      println("---------------------------------------")
      println("")

      do {
        println("INGRESA EL TIPO DE SERVICIO")
        println("[1]CONSULTA [2]FARMACIA" )

        val inputMenuPrincipal = readln()
        try {
          menuPrincipal = inputMenuPrincipal.toInt()
          if (menuPrincipal !in 1..2) {
            println("Ingresa el número correspondiente al tipo de prestación.")
          }
        } catch (ex: NumberFormatException) {
          println("Ingresa solo números válidos (1 - 2 o 3), no letras.")
          menuPrincipal = 0
        }
      } while (menuPrincipal == 0 || menuPrincipal !in 1..2)

      when(menuPrincipal) {
        //SECCION VETERINARIA
        1 -> {
          println("")
          println("----------- DATOS DEL TUTOR -----------")
          print("Nombre del tutor: ")
          val nombreTutor = readln().ifBlank{ null } ?: "Tutor desconocido"

          val telefono = validarInput(
            "Teléfono de contacto (número de 12 dígitos) ",
            "telefono",
            "El teléfono debe tener los 12 dígitos. Ingreslo nuevamente."
          )
          val telefonoFormateado = formatearTelefono(telefono)

          val email = validarInput(
            "Correo electrónico: ",
            "email",
            "El correo debe tener el formato correcto. @ y dominio."
          )

          val tutor = Tutor(nombreTutor, telefonoFormateado, email)

          val agregadoTutor = tutoresRegistrados.add(tutor)
          if (!agregadoTutor) {
            println("**** [AVISO] El Tutor ya esta registrado ****.")
          }

          println("")
          println("--------- DATOS DE LA MASCOTA ---------")
          print("Ingrese el nombre de la mascota: ")
          val nombreMascota = readln().ifBlank{ null } ?: "Mascota sin nombre"
          print("Especie mascota: ")
          val especie = readln().ifBlank { null } ?: "Especie no especificada"

          var edad: Int?
          do {
            print("Edad de la mascota: ")
            val inputEdad = readln()
            try {
              edad = inputEdad.toInt()
              if ( edad < 0){
                println("La mascota debe tener una edad mayor a 0")
              }
            } catch (ex: NumberFormatException) {
              println("Debes ingresar un número")
              edad = null
            }
          } while (edad == null || edad <= 0)


          var peso: Int?
          do {
            print("Peso de la mascota (Kg): ")
            val inputPeso = readln()
            try {
              peso = inputPeso.toInt()
              if ( peso < 0){
                println("El peso de la mascota debe ser mayor a 0")
              }
            } catch (ex: NumberFormatException) {
              println("Debes ingresar un número")
              peso = null
            }
          } while (peso == null || peso <= 0)

          println("")
          println("----------------------------------------")
          println("Basado en los datos de la mascota")
          when (edad){
            in 0..1 -> println("Necesita su vacuna semestral.")
            in 2..6 -> println("Necesita su vacuna anual.")
            else -> println("La vacuna para la mascota es opcional.")
          }
          proximaVacuna(edad)
          dosisRecomendada(peso, edad)
          println("----------------------------------------")
          println("")

          val mascota = Mascota(nombreMascota, especie, edad, tutor)

          println("")
          println("----------- DATOS CONSULTA -----------")
          var opcionConsulta: Int?
          do {
            println("Ingresa el tipo de consulta")
            println("[1]Peluqueria [2]Control [3]Otro")

            val inputConsulta = readln()
            try {
              opcionConsulta = inputConsulta.toInt()
              if (opcionConsulta !in 1..3) {
                println("Ingresa el número correspondiente al tipo de consulta (1 - 2 o 3).")
              }
            } catch (ex: NumberFormatException) {
              println("Ingresa solo números válidos (1 - 2 o 3), no letras.")
              opcionConsulta = null
            }
          } while (opcionConsulta == null || opcionConsulta !in 1..3)

          val descripcion = when (opcionConsulta) {
            1 -> "Peluqueria"
            2 -> "Control"
            3 -> "Otro"
            else -> "Tratamiento desconocido"
          }

          val horasOcupadas = listOf("10:00", "11:30", "15:00")
          var inputHora = ""
          do {
            print("Ingrese hora de consulta (HH:MM): ")
            val hora = readln()
            inputHora = hora
            if (inputHora in horasOcupadas) {
              println("")
              println("Intente con otro horario que no sea:")
              for (item in horasOcupadas) {
                println(item)
              }
            } else {
              println("")
              println("Hora registrada exitosamente para la consulta.")
              println("")
            }
          } while (inputHora in horasOcupadas)


          val valorConsulta = when (opcionConsulta) {
            1 -> 15000
            2 -> 10000
            else -> 10000
          }

          val veterinario = revisarVeterinariosDisponibles(veterinarios)

          val consulta = Consulta(++idConsulta, descripcion, valorConsulta, inputHora, mascota, veterinario)
          listaConsutlas.add(consulta)
          consulta.resumenConsulta()


          println("---------------------------------------------------------------")
          println("------------------¿COMO QUIERES CONTINUAR? --------------------")
          println("[1]VOLVER AL MENU PRINCIPAL [2]VER TODAS LAS CONSULTAS [3]SALIR")
          try {
            inputMenu = readln().toInt()
            if (inputMenu !in 1..3) {
              println("Ingresa el número correspondiente a la opción que deseas ejecutar.")
            }
          } catch (ex: NumberFormatException) {
            println("Ingresa solo el número que aparece en el menú")
            inputMenu = 0
          }

          when(inputMenu){
            1 -> inputMenu = 1
            2 -> {
              for (consulta in listaConsutlas) {
                consulta.resumenConsulta()
              }

              println("---------------------------------------------------------------")
              println("------------------¿COMO QUIERES CONTINUAR? --------------------")
              println("[1]VOLVER AL MENU PRINCIPAL [2]VER TODAS LAS CONSULTAS [3]SALIR")

              try {
                inputMenu = readln().toInt()
                if (inputMenu !in 1..3) {
                  println("Ingresa el número correspondiente a la opción que deseas ejecutar.")
                }
              } catch (ex: NumberFormatException) {
                println("Ingresa solo el número que aparece en el menú")
                inputMenu = 0
              }
            }

            else -> inputMenu = 3
          }

        }


        //SECCION FARMACIA
        2 -> {
          print("Nombre del cliente: ")
          val nombreCliente = readln().ifBlank{ null } ?: "Cliente desconocido"

          val telefono = validarInput(
            "Teléfono de contacto (número de 12 dígitos): ",
            "telefono",
            "El teléfono debe tener los 12 dígitos. Ingreslo nuevamente."
          )
          val telefonoFormateado = formatearTelefono(telefono)

          val email = validarInput(
            "Correo electrónico: ",
            "email",
            "El correo debe tener el formato correcto. @ y dominio."
          )

          val cliente = Cliente(nombreCliente, telefonoFormateado, email)
          val clienteInscrito = clientesRegistrados.add(cliente)
          if (!clienteInscrito) {
            println("**** [AVISO] El cliente ${cliente.nombre} con el teléofono ${cliente.email} ya esta registrado ****.")
          }


          println("")
          println("--------------- DATOS MEDICAMENTOS ---------------")

          var opcionMedicamento = 0
          do {
            println("Ingresa el medicamento que el cliente desea comprar")
            println("[1]Kittydoll [2]K-9 [3]Matapiojo")

            val inputMedicamento = readln()
            try {
              opcionMedicamento = inputMedicamento.toInt()
              if (opcionMedicamento !in 1..3) {
                println("Ingresa la opción que te presenta el menú.")
              }
            } catch (ex: NumberFormatException) {
              println("Ingresa sólo números válidos.")
              opcionMedicamento = 0
            }
          } while (opcionMedicamento !in 1..3)

          val nombreMedicamento = when (opcionMedicamento) {
            1 -> "kittydoll"
            2 -> "k-9"
            else -> "matapiojo"
          }
          val tipoMedicamento = when (opcionMedicamento) {
            1 -> "analgesico"
            2 -> "vitamina"
            else -> "desparasitario"
          }
          var precioMedicamento = when (opcionMedicamento) {
            1 -> 10000
            2 -> 5000
            else -> 2000
          }

          val medicamento = Medicamento(nombreMedicamento, tipoMedicamento, precioMedicamento)

          val fechaInicioPromo = LocalDate.now().minusDays(1)
          val fechaFinPromo = fechaInicioPromo.plusDays(3)
          val fechaVerificacion = LocalDate.now()

          val promociones = medicamento::class.findAnnotations<Promocionable>()
          val promocionesDisponibles = promociones.filter { it.tipo.equals(medicamento.tipo, ignoreCase = true) }
          if (promocionesDisponibles.isEmpty()) {
            println("No hay promociones aplicables para el tipo '${medicamento.tipo}'.")
          }
          if (fechaVerificacion !in fechaInicioPromo..fechaFinPromo) {
            println("Estas fuera del rango de promociones")
          }
          else {
            promocionesDisponibles.forEach { promocion ->
              val descuento = promocion.descuento * medicamento.precio
              precioMedicamento = (medicamento.precio - descuento).toInt()
              println("")
              println("Este producto tiene un descuento de ${promocion.nombre} por ser del tipo ${medicamento.tipo}'.")
              println("El precio con el descuento es de $${precioMedicamento}")
              println("")
            }
          }

          val pedido = Pedido(cliente, medicamento, precioMedicamento, fechaInicioPromo, fechaFinPromo )

          listaPedidos.add(pedido)
          pedido.mostrarDatosCliente()
          pedido.mostrarDetallePedido()


          println("--------------------------------------------------------------")
          println("------------------¿COMO QUIERES CONTINUAR? -------------------")
          println("[1]VOLVER AL MENU PRINCIPAL [2]VER PEDIDOS COMBINADOS [3]SALIR")
          try {
            inputMenu = readln().toInt()
            if (inputMenu !in 1..3) {
              println("Ingresa el número correspondiente a la opción que deseas ejecutar.")
            }
          } catch (ex: NumberFormatException) {
            println("Ingresa solo el número que aparece en el menú")
            inputMenu = 0
          }

          when(inputMenu){
            1 -> inputMenu = 1
            2 -> {
              if (listaPedidos.isNotEmpty()) {
                val pedidoCombinado = listaPedidos.reduce { acc, p -> acc + p }
                println("----------- RESUMEN PEDIDO COMBINADOS -----------")
                println("Clientes combinados: ${pedidoCombinado.cliente.nombre}")
                println("Medicamentos combinados: ${pedidoCombinado.medicamento.nombre}")
                println("Total combinado: $${pedidoCombinado.total}")
              }


              // Listado dinámico de miembros de la clase Medicamento (propiedades y métodos)
              val kClass = medicamento::class
              println("")
              println("----------------------------------------------")
              println("---- Miembros de ${kClass.simpleName} ----")

              // Propiedades declaradas en la clase
              println("Propiedades:")
              kClass.declaredMemberProperties.forEach { prop ->
                val typeDesc = prop.returnType.toString()
                val value = runCatching { prop.getter.call(medicamento) }.getOrNull()
                println("- ${prop.name}: $typeDesc = ${value}")
              }

              // Métodos declarados en la clase
              println("Métodos:")
              kClass.declaredMemberFunctions.forEach { fn ->
                val paramsDesc = fn.parameters.drop(1).joinToString(", ") { it.type.toString() } // drop receiver
                val returnDesc = fn.returnType.toString()
                println("- ${fn.name}(${paramsDesc}): ${returnDesc}")
              }

              println("--------------------------------------------------------------")
              println("------------------¿COMO QUIERES CONTINUAR? -------------------")
              println("[1]VOLVER AL MENU PRINCIPAL [2]VER PEDIDOS COMBINADOS [3]SALIR")

              try {
                inputMenu = readln().toInt()
                if (inputMenu !in 1..3) {
                  println("Ingresa el número correspondiente a la opción que deseas ejecutar.")
                }
              } catch (ex: NumberFormatException) {
                println("Ingresa solo el número que aparece en el menú")
                inputMenu = 0
              }
            }
            else -> inputMenu = 3
          }
        }
      }
    } while(inputMenu != 3)
  }
}