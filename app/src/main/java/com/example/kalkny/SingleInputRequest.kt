package com.example.kalkny

import com.google.gson.annotations.SerializedName

data class SingleInputRequest(
    @SerializedName("input1") val input1: Double
)
