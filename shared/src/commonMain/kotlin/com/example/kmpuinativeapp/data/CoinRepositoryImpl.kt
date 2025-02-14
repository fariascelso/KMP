package com.example.kmpuinativeapp.data

import kotlinx.coroutines.withContext

class CoinRepositoryImpl(
    private val apiClient: ApiClient,
    private val dispatcherProvider: DispatcherProvider
) : CoinRepository {
    override suspend fun getCoins(): List<Coin> {
        return withContext(dispatcherProvider.io) {
            try {
                apiClient.getCoins().map {
                    Coin(
                        id = it.id,
                        name = it.name,
                        symbol = it.symbol,
                        rank = it.rank
                    )
                }
            } catch (e: Throwable) {
                throw e
            }
        }
    }
}