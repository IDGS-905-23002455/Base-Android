package com.example.idgs905baseandroid.Examen_Parcial1

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.idgs905baseandroid.R
import kotlin.math.pow

class Examen : AppCompatActivity() {

    private lateinit var spinner1: Spinner
    private lateinit var spinner2: Spinner
    private lateinit var spinner3: Spinner

    private lateinit var txtValor1: TextView
    private lateinit var txtValor2: TextView
    private lateinit var txtValor3: TextView

    private lateinit var txtOhm: TextView
    private lateinit var txtMaximo: TextView
    private lateinit var txtMinimo: TextView

    private lateinit var rbOro: RadioButton
    private lateinit var rbPlata: RadioButton

    private lateinit var btnCalcular: Button

    private val colores = arrayOf(
        "Negro",
        "Cafe",
        "Rojo",
        "Naranja",
        "Amarillo",
        "Verde",
        "Azul",
        "Violeta",
        "Gris",
        "Blanco"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_examen)

        inicializarControles()
        configurarSpinners()
        configurarEventos()
    }

    private fun inicializarControles() {

        spinner1 = findViewById(R.id.spinner1)
        spinner2 = findViewById(R.id.spinner2)
        spinner3 = findViewById(R.id.spinner3)

        txtValor1 = findViewById(R.id.txtValor1)
        txtValor2 = findViewById(R.id.txtValor2)
        txtValor3 = findViewById(R.id.txtValor3)

        txtOhm = findViewById(R.id.txtOhm)
        txtMaximo = findViewById(R.id.txtMaximo)
        txtMinimo = findViewById(R.id.txtMinimo)

        txtTolerancia = findViewById(R.id.txtTolerancia)

        rbOro = findViewById(R.id.rbOro)
        rbPlata = findViewById(R.id.rbPlata)

        btnCalcular = findViewById(R.id.btnCalcular)
    }

    private fun configurarSpinners() {

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            colores
        )

        adapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )

        spinner1.adapter = adapter
        spinner2.adapter = adapter
        spinner3.adapter = adapter
    }

    private fun configurarEventos() {

        spinner1.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    txtValor1.text = position.toString()
                    cambiarColor(txtValor1, position)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }

        spinner2.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    txtValor2.text = position.toString()
                    cambiarColor(txtValor2, position)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }

        spinner3.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {

                    val multiplicador =
                        10.0.pow(position.toDouble())

                    txtValor3.text =
                        multiplicador.toInt().toString()

                    cambiarColor(txtValor3, position)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
        rbOro.setOnClickListener {

            txtTolerancia.setBackgroundColor(
                Color.rgb(212, 175, 55)
            )

            txtTolerancia.text = "5%"
            txtTolerancia.setTextColor(Color.BLACK)
        }


        rbPlata.setOnClickListener {

            txtTolerancia.setBackgroundColor(
                Color.LTGRAY
            )

            txtTolerancia.text = "10%"
            txtTolerancia.setTextColor(Color.BLACK)
        }

        btnCalcular.setOnClickListener {
            calcularResistencia()
        }
    }

    private fun calcularResistencia() {

        val banda1 =
            spinner1.selectedItemPosition

        val banda2 =
            spinner2.selectedItemPosition

        val multiplicador =
            10.0.pow(
                spinner3.selectedItemPosition.toDouble()
            ).toInt()

        val resistencia =
            ((banda1 * 10) + banda2) * multiplicador

        val tolerancia =
            if (rbOro.isChecked) {
                0.05
            } else {
                0.10
            }

        val maximo =
            resistencia + (resistencia * tolerancia)

        val minimo =
            resistencia - (resistencia * tolerancia)

        txtOhm.text =
            "valor ohm      $resistencia"

        txtMaximo.text =
            "valor maximo   ${maximo.toInt()}"

        txtMinimo.text =
            "valor minimo   ${minimo.toInt()}"
    }

    private fun cambiarColor(
        textView: TextView,
        posicion: Int,

    ) {

        when (posicion) {

            0 -> {
                textView.setBackgroundColor(Color.BLACK)
                textView.setTextColor(Color.WHITE)
            }

            1 -> {
                textView.setBackgroundColor(
                    Color.rgb(139, 69, 19)
                )
                textView.setTextColor(Color.WHITE)
            }

            2 -> {
                textView.setBackgroundColor(Color.RED)
                textView.setTextColor(Color.BLACK)
            }

            3 -> {
                textView.setBackgroundColor(
                    Color.rgb(255, 165, 0)
                )
                textView.setTextColor(Color.BLACK)
            }

            4 -> {
                textView.setBackgroundColor(Color.YELLOW)
                textView.setTextColor(Color.BLACK)
            }

            5 -> {
                textView.setBackgroundColor(Color.GREEN)
                textView.setTextColor(Color.BLACK)
            }

            6 -> {
                textView.setBackgroundColor(Color.BLUE)
                textView.setTextColor(Color.WHITE)
            }

            7 -> {
                textView.setBackgroundColor(Color.MAGENTA)
                textView.setTextColor(Color.WHITE)
            }

            8 -> {
                textView.setBackgroundColor(Color.GRAY)
                textView.setTextColor(Color.BLACK)
            }

            9 -> {
                textView.setBackgroundColor(Color.WHITE)
                textView.setTextColor(Color.BLACK)
            }
        }
    }
    private lateinit var txtTolerancia: TextView
}