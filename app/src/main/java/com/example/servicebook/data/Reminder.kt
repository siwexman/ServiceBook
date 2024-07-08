package com.example.servicebook.data

import java.io.Serializable
import java.util.Calendar
import java.util.Date

class Reminder(val Title: String, var Date: java.util.Date) : Serializable {
    fun AddOneYear(): Date {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.YEAR, 1)
        this.Date = calendar.time
        return this.Date
    }
}