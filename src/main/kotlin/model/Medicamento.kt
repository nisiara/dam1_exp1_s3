package model

@Promocionable("10%", "analgesico", 0.1)
@Promocionable("50%", "vitamina", 0.5)
open class Medicamento(
  val nombre: String,
  val tipo: String,
  val precio: Int,
) {

  operator fun component1(): String = this.nombre
  operator fun component2(): String = this.tipo
  operator fun component3(): Int = this.precio

  operator fun plus(other: Medicamento): String = "${this.nombre} + ${other.nombre}"

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (other !is Medicamento) return false
    return this.nombre == other.nombre && this.tipo == other.tipo
  }

  override fun hashCode(): Int {
    var result = nombre.hashCode()
    result = 31 * result + tipo.hashCode()
    return result
  }
}