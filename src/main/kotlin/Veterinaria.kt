import model.Cliente
import model.Consulta
import model.Pedido
import annotations.Promocionable
import model.Tutor

import service.ConsultaService
import service.MascotaService
import service.MedicamentoService
import service.TutorService
import java.time.LocalDate
import kotlin.reflect.full.declaredMemberFunctions
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.findAnnotations

import utils.formatearTelefono
import utils.validarInput

class Veterinaria {

  fun aplicacion(){
    var menuPrincipal = 0
    var inputMenu = 1

    val servicioMascota = MascotaService()
    val servicioMedicamento = MedicamentoService()
    val servicioTutor = TutorService()
    val servicioConsulta = ConsultaService()

    val listaConsutlas = mutableListOf<Consulta>()
    val listaPedidos = mutableListOf<Pedido>()
    val tutoresRegistrados = mutableSetOf<Tutor>()
    val clientesRegistrados = mutableSetOf<Cliente>()


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
          val tutor = servicioTutor.asignarTutor()
          servicioTutor.revisarTutor(tutor, tutoresRegistrados)

          println("")
          println("--------- DATOS DE LA MASCOTA ---------")
          val mascota = servicioMascota.asignarMascota(tutor)

          println("")
          println("----------- DATOS CONSULTA -----------")
          val consulta = servicioConsulta.asignarConsulta(mascota)
          consulta.resumenConsulta()
          listaConsutlas.add(consulta)


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


          val medicamento = servicioMedicamento.asignarMedicamento(opcionMedicamento)

          val fechaInicioPromo = LocalDate.now().minusDays(1)
          val fechaFinPromo = fechaInicioPromo.plusDays(3)
          val fechaVerificacion = LocalDate.now()

          var precioMedicamento = 0
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