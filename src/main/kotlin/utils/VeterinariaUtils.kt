package utils

import model.Veterinario
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

fun dosisRecomendada(pesoMascota: Int, edadMascota: Int) {
  var dosisBase = 5
  if (edadMascota in 0..3) {
    when (pesoMascota) {
      in 0..0 -> dosisBase = 2
      in 1..2 -> dosisBase = 3
      in 2..3 -> dosisBase = 4
      else -> dosisBase
    }
  } else if (edadMascota in 4..6) {
    when (pesoMascota) {
      in 3..4 -> dosisBase = 5
      in 5..6 -> dosisBase = 6
      else -> dosisBase *= 2
    }
  } else {
    dosisBase *= 3
  }
  println("La dosis recomendada es ${dosisBase}ml.")
}

fun proximaVacuna(edadMascota: Int) {
  val fechaHoy = LocalDate.now()
  val formatoFecha = DateTimeFormatter.ofPattern("dd-MM-yyyy")
  val fechaProximaVacuna = when (edadMascota) {
    in 0..1 -> fechaHoy.plusMonths(6).format(formatoFecha)
    else -> fechaHoy.plusMonths(12).format(formatoFecha)
  }
  println("La próxima vacuna le correspondería el: ${fechaProximaVacuna}.")
}

fun revisarVeterinariosDisponibles(veterinarios: List<Veterinario>): String {
  val hoy = LocalDate.now()
  val nombreDiaHoy = hoy.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.forLanguageTag("es")).lowercase()

  val veterinariosDeTurno = veterinarios.filter { veterinario -> veterinario.turno.contains(nombreDiaHoy) }
  val nombreVeterinario = veterinariosDeTurno.firstOrNull()?.nombre ?: "No hay veterinarios disponibles."

  return nombreVeterinario
}

fun formatearTelefono(telefono: String): String {
  val codigoPais = telefono.substring(0, 2)
  val codigoArea = telefono.substring(2, 5)
  val parteCentral = telefono.substring(5, 8)
  val parteFinal = telefono.substring(8, 12)
  return "+$codigoPais ($codigoArea) $parteCentral-$parteFinal"
}

fun validarInput(prompt: String, type: String, mensajeError: String): String {
  val regex = when (type.lowercase()) {
    "telefono" -> Regex("^\\d{12}$")
    "email" -> Regex("^.+@.+\\..+$")
    else -> Regex(".+")
  }

  var input: String
  do {
    print(prompt)
    input = readln()
    if (!input.matches(regex) || input.isEmpty()) {
      println(mensajeError)
    }
  } while (!input.matches(regex) || input.isEmpty())

  return input
}

