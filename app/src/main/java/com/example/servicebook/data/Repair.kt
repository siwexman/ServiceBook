package com.example.servicebook.data

import java.io.Serializable
import java.sql.Date

data class Repair(
    val Name: String,
    val Price: Int,
    val DateOfRepair: java.util.Date
) : Serializable