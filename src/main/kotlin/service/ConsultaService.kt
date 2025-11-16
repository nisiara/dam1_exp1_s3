package service

import model.Consulta
import model.Mascota
import model.Veterinario
import utils.revisarVeterinariosDisponibles

class ConsultaService {
  val veterinarios = listOf(
    Veterinario("Gabriel Chavez", listOf("lunes", "miércoles")),
    Veterinario("Humberto Velez", listOf("martes", "jueves")),
    Veterinario("Victor Delgado", listOf("viernes", "sábado"))
  )

  fun asignarTipo(): String{
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
    return descripcion
  }

  fun asignarHoras(): String {
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

    return inputHora
  }

  fun asignarValor(tipoConsulta: String): Int {
    val valor = when (tipoConsulta) {
      "Peluqueria" -> 15000
      "Control" -> 10000
      else -> 10000
    }
    return valor
  }

  val veterinario = revisarVeterinariosDisponibles(veterinarios)


  var id = 1
  fun asignarConsulta(mascota: Mascota,): Consulta{
    val tipo = asignarTipo()
    val valor = asignarValor(tipo)
    val hora = asignarHoras()
    val consulta = Consulta(id++, tipo, valor, hora, mascota, veterinario )
    return consulta
  }
}