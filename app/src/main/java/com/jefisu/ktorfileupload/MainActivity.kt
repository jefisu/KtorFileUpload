package com.jefisu.ktorfileupload

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel = viewModel<FileViewModel>()
            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.GetContent(),
                onResult = { result ->
                    val pickedFile = result?.let(contentResolver::openInputStream)
                    pickedFile?.readBytes()?.let(viewModel::uploadImage)
                }
            )

            if (viewModel.responseText.isNotBlank()) {
                Toast.makeText(
                    applicationContext,
                    viewModel.responseText,
                    Toast.LENGTH_SHORT
                ).show()
            }
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                if (viewModel.isLoading) {
                    CircularProgressIndicator()
                    return@Box
                }
                Button(
                    onClick = { launcher.launch("image/*") }
                ) {
                    Text(text = "Upload image")
                }
            }
        }
    }
}