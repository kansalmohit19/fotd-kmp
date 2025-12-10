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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import com.indemand.fotd.domain.model.FactDetails
import com.indemand.fotd.facts.FactListState
import com.indemand.fotd.facts.FactsListViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun FactsListScreenView(
    factsListViewModel: FactsListViewModel = getViewModel()
) {

    val factsState = factsListViewModel.factsListFlow.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Toolbar()
        when (factsState.value) {
            is FactListState.Loading -> Loading()
            is FactListState.Error -> {
                val message = (factsState.value as FactListState.Error).errorMessage
                ErrorView(message)
            }

            is FactListState.ShowFacts -> {
                ListView((factsState.value as FactListState.ShowFacts).listOfFacts)
            }

            is FactListState.Idle -> Unit
        }
    }
}

@Composable
private fun Toolbar() {
    Spacer(modifier = Modifier.height(80.dp))
    Text(
        text = "Facts Section!",
        style = TextStyle(fontSize = 16.sp, color = Color(0xFFFFFFFF)),
    )
    Spacer(modifier = Modifier.height(4.dp))
    Text(
        text = "Coming soon...",
        style = TextStyle(fontSize = 24.sp, color = Color(0xFFFFFFFF)),
    )
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
            text = factDetails.title,
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 22.sp),
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = factDetails.description,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = factDetails.postedBy,
            style = TextStyle(color = Color.Gray, fontSize = 14.sp),
        )
        Text(
            text = factDetails.postedOn,
            style = TextStyle(color = Color.Gray, fontSize = 11.sp),
        )
    }
}