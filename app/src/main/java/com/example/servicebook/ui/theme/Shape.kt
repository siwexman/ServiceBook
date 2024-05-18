package com.example.servicebook.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.servicebook.CarItem
import com.example.servicebook.TopAppBar

val Shapes = Shapes(
    small = RoundedCornerShape(50.dp),
    medium = RoundedCornerShape(
        bottomStart = 16.dp,
        topStart = 5.dp,
        topEnd = 5.dp,
        bottomEnd = 16.dp
    ),
    extraSmall = RoundedCornerShape(5.dp)
)

val BtnShapes = Shapes(
    small = RoundedCornerShape(0.dp),
    medium = RoundedCornerShape(10.dp)
)