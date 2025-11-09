import model.Consulta
import model.Mascota
import model.Tutor
import model.Veterinario
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

class Veterinaria {

  fun aplicacion(){
    var idConsulta = 0
    val listaConsutlas = mutableListOf<Consulta>()
    var inputMenu = 1

    val veterinarios = listOf(
      Veterinario("Gabriel Chavez", listOf("lunes", "miércoles")),
      Veterinario("Humberto Velez", listOf("martes", "jueves")),
      Veterinario("Victor Delgado", listOf("viernes", "sábado"))
    )

    fun revisarVeterinariosDisponibles(veterinarios: List<Veterinario>): String {
      val hoy = LocalDate.now()
      val nombreDiaHoy = hoy.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.forLanguageTag("es")).lowercase()

      val veterinariosDeTurno = veterinarios.filter { veterinario -> veterinario.turno.contains(nombreDiaHoy) }
      val nombreVeterinario = veterinariosDeTurno.firstOrNull()?.nombre ?: "No hay veterinarios disponibles"

      return nombreVeterinario
    }


    fun dosisRecomendada(pesoMascota: Int, edadMascota: Int) {
      var dosisBase = 5;
      if (edadMascota in 0..3){
        when(pesoMascota) {
          in 0..0,9 -> dosisBase = 2
          in 1..2 -> dosisBase = 3
          in 2..3 -> dosisBase = 4
          else -> dosisBase
        }
      } else if (edadMascota in 4 .. 6){
        when(pesoMascota) {
          in 3..4 -> dosisBase = 5
          in 5..6 -> dosisBase = 6
          else -> dosisBase*=2
        }
      }
      else{
        dosisBase*=3
      }

      println("La dosis recomendada es ${dosisBase}ml.")
    }

    fun proximaVacuna(edadMascota: Int){
      val fechaHoy = LocalDate.now()
      val formatoFecha = DateTimeFormatter.ofPattern("dd-MM-yyyy")
      when (edadMascota){
        in 0..1,9 -> println("La próxima vacuna es el ${fechaHoy.plusMonths(1).format(formatoFecha)} .")
        in 2..6 -> println("La próxima vacuna es el ${fechaHoy.plusMonths(2).format(formatoFecha)}")
        else -> println("La próxima vacuna para la mascota es opcional.")
      }

    }

    do {
      println("")
      println("---------------------------------------")
      println("----- BIENVENIDO A LA VETERINARIA -----")
      println("---------------------------------------")

      when(inputMenu){
        1 -> {
          println("")
          println("----------- DATOS DEL TUTOR -----------")
          print("Nombre del tutor: ")
          val nombreTutor = readln().ifBlank{ null } ?: "Tutor desconocido"
          print("Teléfono de contacto: ")
          val telefono = readln().ifBlank{ null } ?: "No tiene número telefónico"

          var email: String
          val regexEmail = "^.+@.+\\..+$".toRegex()
          do {
            print("Correo electrónico: ")
            val inputCorreo = readln()
            email = inputCorreo
            if(!inputCorreo.matches(regexEmail)) {
              println("El correo debe tener el formato correcto. @ y dominio.")
            }
          } while (!email.matches(regexEmail) || email.isEmpty())

          val tutor = Tutor(nombreTutor, telefono, email)

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


          println("----------------------------------------")
          when (edad){
            in 0..1,9 -> println("La mascota requiere su vacuna semestral.")
            in 2..6 -> println("La mascota requiere su vacuna anual.")
            else -> println("La vacuna para la mascota es opcional.")
          }
          println("----------------------------------------")

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
          println("------------------------------------------------------------")
          proximaVacuna(edad)
          dosisRecomendada(peso, edad)
          println("------------------------------------------------------------")
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

          println("--------------------------------------------------------------")
          println("------------------¿COMO QUIERES CONTINUAR? -------------------")
          println("[1]Crear una nueva consulta [2]Ver todas las consultas [3]Salir")
          try {
            inputMenu = readln().toInt()
            if (inputMenu !in 1..3) {
              println("Ingresa el número correspondiente al tipo de consulta (1 - 2 ó 3).")
            }
          } catch (ex: NumberFormatException) {
            println("Ingresa solo números válidos (1 - 2 o 3), no letras.")
            inputMenu = 0
          }
        }
        2 -> {
          for (consulta in listaConsutlas) {
            consulta.resumenConsulta()
          }

          println("--------------------------------------------------------------")
          println("----------------- ¿COMO QUIERES CONTINUAR? -------------------")
          println("[1]Crear una nueva consulta [2]Ver todas las consultas [3]Salir")
          try {
            inputMenu = readln().toInt()
            if (inputMenu !in 1..3) {
              println("Ingresa el número correspondiente al tipo de consulta (1 - 2 ó 3).")
            }
          } catch (ex: NumberFormatException) {
            println("Ingresa solo números válidos (1 - 2 o 3), no letras.")
            inputMenu = 0
          }
        }
        else -> inputMenu = 3
      }
    } while(inputMenu != 3)
  }
}