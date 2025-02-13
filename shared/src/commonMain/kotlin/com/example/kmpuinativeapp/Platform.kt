package com.example.kmpuinativeapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform