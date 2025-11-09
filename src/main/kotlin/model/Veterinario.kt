package model

import kotlin.collections.List

class Veterinario(
  nombre: String,
  val turno: List<String>
): Usuario(nombre) {}