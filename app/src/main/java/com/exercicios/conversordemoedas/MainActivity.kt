package com.exercicios.conversordemoedas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    lateinit var respostaUserA: EditText
    lateinit var respostaUserB: EditText
    lateinit var valorDigitado: EditText
    lateinit var btnCalcular: Button

    var opcaoUserA: Int = 0
    var opcaoUserB: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        respostaUserA = findViewById(R.id.opcao_a)
        respostaUserB = findViewById(R.id.opcao_b)
        valorDigitado = findViewById(R.id.valor_digitado)

//        opcaoUserA = Integer.parseInt(respostaUserA.toString())
//        opcaoUserB = Integer.parseInt(respostaUserB.toString())

        btnCalcular = findViewById(R.id.btn_calcular)

        btnCalcular.setOnClickListener {
            calcularDolarParaReal()

            Log.d("Calculo", calcularDolarParaReal().toString())
        }
    }


    //Interface - Contrato do Cambio
    interface Cambio {
        fun calcular(valorCambio: Double): Double
    }


    //Class
    class DolarReal : Cambio {
        override fun calcular(valorCambio: Double): Double {
            return valorCambio * 5.34
        }
    }

    class DolarEuro : Cambio {
        override fun calcular(valorCambio: Double): Double {
            return valorCambio * 0.98
        }
    }

    class RealDolar : Cambio{
        override fun calcular(valorCambio: Double): Double {
            return valorCambio * 0.18
        }
    }

    class EuroDolar : Cambio{
        override fun calcular(valorCambio: Double): Double {
            return valorCambio * 1.02
        }
    }

    class RealEuro : Cambio{
        override fun calcular(valorCambio: Double): Double {
            return valorCambio * 0.18
        }
    }

    class EuroReal : Cambio{
        override fun calcular(valorCambio: Double): Double {
            return valorCambio * 5.43
        }

    }

    //Função
    fun calcularDolarParaReal(): Double {
        val userDigitou = 50.0
        opcaoUserA = 6
        return when (opcaoUserA) {
            1 -> DolarReal().calcular(userDigitou)
            2 -> RealDolar().calcular(userDigitou)
            3 -> EuroDolar().calcular(userDigitou)
            4 -> DolarEuro().calcular(userDigitou)
            5 -> RealEuro().calcular(userDigitou)
            6 -> EuroReal().calcular(userDigitou)
            else -> {
                return 0.0;
            }
        }
    }
}