package com.indemand.fotd.android.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.indemand.fotd.Platform

@Suppress("FunctionName")
@Composable
fun AboutDevice() {
    Column {
        Toolbar()
        ContentView()
    }
}

@Suppress("FunctionName")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Toolbar() {
    TopAppBar(title = { Text(text = "About Device") })
}

@Suppress("FunctionName")
@Composable
fun ContentView() {
    val items = makeItems()

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(items) { row ->
            RowView(row.title, row.description)
        }
    }
}

@Suppress("FunctionName")
@Composable
fun RowView(
    title: String,
    description: String,
) {
    Column(Modifier.padding(8.dp)) {
        Text(text = title, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
        Text(text = description, style = MaterialTheme.typography.bodyLarge)
    }
}

fun makeItems(): List<AboutDeviceDetails> {
    val listOfItems = mutableListOf<AboutDeviceDetails>()
    listOfItems.add(AboutDeviceDetails(title = "OS Name", description = Platform.osName))
    listOfItems.add(AboutDeviceDetails(title = "OS Version", description = Platform.osVersion))
    listOfItems.add(
        AboutDeviceDetails(
            title = "Device Details",
            description = Platform.deviceModel,
        ),
    )
    return listOfItems
}

data class AboutDeviceDetails(val title: String, val description: String)
