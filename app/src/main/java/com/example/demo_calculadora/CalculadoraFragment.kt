package com.example.demo_calculadora

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

open class FragmentCalculadora : Fragment() {

    protected lateinit var resu_display: TextView
    protected var primerNumero: Double = 0.0
    protected var operador: String = ""
    protected var nuevaEntrada: Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_calculadora, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        resu_display = view.findViewById(R.id.resu_display)

        // Botones numéricos
        val numeros = mapOf(
            R.id.btn_0 to "0", R.id.btn_1 to "1", R.id.btn_2 to "2",
            R.id.btn_3 to "3", R.id.btn_4 to "4", R.id.btn_5 to "5",
            R.id.btn_6 to "6", R.id.btn_7 to "7", R.id.btn_8 to "8",
            R.id.btn_9 to "9"
        )
        numeros.forEach { (id, valor) ->
            view.findViewById<Button>(id).setOnClickListener { ingresarDigito(valor) }
        }

        // Punto decimal
        view.findViewById<Button>(R.id.btn_punto)
            .setOnClickListener { ingresarPunto() }

        // Botón +/- para negativos
        view.findViewById<Button>(R.id.btn_negativo)
            .setOnClickListener { toggleNegativo() }

        // Operadores
        view.findViewById<Button>(R.id.btn_sum).setOnClickListener { seleccionarOperador("+") }
        view.findViewById<Button>(R.id.btn_res).setOnClickListener { seleccionarOperador("-") }
        view.findViewById<Button>(R.id.btn_mul).setOnClickListener { seleccionarOperador("×") }
        view.findViewById<Button>(R.id.btn_div).setOnClickListener { seleccionarOperador("÷") }

        // Igual y Clear
        view.findViewById<Button>(R.id.btn_igual).setOnClickListener { calcular() }
        view.findViewById<Button>(R.id.btn_clear).setOnClickListener { limpiar() }
    }

    protected fun ingresarDigito(digito: String) {
        if (nuevaEntrada) {
            resu_display.text = digito
            nuevaEntrada = false
        } else {
            val actual = resu_display.text.toString()
            resu_display.text = if (actual == "0") digito else actual + digito
        }
    }

    private fun ingresarPunto() {
        if (nuevaEntrada) {
            resu_display.text = "0."
            nuevaEntrada = false
            return
        }
        if (!resu_display.text.contains(".")) {
            resu_display.append(".")
        }
    }

    private fun toggleNegativo() {
        val actual = resu_display.text.toString()
        if (actual == "0" || actual == "Error") return
        resu_display.text = if (actual.startsWith("-")) actual.substring(1)
        else "-$actual"
    }

    protected open fun seleccionarOperador(op: String) {
        if (!nuevaEntrada && operador.isNotEmpty()) calcular()
        primerNumero = resu_display.text.toString().toDoubleOrNull() ?: 0.0
        operador = op
        nuevaEntrada = true
    }

    protected open fun calcular() {
        if (operador.isEmpty()) return
        val segundoNumero = resu_display.text.toString().toDoubleOrNull() ?: return
        val resultado = Funciones.calcularEstandar(primerNumero, operador, segundoNumero)
        mostrarResultado(resultado)
        operador = ""
        nuevaEntrada = true
    }

    protected fun mostrarResultado(resultado: Double) {
        resu_display.text = Funciones.formatearResultado(resultado)
        primerNumero = resultado
    }

    protected fun limpiar() {
        resu_display.text = "0"
        primerNumero = 0.0
        operador = ""
        nuevaEntrada = true
    }
}