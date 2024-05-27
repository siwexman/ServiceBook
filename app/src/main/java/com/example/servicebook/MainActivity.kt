package com.example.servicebook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.servicebook.ui.theme.ServiceBookTheme
import com.example.servicebook.ui.theme.Shapes

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ServiceBookTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    ServiceBookPreview()
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
fun CarItem(modifier: Modifier = Modifier) {
    Card(
        shape = Shapes.medium,
        modifier = Modifier.padding(10.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Car Name",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(5.dp)
            )
            Row(
                horizontalArrangement = Arrangement.Absolute.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 10.dp)
            ) {
                Button(onClick = { /*TODO*/ }) {
                    Text(
                        text = "Active",
                        style = MaterialTheme.typography.labelSmall
                    )
                }
                Button(onClick = { /*TODO*/ }) {
                    Text(
                        text = "Repairs",
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        }
    }
}

@Composable
fun AddCar(modifier: Modifier = Modifier) {
    Card(
        shape = Shapes.medium,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 0.dp, start = 10.dp, end = 10.dp, bottom = 10.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Add New Car",
                style = MaterialTheme.typography.labelSmall,
                fontSize = 20.sp,
                modifier = Modifier.padding(top = 5.dp)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.weight(1f))
                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = modifier
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.secondary
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ServiceBookPreview() {
    ServiceBookTheme(darkTheme = true, dynamicColor = false) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .fillMaxWidth()
        ) {
            TopAppBar(MaterialTheme.shapes.extraSmall)
            CarItem()
            AddCar()
        }
    }
}