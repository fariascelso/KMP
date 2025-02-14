package com.example.kmpuinativeapp.android.ui.coinlist
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kmpuinativeapp.data.ApiClient
import com.example.kmpuinativeapp.data.CoinRepositoryImpl
import com.example.kmpuinativeapp.data.coin1
import com.example.kmpuinativeapp.data.coin2
import com.example.kmpuinativeapp.data.coin3
import com.example.kmpuinativeapp.data.getDispatcherProvider

@Composable
fun CoinListScreen() {
    val apiClient = ApiClient()
    val dispatcherProvider = getDispatcherProvider()

    val coinRepository = CoinRepositoryImpl(apiClient, dispatcherProvider)
    val viewModel = viewModel {
        CoinListViewModel(coinRepository)
    }

    val coinListUiState by viewModel.coinListUiState.collectAsStateWithLifecycle()

    CoinListContent(
        coinListUiState = coinListUiState
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoinListContent(
    coinListUiState: CoinListViewModel.CoinListUiState
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Coin List")
                }
            )
        }
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
        ) {
            when (coinListUiState) {
                is CoinListViewModel.CoinListUiState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(Alignment.Center)
                    )
                }

                is CoinListViewModel.CoinListUiState.Success -> {
                    LazyColumn(
                        contentPadding = PaddingValues(16.dp)
                    ) {
                        itemsIndexed(coinListUiState.coins) { index, coin ->
                            Column {
                                Text(text = "#${coin.rank}")
                                Text(text = coin.name)
                                Text(text = coin.symbol)
                            }

                            if (index < coinListUiState.coins.lastIndex) {
                                HorizontalDivider(
                                    modifier = Modifier
                                        .padding(vertical = 8.dp)
                                )
                            }
                        }
                    }
                }

                is CoinListViewModel.CoinListUiState.Error -> {
                    Text(
                        text = "Ocorreu um erro",
                        modifier = Modifier
                            .align(Alignment.Center)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun CoinListContentPreview() {
    CoinListContent(
        coinListUiState = CoinListViewModel.CoinListUiState.Loading
    )
}

@Preview
@Composable
private fun CoinListContentSuccessPreview() {
    CoinListContent(
        coinListUiState = CoinListViewModel.CoinListUiState.Success(
            listOf(
                coin1,
                coin2,
                coin3
            )
        )
    )
}

@Preview
@Composable
private fun CoinListContentErrorPreview() {
    CoinListContent(
        coinListUiState = CoinListViewModel.CoinListUiState.Error
    )
}