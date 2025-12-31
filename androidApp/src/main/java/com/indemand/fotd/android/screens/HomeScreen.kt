package com.indemand.fotd.android.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.indemand.fotd.domain.model.FactDetails
import com.indemand.fotd.domain.uistate.HomeUiState
import com.indemand.fotd.viewmodel.facts.home.HomeViewModel
import org.koin.androidx.compose.koinViewModel

@Suppress("FunctionName")
@Composable
fun HomeScreenView(
    homeViewModel: HomeViewModel = koinViewModel(),
    paddingValues: PaddingValues,
) {
    val homeState = homeViewModel.factsListFlow.collectAsState()
    val modifier = Modifier
    Column(
        modifier =
            modifier
                .fillMaxSize()
                .padding(
                    top = paddingValues.calculateTopPadding(),
                    bottom = paddingValues.calculateBottomPadding(),
                    start = 16.dp,
                    end = 16.dp,
                ).background(Color(0xFF102131)),
    ) {
        Toolbar()
        when (homeState.value) {
            is HomeUiState.Loading -> {
                Loading()
            }

            is HomeUiState.Error -> {
                val message = (homeState.value as HomeUiState.Error).errorMessage
                ErrorView(message)
            }

            is HomeUiState.ShowFact -> {
                val fact = (homeState.value as HomeUiState.ShowFact).factDetails
                FactRowView(fact, modifier)
            }

            HomeUiState.Idle -> Unit
        }
    }
}

@Suppress("FunctionName")
@Composable
private fun Toolbar() {
    Text(
        text = "welcome!",
        style = TextStyle(fontSize = 16.sp, color = Color(0xFFFFFFFF)),
    )
    Spacer(modifier = Modifier.height(4.dp))
    Text(
        text = "Guest User",
        style = TextStyle(fontSize = 24.sp, color = Color(0xFFFFFFFF)),
    )
}

@Suppress("FunctionName")
@Composable
private fun Loading() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator(
            modifier = Modifier.width(64.dp),
            color = MaterialTheme.colorScheme.surfaceVariant,
            trackColor = MaterialTheme.colorScheme.secondary,
        )
    }
}

@Suppress("FunctionName")
@Composable
private fun ErrorView(message: String? = "") {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(bottom = 24.dp, start = 8.dp, end = 8.dp),
        ) {
            Text(
                text = "Error!",
                style =
                    TextStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 26.sp,
                    ),
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                textAlign = TextAlign.Center,
                text = message ?: "",
                style =
                    TextStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Normal,
                        fontSize = 18.sp,
                    ),
            )
        }
    }
}

@Suppress("FunctionName")
@Composable
private fun FactRowView(
    factDetails: FactDetails,
    modifier: Modifier,
) {
    Column(
        modifier =
            modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
    ) {
        // Spacer(modifier = Modifier.height(20.dp))
        Text(
            overflow = TextOverflow.Ellipsis,
            text = factDetails.title,
            style = TextStyle(fontSize = 40.sp, color = Color(0xFFFFFFFF)),
        )
        // Spacer(modifier = Modifier.height(8.dp))
    }
}
