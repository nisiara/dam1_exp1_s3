package service

import model.Tutor
import utils.formatearTelefono
import utils.validarInput

class TutorService {

  private fun asignarNombre(): String{
    print("Nombre del tutor: ")
    val nombre = readln().ifBlank{ null } ?: "Tutor desconocido"
    return nombre
  }

  private fun asignarTelefono(): String{
    val telefono = validarInput(
      "Teléfono de contacto (número de 12 dígitos) ",
      "telefono",
      "El teléfono debe tener los 12 dígitos. Ingreslo nuevamente."
    )
    val telefonoFormateado = formatearTelefono(telefono)
    return telefonoFormateado
  }

  private fun asignarEmail(): String{
    val email = validarInput(
      "Correo electrónico: ",
      "email",
      "El correo debe tener el formato correcto. @ y dominio."
    )
    return email
  }

  fun asignarTutor(): Tutor {
    val nombre = asignarNombre()
    val telefono = asignarTelefono()
    val email = asignarEmail()
    return Tutor(nombre, telefono, email)
  }

  fun revisarTutor(tutor: Tutor, listaTutores: MutableSet<Tutor>){

    val listaTutorAgregado = listaTutores.add(tutor)
    if (!listaTutorAgregado) {
      println("")
      println("**** [AVISO] El Tutor ya esta registrado ****.")
    }
  }
}