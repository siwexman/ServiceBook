package com.example.servicebook.data

import java.sql.Date

data class Repairs(
    val ID: Int,
    val Name: String,
    val Price: Float,
    val DateOfRepair: Date
)