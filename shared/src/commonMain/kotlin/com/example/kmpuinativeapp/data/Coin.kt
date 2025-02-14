package com.example.kmpuinativeapp.data

data class Coin(
    val id: String,
    val name: String,
    val symbol: String,
    val rank: Int,
)

val coin1 = Coin(
    id = "1",
    name = "Bitcoin",
    symbol = "BTC",
    rank = 1,
)

val coin2 = Coin(
    id = "2",
    name = "Ethereum",
    symbol = "ETH",
    rank = 2,
)

val coin3 = Coin(
    id = "3",
    name = "Litecoin",
    symbol = "LTC",
    rank = 3,
)
