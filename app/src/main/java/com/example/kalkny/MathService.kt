package com.example.kalkny

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface MathService {
    @POST("addition")
    fun add(@Body requestData: CalculationRequest): Call<CalculationResult>

    @POST("subtraction")
    fun subtract(@Body requestData: CalculationRequest): Call<CalculationResult>

    @POST("division")
    fun divide(@Body requestData: CalculationRequest): Call<CalculationResult>

    @POST("multiplication")
    fun multiply(@Body requestData: CalculationRequest): Call<CalculationResult>

    @POST("square_root")
    fun calculateSquareRoot(@Body requestData: SingleInputRequest): Call<CalculationResult>

    @POST("percentage")
    fun calculatePercentage(@Body requestData: CalculationRequest): Call<CalculationResult>

    @POST("pythagoras")
    fun calculatePythagoras(@Query("sideA") sideA: Double, @Query("sideB") sideB: Double): Call<CalculationResult>

    @POST("circlearea")
    fun calculateCircleArea(@Body requestData: CircleAreaRequest): Call<CalculationResult>

    @POST("cylindervolume")
    fun calculateCylinderVolume(@Body requestData: CylinderVolumeRequest): Call<CalculationResult>
}
