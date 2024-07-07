package com.example.servicebook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.servicebook.data.Car
import com.example.servicebook.ui.theme.ServiceBookTheme
import kotlin.math.abs


class AddNewCarActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ServiceBookTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxSize()
                            .fillMaxWidth()
                    ) {
                        TopAppBar(MaterialTheme.shapes.extraSmall)
                        AddingNewCar()
                    }
                }
            }
        }
    }
}


@Composable
fun AddingNewCar(modifier: Modifier = Modifier) {
    var name by remember { mutableStateOf("") }
    var engine by remember { mutableStateOf("") }
    var oil by remember { mutableStateOf("") }
    var tirePressure by remember { mutableStateOf("") }
    var fuelCapacity by remember { mutableStateOf("") }
    var registrationNumber by remember { mutableStateOf("") }
    var car by remember { mutableStateOf<Car?>(null) }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        TextField(
            value = name,
            onValueChange = {
                name = it
            },
            label = { Text(stringResource(R.string.car_name)) },
            placeholder = { Text(text = "Name your Car") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        )

        TextField(
            value = engine,
            onValueChange = {
                engine = it
            },
            label = { Text(stringResource(R.string.engine)) },
            placeholder = { Text(text = "Value in liters") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        )
        TextField(
            value = oil,
            onValueChange = {
                oil = it
            },
            label = { Text(stringResource(R.string.oil)) },
            placeholder = { Text(text = "Engine oil markings") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        )
        TextField(
            value = tirePressure,
            onValueChange = {
                tirePressure = it
            },
            label = { Text(stringResource(R.string.tire_pressure)) },
            placeholder = { Text(text = "Recommended in bars: 2,2 - 2,5 bar") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        )
        TextField(
            value = fuelCapacity,
            onValueChange = {
                fuelCapacity = it
            },
            label = { Text(stringResource(R.string.fuel_capacity)) },
            placeholder = { Text(text = "Value in liters") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        )
        TextField(
            value = registrationNumber,
            onValueChange = {
                registrationNumber = it
            },
            label = { Text(stringResource(R.string.registration_numbers)) },
            placeholder = { Text(text = "Plate numbers") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        )

        Button(
            modifier = Modifier.padding(top = 5.dp),
            onClick = {
                val carEngine = engine.toDoubleOrNull()?.let { abs(it) }
                val carTirePressure = abs(tirePressure.toDouble())
                val carFuelCapacity = abs(fuelCapacity.toDouble())
                val plateNumbers = registrationNumber.uppercase()



                if (carEngine != null) {
                    car = Car(
                        name,
                        carEngine,
                        oil,
                        carTirePressure,
                        carFuelCapacity,
                        plateNumbers
                    )
                    // clear inputs
                    name = ""
                    engine = ""
                    oil = ""
                    tirePressure = ""
                    fuelCapacity = ""
                    registrationNumber = ""
                }
            }
        ) {
            Text(text = stringResource(R.string.add_new_car))
        }

        car?.let {
            Text(text = "Created Car: $it", modifier = Modifier.padding(top = 16.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ServiceBookTheme(darkTheme = true, dynamicColor = false) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .fillMaxWidth()
        ) {
            TopAppBar(MaterialTheme.shapes.extraSmall)
            AddingNewCar()
        }
    }
}