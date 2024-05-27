package com.example.servicebook.data

import androidx.annotation.StringRes

data class Car(
    val ID: Int,
    val Name: String,
    val Engine: Float,
    val Oil: String,
    val TirePressure: Float, // in bar recommended 2,2 - 2,5 bar
    val FuelCapacity: Float,
    val RegistrationNnumbers: String,
    val Repairs: List<Repairs>, // List of Repairs of specific car
){
    fun AddCar(){
        // Adds Car
    }

    fun RemoveCar(){
        // Removes Car
    }

    fun AddRepair(/* Repair.ID, Car.ID */){
        // Adds Repair to Car
    }
}