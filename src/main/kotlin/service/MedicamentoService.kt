package service

import model.Medicamento

class MedicamentoService {
  fun asignarMedicamento(opcionMedicammento: Int): Medicamento = when (opcionMedicammento) {
    1 -> Medicamento("kittydoll", "analgesico", 10000)
    2 -> Medicamento("k-9", "vitamina", 5000)
    else -> Medicamento("matapiojo", "desparasitario", 2000)
  }
}