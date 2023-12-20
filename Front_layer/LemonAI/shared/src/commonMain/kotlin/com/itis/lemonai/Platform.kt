package com.itis.lemonai

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform