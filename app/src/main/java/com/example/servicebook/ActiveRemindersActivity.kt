package com.example.servicebook

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.servicebook.data.Car
import com.example.servicebook.data.Reminder
import com.example.servicebook.ui.theme.ServiceBookTheme
import com.example.servicebook.ui.theme.Shapes
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Calendar
import java.util.Date

class ActiveRemindersActivity : ComponentActivity() {
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
                        RemindersContent(car = car)
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ReminderItem(
    title: String,
    date: Date,
    modifier: Modifier = Modifier
) {
    val dateFormat = SimpleDateFormat("dd-MM-yyyy")
    val currentDate = Date()
    val calendar = Calendar.getInstance()
    calendar.time = date
    calendar.add(Calendar.DAY_OF_YEAR, -7)
    val sevenDaysBefore = calendar.time

    val isExpiringSoon = currentDate.after(sevenDaysBefore) && currentDate.before(date)
    val backgroundColor = if (isExpiringSoon) Color.Red else MaterialTheme.colorScheme.background

    Card(
        shape = Shapes.medium,
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 0.dp, start = 10.dp, end = 10.dp, bottom = 10.dp)
            .background(backgroundColor)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = modifier
                .fillMaxWidth()
                .padding(5.dp, 10.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                fontSize = 20.sp,
                modifier = modifier.padding(0.dp, 5.dp)
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(10.dp, 5.dp)
            ) {
                Text(text = stringResource(R.string.expiration_date))
                Text(text = dateFormat.format(date).toString())
            }
            Row {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = stringResource(R.string.renew))
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddReminder(car: Car, reminders: MutableList<Reminder>, modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

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
                var date by remember { mutableStateOf("") }

                val datePickerDialog = DatePickerDialog(
                    context,
                    { _, selectedYear, selectedMonth, selectedDay ->
                        date = "$selectedDay-${selectedMonth + 1}-$selectedYear"
                    }, year, month, day
                )

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    TextField(
                        value = title, onValueChange = { title = it },
                        label = { Text(text = stringResource(id = R.string.title)) },
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                        keyboardActions = KeyboardActions(onDone = { focusRequester.requestFocus() })
                    )
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(5.dp)
                    ) {
                        Text(text = if (date.isEmpty()) "No date selected" else "Selected date: $date")
                        Button(onClick = {
                            datePickerDialog.show()
                        }) {
                            Text(text = stringResource(id = R.string.pick_date))
                        }
                    }
                    Spacer(modifier = Modifier.padding(5.dp))
                    Button(onClick = {
                        val expirationDate = SimpleDateFormat("dd-MM-yyyy").parse(date)
                        val reminder = Reminder(title, expirationDate)
                        reminders.add(reminder)
                        car.AddReminder(reminder)

                        title = ""
                        date = ""
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

@Composable
fun ScrollableRemindersList(car: Car, modifier: Modifier = Modifier) {
    val reminders = remember { mutableStateListOf(*car.Reminders.toTypedArray()) }
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.fillMaxSize()
    ) {
        AddReminder(car = car, reminders = reminders)
        LazyColumn(
            modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            items(reminders) { reminder ->
                ReminderItem(title = reminder.Title, date = reminder.Date)
            }
        }
    }
}

@Composable
fun RemindersContent(car: Car) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth()
    ) {
        TopAppBar(MaterialTheme.shapes.extraSmall)
        ScrollableRemindersList(car = car)
    }
}

@Composable
fun DatePickerSample() {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {

        }) {
            Text(text = "Show Date Picker")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    ServiceBookTheme(darkTheme = true, dynamicColor = false) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .fillMaxWidth()
        ) {
            TopAppBar(MaterialTheme.shapes.extraSmall)
            ReminderItem(
                "Title Reminder",
                Calendar.getInstance().time
            )
//            AddReminder(car = , reminders = )
            DatePickerSample()
        }
    }
}