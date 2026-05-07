import kotlin.math.*

object Funciones {

    fun sumar(a: Double, b: Double): Double = a + b
    fun restar(a: Double, b: Double): Double = a - b
    fun multiplicar(a: Double, b: Double): Double = a * b
    fun dividir(a: Double, b: Double): Double =
        if (b != 0.0) a / b else Double.NaN
    fun potencia(base: Double, exponente: Double): Double {
        return base.pow(exponente)
    }

    fun raizCuadrada(numero: Double): Double {
        return sqrt(numero)
    }

    fun seno(grados: Double): Double {
        val radianes = Math.toRadians(grados)
        return sin(radianes)
    }

    fun factorial(n: Int): Double {
        if (n < 0) return Double.NaN
        var resultado = 1L
        for (i in 1..n) {
            resultado *= i
        }
        return resultado.toDouble()
    }

    fun gradosARadianes(grados: Double): Double {
        return Math.toRadians(grados)
    }

    // ── UTILIDAD: formato del resultado ──────────────
    fun formatearResultado(valor: Double): String {
        if (valor.isNaN()) return "Error"
        if (valor.isInfinite()) return if (valor > 0) "∞" else "-∞"
        // Redondear a 2 decimales
        val redondeado = Math.round(valor * 100) / 100.0
        return if (redondeado % 1 == 0.0) redondeado.toLong().toString()
        else redondeado.toString()
    }

    // ── EJECUTAR OPERACIÓN BINARIA ────────────────────
    fun calcularEstandar(a: Double, op: String, b: Double): Double =
        when (op) {
            "+"  -> sumar(a, b)
            "-"  -> restar(a, b)
            "×"  -> multiplicar(a, b)
            "÷"  -> dividir(a, b)
            "^" -> potencia(a, b)
            else -> Double.NaN
        }
}

fun main() {

    val base = 2.0
    val exponente = 3.0
    val numero = 16.0
    val angulo = 30.0
    val n = -65

    println("Potencia: $base^$exponente = ${Funciones.potencia(base, exponente)}")
    println("Raíz cuadrada: √$numero = ${Funciones.raizCuadrada(numero)}")
    println("Seno: sin($angulo°) = ${Funciones.seno(angulo)}")
    println("Factorial: $n! = ${Funciones.factorial(n)}")
    println("Grados a radianes: $angulo° = ${Funciones.gradosARadianes(angulo)} rad")
}