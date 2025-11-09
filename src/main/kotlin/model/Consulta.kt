package model

class Consulta(
  val idConsulta:Int,
  val descripcion: String,
  val valorConsulta: Int,
  val hora: String,
  val mascota: Mascota,
  val nombreVeterinario: String
){

  fun calcularTotalConsulta(): Double {
    val descuentoPeluqueria = if(descripcion == "Peluqueria") 0.10 else 0.0
    val descuentoControl = if(descripcion == "Control") 0.15 else 0.0

    val descuento = if(descripcion == "Peluqueria") descuentoPeluqueria else descuentoControl
    val costoConsultaConDescuento = valorConsulta - (valorConsulta * descuento)
    return costoConsultaConDescuento
  }

  fun resumenConsulta() {
    val totalConsulta = calcularTotalConsulta()

    println("")
    println("-------------- RESUMEN CONSULTA Nº${idConsulta} ----")
    println("-------------------------------------------------")
    println("Tutor: ${mascota.tutor.nombre}")
    println("Teléfono: ${mascota.tutor.telefono} - Correo Electrónico: ${mascota.tutor.email}")
    println("Mascota: ${mascota.nombre} - ${mascota.especie} - ${mascota.edad} años")
    println("-------------------------------------------------")
    println("Motivo Consulta: $descripcion")
    println("Hora: $hora")
    println("Veterinario: ${nombreVeterinario}")
    println("-------------------------------------------------")
    println("El valor de la prestación es de: $$valorConsulta")
    when (descripcion) {
      "Peluqueria" -> println("Tienes un descuento de 10%")
      "Control" -> println("Tienes un descuento de 15%")
    }
    println("-------------------------------------------------")
    println("Total a pagar: $${totalConsulta.toInt()}")
  }
}