package com.example.demo_calculadora

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

open class FragmentCientifica : FragmentCalculadora() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_cientifica, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // Inicializa botones estándar (0-9, +, -, *, /, =, C) heredados
        super.onViewCreated(view, savedInstanceState)

        // 1. Potencia xʸ
        view.findViewById<Button>(R.id.btn_potencia).setOnClickListener {
            seleccionarOperador("^")
        }

        // 2. Raíz cuadrada √x
        view.findViewById<Button>(R.id.btn_raiz).setOnClickListener {
            ejecutarOperacionInmediata { x -> Funciones.raizCuadrada(x) }
        }

        // 3. Seno sin(°)
        view.findViewById<Button>(R.id.btn_seno).setOnClickListener {
            ejecutarOperacionInmediata { x -> Funciones.seno(x) }
        }

        // 4. Factorial n!
        view.findViewById<Button>(R.id.btn_factorial).setOnClickListener {
            val actual = resu_display.text.toString().toIntOrNull()
            if (actual != null) {
                mostrarResultado(Funciones.factorial(actual))
                finalizarOperacion()
            }
        }

        // 5. Grados a Radianes
        view.findViewById<Button>(R.id.btn_grad_rad).setOnClickListener {
            ejecutarOperacionInmediata { x -> Funciones.gradosARadianes(x) }
        }
    }

    private fun ejecutarOperacionInmediata(operacion: (Double) -> Double) {
        val x = resu_display.text.toString().toDoubleOrNull()
        if (x != null) {
            mostrarResultado(operacion(x))
            finalizarOperacion()
        }
    }
    private fun finalizarOperacion() {
        operador = ""
        nuevaEntrada = true
    }
}
