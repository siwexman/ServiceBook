package com.example.servicebook.data

import java.io.Serializable

data class Car(
    val Name: String,
    val Engine: Double,
    val Oil: String,
    val TirePressure: Double, // in bar recommended 2,2 - 2,5 bar
    val FuelCapacity: Double,
    val RegistrationNnumbers: String,
    var Repairs: List<Repair> = mutableListOf(), // List of Repairs of specific car
    var Reminders: List<Reminder> = mutableListOf() // List of Reminders of specific car
) : Serializable {
    fun AddRepair(repair: Repair) {
        // Add Car
        val newList = this.Repairs + repair
        this.Repairs = newList
    }

    fun AddReminder(reminder: Reminder) {
        val newList = this.Reminders + reminder
        this.Reminders = newList
    }
}