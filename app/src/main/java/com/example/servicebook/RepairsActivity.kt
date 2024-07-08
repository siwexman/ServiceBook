package com.example.servicebook

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.servicebook.data.Car
import com.example.servicebook.data.Repair
import com.example.servicebook.ui.theme.ServiceBookTheme
import com.example.servicebook.ui.theme.Shapes
import java.text.SimpleDateFormat
import java.util.Calendar

class RepairsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val car: Car? = intent.getSerializableExtra("car") as? Car

        setContent {

            ServiceBookTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (car != null) {
                        RepairsFull(car = car)
                    }
                }
            }
        }
    }
}

@SuppressLint("SimpleDateFormat")
@Composable
fun RepairItem(repair: Repair, modifier: Modifier = Modifier) {
    Card(
        shape = Shapes.medium,
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 0.dp, start = 10.dp, end = 10.dp, bottom = 10.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = modifier
                .fillMaxWidth()
                .padding(5.dp, 10.dp)
        ) {
            Text(
                text = repair.Name,
                style = MaterialTheme.typography.titleLarge,
                fontSize = 20.sp,
                modifier = modifier.padding(0.dp, 5.dp)
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier
                    .padding(10.dp)
                    .fillMaxWidth(0.75f)
            ) {
                val dateFormat = SimpleDateFormat("dd-MM-yyyy")
                RowData(
                    stringResourcesId = R.string.price,
                    text = repair.Price.toString(),
                    modifier = modifier
                )
                RowData(
                    stringResourcesId = R.string.repair_date,
                    text = dateFormat.format(repair.DateOfRepair).toString(),
                    modifier = modifier
                )
            }
        }
    }
}

@Composable
fun RowData(stringResourcesId: Int, text: String, modifier: Modifier) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(text = stringResource(id = stringResourcesId))
        Text(text = text)
    }
}

@Composable
fun SummaryFooter(repairs: List<Repair>, modifier: Modifier = Modifier) {
    fun TotalPrice(): Int {
        var sum = 0

        for (repair in repairs) {
            sum += repair.Price
        }
        return sum
    }

    Card(
        shape = Shapes.extraSmall,
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(
                text = "Summary",
                style = MaterialTheme.typography.titleLarge,
//                fontSize = 20.sp,
                modifier = modifier.padding(0.dp, 5.dp)
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween, modifier = modifier.fillMaxWidth()
            ) {
                Text(text = "Total Price:", fontSize = 18.sp)
                Text(text = TotalPrice().toString(), fontSize = 18.sp)
            }
        }
    }
}

@Composable
fun RepairsContent(car: Car) {
    val repairs = remember { mutableStateListOf(*car.Repairs.toTypedArray()) }

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxSize()
    ) {
        AddRepair(car, repairs)
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            items(repairs) { repair ->
                RepairItem(repair)
            }
        }
        SummaryFooter(repairs)
    }
}

@Composable
fun RepairsFull(car: Car) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth()
    ) {
        TopAppBar(MaterialTheme.shapes.extraSmall)
        RepairsContent(car)
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddRepair(car: Car, repairs: MutableList<Repair>, modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        shape = Shapes.extraSmall,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp, 5.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                stringResource(R.string.add_new_repair),
                style = MaterialTheme.typography.titleLarge,
                fontSize = 20.sp,
                modifier = Modifier.padding(5.dp)
            )
            if (expanded) {
                val (focusRequester) = FocusRequester.createRefs()
                var title by remember { mutableStateOf("") }
                var price by remember { mutableStateOf("") }

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    TextField(
                        value = title, onValueChange = { title = it },
                        label = { Text(text = stringResource(id = R.string.title)) },
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                        keyboardActions = KeyboardActions(onDone = { focusRequester.requestFocus() })
                    )
                    TextField(
                        value = price,
                        onValueChange = { price = it },
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done,
                            keyboardType = KeyboardType.Number
                        ),
                        label = { Text(stringResource(R.string.price)) },
                        modifier = modifier.focusRequester(focusRequester)
                    )
                    Spacer(modifier = Modifier.padding(5.dp))
                    Button(onClick = {
                        val priceInt = price.toInt()
                        val date = Calendar.getInstance().time

                        val newRepair = Repair(title, priceInt, date)

                        car.AddRepair(newRepair)
                        repairs.add(newRepair)
                        title = ""
                        price = ""
                        expanded = false
                    }) {
                        Text(text = stringResource(id = R.string.confirm))
                    }
                }
            }
            Row {
                Spacer(modifier = Modifier.weight(1f))
                ItemButton(
                    imageVector = if (expanded) Icons.Filled.Remove else Icons.Filled.Add,
                    expanded = expanded,
                    onClick = {
                        expanded = !expanded
                    },
                    modifier = modifier
                )
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    val car = Car("Yootasd", 1.3, "km20", 2.2, 40.3, "rsa23")
    ServiceBookTheme(darkTheme = true, dynamicColor = false) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .fillMaxWidth()
        ) {
            TopAppBar(MaterialTheme.shapes.extraSmall)
//            AddRepair()
//            Inputs(car = car)
        }
    }
}