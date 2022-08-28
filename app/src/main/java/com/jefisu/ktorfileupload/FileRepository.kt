package com.jefisu.ktorfileupload

import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.client.statement.*
import io.ktor.utils.io.core.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class FileRepository {

    suspend fun uploadImage(fileBytes: ByteArray): String {
        val response = FileApi.client.post("file") {
            setBody(MultiPartFormDataContent(
                formData {
                    append(
                        key = "image",
                        filename = "ktor_image.png",
                        bodyBuilder = {
                            writeFully(fileBytes)
                        }
                    )
                }
            ))
        }
        return Json.decodeFromString(response.bodyAsText())
    }
}