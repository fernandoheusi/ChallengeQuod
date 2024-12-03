package br.com.fiap.challengequod.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

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
