package com.example.kmpuinativeapp.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class DispatcherProviderImpl : DispatcherProvider {
    override val io: CoroutineDispatcher = Dispatchers.IO
    override val main: CoroutineDispatcher = Dispatchers.Main
}

actual fun getDispatcherProvider(): DispatcherProvider {
    return DispatcherProviderImpl()
}