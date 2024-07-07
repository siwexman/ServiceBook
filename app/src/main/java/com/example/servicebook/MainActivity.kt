package com.example.servicebook

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.servicebook.data.Car
import com.example.servicebook.ui.theme.ServiceBookTheme
import com.example.servicebook.ui.theme.Shapes
import android.content.Context
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.platform.LocalContext
import com.example.servicebook.data.Repair
import java.io.Serializable
import java.text.SimpleDateFormat


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
//        List<Car> from database
        val repairs = listOf(
            Repair(
                "Klocki",
                200.0,
                java.sql.Date(SimpleDateFormat("dd-MM-yyyy").parse("21-11-2024").time)
            ),
            Repair(
                "Olej",
                300.0,
                java.sql.Date(SimpleDateFormat("dd-MM-yyyy").parse("21-11-2024").time)
            ),
            Repair(
                "Sprzeglo",
                1000.0,
                java.sql.Date(SimpleDateFormat("dd-MM-yyyy").parse("21-11-2024").time)
            )
        )
        val car1 = Car("Toyota", 1.4, "KM20", 2.2, 40.0, "RZ9040E", repairs)
        val car2 = Car("BMW", 1.4, "KM20", 2.2, 40.0, "RZ9SAFJE")
        val car3 = Car("Audi", 1.4, "KM20", 2.2, 40.0, "KK349SUI")
        val cars = listOf(car1, car2, car3)


        fun <T> navigate(targetActivity: Class<T>) {
            val navigate = Intent(this, targetActivity)
            startActivity(navigate)
        }

        super.onCreate(savedInstanceState)
        setContent {
            ServiceBookTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .fillMaxWidth()
                    ) {
                        TopAppBar(MaterialTheme.shapes.extraSmall)
                        ScrollableList(cars = cars, footerContent = {
                            AddItem(
                                onClick = { navigate(AddNewCarActivity::class.java) },
                                addNewItem = "car"
                            )
                        })
                    }
                }
            }
        }
    }
}

@Composable
fun TopAppBar(shape: CornerBasedShape, modifier: Modifier = Modifier) {
    Card(
        shape = shape,
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(R.drawable.service_book_logo),
                contentDescription = null,
                modifier = Modifier,
            )
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.displayLarge,
                modifier = Modifier.padding(10.dp)
            )
        }
    }
}


@Composable
fun CarItem(
    car: Car,
    context: Context,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        shape = Shapes.medium,
        modifier = Modifier.padding(10.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioNoBouncy,
                    stiffness = Spring.StiffnessMedium
                )
            )
        ) {
            Text(
                text = car.Name,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(5.dp, 5.dp, 5.dp, 0.dp)
            )
            Text(
                text = car.RegistrationNnumbers,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )

            if (expanded) {
                ExpandedOptions(activeOnClick =
                {
                    val intent = Intent(context, ActiveRemindersActivity::class.java)
                    intent.putExtra("car", car as Serializable)
                    context.startActivity(intent)
                }, repairsOnClick = {
                    val intent = Intent(context, RepairsActivity::class.java)
                    intent.putExtra("car", car as Serializable)
                    context.startActivity(intent)
                })
            }
            Row {
                Spacer(modifier = Modifier.weight(1f))
                ItemButton(
                    imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
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
fun AddItem(onClick: () -> Unit, addNewItem: String, modifier: Modifier = Modifier) {
    Card(
        shape = Shapes.extraSmall,
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 0.dp, start = 10.dp, end = 10.dp, bottom = 10.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (addNewItem == "car") {
                Text(
                    text = stringResource(id = R.string.add_new_car),
                    style = MaterialTheme.typography.titleLarge,
                    fontSize = 20.sp,
                    modifier = modifier.padding(top = 5.dp)
                )
            } else if (addNewItem == "reminder") {
                Text(
                    text = stringResource(id = R.string.add_new_reminder),
                    style = MaterialTheme.typography.titleLarge,
                    fontSize = 20.sp,
                    modifier = modifier.padding(top = 5.dp)
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.weight(1f))
                ItemButton(
                    imageVector = Icons.Filled.Add,
                    onClick = onClick,
                    modifier = modifier
                )
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
fun ItemButton(
    onClick: () -> Unit,
    expanded: Boolean = false,
    imageVector: ImageVector,
    modifier: Modifier,
) {
    IconButton(
        onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
fun ExpandedOptions(
    activeOnClick: () -> Unit,
    repairsOnClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Absolute.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(0.dp, 10.dp)
    ) {
        Button(onClick = activeOnClick) {
            Text(
                text = stringResource(id = R.string.active),
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold
            )
        }
        Button(onClick = repairsOnClick) {
            Text(
                text = stringResource(id = R.string.repairs),
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun ScrollableList(
    cars: List<Car>,
    footerContent: @Composable () -> Unit,
) {
    val context = LocalContext.current
    Column {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            // foreach car in cars => CarItem
            items(cars) { car -> CarItem(car = car, context = context) }
        }
        footerContent()
    }
}

@Preview(showBackground = true)
@Composable
fun ServiceBookPreview() {
    val car1 = Car("Toyota", 1.4, "KM20", 2.2, 40.0, "RZ9040E")
    val car2 = Car("VW", 1.4, "KM20", 2.2, 40.0, "RZ9040E")
    val car3 = Car("Audi", 1.4, "KM20", 2.2, 40.0, "RZ9040E")
    val cars = listOf(car1, car2, car3)

    ServiceBookTheme(darkTheme = true, dynamicColor = false) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .fillMaxWidth()
        ) {
            TopAppBar(MaterialTheme.shapes.extraSmall)
            ScrollableList(cars = cars) { AddItem(onClick = { /*TODO*/ }, addNewItem = "car") }

        }
    }
}