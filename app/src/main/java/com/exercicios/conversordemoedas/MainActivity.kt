package com.exercicios.conversordemoedas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner

class MainActivity : AppCompatActivity() {
    lateinit var respostaUserA: String
    lateinit var respostaUserB: String
    lateinit var btnCalcular: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnCalcular = findViewById(R.id.btn_calcular)

        btnCalcular.setOnClickListener {
            calcularDolarParaReal(DolarReal())
            Log.d("Teste", calcularDolarParaReal(DolarReal()).toString())
        }

        respostaUserA = "Dolar"
        respostaUserB = "Real"
        }


    //Interface - Contrato do Cambio
    interface Cambio{
        fun calcular(valorCambio: Double): Double
    }


    //Class
    class DolarReal: Cambio{
        override fun calcular(valorCambio: Double): Double {
            return valorCambio * 5.42
        }
    }

    class DolarEuro: Cambio{
        override fun calcular(valorCambio: Double): Double {
            return valorCambio * 5.52
        }
    }

    //Função
    fun calcularDolarParaReal(cambio: Cambio): Double {
        val userDigitou = 50.0
        return cambio.calcular(userDigitou)
    }

}