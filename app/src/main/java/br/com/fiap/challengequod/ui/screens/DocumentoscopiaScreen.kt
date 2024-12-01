package br.com.fiap.challengequod.ui.screens

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import br.com.fiap.challengequod.ui.components.CustomButton

@Composable
fun DocumentoscopiaScreen(navController: NavHostController) {
    val context = LocalContext.current

    // Estado para armazenar a imagem capturada
    var capturedImage by remember { mutableStateOf<Bitmap?>(null) }

    // Lançador para iniciar a captura da imagem
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
        if (bitmap != null) {
            capturedImage = bitmap
        } else {
            Log.e("DocumentoscopiaScreen", "Erro ao capturar imagem")
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Título
        Text(
            text = "Cadastro de Documentoscopia",
            style = MaterialTheme.typography.headlineMedium
        )

        // Botão para capturar documento
        CustomButton(
            text = "Capturar Documento",
            onClick = {
                // Iniciar a câmera para tirar a foto
                launcher.launch(null)
            }
        )

        // Exibir a imagem capturada, se disponível
        capturedImage?.let {
            Image(
                bitmap = it.asImageBitmap(),
                contentDescription = "Imagem do Documento Capturado",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(450.dp)
            )
        }

        // Botão para voltar à tela inicial
        CustomButton(
            text = "Voltar",
            onClick = {
                navController.popBackStack()
            }
        )
    }
}
