package br.com.fiap.challengequod.ui.screens

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.com.fiap.challengequod.model.Biometric
import br.com.fiap.challengequod.model.Biometric.BiometricResult

@Composable
fun CadastroBiometriaDigitalScreen(navController: NavHostController,promptManager: Biometric) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        val result by promptManager.promptResults.collectAsState(initial = null)
        val enrollLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartActivityForResult(),
            onResult = {
                println("Activity result: $it")
            }
        )
        LaunchedEffect(result) {
            if(result is BiometricResult.AuthenticationNotSet){
                if(Build.VERSION.SDK_INT >= 30) {
                    val enrollIntent = Intent(Settings.ACTION_BIOMETRIC_ENROLL).apply {
                        val extras = Bundle().apply {
                            putInt(
                                Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                                BIOMETRIC_STRONG or DEVICE_CREDENTIAL
                            )
                        }
                        putExtras(extras)
                    }
                    enrollLauncher.launch(enrollIntent)
                }
            }
        }
        Text(text = "Cadastro de Biometria Digital", style = MaterialTheme.typography.headlineMedium)
        Button(onClick = {
            promptManager.showBiometricprompt(
                titulo = "Autentique-se",
                descricao = "valide com sua digital"
            )
        }) {
            Text(text = "Capturar digital")
        }
        result?.let { result ->
            Text(text = when(result){
                is BiometricResult.AuthenticationError -> "Erro de autenticacao"
                BiometricResult.AuthenticationFailed -> "Autenticação falhou"
                BiometricResult.AuthenticationNotSet -> "Autenticação não cadastrada"
                BiometricResult.AuthenticationSucess -> "Sucesso: Captura digital bem-sucedida!"
                BiometricResult.FeatureUnavailable -> " Funcionalidade não disponível"
                BiometricResult.HardwareUnavailable -> "Hardware não disponível"
            }, style = MaterialTheme.typography.bodyMedium)
        }

        Button(onClick = { navController.popBackStack() }) {
            Text("Voltar")
        }
    }
}
