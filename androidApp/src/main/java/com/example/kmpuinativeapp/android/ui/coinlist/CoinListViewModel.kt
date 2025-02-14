package com.example.kmpuinativeapp.android.ui.coinlist
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kmpuinativeapp.data.Coin
import com.example.kmpuinativeapp.data.CoinRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CoinListViewModel(
    private val coinRepository: CoinRepository
) : ViewModel() {

    private val _coinListUiState = MutableStateFlow<CoinListUiState>(CoinListUiState.Loading)
    val coinListUiState = _coinListUiState.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                val coins = coinRepository.getCoins()
                _coinListUiState.update {
                    CoinListUiState.Success(coins)
                }
            } catch (e: Throwable) {
                _coinListUiState.update {
                    CoinListUiState.Error
                }
            }
        }
    }

    sealed interface CoinListUiState {
        data object Loading : CoinListUiState
        data class Success(val coins: List<Coin>) : CoinListUiState
        data object Error : CoinListUiState
    }
}