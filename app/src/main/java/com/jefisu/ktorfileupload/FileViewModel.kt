package com.jefisu.ktorfileupload

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FileViewModel(
    private val repository: FileRepository = FileRepository()
) : ViewModel() {

    var isLoading by mutableStateOf(false)
        private set

    var responseText by mutableStateOf("")
        private set

    fun uploadImage(fileBytes: ByteArray) {
        isLoading = true
        viewModelScope.launch {
            responseText = repository.uploadImage(fileBytes)
            delay(300)
            isLoading = false
        }
    }
}