package br.com.fiap.challengequod.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.com.fiap.challengequod.ui.components.CustomButton

@Composable
fun SimSwapScreen(navController: NavHostController) {
    var nome by remember { mutableStateOf("") }
    var cpf by remember { mutableStateOf("") }
    var telefone by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf<String?>(null) }
    var alertaSimSwap by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Simulação de Ataque SIM Swap",
            style = MaterialTheme.typography.headlineMedium
        )

        TextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text("Nome Completo") }
        )

        TextField(
            value = cpf,
            onValueChange = { cpf = it },
            label = { Text("CPF") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        TextField(
            value = telefone,
            onValueChange = { telefone = it },
            label = { Text("Telefone Celular") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
        )

        CustomButton(
            text = "Simular Ataque",
            onClick = {
                if (nome.isNotBlank() && cpf.length == 11 && telefone.length >= 10) {
                    alertaSimSwap = true
                    resultado = "Alerta: Possível tentativa de SIM Swap detectada!"
                } else {
                    resultado = "Por favor, preencha todos os campos corretamente."
                }
            }
        )

        resultado?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.bodyMedium,
                color = if (alertaSimSwap) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onBackground
            )
        }

        CustomButton(
            text = "Voltar",
            onClick = { navController.popBackStack() }
        )
    }
}
