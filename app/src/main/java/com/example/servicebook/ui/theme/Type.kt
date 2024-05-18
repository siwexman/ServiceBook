package com.example.servicebook.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.servicebook.R

// Set of Material typography styles to start with

val Neuropolitical = FontFamily(
    Font(R.font.neuropolitical_rg)
)

val MuseoModerno = FontFamily(
    Font(R.font.museo_moderno_light),
    Font(R.font.museo_moderno_medium, FontWeight.Bold)
)


val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = Neuropolitical,
        fontWeight = FontWeight.Normal,
        fontSize = 30.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    titleLarge = TextStyle(
        fontFamily = MuseoModerno,
        fontWeight = FontWeight.Bold,
        fontSize = 26.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = MuseoModerno,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)