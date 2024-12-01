package br.com.fiap.challengequod.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.com.fiap.challengequod.ui.components.CustomButton

@Composable
fun BiometriaFacialScreen(navController: NavHostController) {
    var captureResult by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Título
        Text(
            text = "Cadastro de Biometria Facial",
            style = MaterialTheme.typography.headlineMedium
        )

        // Botão para capturar face
        CustomButton(
            text = "Capturar Face",
            onClick = {
                // Simula a captura de biometria facial
                captureResult = "Sucesso: Captura facial bem-sucedida!"
            }
        )

        // Exibe o resultado da captura, caso exista
        captureResult?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.bodyMedium
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
