package com.example.kmpuinativeapp.data

interface CoinRepository {
    suspend fun getCoins(): List<Coin>
}