package com.exercicios.conversordemoedas

import android.os.Bundle
import android.text.TextUtils.*
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    lateinit var respostaUserA: Spinner
    lateinit var respostaUserB: Spinner
    lateinit var valorDigitado: EditText
    lateinit var respostaAoUser: TextView
    lateinit var btnCalcular: Button

    var valorDigitadoUser: Double = 0.0
    var opcaoUser: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pegaView()
        defineSpinner()

        btnCalcular.setOnClickListener {

            mensagemNaoDigitado()

            spinnerSelecionado()

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

    class RealDolar : Cambio {
        override fun calcular(valorCambio: Double): Double {
            return valorCambio * 0.18
        }
    }

    class EuroDolar : Cambio {
        override fun calcular(valorCambio: Double): Double {
            return valorCambio * 1.02
        }
    }

    class RealEuro : Cambio {
        override fun calcular(valorCambio: Double): Double {
            return valorCambio * 0.18
        }
    }

    class EuroReal : Cambio {
        override fun calcular(valorCambio: Double): Double {
            return valorCambio * 5.43
        }

    }

    //Função
    fun pegaView() {
        respostaUserA = findViewById(R.id.spinner_a)
        respostaUserB = findViewById(R.id.spinner_b)
        valorDigitado = findViewById(R.id.valor_digitado)
        btnCalcular = findViewById(R.id.btn_calcular)
        respostaAoUser = findViewById(R.id.resposta_para_user)
    }

    fun calcularMoedaParaMoeda(): Double {
        val userDigitou = valorDigitadoUser
        opcaoUser = spinnerSelecionado()
        return when (opcaoUser) {
            1 -> DolarReal().calcular(userDigitou)
            2 -> RealDolar().calcular(userDigitou)
            3 -> EuroDolar().calcular(userDigitou)
            4 -> DolarEuro().calcular(userDigitou)
            5 -> RealEuro().calcular(userDigitou)
            6 -> EuroReal().calcular(userDigitou)
            else -> {
                return 1.0;
            }
        }
    }

    fun defineSpinner() {

        val spinnerA = respostaUserA
        val spinnerB = respostaUserB

        ArrayAdapter.createFromResource(
            this,
            R.array.moedas_lista_A,
            R.layout.spinner_text
        ).also { adapter ->
            adapter.setDropDownViewResource(R.layout.spinner_drop)
            spinnerA.adapter = adapter
        }

        ArrayAdapter.createFromResource(
            this,
            R.array.moedas_lista_A,
            R.layout.spinner_text
        ).also { adapter ->
            adapter.setDropDownViewResource(R.layout.spinner_drop)
            spinnerB.adapter = adapter
        }
    }

    fun spinnerSelecionado(): Int {

        val spinnerA = respostaUserA
        val spinnerB = respostaUserB

        val spinnerSelecionadoA = spinnerA.selectedItem
        val spinnerSelecionadoB = spinnerB.selectedItem

        var respostaSpinner: Int = 0

        if (spinnerSelecionadoA.equals("Dolar") && spinnerSelecionadoB.equals("Real")) {
            respostaSpinner = 1
            return respostaSpinner
        }

//        spinnerA.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
//
//            override fun onItemSelected(spinnerA: AdapterView<*>, v: View, arg2: Int, arg3: Long) {
//            }
//            override fun onNothingSelected(arg0: AdapterView<*>?) {
//            }
//        })

        return 0
    }

    fun mensagemNaoDigitado(){
        if (valorDigitado.text.toString().trim().isNotEmpty()){
            valorDigitadoUser = valorDigitado.text.toString().toDouble()
            respostaAoUser.setText(calcularMoedaParaMoeda().toString())
        }else{
            Toast.makeText(this, "Digite um valor a ser convertido", Toast.LENGTH_SHORT).show()
        }
    }
}