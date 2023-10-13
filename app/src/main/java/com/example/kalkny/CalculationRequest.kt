package com.example.kalkny

import com.google.gson.annotations.SerializedName

data class CalculationRequest(
    @SerializedName("input1") val input1: Double,
    @SerializedName("input2") val input2: Double
)