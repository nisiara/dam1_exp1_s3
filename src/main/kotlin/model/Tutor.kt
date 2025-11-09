package model

class Tutor(
  nombre: String,
  val telefono: String,
  val email: String
): Usuario(nombre) {}