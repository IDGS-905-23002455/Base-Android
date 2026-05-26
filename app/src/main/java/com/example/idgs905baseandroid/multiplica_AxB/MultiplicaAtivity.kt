package com.example.idgs905baseandroid.multiplica_AxB

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.idgs905baseandroid.R

class MultiplicaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_multiplicacion)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etNumeroA = findViewById<EditText>(R.id.etNumeroA)
        val etNumeroB = findViewById<EditText>(R.id.etNumeroB)
        val btnCalcular = findViewById<Button>(R.id.btnCalcular)

        btnCalcular.setOnClickListener {
            val txtA = etNumeroA.text.toString()
            val txtB = etNumeroB.text.toString()

            if (txtA.isEmpty() || txtB.isEmpty()) {
                Toast.makeText(this, "Por favor llena ambos campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val numA = txtA.toInt()
            val numB = txtB.toInt()

            val resultadoFinal = numA * numB

            val sumasLista = mutableListOf<String>()
            for (i in 1..numB) {
                sumasLista.add(numA.toString())
            }
            val cadenaSumas = sumasLista.joinToString("+")

            val mensajeEnviar = "La multiplicacion de AxB es $cadenaSumas =$resultadoFinal"

            val intent = Intent(this, ResSumaActivity::class.java)
            intent.putExtra("EXTRA_RESULTADO", mensajeEnviar)
            startActivity(intent)
        }
    }
}