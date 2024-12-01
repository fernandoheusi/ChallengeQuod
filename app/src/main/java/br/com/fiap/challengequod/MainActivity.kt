package br.com.fiap.challengequod

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.fiap.challengequod.ui.theme.ChallengeQuodTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChallengeQuodTheme {
                MeuApp()
            }
        }
    }
}

@Composable
fun MeuApp() {
    val navController = rememberNavController()
    Scaffold(
        topBar = {
        },
        content = { innerPadding ->
            NavigationComponent(navController, Modifier.padding(innerPadding))
        }
    )
}

@Composable
fun NavigationComponent(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("biometria_facial") {
            CadastroBiometriaFacialScreen(navController)
        }
        composable("biometria_digital") {
            CadastroBiometriaDigitalScreen(navController)
        }
        composable("documentoscopia") {
            CadastroDocumentoscopiaScreen(navController)
        }
        composable("sim_swap") {
            CadastroSimSwapScreen(navController)
        }
        composable("autenticacao_cadastral") {
            CadastroAutenticacaoCadastralScreen(navController)
        }
        composable("score_anti_fraude") {
            CadastroScoreAntifraudeScreen(navController)
        }
    }
}

@Composable
fun HomeScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Escolha uma demonstração para cadastrar:",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Button(onClick = { navController.navigate("biometria_facial") }) {
            Text("Cadastrar Biometria Facial")
        }
        Button(onClick = { navController.navigate("biometria_digital") }) {
            Text("Cadastrar Biometria Digital")
        }
        Button(onClick = { navController.navigate("documentoscopia") }) {
            Text("Cadastrar Documentoscopia")
        }
        Button(onClick = { navController.navigate("sim_swap") }) {
            Text("Cadastrar SIM Swap")
        }
        Button(onClick = { navController.navigate("autenticacao_cadastral") }) {
            Text("Cadastrar Autenticação Cadastral")
        }
        Button(onClick = { navController.navigate("score_anti_fraude") }) {
            Text("Cadastrar Score Antifraude")
        }
    }
}

@Composable
fun CadastroBiometriaFacialScreen(navController: NavHostController) {
    var result by remember { mutableStateOf<String?>(null) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "Cadastro de Biometria Facial", style = MaterialTheme.typography.headlineMedium)
        Button(onClick = { result = "Sucesso: Captura facial bem-sucedida!" }) {
            Text(text = "Capturar Face")
        }
        result?.let {
            Text(text = it, style = MaterialTheme.typography.bodyMedium)
        }
        Button(onClick = { navController.popBackStack() }) {
            Text("Voltar")
        }
    }
}

@Composable
fun CadastroBiometriaDigitalScreen(navController: NavHostController) {
    var result by remember { mutableStateOf<String?>(null) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "Cadastro de Biometria Digital", style = MaterialTheme.typography.headlineMedium)
        Button(onClick = { result = "Sucesso: Captura digital bem-sucedida!" }) {
            Text(text = "Capturar Digital")
        }
        result?.let {
            Text(text = it, style = MaterialTheme.typography.bodyMedium)
        }
        Button(onClick = { navController.popBackStack() }) {
            Text("Voltar")
        }
    }
}

@Composable
fun CadastroDocumentoscopiaScreen(navController: NavHostController) {
    var result by remember { mutableStateOf<String?>(null) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "Cadastro de Documentoscopia", style = MaterialTheme.typography.headlineMedium)
        Button(onClick = { result = "Sucesso: Documento validado!" }) {
            Text(text = "Validar Documento")
        }
        result?.let {
            Text(text = it, style = MaterialTheme.typography.bodyMedium)
        }
        Button(onClick = { navController.popBackStack() }) {
            Text("Voltar")
        }
    }
}

@Composable
fun CadastroSimSwapScreen(navController: NavHostController) {
    var result by remember { mutableStateOf<String?>(null) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "Cadastro de SIM Swap", style = MaterialTheme.typography.headlineMedium)
        Button(onClick = { result = "Sucesso: SIM Swap confirmado!" }) {
            Text(text = "Confirmar SIM Swap")
        }
        result?.let {
            Text(text = it, style = MaterialTheme.typography.bodyMedium)
        }
        Button(onClick = { navController.popBackStack() }) {
            Text("Voltar")
        }
    }
}

@Composable
fun CadastroAutenticacaoCadastralScreen(navController: NavHostController) {
    var cpf by remember { mutableStateOf("") }
    var nome by remember { mutableStateOf("") }
    var validationResult by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "Cadastro de Autenticacao Cadastral", style = MaterialTheme.typography.headlineMedium)
        TextField(
            value = cpf,
            onValueChange = { cpf = it },
            label = { Text("CPF") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        TextField(value = nome, onValueChange = { nome = it }, label = { Text("Nome") })
        Button(onClick = {
            validationResult = if (cpf.isNotEmpty() && nome.isNotEmpty()) {
                "Sucesso: Dados validados!"
            } else {
                "Erro: Preencha todos os campos obrigatórios."
            }
        }) {
            Text("Validar")
        }
        validationResult?.let {
            Text(text = it, style = MaterialTheme.typography.bodyMedium)
        }
        Button(onClick = { navController.popBackStack() }) {
            Text("Voltar")
        }
    }
}

fun isValidCPF(cpf: String): Boolean {

    val cleanedCPF = cpf.replace("[^\\d]".toRegex(), "")

    return cleanedCPF.length == 11 && cleanedCPF.all { it.isDigit() }
}

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
            onValueChange = { cpf = it },
            label = { Text("CPF") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = cpfError != null
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
                cpfError = null // Limpar erro se o CPF for válido
            } else {
                cpfError = "CPF inválido."
                score = null // Limpar o score se CPF for inválido
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

