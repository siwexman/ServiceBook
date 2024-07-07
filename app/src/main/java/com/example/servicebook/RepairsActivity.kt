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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.servicebook.ui.theme.ServiceBookTheme
import com.example.servicebook.ui.theme.Shapes
import androidx.compose.foundation.lazy.items

class RepairsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ServiceBookTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                }
            }
        }
    }
}

@Composable
fun RepairItem(modifier: Modifier = Modifier) {
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
                text = "Title of Repair",
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
                RowData(
                    stringResourcesId = R.string.price,
                    text = "250.50",
                    modifier = modifier
                )
                RowData(
                    stringResourcesId = R.string.repair_date,
                    text = "02.11.2024",
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
fun SummaryFooter(modifier: Modifier = Modifier) {
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
                Text(text = "1000", fontSize = 18.sp)
            }
        }
    }
}

@Composable
fun RepairsContent(items: List<String>) {
    Column(verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.weight(1f).fillMaxWidth()) {
            items(items) { item ->
                RepairItem()
            }
        }
        SummaryFooter()
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    val items = List(10) { "Item #$it" }
    ServiceBookTheme(darkTheme = true, dynamicColor = false) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .fillMaxWidth()
        ) {
            TopAppBar(MaterialTheme.shapes.extraSmall)
            RepairsContent(items = items)
        }
    }
}