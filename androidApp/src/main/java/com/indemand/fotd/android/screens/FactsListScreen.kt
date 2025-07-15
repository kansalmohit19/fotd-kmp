package com.indemand.fotd.android.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage

import com.indemand.fotd.facts.FactDetails
import com.indemand.fotd.facts.FactsListViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun FactsListScreen(
    factsListViewModel: FactsListViewModel = getViewModel()
) {

    val factsState = factsListViewModel.factsListFlow.collectAsState()
    Column {
        Toolbar()
        if (factsState.value.isLoading) {
            Loading()
        }
        if (factsState.value.errorMessage != null) {
            ErrorView(factsState.value.errorMessage)
        }
        if (factsState.value.listOfFacts.isNotEmpty()) {
            ListView(factsState.value.listOfFacts)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Toolbar() {
    TopAppBar(title = { Text(text = "Facts List") })
}

@Composable
private fun Loading() {
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.width(64.dp),
            color = MaterialTheme.colorScheme.surfaceVariant,
            trackColor = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
private fun ErrorView(message: String? = "") {
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(bottom = 24.dp, start = 8.dp, end = 8.dp)
        ) {
            Text(
                text = "Error!",
                style = TextStyle(
                    color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 26.sp
                ),
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                textAlign = TextAlign.Center,
                text = message ?: "",
                style = TextStyle(
                    color = Color.Black, fontWeight = FontWeight.Normal, fontSize = 18.sp
                ),
            )
        }
    }
}

@Composable
private fun ListView(listOfFacts: List<FactDetails>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(listOfFacts) { factDetails ->
            FactRowView(factDetails)
        }
    }
}

@Composable
private fun FactRowView(factDetails: FactDetails) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        AsyncImage(model = factDetails.imageUrl, contentDescription = null)
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = factDetails.titleText,
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 22.sp),
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = factDetails.descriptionText,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = factDetails.postedBy,
            style = TextStyle(color = Color.Gray, fontSize = 14.sp),
        )
        Text(
            text = factDetails.postedOnDate,
            style = TextStyle(color = Color.Gray, fontSize = 11.sp),
        )
    }
}