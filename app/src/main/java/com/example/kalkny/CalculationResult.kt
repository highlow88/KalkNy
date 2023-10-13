package com.example.kalkny

import com.google.gson.annotations.SerializedName

data class CalculationResult(
    @SerializedName("result") val result: String
)
