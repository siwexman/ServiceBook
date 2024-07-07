package com.example.servicebook.data

import java.io.Serializable

data class Car(
    val Name: String,
    val Engine: Double,
    val Oil: String,
    val TirePressure: Double, // in bar recommended 2,2 - 2,5 bar
    val FuelCapacity: Double,
    val RegistrationNnumbers: String,
    val Repairs: List<Repair> = mutableListOf(), // List of Repairs of specific car
    val Reminders: List<Reminder> = mutableListOf() // List of Reminders of specific car
) :Serializable {
    fun AddCar() {
        // Adds Car
    }

    fun RemoveCar() {
        // Removes Car
    }

    fun AddRepair(repair: Repair) {
        // Add Car
    }
}