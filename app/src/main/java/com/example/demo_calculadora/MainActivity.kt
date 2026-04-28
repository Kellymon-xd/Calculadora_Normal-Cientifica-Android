package com.example.demo_calculadora

import android.os.Bundle
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var switchModo: Switch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        switchModo = findViewById(R.id.switch_modo)

        // Carga la calculadora estándar al iniciar
        if (savedInstanceState == null) {
            cargarFragmento(esCientifica = false)
        }

        // Cada vez que el usuario mueve el Switch, cambia el fragmento
        switchModo.setOnCheckedChangeListener { _, isChecked ->
            cargarFragmento(esCientifica = isChecked)
        }
    }

    private fun cargarFragmento(esCientifica: Boolean) {
        val fragmento = if (esCientifica) FragmentCientifica() else FragmentCalculadora()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragmento)
            .commit()
    }
}