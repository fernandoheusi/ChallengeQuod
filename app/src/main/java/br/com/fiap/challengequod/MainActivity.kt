package br.com.fiap.challengequod

import CadastroScoreAntifraudeScreen
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.fiap.challengequod.model.Biometric
import br.com.fiap.challengequod.ui.screens.BiometriaFacialScreen
import br.com.fiap.challengequod.ui.screens.CadastroAutenticacaoCadastralScreen
import br.com.fiap.challengequod.ui.screens.CadastroBiometriaDigitalScreen
import br.com.fiap.challengequod.ui.screens.DocumentoscopiaScreen
import br.com.fiap.challengequod.ui.screens.SimSwapScreen
import br.com.fiap.challengequod.ui.theme.ChallengeQuodTheme

class MainActivity : AppCompatActivity() {
    private val promptManager by lazy {
        Biometric(this)
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            // permissão garantida
        } else {
            // Permissão negada
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestPermissionLauncher.launch(android.Manifest.permission.CAMERA)
        setContent {
            ChallengeQuodTheme {
                MeuApp(promptManager)
            }
        }
    }
}

@Composable
fun MeuApp(promptManager: Biometric) {
    val navController = rememberNavController()
    Scaffold(
        topBar = {
        },
        content = { innerPadding ->
            NavigationComponent(navController, Modifier.padding(innerPadding), promptManager)
        }
    )
}

@Composable
fun NavigationComponent(navController: NavHostController, modifier: Modifier = Modifier, promptManager: Biometric) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("biometria_facial") {
            BiometriaFacialScreen(navController)
        }
        composable("biometria_digital") {
            CadastroBiometriaDigitalScreen(navController,promptManager)
        }
        composable("documentoscopia") {
            DocumentoscopiaScreen(navController)
        }
        composable("sim_swap") {
            SimSwapScreen(navController)
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
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // Adicionando a logo
        Image(
            painter = painterResource(id = R.drawable.logo_quod), // Substitua pelo ID da sua imagem
            contentDescription = "Logo Quod",
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(bottom = 16.dp)
        )

        Text(
            text = "Pilares para Mitigar o Risco de Fraude",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            PillarCard(
                title = "Biometria Facial",
                description = "Autenticação através da câmera",
                icon = Icons.Default.Face,
                onClick = { navController.navigate("biometria_facial") },
                modifier = Modifier.weight(1f).fillMaxWidth()
            )

            PillarCard(
                title = "Biometria Digital",
                description = "Funcionalidade que testa a autenticação de biometria digital.",
                icon = Icons.Default.ThumbUp,
                onClick = { navController.navigate("biometria_digital") },
                modifier = Modifier.weight(1f).fillMaxWidth()
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            PillarCard(
                title = "Documentoscopia",
                description = "Verificação de documentos",
                icon = Icons.Default.Check,
                onClick = { navController.navigate("documentoscopia") },
                modifier = Modifier.weight(1f).fillMaxWidth()
            )

            PillarCard(
                title = "SIM SWAP",
                description = "Teste de troca de SIM",
                icon = Icons.Default.Lock,
                onClick = { navController.navigate("sim_swap") },
                modifier = Modifier.weight(1f).fillMaxWidth()
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            PillarCard(
                title = "Autenticação Cadastral",
                description = "Cadastro e validação de dados",
                icon = Icons.Default.Search,
                onClick = { navController.navigate("autenticacao_cadastral") },
                modifier = Modifier.weight(1f).fillMaxWidth()
            )

            PillarCard(
                title = "Score Antifraude",
                description = "Análise do score de risco",
                icon = Icons.Default.Warning,
                onClick = { navController.navigate("score_anti_fraude") },
                modifier = Modifier.weight(1f).fillMaxWidth()
            )
        }
    }
}

@Composable
fun PillarCard(
    title: String,
    description: String,
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(150.dp), // Adicionado altura fixa para uniformidade
        elevation = CardDefaults.cardElevation(4.dp),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                modifier = Modifier.size(40.dp)
            )
            Text(text = title, style = MaterialTheme.typography.titleMedium)
            Text(text = description, style = MaterialTheme.typography.bodySmall)
        }
    }
}



