package com.example.servicebook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.servicebook.ui.theme.ServiceBookTheme
import com.example.servicebook.ui.theme.Shapes

class ActiveRemindersActivity : ComponentActivity() {
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
                        modifier = Modifier
                            .fillMaxSize()
                            .fillMaxWidth()
                    ) {
                        TopAppBar(MaterialTheme.shapes.extraSmall)
                        Reminder()
                        AddItem(onClick = {}, "reminder")
                    }
                }
            }
        }
    }
}

@Composable
fun Reminder(modifier: Modifier = Modifier) {
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
                text = "Title Reminder",
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
                Text(text = "20.11.2024")
            }
            Row {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = stringResource(id = R.string.renew))
                }
            }
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
            Reminder()
            AddItem(onClick = {}, "reminder")
        }
    }
}