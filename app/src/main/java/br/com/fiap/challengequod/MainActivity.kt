package br.com.fiap.challengequod

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
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
import br.com.fiap.challengequod.ui.screens.BiometriaFacialScreen
import br.com.fiap.challengequod.ui.screens.DocumentoscopiaScreen
import br.com.fiap.challengequod.ui.theme.ChallengeQuodTheme

class MainActivity : ComponentActivity() {
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            //permissão garantida
        } else {
            // Permissão negada
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestPermissionLauncher.launch(android.Manifest.permission.CAMERA)
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
            BiometriaFacialScreen(navController)
        }
        composable("biometria_digital") {
            CadastroBiometriaDigitalScreen(navController)
        }
        composable("documentoscopia") {
            DocumentoscopiaScreen(navController)
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

// Cadastro de Biometria Facial
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

// Cadastro de Biometria Digital
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

// Cadastro de Documentoscopia
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

// Cadastro de SIM Swap
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

// Cadastro de Autenticação Cadastral
@Composable
fun CadastroAutenticacaoCadastralScreen(navController: NavHostController) {
    var cpf by remember { mutableStateOf("") }
    var nome by remember { mutableStateOf("") }
    var endereco by remember { mutableStateOf("") }
    var telefone by remember { mutableStateOf("") }
    var validationResult by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "Cadastro de Autenticação Cadastral", style = MaterialTheme.typography.headlineMedium)

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
            value = endereco,
            onValueChange = { endereco = it },
            label = { Text("Endereço") }
        )
        TextField(
            value = telefone,
            onValueChange = { telefone = it },
            label = { Text("Telefone Celular") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
        )

        Button(onClick = {
            validationResult = validarCadastro(nome, cpf, endereco, telefone)
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

// Função para validação dos campos
fun validarCadastro(nome: String, cpf: String, endereco: String, telefone: String): String {
    if (nome.isBlank() || !nome.contains(" ")) {
        return "Erro: Nome inválido. Inclua nome e sobrenome."
    }

    if (!validarCpf(cpf)) {
        return "Erro: CPF inválido."
    }

    if (endereco.isBlank() || endereco.length < 5) {
        return "Erro: Endereço inválido."
    }

    if (!telefone.matches(Regex("^\\d{11}$"))) {
        return "Erro: Telefone inválido. Use o formato DDD + número."
    }

    return "Sucesso: Cadastro validado!"
}

// Função para validação de CPF
fun validarCpf(cpf: String): Boolean {
    if (cpf.length != 11 || cpf.all { it == cpf[0] }) return false
    val pesos1 = intArrayOf(10, 9, 8, 7, 6, 5, 4, 3, 2)
    val pesos2 = intArrayOf(11, 10, 9, 8, 7, 6, 5, 4, 3, 2)

    fun calcularDigito(pesos: IntArray, cpf: String): Int {
        val soma = cpf.take(pesos.size).mapIndexed { i, c -> (c - '0') * pesos[i] }.sum()
        val resto = soma % 11
        return if (resto < 2) 0 else 11 - resto
    }

    val digito1 = calcularDigito(pesos1, cpf)
    val digito2 = calcularDigito(pesos2, cpf + digito1)

    return cpf.endsWith("$digito1$digito2")
}


// Cadastro de Score Antifraude
@Composable
fun CadastroScoreAntifraudeScreen(navController: NavHostController) {
    var cpf by remember { mutableStateOf("") }
    var score by remember { mutableStateOf<Int?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "Cadastro de Score Antifraude", style = MaterialTheme.typography.headlineMedium)
        TextField(
            value = cpf,
            onValueChange = { cpf = it },
            label = { Text("CPF") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Button(onClick = {
            score = if (cpf.isNotEmpty()) (300..850).random() else null
        }) {
            Text("Consultar Score")
        }
        score?.let {
            Text(text = "Resultado: Seu Score Antifraude é $it", style = MaterialTheme.typography.bodyMedium)
        }
        Button(onClick = { navController.popBackStack() }) {
            Text("Voltar")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewApp() {
    ChallengeQuodTheme {
        MeuApp()
    }
}

