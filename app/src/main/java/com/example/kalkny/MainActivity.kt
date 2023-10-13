package com.example.kalkny

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var editTextNumber1: EditText
    lateinit var editTextNumber2: EditText
    lateinit var resultValue: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextNumber1 = findViewById(R.id.editTextNumber)
        editTextNumber2 = findViewById(R.id.editTextNumber2)
        resultValue = findViewById(R.id.resultValue)

        val buttonPlus: Button = findViewById(R.id.btnPlus)
        val buttonMinus: Button = findViewById(R.id.btnMinus)
        val buttonMultiply: Button = findViewById(R.id.btnMultiply)
        val buttonDivide: Button = findViewById(R.id.btnSlash)
        val buttonSqrt: Button = findViewById(R.id.btnSqr)
        val buttonPercent: Button = findViewById(R.id.btnProcent)
        val buttonPythagoras: Button = findViewById(R.id.btnPythagoras)
        val buttonCircle: Button = findViewById(R.id.btnCircle)
        val buttonVolume: Button = findViewById(R.id.btnVolym)

        buttonPlus.setOnClickListener { performCalculation("addition") }
        buttonMinus.setOnClickListener { performCalculation("subtraction") }
        buttonMultiply.setOnClickListener { performCalculation("multiplication") }
        buttonDivide.setOnClickListener { performCalculation("division") }
        buttonSqrt.setOnClickListener { performSingleInputCalculation("square_root") }
        buttonPercent.setOnClickListener { performCalculation("percentage") }
        buttonPythagoras.setOnClickListener { performCalculation("pythagoras") }
        buttonCircle.setOnClickListener { performCalculation("circlearea") }
        buttonVolume.setOnClickListener { performCalculation("cylindervolume") }
    }

    private fun performCalculation(operation: String) {
        val input1Str = editTextNumber1.text.toString()
        val input2Str = editTextNumber2.text.toString()

        if (input1Str.isEmpty() || input2Str.isEmpty()) {
            resultValue.text = "Please provide both inputs"
            return
        }

        val input1 = editTextNumber1.text.toString().toDouble()
        val input2 = editTextNumber2.text.toString().toDouble()

        val mathService = RetrofitClient.getMathService()

        val requestData = CalculationRequest(input1, input2)

        val call: Call<CalculationResult> = when (operation) {
            "addition" -> mathService.add(requestData)
            "subtraction" -> mathService.subtract(requestData)
            "division" -> mathService.divide(requestData)
            "multiplication" -> mathService.multiply(requestData)
            "percentage" -> mathService.calculatePercentage(requestData)
            "pythagoras" -> mathService.calculatePythagoras(input1, input2)
            "circlearea" -> {
                val circleAreaRequestData = CircleAreaRequest(input1)
                mathService.calculateCircleArea(circleAreaRequestData)
            }
            "cylindervolume" -> {
                val radius = input1
                val height = input2
                val cylinderVolumeRequest = CylinderVolumeRequest(radius, height)
                mathService.calculateCylinderVolume(cylinderVolumeRequest)
            }
            else -> throw IllegalArgumentException("Invalid operation")
        }




        call.enqueue(object : Callback<CalculationResult> {
            override fun onResponse(
                call: Call<CalculationResult>,
                response: Response<CalculationResult>
            ) {
                if (response.isSuccessful) {
                    val result = response.body()?.result ?: "Error"
                    Log.d("Retrofit", "Response successful: result = $result")
                    resultValue.text = result
                } else {
                    Log.e("Retrofit", "Response error: ${response.code()}")
                    resultValue.text = "Error"
                }
            }

            override fun onFailure(call: Call<CalculationResult>, t: Throwable) {
                Log.e("Retrofit", "Request failed: ${t.message}")
                resultValue.text = "Error"
            }
        })
    }

    private fun performSingleInputCalculation(operation: String) {
        val input1Str = editTextNumber1.text.toString()
        val input2Str = editTextNumber2.text.toString()

        if (input2Str.isNotEmpty()) {
            resultValue.text = "Please provide input 1 only"
            return
        }

        if (input1Str.isEmpty()) {
            resultValue.text = "Please provide input 1"
            return
        }

        val input1 = editTextNumber1.text.toString().toDouble()

        val mathService = RetrofitClient.getMathService()

        val requestData = SingleInputRequest(input1)

        val call: Call<CalculationResult> = when (operation) {
            "square_root" -> mathService.calculateSquareRoot(requestData)

            else -> throw IllegalArgumentException("Invalid operation")
        }




        call.enqueue(object : Callback<CalculationResult> {
            override fun onResponse(
                call: Call<CalculationResult>,
                response: Response<CalculationResult>
            ) {
                if (response.isSuccessful) {
                    val result = response.body()?.result ?: "Error"
                    Log.d("Retrofit", "Response successful: result = $result")
                    resultValue.text = result
                } else {
                    Log.e("Retrofit", "Response error: ${response.code()}")
                    resultValue.text = "Error"
                }
            }

            override fun onFailure(call: Call<CalculationResult>, t: Throwable) {
                Log.e("Retrofit", "Request failed: ${t.message}")
                resultValue.text = "Error"
            }
        })
    }
}
