package model

import java.time.LocalDate

class Pedido(
  val cliente: Cliente,
  val medicamento: Medicamento,
  val total: Int,
  val inicioPromocion: LocalDate,
  val terminoPromocion: LocalDate,
) {

  operator fun component1(): Int = this.total
  operator fun component2(): String = this.cliente.nombre
  operator fun component3(): String = this.medicamento.nombre

  operator fun plus(otro: Pedido): Pedido {
    val nombreClienteCombinado = this.cliente + otro.cliente
    val nombreMedicamentoCombinado = this.medicamento + otro.medicamento
    //val nombreMedicamentoCombinado = "${this.nombreMedicamento} + ${otro.nombreMedicamento}"

    val clienteCombinado = Cliente(
      nombre = nombreClienteCombinado,
      telefono = this.cliente.telefono,
      email = this.cliente.email
    )

    val medicamentoCombinado = Medicamento(
      nombre = nombreMedicamentoCombinado,
      tipo = this.medicamento.tipo,
      precio = this.medicamento.precio,
    )

    return Pedido(
      cliente = clienteCombinado,
      medicamento = medicamentoCombinado,
      total = this.total + otro.total,
      inicioPromocion = inicioPromocion,
      terminoPromocion = terminoPromocion
    )
  }

  fun mostrarDatosCliente() {
    val (nombre, email, telefono) = cliente
    println("-------------------- DATOS DEL CLIENTE --------------------")
    println("Nombre: $nombre")
    println("Correo electrónico: $email")
    println("Teléfono: $telefono")
  }

  fun mostrarDetallePedido() {
    val (nombre, tipo, precio) = medicamento
    println("---------------------- DETALLE VENTA ----------------------")
    println("Medicamento: $nombre")
    println("Tipo: $tipo")
    println("Precio Normal: $$precio")
    println("Total a pagar: $$total")

  }
}