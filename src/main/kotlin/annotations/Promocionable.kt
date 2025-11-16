package annotations

@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@Repeatable
annotation class Promocionable(
  val nombre: String,
  val tipo: String,
  val descuento: Double
) {}