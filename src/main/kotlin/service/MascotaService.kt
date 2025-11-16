package service

import model.Mascota
import model.Tutor
import utils.dosisRecomendada
import utils.proximaVacuna

class MascotaService {
  private fun asignarNombre(): String{
    print("Ingrese el nombre de la mascota: ")
    val nombre = readln().ifBlank{ null } ?: "Mascota sin nombre"
    return nombre
  }

  private fun asignarEspecie(): String{
    print("Especie mascota: ")
    val especie = readln().ifBlank { null } ?: "Especie no especificada"
    return especie
  }

  private fun asignarEdad(): Int{
    var edad: Int?
    do {
      print("Edad de la mascota: ")
      val inputEdad = readln()
      try {
        edad = inputEdad.toInt()
        if ( edad <= 0){
          println("La mascota debe tener una edad mayor a 0")
        }
      } catch (ex: NumberFormatException) {
        println("Debes ingresar un número")
        edad = null
      }
    } while (edad == null || edad <= 0)

    return edad
  }

  private fun asignarPeso(): Int{
    var peso: Int?
    do {
      print("Peso de la mascota (Kg): ")
      val inputPeso = readln()
      try {
        peso = inputPeso.toInt()
        if ( peso <= 0){
          println("El peso de la mascota debe ser mayor a 0")
        }
      } catch (ex: NumberFormatException) {
        println("Debes ingresar un número")
        peso = null
      }
    } while (peso == null || peso <= 0)
    return peso
  }

  private fun mensajeVacuna(edadMascota: Int, pesoMascota: Int){ // Recibe los parámetros
    println("")
    println("----------------------------------------")
    println("Basado en los datos de la mascota")
    when (edadMascota){
      in 0..1 -> println("Necesita su vacuna semestral.")
      in 2..6 -> println("Necesita su vacuna anual.")
      else -> println("La vacuna para la mascota es opcional.")
    }
    proximaVacuna(edadMascota)
    dosisRecomendada(pesoMascota, edadMascota)
    println("----------------------------------------")
    println("")
  }

  fun asignarMascota(tutor: Tutor): Mascota{
    val nombre = asignarNombre()
    val especie = asignarEspecie()
    val edad = asignarEdad()
    val peso = asignarPeso()

    mensajeVacuna(edad, peso) // Le pasa los datos ya obtenidos

    return Mascota(nombre, especie, edad, tutor)
  }
}