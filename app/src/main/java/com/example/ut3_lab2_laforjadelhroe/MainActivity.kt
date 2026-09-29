package com.example.ut3_lab2_laforjadelhroe

import android.os.Bundle
import android.util.Log
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var nombreGuerrero: EditText
    private lateinit var selectorRaza: Spinner
    private lateinit var grupoFaccion: RadioGroup
    private lateinit var habilidadSigilo: CheckBox
    private lateinit var habilidadEspada: CheckBox
    private lateinit var botonForjar: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        // buscamos cada elemento que tenemos en el xml
        nombreGuerrero = findViewById(R.id.nombreGuerrero)
        selectorRaza = findViewById(R.id.selectorRaza)
        grupoFaccion = findViewById(R.id.grupoFaccion)
        habilidadSigilo = findViewById(R.id.habilidadSigilo)
        habilidadEspada = findViewById(R.id.habilidadEspada)
        botonForjar = findViewById(R.id.botonForjar)

        // esta funcion sirve para guardar el personaje en Logcat
        val guardarEnLog: (String) -> Unit = { datos ->
            Log.d("ForjaHeroe", datos)
        }

        // comprobamos los datos cuando pulsamos el boton
        botonForjar.setOnClickListener {

            // cogemos el nombre escrito
            val nombre = nombreGuerrero.text.toString()

            // cogemos la raza elegida
            val raza = selectorRaza.selectedItem.toString()

            // comprobamos que faccion ha seleccionado
            val idSeleccionado = grupoFaccion.checkedRadioButtonId

            var faccion = "Ninguna"

            if (idSeleccionado != -1) {

                val radioElegido =
                    findViewById<RadioButton>(idSeleccionado)

                faccion = radioElegido.text.toString()
            }

            // hacemos una lista para guardar las habilidades
            val habilidades = mutableListOf<String>()

            if (habilidadSigilo.isChecked) {
                habilidades.add("Sigilo")
            }

            if (habilidadEspada.isChecked) {
                habilidades.add("Combate con Espada")
            }

            // si no marca ninguna ponemos que no tiene habilidades
            if (habilidades.isEmpty()) {
                habilidades.add("Ninguna")
            }

            // juntamos toda la informacion del personaje
            val personaje = """
                Nombre: $nombre
                Raza: $raza
                Faccion: $faccion
                Habilidades: ${habilidades.joinToString(", ")}
            """.trimIndent()

            // guardamos el resultado en Logcat usando la lambda
            guardarEnLog(personaje)

            // mostramos el personaje por pantalla
            Toast.makeText(
                this,
                personaje,
                Toast.LENGTH_LONG
            ).show()
        }
    }
}