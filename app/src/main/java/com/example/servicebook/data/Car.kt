package com.example.servicebook.data

data class Car(
    val Id: Int,
    val Name: String,
    val Engine: Float,
    val Oil: String,
    val TirePressure: Float, // in bar recommended 2,2 - 2,5 bar
    val FuelCapacity: Float,
    val RegistrationNnumbers: String,
    val Repairs: MutableList<Repair> = mutableListOf() // List of Repairs of specific car
) {
    fun AddCar() {
        // Adds Car
    }

    fun RemoveCar() {
        // Removes Car
    }

    fun AddRepair(repair: Repair) {
        Repairs.add(repair)
    }
}