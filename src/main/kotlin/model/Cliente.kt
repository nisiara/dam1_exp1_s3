package model

class Cliente(
  nombre: String,
  val telefono: String,
  val email: String
): Usuario(nombre) {

  operator fun component1(): String = this.nombre
  operator fun component2(): String = this.email
  operator fun component3(): String = this.telefono

  operator fun plus(other: Cliente): String = "${this.nombre} + ${other.nombre}"

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (other !is Cliente) return false
    return this.nombre == other.nombre && this.email == other.email
  }

  override fun hashCode(): Int {
    var result = nombre.hashCode()
    result = 31 * result + email.hashCode()
    return result
  }
}