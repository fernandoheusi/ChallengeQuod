package br.com.fiap.challengequod

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.fiap.challengequod.ui.theme.ChallengeQuodTheme
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
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
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        NavigationComponent(navController, Modifier.padding(innerPadding))
    }
}

@Composable
fun Greeting() {
    Text(
        text = "Hello povo!",
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ChallengeQuodTheme {
        Greeting()
    }
}