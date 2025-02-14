package com.example.kmpuinativeapp.data

import kotlinx.coroutines.CoroutineDispatcher

interface DispatcherProvider {
    val io: CoroutineDispatcher
    val main: CoroutineDispatcher
}

expect fun getDispatcherProvider(): DispatcherProvider