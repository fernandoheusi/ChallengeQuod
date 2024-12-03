import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun CadastroScoreAntifraudeScreen(navController: NavHostController) {
    var cpf by remember { mutableStateOf("") }
    var score by remember { mutableStateOf<Int?>(null) }
    var scoreLevel by remember { mutableStateOf<String?>(null) }
    var cpfError by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Cadastro de Score Antifraude",
            style = MaterialTheme.typography.headlineMedium
        )
        TextField(
            value = cpf,
            onValueChange = {
                if (it.length <= 11 && it.all { char -> char.isDigit() }) {
                    cpf = it
                }
            },
            label = { Text("CPF") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = cpfError != null,
            modifier = Modifier.fillMaxWidth()
        )
        cpfError?.let {
            Text(text = it, color = MaterialTheme.colorScheme.error)
        }

        Button(onClick = {
            if (isValidCPF(cpf)) {
                score = (1..1000).random()
                scoreLevel = when (score) {
                    in 0..300 -> "Muito baixo"
                    in 301..500 -> "Baixo"
                    in 501..700 -> "Bom"
                    in 701..1000 -> "Excelente"
                    else -> "Inválido"
                }
                cpfError = null 
            } else {
                cpfError = "CPF inválido."
                score = null
                scoreLevel = null
            }
        }) {
            Text("Consultar Score")
        }

        score?.let {
            Text(
                text = "Seu Score é de $it, $scoreLevel",
                style = MaterialTheme.typography.bodyMedium
            )
            LinearProgressIndicator(
                progress = it / 1000f,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(16.dp)
            )
        }

        Button(onClick = { navController.popBackStack() }) {
            Text("Voltar")
        }
    }
}

// Função para validar CPF (coloque sua implementação de validação real aqui)
fun isValidCPF(cpf: String): Boolean {
    // Substitua pelo algoritmo de validação de CPF real
    return cpf.length == 11
}
