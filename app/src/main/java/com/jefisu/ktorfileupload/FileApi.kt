package com.jefisu.ktorfileupload

import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.logging.*

object FileApi {
    val client by lazy {
        HttpClient(Android) {
            install(Logging) {
                level = LogLevel.ALL
            }
            defaultRequest {
                url("http://192.168.0.2:8080/")
            }
        }
    }
}