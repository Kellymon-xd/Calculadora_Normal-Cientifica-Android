package com.example.demo_calculadora

/** KELLY BEITIA, 8-1023-152 (COORDINADORA)
 * LEONARDO CASTRO, 8-1032-1264
 * JORGE SARMIENTO, 3-757-1758
 * MARIAM HARRIS 1-756-2331
 **/

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