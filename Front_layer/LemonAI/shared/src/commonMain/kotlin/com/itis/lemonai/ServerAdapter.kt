package com.itis.lemonai

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.HttpResponseData
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.http.path
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

private const val BASE_URL = "http://10.0.2.2:5050"
object CurrentUser {
    var login: String = ""
    var name: String = ""
    var surname: String = ""
}

fun clearCurrentUser(){
    CurrentUser.login = ""
    CurrentUser.name = ""
    CurrentUser.surname = ""
}

@Serializable
data class User(
    val Login: String,
    val Password: String,
    val Name: String,
    val Surname: String
)
@Serializable
data class HistoryItem(
    val Login: String,
    val Date: String,
    val Result: String,
    val Shop: String
)

suspend fun httpAddUser(user: User): Boolean {
    val client = HttpClient(){
        install(ContentNegotiation){
            json()
        }
    }
    val response: HttpResponse = client.post("$BASE_URL/add_user") {
        contentType(ContentType.Application.Json)
        setBody(user)
    }.body()
    client.close()
    return if (response.status.value in 200..299){
        CurrentUser.name = user.Name
        CurrentUser.surname = user.Surname
        CurrentUser.login = user.Login
        true
    }else{
        false
    }
}

suspend fun httpGetHistory(login: String): List<HistoryItem> {
    val client = HttpClient(){
        install(ContentNegotiation){
            json()
        }
    }
    val historyItem: HistoryItem = client.get("$BASE_URL/$login").body()
    client.close()

    return listOf()
}