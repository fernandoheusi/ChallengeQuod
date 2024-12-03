package br.com.fiap.challengequod.ui.screens

import android.graphics.Bitmap
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
import androidx.navigation.NavHostController
import br.com.fiap.challengequod.ui.components.CustomButton

@Composable
fun BiometriaFacialScreen(navController: NavHostController) {
    var capturedImage by remember { mutableStateOf<Bitmap?>(null) }
    var captureResult by remember { mutableStateOf<String?>(null) }
    // Launcher para capturar a imagem com a câmera
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
        if (bitmap != null) {
            capturedImage = bitmap
            captureResult = "Sucesso: Captura facial bem-sucedida!"
        } else {
            Log.e("BiometriaFacialScreen", "Erro ao capturar imagem")
            captureResult = "Erro: Não foi possível capturar a face."
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = "Cadastro de Biometria Facial",
            style = MaterialTheme.typography.headlineMedium
        )

        CustomButton(
            text = "Capturar Face",
            onClick = {
                launcher.launch(null)
            }
        )

        capturedImage?.let {
            Image(
                bitmap = it.asImageBitmap(),
                contentDescription = "Imagem Capturada",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            )
        }

        captureResult?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        CustomButton(
            text = "Voltar",
            onClick = {
                navController.popBackStack()
            }
        )
    }
}
